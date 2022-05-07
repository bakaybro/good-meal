package com.example.controller;

import com.example.exceptions.ApiException;
import com.example.model.InstitutionImageModel;
import com.example.service.InstitutionImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institution-image")
public class InstitutionImageController {

    @Autowired
    private InstitutionImageService institutionImageService;

    @GetMapping
    public List<InstitutionImageModel> getAll() {
        return institutionImageService.getAll();
    }

    @PostMapping
    public ApiException create(@RequestBody InstitutionImageModel institutionImageModel) {
        return institutionImageService.create(institutionImageModel);
    }

    @GetMapping("/{id}")
    public InstitutionImageModel getById(@PathVariable Long id) {
        return institutionImageService.getById(id);
    }

    @PutMapping
    public InstitutionImageModel update(@RequestBody InstitutionImageModel institutionImageModel) {
        return institutionImageService.update(institutionImageModel);
    }

    @DeleteMapping("/{id}")
    public InstitutionImageModel deleteById(@PathVariable Long id) {
        return institutionImageService.deleteById(id);
    }
}
