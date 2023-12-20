package com.clever.controller;

import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 用户接口
 *
 * @Author xixi
 * @Date 2023-12-15 14:38
 **/
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     * @return Result<OnlineUser>
     */
    @PostMapping("/login")
    public Result<OnlineUser> login(@NotBlank(message = "账号不能为空") String account, @NotBlank(message = "密码不能为空") String password) {
        return new Result<>(userService.login(account, password), "登录成功");
    }


}
