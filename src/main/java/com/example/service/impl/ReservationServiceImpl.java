package com.example.service.impl;

import com.example.converter.ReservationConverter;
import com.example.entity.Reservation;
import com.example.exceptions.ApiException;
import com.example.model.InstitutionModel;
import com.example.model.ReservationModel;
import com.example.repository.ReservationRepository;
import com.example.service.InstitutionService;
import com.example.service.ReservationService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationConverter reservationConverter;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Override
    public ReservationModel create(ReservationModel reservationModel) {
        if (reservationModel.getBookingTimeFrom() == null) throw new ApiException("Enter the reservation time from", HttpStatus.BAD_REQUEST);
        if (reservationModel.getBookingTimeTill() == null) throw new ApiException("Enter the reservation time before", HttpStatus.BAD_REQUEST);
        if (reservationModel.getTableId() == null) throw new ApiException("Enter a table", HttpStatus.BAD_REQUEST);
        List<Reservation> reservations = reservationRepository.findby(reservationModel.getTableId());
        for (Reservation reservation : reservations) {
            Reservation reservationFromClient = reservationConverter.convertFromModel(reservationModel);
            if (reservation.getReservationTimeFrom().isEqual(reservationFromClient.getReservationTimeFrom())
                    || reservation.getReservationTimeTill().isEqual(reservationFromClient.getReservationTimeTill()))
                    throw new ApiException("There is already a reservation for this time", HttpStatus.BAD_REQUEST);
        }
        reservationModel.setUserId(userService.getCurrentUser().getId());
        reservationRepository.save(reservationConverter.convertFromModel(reservationModel));
        return reservationModel;
    }

    @Override
    public ReservationModel getById(Long id) {
        return reservationConverter.convertFromEntity(reservationRepository.findById(id)
                .orElseThrow( () -> new ApiException("Didn't find a reservation under id: " + id, HttpStatus.BAD_REQUEST)));
    }

    @Override
    public List<ReservationModel> getAll() {
        List<ReservationModel> reservationModels = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll()) {
            reservationModels.add(reservationConverter.convertFromEntity(reservation));
        }
        if (reservationModels.isEmpty()) throw new ApiException("List is empty", HttpStatus.BAD_REQUEST);
        return reservationModels;
    }

    @Override
    public ReservationModel update(ReservationModel reservationModel) {
        ReservationModel reservationModelForUpdate = getById(reservationConverter.convertFromModel(reservationModel).getId());

        if (reservationModel.getBookingTimeFrom() != null) reservationModelForUpdate.setBookingTimeFrom(reservationModel.getBookingTimeFrom());
        if (reservationModel.getBookingTimeTill() != null) reservationModelForUpdate.setBookingTimeTill(reservationModel.getBookingTimeTill());
        if (reservationModel.getTableId() != null) reservationModelForUpdate.setTableId(reservationModel.getTableId());
        if (reservationModel.getUserId() != null) reservationModelForUpdate.setUserId(reservationModel.getUserId());

        reservationRepository.save(reservationConverter.convertFromModel(reservationModelForUpdate));
        return reservationModelForUpdate;
    }

    @Override
    public ReservationModel deleteById(Long id) {
        ReservationModel reservationModelForDelete = reservationConverter.convertFromEntity(reservationRepository.findById(id)
                .orElseThrow( () -> new ApiException("Did not find the reservation under the id to delete. ID: " + id, HttpStatus.BAD_REQUEST)));

        reservationRepository.deleteById(id);
        return reservationModelForDelete;
    }
}
