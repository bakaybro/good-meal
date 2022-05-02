package com.example.service;

import com.example.entity.User;
import com.example.model.FeatureModel;

import java.util.List;

public interface FeatureService {
    FeatureModel create(FeatureModel featureModel);
    FeatureModel getById(Long id);
    FeatureModel update(FeatureModel featureModel);
    FeatureModel deleteById(Long id);
    List<FeatureModel> getAll();
}
