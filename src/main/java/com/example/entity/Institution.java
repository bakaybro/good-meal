package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "institutions")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Institution extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "started_work_from")
    private String startedWorkFrom;

    @Column(name = "end_of_work_in")
    private String endOfWorkIn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
