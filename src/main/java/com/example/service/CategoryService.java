package com.example.service;

import com.example.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    CategoryModel create(CategoryModel categoryModel);
    CategoryModel getById(Long id);
    List<CategoryModel> getAll();
    CategoryModel update(Long id, CategoryModel categoryModel);
    CategoryModel deleteById(Long id);
}
