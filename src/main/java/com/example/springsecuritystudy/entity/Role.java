package com.example.springsecuritystudy.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer rid;

    @Column(name = "role_name", unique = true)
    private String rName;
}
