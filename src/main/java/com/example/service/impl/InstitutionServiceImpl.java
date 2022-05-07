package com.example.service.impl;

import com.example.converter.InstitutionConverter;
import com.example.entity.Category;
import com.example.entity.Institution;
import com.example.exceptions.ApiException;
import com.example.model.CategoryModel;
import com.example.model.InstitutionModel;
import com.example.repository.InstitutionRepository;
import com.example.service.InstitutionService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    @Override
    public InstitutionModel create(InstitutionModel institutionModel) {
        if (institutionModel.getName().isEmpty()) throw new ApiException("Enter the name institution", HttpStatus.BAD_REQUEST);
        if (institutionModel.getAddress().isEmpty()) throw new ApiException("Enter the address", HttpStatus.BAD_REQUEST);
        if (institutionModel.getStartedWorkFrom() == null) throw new ApiException("Enter the working time from", HttpStatus.BAD_REQUEST);
        if (institutionModel.getEndOfWorkIn() == null) throw new ApiException("Enter the working time before", HttpStatus.BAD_REQUEST);
        if (institutionModel.getCategoryId() == null) throw new ApiException("Enter the category", HttpStatus.BAD_REQUEST);

//        List<Institution> institutions = institutionRepository.findBy(institutionModel.getName());
//        for (Institution institution : institutions) {
//            if (institution.getAddress().equals(institutionModel.getAddress())) throw new ApiException("At this address already exists", HttpStatus.BAD_REQUEST);
//        }
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
}
