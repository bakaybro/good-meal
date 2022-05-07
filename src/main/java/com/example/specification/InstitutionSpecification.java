package com.example.specification;

import com.example.entity.Institution;
import com.example.model.InstitutionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InstitutionSpecification implements Specification<Institution> {

    private final InstitutionModel institutionModel;
    @Override
    public Predicate toPredicate(Root<Institution> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (institutionModel.getName() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + institutionModel.getName() + "%"));
        }

        if (institutionModel.getAddress() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + institutionModel.getAddress() + "%"));
        }

        if (institutionModel.getStartedWorkFrom() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("started_work_from")), "%" + institutionModel.getStartedWorkFrom() + "%"));
        }

        if (institutionModel.getEndOfWorkIn() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("end_of_work_in")), "%" + institutionModel.getEndOfWorkIn() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
