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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统权限接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/permission")
@AuthGroup(value = "clever-system.permission", name = "系统权限模块", description = "系统权限模块权限组")
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
    public Result<Page<Permission>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String groupId, String name, String code, String type) {
        return new Result<>(permissionService.selectPage(pageNumber, pageSize, platformId, groupId, name, code, type), "分页数据查询成功");
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Permission> 系统权限列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.permission.listByPlatformId", name = "根据平台id获取系统权限列表", description = "根据平台id获取系统权限列表接口")
    public Result<List<Permission>> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(permissionService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 根据权限组id获取列表
     *
     * @param groupId 权限组id
     * @return List<Permission> 系统权限列表
     */
    @GetMapping("/listByGroupId/{groupId}")
    @Auth(value = "clever-system.permission.listByGroupId", name = "根据权限组id获取系统权限列表", description = "根据权限组id获取系统权限列表接口")
    public Result<List<Permission>> selectListByGroupId(@PathVariable("groupId") String groupId) {
        return new Result<>(permissionService.selectListByGroupId(groupId), "查询成功");
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Permission> 系统权限列表
     */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.permission.listByCreator", name = "根据创建者id获取系统权限列表", description = "根据创建者id获取系统权限列表接口")
    public Result<List<Permission>> selectListByCreator(@PathVariable("creator") String creator) {
        return new Result<>(permissionService.selectListByCreator(creator), "查询成功");
    }

    /**
     * 根据权限id获取系统权限信息
     *
     * @param id 权限id
     * @return 系统权限信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.permission.selectById", name = "根据权限id获取系统权限信息", description = "根据权限id获取系统权限信息接口")
    public Result<Permission> selectById(@PathVariable("id") String id) {
        return new Result<>(permissionService.selectById(id), "查询成功");
    }

    /**
     * 创建系统权限信息
     *
     * @param permission 系统权限实体信息
     * @return 创建后的系统权限信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.permission.create", name = "创建系统权限", description = "创建系统权限信息接口")
    public Result<Permission> create(@Validated Permission permission) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(permissionService.create(permission, onlineUser), "创建成功");
    }

    /**
     * 修改系统权限信息
     *
     * @param permission 系统权限实体信息
     * @return 修改后的系统权限信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.permission.update", name = "修改系统权限", description = "修改系统权限信息接口")
    public Result<Permission> update(@Validated Permission permission, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permission.setId(id);
        return new Result<>(permissionService.update(permission, onlineUser), "修改成功");
    }

    /**
     * 保存系统权限信息
     *
     * @param permission 系统权限实体信息
     * @return 保存后的系统权限信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.permission.save", name = "保存系统权限", description = "保存系统权限信息接口")
    public Result<Permission> save(@Validated Permission permission) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(permissionService.save(permission, onlineUser), "保存成功");
    }

    /**
     * 根据系统权限id删除系统权限信息
     *
     * @param id 权限id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.permission.delete", name = "删除系统权限", description = "删除系统权限信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permissionService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
