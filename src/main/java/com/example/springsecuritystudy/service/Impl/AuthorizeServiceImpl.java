package com.example.springsecuritystudy.service.Impl;

import com.example.springsecuritystudy.repository.UserRepository;
import com.example.springsecuritystudy.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空!");
        }
        // 获取当前用户的
        var user = userRepository.findByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException("账号或密码错误");
        }
        System.out.println(user);
        return User
                .withUsername(username)
                .password(encoder.encode(user.getPassword()))
                .roles()
                .build();
    }
}
