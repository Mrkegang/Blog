package com.kgstudy.controller;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.dto.UserInfoDto;
import com.kgstudy.domain.entity.User;
import com.kgstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/12 14:08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return userService.updateUserInfo(userInfoDto);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
