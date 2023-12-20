package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.RolePermission;
import com.clever.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色-权限接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
@RestController
@RequestMapping("/rolePermission")
@AuthGroup(name = "角色-权限模块", description = "角色-权限模块权限组")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;


    /**
     * 分页查询角色-权限列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param roleId       角色
     * @param permissionId 权限
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.rolePermission.page", name = "角色-权限分页", description = "角色-权限分页接口")
    public Result<Page<RolePermission>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String roleId, String permissionId) {
        return new Result<>(rolePermissionService.selectPage(pageNumber, pageSize, roleId, permissionId), "分页数据查询成功");
    }

    /**
     * 根据角色权限中间表获取角色-权限信息
     *
     * @param id 角色权限中间表
     * @return 角色-权限信息
     */
    @GetMapping("/get/{id}")
    public Result<RolePermission> selectById(@PathVariable("id") String id) {
        return new Result<>(rolePermissionService.selectById(id), "查询成功");
    }

    /**
     * 根据角色获取角色-权限列表
     *
     * @param roleId 角色
     * @return 角色-权限列表
     */
    @GetMapping("/getByRoleId/{roleId}")
    @Auth(value = "clever-system.rolePermission.getByRoleId", name = "根据role_id获取角色-权限列表", description = "角色-权限列表接口")
    public Result<List<RolePermission>> selectByRoleId(@PathVariable("roleId") String roleId) {
        return new Result<>(rolePermissionService.selectListByRoleId(roleId), "查询成功");
    }

    /**
     * 根据权限获取角色-权限列表
     *
     * @param permissionId 权限
     * @return 角色-权限列表
     */
    @GetMapping("/getByPermissionId/{permissionId}")
    @Auth(value = "clever-system.rolePermission.getByPermissionId", name = "根据permission_id获取角色-权限列表", description = "角色-权限列表接口")
    public Result<List<RolePermission>> selectByPermissionId(@PathVariable("permissionId") String permissionId) {
        return new Result<>(rolePermissionService.selectListByPermissionId(permissionId), "查询成功");
    }

    /**
     * 保存角色-权限信息
     *
     * @param rolePermission 角色-权限实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.rolePermission.save", name = "保存角色-权限", description = "保存角色-权限信息接口")
    public Result<String> save(RolePermission rolePermission) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        rolePermissionService.save(rolePermission, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据角色权限中间表获取角色-权限列表
     *
     * @param id 角色权限中间表
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.rolePermission.delete", name = "删除角色-权限", description = "删除角色-权限信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        rolePermissionService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
