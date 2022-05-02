package com.example.repository;

import com.example.entity.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
    List<TableReservation> findTableByInstitutionId(Long id);
}
