package com.example.repository;

import com.example.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findFeatureByInstitutionId(Long id);
}
