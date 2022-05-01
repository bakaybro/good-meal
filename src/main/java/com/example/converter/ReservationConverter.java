package com.example.converter;

import com.example.entity.Reservation;
import com.example.entity.TableReservation;
import com.example.entity.User;
import com.example.model.ReservationModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Component
public class ReservationConverter extends BaseConverter<ReservationModel, Reservation> {

    public ReservationConverter() {
        super(ReservationConverter::convertToEntity, ReservationConverter::convertToModel);
    }

    private static ReservationModel convertToModel(Reservation entityToConvert){
        if (entityToConvert == null) return null;
        String bookingTimeFrom = String.valueOf(entityToConvert.getReservationTimeFrom());
        String bookingTimeTill = String.valueOf(entityToConvert.getReservationTimeTill());
        return ReservationModel.builder()
                .bookingTimeFrom(bookingTimeFrom)
                .bookingTimeTill(bookingTimeTill)
                .tableId(entityToConvert.getTableReservation().getId())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static Reservation convertToEntity(ReservationModel modelToConvert){
        if (modelToConvert == null) return null;

        Reservation reservationToReturn = new Reservation();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime reservationTimeFrom = LocalDateTime.parse(modelToConvert.getBookingTimeFrom(), dateTimeFormatter);
        LocalDateTime reservationTimeTill = LocalDateTime.parse(modelToConvert.getBookingTimeTill(), dateTimeFormatter);

        reservationToReturn.setReservationTimeFrom(reservationTimeFrom);
        reservationToReturn.setReservationTimeTill(reservationTimeTill);

        TableReservation establishmentTable = new TableReservation();
        establishmentTable.setId(modelToConvert.getTableId());

        reservationToReturn.setTableReservation(establishmentTable);

        User user = new User();
        user.setId(modelToConvert.getUserId());

        reservationToReturn.setUser(user);

        return reservationToReturn;
    }
}
