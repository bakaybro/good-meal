package com.example.service.impl;

import com.example.converter.InstitutionImageConverter;
import com.example.entity.InstitutionImage;
import com.example.exceptions.ApiException;
import com.example.model.CategoryModel;
import com.example.model.InstitutionImageModel;
import com.example.repository.InstitutionImageRepository;
import com.example.service.InstitutionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionImageServiceImpl implements InstitutionImageService {

    @Autowired
    private InstitutionImageRepository institutionImageRepository;

    @Autowired
    private InstitutionImageConverter institutionImageConverter;

    @Override
    public ApiException create(InstitutionImageModel institutionImageModel) {
        if (institutionImageModel.getImageId() == null)  throw new ApiException("Enter a images id", HttpStatus.BAD_REQUEST);
        if (institutionImageModel.getInstitutionId() == null) throw new ApiException("Enter a institutions id", HttpStatus.BAD_REQUEST);
        institutionImageRepository.save(institutionImageConverter.convertFromModel(institutionImageModel));
        return new ApiException("Everything has been successfully saved", HttpStatus.OK);
    }

    @Override
    public List<InstitutionImageModel> getAll() {
        List<InstitutionImageModel> institutionImageModels = new ArrayList<>();
        for (InstitutionImage institutionImageModel : institutionImageRepository.findAll()) {
            institutionImageModels.add(institutionImageConverter.convertFromEntity(institutionImageModel));
        }
        if (institutionImageModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return institutionImageModels;
    }

    @Override
    public InstitutionImageModel getById(Long id) {
        return institutionImageConverter.convertFromEntity(institutionImageRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a image under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public InstitutionImageModel update(InstitutionImageModel institutionImageModel) {
        InstitutionImageModel institutionImageModelForUpdate = getById(institutionImageConverter.convertFromModel(institutionImageModel).getId());

        if (institutionImageModel.getInstitutionId() != null) institutionImageModelForUpdate.setInstitutionId(institutionImageModel.getInstitutionId());
        if (institutionImageModel.getImageId() != null) institutionImageModelForUpdate.setImageId(institutionImageModel.getImageId());

        institutionImageRepository.save(institutionImageConverter.convertFromModel(institutionImageModelForUpdate));
        return institutionImageModelForUpdate;
    }

    @Override
    public InstitutionImageModel deleteById(Long id) {
        InstitutionImageModel institutionImageServiceImpl = institutionImageConverter.convertFromEntity(institutionImageRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the image under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        institutionImageRepository.deleteById(id);
        return institutionImageServiceImpl;
    }
}
