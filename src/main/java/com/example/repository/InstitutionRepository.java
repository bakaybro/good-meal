package com.example.repository;

import com.example.entity.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends JpaRepository<Institution, Long>, JpaSpecificationExecutor<Institution> {
    Optional<Institution> findInstitutionId(Long id, Pageable pageable);
    Page<Institution> findAllByCategoryId(Long id);
    List<Institution> findInstitutionId(String name);
}
