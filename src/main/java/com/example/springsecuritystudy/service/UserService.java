package com.example.springsecuritystudy.service;

import com.example.springsecuritystudy.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllAdmin();

    User findByUsername(String username);

    int updateUserInfo(User user);

    User saveUser(User user);

    int deleteByUsername(String username);
}
