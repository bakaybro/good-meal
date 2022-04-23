package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "features")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feature extends BaseEntity {

    @Column(name = "feature")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}
