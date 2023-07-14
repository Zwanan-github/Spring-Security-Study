package com.example.springsecuritystudy.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer pid;

    @Column(name = "permission_name", unique = true)
    private String pName;
}
