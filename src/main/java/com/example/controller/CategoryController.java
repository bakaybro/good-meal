package com.example.controller;

import com.example.model.CategoryModel;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    public List<CategoryModel> getAll() {
        return categoryService.getAll();
    }

    @PutMapping
    public CategoryModel update(@RequestBody CategoryModel categoryModel) {
        return categoryService.update(categoryModel);
    }

    @DeleteMapping("/{id}")
    public CategoryModel deleteById(@PathVariable Long id) {
        return categoryService.deleteById(id);
    }
}
