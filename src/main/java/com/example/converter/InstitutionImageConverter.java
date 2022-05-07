package com.example.converter;

import com.example.entity.Image;
import com.example.entity.Institution;
import com.example.entity.InstitutionImage;
import com.example.model.InstitutionImageModel;
import org.springframework.stereotype.Component;

@Component
public class InstitutionImageConverter extends BaseConverter<InstitutionImageModel, InstitutionImage>{

    public InstitutionImageConverter() {
        super(InstitutionImageConverter::convertToEntity, InstitutionImageConverter::convertToModel);
    }

    private static InstitutionImageModel convertToModel(InstitutionImage entityToConvert){
        if (entityToConvert == null) return null;
        return InstitutionImageModel.builder()
                .imageId(entityToConvert.getImage().getId())
                .institutionId(entityToConvert.getInstitution().getId())
                .build();
    }

    private static InstitutionImage convertToEntity(InstitutionImageModel modelToConvert){
        if (modelToConvert == null) return null;

        InstitutionImage institutionImageReturn = new InstitutionImage();

        Image image = new Image();
        image.setId(modelToConvert.getImageId());
        institutionImageReturn.setImage(image);
        Institution institution = new Institution();
        institution.setId(modelToConvert.getInstitutionId());
        institutionImageReturn.setInstitution(institution);

        return institutionImageReturn;
    }
}
