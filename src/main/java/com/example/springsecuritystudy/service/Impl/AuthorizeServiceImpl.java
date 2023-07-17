package com.example.springsecuritystudy.service.Impl;

import com.example.springsecuritystudy.entity.Permission;
import com.example.springsecuritystudy.entity.Role;
import com.example.springsecuritystudy.repository.UserRepository;
import com.example.springsecuritystudy.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    UserRepository userRepository;

    //多方默认懒加载, 在Service层加上@Transactional保持session
    @Transactional
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
        String[] roles = user.getRoles().stream()
                .map(Role::getRName)
                .toArray(String[]::new);
        String[] permissions = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getPName)
                .toArray(String[]::new);

        System.out.println("用户{" + user + "}登录成功, 角色:{" + Arrays.toString(roles) + "}" + "权限:{" + Arrays.toString(permissions) + "}");
        return User
                .withUsername(username)
                .password(user.getPassword())
                .roles(permissions)
                .build();
    }
}
