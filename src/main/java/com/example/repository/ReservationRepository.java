package com.example.repository;

import com.example.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findReservationByTableId(Long id);
}
