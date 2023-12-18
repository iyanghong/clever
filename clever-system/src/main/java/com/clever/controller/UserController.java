package com.clever.controller;

import com.clever.bean.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户接口
 *
 * @Author xixi
 * @Date 2023-12-15 14:38
 **/
@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
//    private UserService userService;

    @GetMapping("/test")
    public Result<String> test() {
        return Result.ofSuccess("test");
    }
}
