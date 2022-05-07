package com.example.controller;

import com.example.model.InstitutionModel;
import com.example.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @PostMapping
    public InstitutionModel create(InstitutionModel institutionModel) {
        return institutionService.create(institutionModel);
    }

    @GetMapping("/{id}")
    public InstitutionModel getById(@PathVariable Long id) {
        return institutionService.getById(id);
    }

    @PutMapping
    public InstitutionModel update(@RequestBody InstitutionModel institutionModel) {
        return institutionService.update(institutionModel);
    }

    @DeleteMapping("/{id}")
    public InstitutionModel deleteById(@PathVariable Long id) {
        return institutionService.deleteById(id);
    }

    @GetMapping("/get-all")
    public List<InstitutionModel> getAll() {
        return institutionService.getAll();
    }
}
