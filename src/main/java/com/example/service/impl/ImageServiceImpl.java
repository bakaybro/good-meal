package com.example.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.converter.ImageConverter;
import com.example.entity.Image;
import com.example.exceptions.ApiException;
import com.example.model.ImageModel;
import com.example.repository.ImageRepository;
import com.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageConverter imageConverter;


    @Override
    public List<ImageModel> getAll() {
        List<ImageModel> imageModels = new ArrayList<>();
        for (Image image:imageRepository.findAll()) {
            imageModels.add(imageConverter.convertFromEntity(image));
        }
        if (imageModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return imageModels;
    }

    private static final String CLOUDINARY_URL = "cloudinary://276123242311464:R5oT9kU36JbIJqurpzev_8MPMl8@dq0y7sp3u";

    @Override
    public String saveImageInCloudinary(MultipartFile multipartFile) {

        File file;

        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

            return ((String) uploadResult.get("url"));


        } catch (IOException e) {
            System.out.println("ImageService.createImage: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ImageModel saveImage(String url) {
        ImageModel image = new ImageModel();
        image.setUrl(url);
        Image savedImage = imageRepository.save(imageConverter.convertFromModel(image));
        image.setId(savedImage.getId());
        return image;
    }

    @Override
    public ImageModel saveImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) throw new ApiException("You didn't add the file", HttpStatus.BAD_REQUEST);
        String imageUrl = saveImageInCloudinary(multipartFile);
        return saveImage(imageUrl);
    }
}
