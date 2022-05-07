package com.example.specification;

import com.example.entity.Review;
import com.example.model.ReviewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReviewSpecification implements Specification<Review> {

    private final ReviewModel reviewModel;

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (reviewModel.getReview() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("review")), "%" + reviewModel.getReview() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
