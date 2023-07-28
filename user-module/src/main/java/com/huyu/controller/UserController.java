package com.huyu.controller;

import com.huyu.service.UserService;
import com.huyu.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user-info/{userId}")
    public R getUserInfo(@PathVariable("userId") Integer userId){

        return userService.getUserInfo(userId);

    }

    @GetMapping("/user-info-username/{username}")
    public R getUserInfoByUsername(@PathVariable("username") String username){

        return userService.getUserInfoByUsername(username);

    }
}
