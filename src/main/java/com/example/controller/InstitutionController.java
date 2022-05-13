package com.example.controller;

import com.example.exceptions.ApiException;
import com.example.model.InstitutionModel;
import com.example.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @PostMapping
    public InstitutionModel create(@RequestBody InstitutionModel institutionModel) {
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

    @GetMapping
    public Page<InstitutionModel> getPage(Pageable pageable) {
        return institutionService.getPage(pageable);
    }

    @PostMapping("/pageable")
    public Page<InstitutionModel> getSortedPage(@RequestBody InstitutionModel institutionModel, Pageable pageable) {
        return institutionService.getSortedPage(institutionModel, pageable);
    }

    @GetMapping("/category/{id}")
    public Page<InstitutionModel> getPageSortedByCategory(@PathVariable Long id, Pageable pageable) {
        return institutionService.getPageSortedByCategory(id, pageable);
    }

    @PostMapping("/images")
    public ApiException saveImages(@RequestParam List<MultipartFile> images,
                                   @RequestParam Long institutionId) {
        return institutionService.saveImages(images, institutionId);
    }
}
