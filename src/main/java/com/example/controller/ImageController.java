package com.example.controller;

import com.example.model.ImageModel;
import com.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ImageModel saveImage(@RequestParam(name = "file") MultipartFile multipartFile) {
        return imageService.saveImage(multipartFile);
    }

    @GetMapping
    public List<ImageModel> getAll() {
        return imageService.getAll();
    }
}
