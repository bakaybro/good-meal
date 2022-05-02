package com.example.service;

import com.example.model.ReservationModel;

import java.util.List;

public interface ReservationService {
    ReservationModel create(ReservationModel reservationModel);
    ReservationModel getById(Long id);
    List<ReservationModel> gerAll();
    ReservationModel update(ReservationModel reservationModel);
    ReservationModel deleteById(Long id);
}
