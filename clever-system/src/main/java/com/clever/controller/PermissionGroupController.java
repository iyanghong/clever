package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.PermissionGroup;
import com.clever.service.PermissionGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统权限组接口
 *
 * @Author xixi
 * @Date 2023-12-21 05:07:38
 */
@RestController
@RequestMapping("/permissionGroup")
@AuthGroup(name = "系统权限组模块", description = "系统权限组模块权限组")
public class PermissionGroupController {

    @Resource
    private PermissionGroupService permissionGroupService;


    /**
     * 分页查询系统权限组列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param parentId   上级id
     * @param name       权限组名称
     * @param sortCode   排序号
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.permissionGroup.page", name = "系统权限组分页", description = "系统权限组分页接口")
    public Result<Page<PermissionGroup>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String platformId, String parentId, String name, Integer sortCode) {
        return new Result<>(permissionGroupService.selectPage(pageNumber, pageSize, platformId, parentId, name, sortCode), "分页数据查询成功");
    }

    /**
     * 根据权限组id获取系统权限组信息
     *
     * @param id 权限组id
     * @return 系统权限组信息
     */
    @GetMapping("/get/{id}")
    public Result<PermissionGroup> selectById(@PathVariable("id") String id) {
        return new Result<>(permissionGroupService.selectById(id), "查询成功");
    }

    /**
     * 根据平台id获取系统权限组列表
     *
     * @param platformId 平台id
     * @return 系统权限组列表
     */
    @GetMapping("/getListByPlatformId/{platformId}")
    @Auth(value = "clever-system.permissionGroup.getByPlatformId", name = "根据platform_id获取系统权限组列表", description = "系统权限组列表接口")
    public Result<List<PermissionGroup>> selectListByPlatformId(@PathVariable("platformId") String platformId) {
        return new Result<>(permissionGroupService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 根据上级id获取系统权限组列表
     *
     * @param parentId 上级id
     * @return 系统权限组列表
     */
    @GetMapping("/getListByParentId/{parentId}")
    @Auth(value = "clever-system.permissionGroup.getByParentId", name = "根据parent_id获取系统权限组列表", description = "系统权限组列表接口")
    public Result<List<PermissionGroup>> selectListByParentId(@PathVariable("parentId") String parentId) {
        return new Result<>(permissionGroupService.selectListByParentId(parentId), "查询成功");
    }

    /**
     * 保存系统权限组信息
     *
     * @param permissionGroup 系统权限组实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.permissionGroup.save", name = "保存系统权限组", description = "保存系统权限组信息接口")
    public Result<String> save(@Validated PermissionGroup permissionGroup) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permissionGroupService.save(permissionGroup, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据权限组id删除系统权限组信息
     *
     * @param id 权限组id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.permissionGroup.delete", name = "删除系统权限组", description = "删除系统权限组信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        permissionGroupService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
