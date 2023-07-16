package com.example.springsecuritystudy.service;

import com.example.springsecuritystudy.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllAdmin();

}
