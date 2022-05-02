package com.example.service;

import com.example.model.InstitutionModel;

import java.util.List;

public interface InstitutionService {
    InstitutionModel create(InstitutionModel institutionModel);
    InstitutionModel getById(Long id);
    InstitutionModel update(InstitutionModel institutionModel);
    InstitutionModel deleteById(Long id);
    List<InstitutionModel> getAll();
}
