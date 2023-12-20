package com.clever.controller;

import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;
import com.clever.bean.system.Role;
import com.clever.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xixi
 * @Date 2023-12-19 14:57
 **/
@RestController

@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/save")
    public Result<String> addRole(@Validated Role role, OnlineUser onlineUser) {
        roleService.save(role, onlineUser);
        return Result.ofSuccess("添加成功");
    }
}
