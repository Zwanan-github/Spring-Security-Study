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

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public int updateUserInfo(User user) {
        return userRepository.updateUserInfo(user.getNickname(), user.getPassword(), user.getUsername());
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public int deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }


}
