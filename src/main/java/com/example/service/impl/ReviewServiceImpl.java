package com.example.service.impl;

import com.example.converter.ReviewConverter;
import com.example.entity.Reservation;
import com.example.entity.Review;
import com.example.exceptions.ApiException;
import com.example.model.ReservationModel;
import com.example.model.ReviewModel;
import com.example.repository.ReviewRepository;
import com.example.service.ReviewService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Override
    public ReviewModel create(ReviewModel reviewModel) {
        if (reviewModel.getReview().isEmpty()) throw new ApiException("Enter a review", HttpStatus.BAD_REQUEST);
        if (reviewModel.getInstitutionId() == null) throw new ApiException("Didn't find a institution under id: " + reviewModel.getInstitutionId(), HttpStatus.BAD_REQUEST);
        reviewModel.setUserId(userService.getCurrentUser().getId());
        reviewRepository.save(reviewConverter.convertFromModel(reviewModel));
        return reviewModel;
    }

    @Override
    public ReviewModel getById(Long id) {
        return reviewConverter.convertFromEntity(reviewRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a review under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<ReviewModel> getAll() {
        List<ReviewModel> reviewModels = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            reviewModels.add(reviewConverter.convertFromEntity(review));
        }
        if (reviewModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return reviewModels;
    }

    @Override
    public ReviewModel update(ReviewModel reviewModel) {
        ReviewModel reviewModelForUpdate = getById(reviewConverter.convertFromModel(reviewModel).getId());

        if (reviewModel.getReview() != null) reviewModelForUpdate.setReview(reviewModel.getReview());
        if (reviewModel.getInstitutionId() != null) reviewModelForUpdate.setInstitutionId(reviewModel.getInstitutionId());

        reviewRepository.save(reviewConverter.convertFromModel(reviewModelForUpdate));
        return reviewModelForUpdate;
    }

    @Override
    public ReviewModel deleteById(Long id) {
        ReviewModel reviewModelForDelete = reviewConverter.convertFromEntity(reviewRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the review under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        reviewRepository.deleteById(id);
        return reviewModelForDelete;
    }
}
