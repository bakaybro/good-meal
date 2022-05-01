package com.example.converter;

import com.example.entity.Feature;
import com.example.entity.Institution;
import com.example.model.FeatureModel;
import org.springframework.stereotype.Component;

@Component
public class FeatureConverter extends BaseConverter<FeatureModel, Feature> {

    public FeatureConverter() {
        super(FeatureConverter::convertToEntity, FeatureConverter::convertToModel);
    }

    private static FeatureModel convertToModel(Feature entityToConvert){
        if (entityToConvert == null) return null;
        return FeatureModel.builder()
                .feature(entityToConvert.getFeature())
                .institutionId(entityToConvert.getInstitution().getId())
                .build();
    }

    private static Feature convertToEntity(FeatureModel modelToConvert){
        if (modelToConvert == null) return null;

        Feature featureToReturn = new Feature();

        featureToReturn.setFeature(modelToConvert.getFeature());
        Institution institution = new Institution();
        institution.setId(modelToConvert.getInstitutionId());
        featureToReturn.setInstitution(institution);

        return featureToReturn;
    }
}
