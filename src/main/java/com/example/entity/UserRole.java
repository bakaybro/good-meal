package com.example.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_roles")
@Entity
@Getter
@Setter
public class UserRole extends BaseEntity {

    @Column(name = "role_name")
    private String roleName;
}
