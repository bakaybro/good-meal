package com.example.converter;

import com.example.entity.Category;
import com.example.entity.Institution;
import com.example.entity.User;
import com.example.model.InstitutionModel;
import org.springframework.stereotype.Component;

@Component
public class InstitutionConverter extends BaseConverter<InstitutionModel, Institution> {

    public InstitutionConverter() {
        super(InstitutionConverter::convertToEntity, InstitutionConverter::convertToModel);
    }

    private static InstitutionModel convertToModel(Institution entityToConvert){
        if (entityToConvert == null) return null;
        return InstitutionModel.builder()
                .name(entityToConvert.getName())
                .address(entityToConvert.getAddress())
                .startedWorkFrom(entityToConvert.getStartedWorkFrom())
                .endOfWorkIn(entityToConvert.getEndOfWorkIn())
                .userId(entityToConvert.getUser().getId())
                .categoryId(entityToConvert.getCategory().getId())
                .build();
    }

    private static Institution convertToEntity(InstitutionModel modelToConvert){
        if (modelToConvert == null) return null;

        Institution institutionToReturn = new Institution();

        institutionToReturn.setName(modelToConvert.getName());
        institutionToReturn.setAddress(modelToConvert.getAddress());
        institutionToReturn.setStartedWorkFrom(modelToConvert.getStartedWorkFrom());
        institutionToReturn.setEndOfWorkIn(modelToConvert.getEndOfWorkIn());
        User user = new User();
        user.setId(modelToConvert.getUserId());
        institutionToReturn.setUser(user);
        Category category = new Category();
        category.setId(modelToConvert.getCategoryId());
        institutionToReturn.setCategory(category);

        return institutionToReturn;
    }
}
