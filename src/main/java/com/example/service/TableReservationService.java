package com.example.service;

import com.example.model.TableReservationModel;

import java.util.List;

public interface TableReservationService {
    TableReservationModel create(TableReservationModel tableReservationModel);
    TableReservationModel getById(Long id);
    List<TableReservationModel> getAll();
    TableReservationModel update(TableReservationModel tableReservationModel);
    TableReservationModel deleteById(Long id);
}
