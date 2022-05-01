package com.example.converter;

import com.example.entity.Institution;
import com.example.entity.Review;
import com.example.entity.User;
import com.example.model.ReviewModel;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter extends BaseConverter<ReviewModel, Review> {

    public ReviewConverter() {
        super(ReviewConverter::convertToEntity, ReviewConverter::convertToModel);
    }

    private static ReviewModel convertToModel(Review entityToConvert){
        if (entityToConvert == null) return null;
        return ReviewModel.builder()
                .review(entityToConvert.getReview())
                .userId(entityToConvert.getUser().getId())
                .institutionId(entityToConvert.getInstitution().getId())
                .build();
    }

    private static Review convertToEntity(ReviewModel modelToConvert){
        if (modelToConvert == null) return null;

        Review reviewToReturn = new Review();

        reviewToReturn.setReview(modelToConvert.getReview());

        User user = new User();
        user.setId(modelToConvert.getUserId());
        reviewToReturn.setUser(user);

        Institution institution = new Institution();
        institution.setId(modelToConvert.getInstitutionId());
        reviewToReturn.setInstitution(institution);

        return reviewToReturn;
    }
}
