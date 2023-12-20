package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserRole;
import com.clever.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户-角色接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/UserRole")
@AuthGroup(name = "用户-角色模块", description = "用户-角色模块权限组")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;


    /**
     * 分页查询用户-角色列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户
     * @param roleId     角色
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.userRole.page", name = "用户-角色分页", description = "用户-角色分页接口")
    public Result<Page<UserRole>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, String roleId) {
        return new Result<>(userRoleService.selectPage(pageNumber, pageSize, userId, roleId), "分页数据查询成功");
    }

    /**
     * 根据用户角色中间表获取用户-角色信息
     *
     * @param id 用户角色中间表
     * @return 用户-角色信息
     */
    @GetMapping("/get/{id}")
    public Result<UserRole> selectById(@PathVariable("id") String id) {
        return new Result<>(userRoleService.selectById(id), "查询成功");
    }

    /**
     * 根据用户获取用户-角色列表
     *
     * @param userId 用户
     * @return 用户-角色列表
     */
    @GetMapping("/getByUserId/{userId}")
    @Auth(value = "clever-system.userRole.getByUserId", name = "根据user_id获取用户-角色列表", description = "用户-角色列表接口")
    public Result<List<UserRole>> selectByUserId(@PathVariable("userId") String userId) {
        return new Result<>(userRoleService.selectListByUserId(userId), "查询成功");
    }

    /**
     * 根据角色获取用户-角色列表
     *
     * @param roleId 角色
     * @return 用户-角色列表
     */
    @GetMapping("/getByRoleId/{roleId}")
    @Auth(value = "clever-system.userRole.getByRoleId", name = "根据role_id获取用户-角色列表", description = "用户-角色列表接口")
    public Result<List<UserRole>> selectByRoleId(@PathVariable("roleId") String roleId) {
        return new Result<>(userRoleService.selectListByRoleId(roleId), "查询成功");
    }

    /**
     * 保存用户-角色信息
     *
     * @param userRole 用户-角色实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userRole.save", name = "保存用户-角色", description = "保存用户-角色信息接口")
    public Result<String> save(UserRole userRole) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userRoleService.save(userRole, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据用户角色中间表获取用户-角色列表
     *
     * @param id 用户角色中间表
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.userRole.delete", name = "删除用户-角色", description = "删除用户-角色信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userRoleService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
