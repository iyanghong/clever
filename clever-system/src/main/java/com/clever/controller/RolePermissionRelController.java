package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.RolePermissionRel;
import com.clever.service.RolePermissionRelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色-权限接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/rolePermissionRel")
@AuthGroup(name = "角色-权限模块", description = "角色-权限模块权限组")
public class RolePermissionRelController {

    @Resource
    private RolePermissionRelService rolePermissionRelService;


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
    @Auth(value = "clever-system.rolePermissionRel.page", name = "角色-权限分页", description = "角色-权限分页接口")
    public Result<Page<RolePermissionRel>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String roleId, String permissionId) {
        return new Result<>(rolePermissionRelService.selectPage(pageNumber, pageSize, roleId, permissionId), "分页数据查询成功");
    }

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<RolePermissionRel> 角色-权限列表
     */
    @GetMapping("/listByRoleId/{roleId}")
    @Auth(value = "clever-system.rolePermissionRel.listByRoleId", name = "根据角色获取角色-权限列表", description = "根据角色获取角色-权限列表接口")
    public List<RolePermissionRel> selectListByRoleId(@PathVariable("roleId") String roleId) {
        return rolePermissionRelService.selectListByRoleId(roleId);
    }

    /**
     * 根据权限获取列表
     *
     * @param permissionId 权限
     * @return List<RolePermissionRel> 角色-权限列表
     */
    @GetMapping("/listByPermissionId/{permissionId}")
    @Auth(value = "clever-system.rolePermissionRel.listByPermissionId", name = "根据权限获取角色-权限列表", description = "根据权限获取角色-权限列表接口")
    public List<RolePermissionRel> selectListByPermissionId(@PathVariable("permissionId") String permissionId) {
        return rolePermissionRelService.selectListByPermissionId(permissionId);
    }

    /**
     * 根据角色权限中间表获取角色-权限信息
     *
     * @param id 角色权限中间表
     * @return 角色-权限信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据角色权限中间表获取角色-权限信息", description = "根据角色权限中间表获取角色-权限信息接口")
    public Result<RolePermissionRel> selectById(@PathVariable("id") String id) {
        return new Result<>(rolePermissionRelService.selectById(id), "查询成功");
    }

    /**
     * 创建角色-权限信息
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @return 创建后的角色-权限信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.rolePermissionRel.create", name = "创建角色-权限", description = "创建角色-权限信息接口")
    public Result<RolePermissionRel> create(@Validated RolePermissionRel rolePermissionRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(rolePermissionRelService.create(rolePermissionRel, onlineUser), "创建成功");
    }

    /**
     * 修改角色-权限信息
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @return 修改后的角色-权限信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.rolePermissionRel.update", name = "修改角色-权限", description = "修改角色-权限信息接口")
    public Result<RolePermissionRel> update(@Validated RolePermissionRel rolePermissionRel, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        rolePermissionRel.setId(id);
        return new Result<>(rolePermissionRelService.update(rolePermissionRel, onlineUser), "修改成功");
    }

    /**
     * 保存角色-权限信息
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @return 保存后的角色-权限信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.rolePermissionRel.save", name = "保存角色-权限", description = "保存角色-权限信息接口")
    public Result<RolePermissionRel> save(@Validated RolePermissionRel rolePermissionRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(rolePermissionRelService.save(rolePermissionRel, onlineUser), "保存成功");
    }

    /**
     * 根据角色-权限id删除角色-权限信息
     *
     * @param id 角色权限中间表
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.rolePermissionRel.delete", name = "删除角色-权限", description = "删除角色-权限信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        rolePermissionRelService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
