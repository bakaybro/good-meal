package com.example.converter;

import com.example.entity.Category;
import com.example.model.CategoryModel;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryConverter extends BaseConverter<CategoryModel, Category> {

    public CategoryConverter() {
        super(CategoryConverter::convertToEntity, CategoryConverter::convertToModel);
    }

    private static CategoryModel convertToModel(Category entityToConvert){
        if (entityToConvert == null) return null;
        return CategoryModel.builder()
                .name(entityToConvert.getName())
                .build();
    }

    private static Category convertToEntity(CategoryModel modelToConvert){
        if (modelToConvert == null) return null;

        Category categoryToReturn = new Category();

        categoryToReturn.setName(modelToConvert.getName());

        return categoryToReturn;
    }
}
