package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private Long isActive;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
