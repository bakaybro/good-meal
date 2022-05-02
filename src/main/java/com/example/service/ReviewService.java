package com.example.service;

import com.example.model.ReviewModel;

import java.util.List;

public interface ReviewService {
    ReviewModel create(ReviewModel reviewModel);
    ReviewModel getById(Long id);
    List<ReviewModel> getAll();
    ReviewModel update(ReviewModel reviewModel);
    ReviewModel deleteById(Long id);
}
