package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "reviews")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
