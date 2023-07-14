package com.example.springsecuritystudy.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @JoinColumn(name = "role_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private Role role;

    @JoinColumn(name = "permission_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private Permission permission;

}
