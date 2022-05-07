package com.example.service.impl;

import com.example.converter.FeatureConverter;
import com.example.entity.Feature;
import com.example.exceptions.ApiException;
import com.example.model.FeatureModel;
import com.example.repository.FeatureRepository;
import com.example.repository.InstitutionRepository;
import com.example.service.FeatureService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureConverter featureConverter;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserService userService;

    @Override
    public FeatureModel create(FeatureModel featureModel) {
        if (featureModel.getFeature().isEmpty()) throw new ApiException("Enter a feature", HttpStatus.BAD_REQUEST);
        if (featureModel.getInstitutionId() == null) throw new ApiException("Enter the ID of the institution", HttpStatus.BAD_REQUEST);
        if (!Objects.requireNonNull(institutionRepository.findById(featureModel.getInstitutionId())
                .orElse(null)).getUser().getId().equals(userService.getCurrentUser().getId()))
            throw new ApiException("You cannot make changes to this institution", HttpStatus.BAD_REQUEST);

        List<Feature> features = featureRepository.findFeatureByInstitutionId(featureModel.getInstitutionId());
        for (Feature feature : features) {
            if (feature.getFeature().equals(featureModel.getFeature())) throw new ApiException("This feature already exists", HttpStatus.BAD_REQUEST);
        }
        featureRepository.save(featureConverter.convertFromModel(featureModel));
        return featureModel;
    }

    @Override
    public FeatureModel getById(Long id) {
        return null;
    }

    @Override
    public FeatureModel update(FeatureModel featureModel) {
        return null;
    }

    @Override
    public FeatureModel deleteById(Long id) {
        return null;
    }

    @Override
    public List<FeatureModel> getAll() {
        return null;
    }
}
