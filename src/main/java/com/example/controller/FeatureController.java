package com.example.controller;

import com.example.model.FeatureModel;
import com.example.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping
    public FeatureModel create(FeatureModel featureModel) {
        return featureService.create(featureModel);
    }
}
