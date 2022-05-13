package com.example.service.impl;

import com.example.converter.CategoryConverter;
import com.example.entity.Category;
import com.example.entity.User;
import com.example.exceptions.ApiException;
import com.example.model.CategoryModel;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryModel create(CategoryModel categoryModel) {
        if (categoryModel.getName().isEmpty()) throw new ApiException("Enter the name category", HttpStatus.BAD_REQUEST);
        categoryRepository.save(categoryConverter.convertFromModel(categoryModel));
        return categoryModel;
    }

    @Override
    public CategoryModel getById(Long id) {
        return categoryConverter.convertFromEntity(categoryRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a CATEGORY under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<CategoryModel> getAll() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categoryModels.add(categoryConverter.convertFromEntity(category));
        }
        if (categoryModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return categoryModels;
    }

    @Override
    public CategoryModel update(Long id, CategoryModel categoryModel) {
        Category categoryForUpdate = categoryConverter.convertFromModel(categoryModel);

        categoryForUpdate.setId(id);
        categoryRepository.save(categoryForUpdate);
        return categoryConverter.convertFromEntity(categoryForUpdate);
    }

    @Override
    public CategoryModel deleteById(Long id) {
        CategoryModel categoryModelForDelete = categoryConverter.convertFromEntity(categoryRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the category under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        categoryRepository.deleteById(id);
        return categoryModelForDelete;
    }
}
