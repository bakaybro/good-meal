package com.example.controller;

import com.example.model.CategoryModel;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public CategoryModel create(@RequestBody CategoryModel categoryModel) {
        return categoryService.create(categoryModel);
    }

    @GetMapping("/{id}")
    public CategoryModel getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }
}
