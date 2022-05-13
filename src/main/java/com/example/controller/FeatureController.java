package com.example.controller;

import com.example.model.FeatureModel;
import com.example.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping
    public FeatureModel create(FeatureModel featureModel) {
        return featureService.create(featureModel);
    }

    @GetMapping("/{id}")
    public FeatureModel getById(@PathVariable Long id) {
        return featureService.getById(id);
    }

    @GetMapping
    public Page<FeatureModel> getPage(Pageable pageable) {
        return featureService.getPage(pageable);
    }

    @PutMapping
    FeatureModel update(@RequestBody FeatureModel featureModel) {
        return featureService.update(featureModel);
    }

    @DeleteMapping("/{id}")
    public FeatureModel deleteById(@PathVariable Long id) {
        return featureService.deleteById(id);
    }

}
