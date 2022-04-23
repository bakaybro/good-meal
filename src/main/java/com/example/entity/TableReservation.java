package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "table_reservations")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableReservation extends BaseEntity {

    @Column(name = "table_number")
    private Long tableNumber;

    @Column(name = "place")
    private Integer place;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}
