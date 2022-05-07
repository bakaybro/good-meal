package com.example.service;

import com.example.model.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ImageModel> getAll();
    String saveImageInCloudinary(MultipartFile multipartFile);
    ImageModel saveImage(String url);
    ImageModel saveImage(MultipartFile multipartFile);
}
