package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationModel {
    private String bookingTimeFrom;
    private String bookingTimeTill;
    private Long userId;
    private Long tableId;
}
