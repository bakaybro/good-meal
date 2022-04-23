package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "reservations")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    @Column(name = "reservation_time_from")
    private LocalDateTime reservationTimeFrom;

    @Column(name = "reservation_time_till")
    private LocalDateTime reservationTimeTill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_reservation_id")
    private TableReservation tableReservation;
}
