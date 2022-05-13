package com.example.service.impl;

import com.example.converter.InstitutionConverter;
import com.example.entity.Category;
import com.example.entity.Institution;
import com.example.exceptions.ApiException;
import com.example.model.CategoryModel;
import com.example.model.ImageModel;
import com.example.model.InstitutionImageModel;
import com.example.model.InstitutionModel;
import com.example.repository.InstitutionRepository;
import com.example.service.ImageService;
import com.example.service.InstitutionImageService;
import com.example.service.InstitutionService;
import com.example.service.UserService;
import com.example.specification.InstitutionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private InstitutionConverter institutionConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private InstitutionImageService institutionImageService;

    @Override
    public InstitutionModel create(InstitutionModel institutionModel) {
        if (institutionModel.getName() == null) throw new ApiException("Enter the name institution", HttpStatus.BAD_REQUEST);
        if (institutionModel.getAddress() == null) throw new ApiException("Enter the address", HttpStatus.BAD_REQUEST);
        if (institutionModel.getStartedWorkFrom() == null) throw new ApiException("Enter the working time from", HttpStatus.BAD_REQUEST);
        if (institutionModel.getEndOfWorkIn() == null) throw new ApiException("Enter the working time before", HttpStatus.BAD_REQUEST);
        if (institutionModel.getCategoryId() == null) throw new ApiException("Enter the category", HttpStatus.BAD_REQUEST);
        if (institutionModel.getUserId() == null) throw new ApiException("Enter the users id", HttpStatus.BAD_REQUEST);

        List<Institution> institutions = institutionRepository.findInstitutionByName(institutionModel.getName());
        for (Institution institution : institutions) {
            if (institution.getAddress().equals(institutionModel.getAddress())) throw new ApiException("At this address already exists", HttpStatus.BAD_REQUEST);
        }
        institutionModel.setUserId(userService.getCurrentUser().getId());
        institutionRepository.save(institutionConverter.convertFromModel(institutionModel));
        return institutionModel;
    }

    @Override
    public InstitutionModel getById(Long id) {
        return institutionConverter.convertFromEntity(institutionRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a INSTITUTION under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public InstitutionModel update(InstitutionModel institutionModel) {
        InstitutionModel institutionModelUpdate = getById(institutionConverter.convertFromModel(institutionModel).getId());

        if (institutionModel.getName() != null) institutionModelUpdate.setName(institutionModel.getName());
        if (institutionModel.getAddress() != null) institutionModelUpdate.setAddress(institutionModel.getAddress());
        if (institutionModel.getStartedWorkFrom() != null) institutionModelUpdate.setStartedWorkFrom(institutionModel.getStartedWorkFrom());
        if (institutionModel.getEndOfWorkIn() != null) institutionModelUpdate.setEndOfWorkIn(institutionModel.getEndOfWorkIn());
        if (institutionModel.getUserId() != null) institutionModelUpdate.setUserId(institutionModel.getUserId());
        if (institutionModel.getCategoryId() != null) institutionModelUpdate.setCategoryId(institutionModel.getCategoryId());
        institutionRepository.save(institutionConverter.convertFromModel(institutionModelUpdate));
        return institutionModelUpdate;
    }

    @Override
    public InstitutionModel deleteById(Long id) {
        InstitutionModel institutionModelForDelete = institutionConverter.convertFromEntity(institutionRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the institution under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        institutionRepository.deleteById(id);
        return institutionModelForDelete;
    }

    @Override
    public List<InstitutionModel> getAll() {
        List<InstitutionModel> institutionModels = new ArrayList<>();
        for (Institution institution : institutionRepository.findAll()) {
            institutionModels.add(institutionConverter.convertFromEntity(institution));
        }
        if (institutionModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return institutionModels;
    }

    @Override
    public Page<InstitutionModel> getPage(Pageable pageable) {
        Page<Institution> institutionPage = institutionRepository.findAll(pageable);
        if (institutionPage.getContent().isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return institutionPage.map(institutionConverter::convertFromEntity);
    }

    @Override
    public Page<InstitutionModel> getPageSortedByCategory(Long id, Pageable pageable) {
        Page<Institution> institutionPage = institutionRepository.findAllByCategoryId(id, pageable);
        if (institutionPage.getContent().isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return institutionPage.map(institutionConverter::convertFromEntity);
    }

    @Override
    public Page<InstitutionModel> getSortedPage(InstitutionModel institutionModel, Pageable pageable) {
        InstitutionSpecification institutionSpecification = new InstitutionSpecification(institutionModel);
        Page<Institution> institutionPage = institutionRepository.findAll(institutionSpecification, pageable);
        if (institutionPage.getContent().isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return institutionPage.map(institutionConverter::convertFromEntity);
    }

    @Override
    public ApiException saveImages(List<MultipartFile> images, Long institutionId) {
        if (institutionId == null) throw new ApiException("Enter a institutions id", HttpStatus.BAD_REQUEST);
        if (images.isEmpty()) throw new ApiException("There are no images", HttpStatus.BAD_REQUEST);

        for (MultipartFile image : images) {
            ImageModel imageModel = imageService.saveImage(image);
            InstitutionImageModel institutionImageModel = new InstitutionImageModel();
            institutionImageModel.setImageId(imageModel.getId());
            institutionImageModel.setInstitutionId(institutionId);

            institutionImageService.create(institutionImageModel);
        }
        return new ApiException("Everything was saved successfully! :)", HttpStatus.BAD_REQUEST);
    }
}
