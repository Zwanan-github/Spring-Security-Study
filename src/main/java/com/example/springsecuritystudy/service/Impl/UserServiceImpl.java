package com.example.springsecuritystudy.service.Impl;

import com.example.springsecuritystudy.entity.User;
import com.example.springsecuritystudy.repository.UserRepository;
import com.example.springsecuritystudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllAdmin() {
        return userRepository.findAllAdmin();
    }

}
