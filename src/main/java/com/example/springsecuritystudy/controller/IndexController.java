package com.example.springsecuritystudy.controller;

import com.example.springsecuritystudy.common.Result;
import com.example.springsecuritystudy.entity.User;
import com.example.springsecuritystudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    UserService userService;


    @PostMapping("/")
    public Result<String> index() {
        return Result.success("首页--index");
    }

    @GetMapping("/publish-board")
    public Result<String> publish_board() {
        // 获取数据
        List<User> adminList = userService.findAllAdmin();
        // 返回公告
        List<String> admins = adminList.stream()
                .map(user -> user.getNickname() + "(ADMIN)").toList();
        return Result.success("本站管理员有:" + admins + "，有事联系管理员");
    }

}
