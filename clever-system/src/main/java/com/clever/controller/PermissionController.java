package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Permission;
import com.clever.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统权限接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/Permission")
@AuthGroup(name = "系统权限模块", description = "系统权限模块权限组")
public class PermissionController {

    @Resource
    private PermissionService permissionService;


    /**
     * 分页查询系统权限列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param groupId    权限组id
     * @param name       权限名称
     * @param code       权限标识
     * @param type       权限类型-字典表
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.permission.page", name = "系统权限分页", description = "系统权限分页接口")
    public Result<Page<Permission>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String platformId, String groupId, String name, String code, String type) {
        return new Result<>(permissionService.selectPage(pageNumber, pageSize, platformId, groupId, name, code, type), "分页数据查询成功");
    }

    /**
     * 根据权限id获取系统权限信息
     *
     * @param id 权限id
     * @return 系统权限信息
     */
    @GetMapping("/get/{id}")
    public Result<Permission> selectById(@PathVariable("id") String id) {
        return new Result<>(permissionService.selectById(id), "查询成功");
    }

    /**
     * 根据平台id获取系统权限列表
     *
     * @param platformId 平台id
     * @return 系统权限列表
     */
    @GetMapping("/getByPlatformId/{platformId}")
    @Auth(value = "clever-system.permission.getByPlatformId", name = "根据platform_id获取系统权限列表", description = "系统权限列表接口")
    public Result<List<Permission>> selectByPlatformId(@PathVariable("platformId") String platformId) {
        return new Result<>(permissionService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 根据权限组id获取系统权限列表
     *
     * @param groupId 权限组id
     * @return 系统权限列表
     */
    @GetMapping("/getByGroupId/{groupId}")
    @Auth(value = "clever-system.permission.getByGroupId", name = "根据group_id获取系统权限列表", description = "系统权限列表接口")
    public Result<List<Permission>> selectByGroupId(@PathVariable("groupId") String groupId) {
        return new Result<>(permissionService.selectListByGroupId(groupId), "查询成功");
    }

    /**
     * 保存系统权限信息
     *
     * @param permission 系统权限实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.permission.save", name = "保存系统权限", description = "保存系统权限信息接口")
    public Result<String> save(Permission permission) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permissionService.save(permission, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据权限id获取系统权限列表
     *
     * @param id 权限id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.permission.delete", name = "删除系统权限", description = "删除系统权限信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permissionService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
