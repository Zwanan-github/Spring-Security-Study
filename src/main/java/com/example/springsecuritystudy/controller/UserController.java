package com.example.springsecuritystudy.controller;

import com.example.springsecuritystudy.common.Result;
import com.example.springsecuritystudy.entity.User;
import com.example.springsecuritystudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{user_name}")
    public Result<User> getUserByUsername(@PathVariable("user_name") String username) {
        // 获取用户信息
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.failure(400,null);
        }
    }

    @PutMapping("/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Result<String> updateUserInfoByUsername(@RequestBody User user) {
        // 修改
        if (userService.updateUserInfo(user) == 1) {
            return Result.success("修改信息成功");
        }
        return Result.failure(400, "修改成功失败");
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Result<String> insertUser(@RequestBody User user) {
        // 添加默认用户
        User respUser = userService.saveUser(user);
        if (respUser != null) {
            return Result.success("添加信息成功:" + respUser + (respUser.getRoles() == null ? "" : respUser.getRoles()));
        }
        return Result.failure(400, "添加信息失败");
    }

    @DeleteMapping("/{user_name}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Result<String> deleteByUsername(@PathVariable("user_name") String username) {
        if (userService.deleteByUsername(username) == 1) {
            return Result.success("删除用户成功");
        } else {
            return Result.failure(400,"删除信息失败");
        }
    }
}
