package com.example.service;

import com.example.entity.InstitutionImage;
import com.example.exceptions.ApiException;
import com.example.model.InstitutionImageModel;

import java.util.List;

public interface InstitutionImageService {
    ApiException create(InstitutionImageModel institutionImageModel);
    List<InstitutionImageModel> getAll();
    InstitutionImageModel getById(Long id);
    InstitutionImageModel update(InstitutionImageModel institutionImageModel);
    InstitutionImageModel deleteById(Long id);
}
