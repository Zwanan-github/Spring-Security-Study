package com.example.springsecuritystudy.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    // 自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long uid;

    //用户登录凭证
    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "pass_word")
    private String password;
}
