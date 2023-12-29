package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Role;
import com.clever.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统角色接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/role")
@AuthGroup(value = "clever-system.role", name = "系统角色模块", description = "系统角色模块权限组")
public class RoleController {

    @Resource
    private RoleService roleService;


    /**
     * 分页查询系统角色列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       角色名
     * @param platformId 平台id
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.role.page", name = "系统角色分页", description = "系统角色分页接口")
    public Result<Page<Role>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer platformId) {
        return new Result<>(roleService.selectPage(pageNumber, pageSize, name, platformId), "分页数据查询成功");
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Role> 系统角色列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.role.listByPlatformId", name = "根据平台id获取系统角色列表", description = "根据平台id获取系统角色列表接口")
    public List<Role> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return roleService.selectListByPlatformId(platformId);
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Role> 系统角色列表
     */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.role.listByCreator", name = "根据创建者id获取系统角色列表", description = "根据创建者id获取系统角色列表接口")
    public List<Role> selectListByCreator(@PathVariable("creator") String creator) {
        return roleService.selectListByCreator(creator);
    }

    /**
     * 根据角色id获取系统角色信息
     *
     * @param id 角色id
     * @return 系统角色信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.role.selectById", name = "根据角色id获取系统角色信息", description = "根据角色id获取系统角色信息接口")
    public Result<Role> selectById(@PathVariable("id") String id) {
        return new Result<>(roleService.selectById(id), "查询成功");
    }

    /**
     * 创建系统角色信息
     *
     * @param role 系统角色实体信息
     * @return 创建后的系统角色信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.role.create", name = "创建系统角色", description = "创建系统角色信息接口")
    public Result<Role> create(@Validated Role role) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(roleService.create(role, onlineUser), "创建成功");
    }

    /**
     * 修改系统角色信息
     *
     * @param role 系统角色实体信息
     * @return 修改后的系统角色信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.role.update", name = "修改系统角色", description = "修改系统角色信息接口")
    public Result<Role> update(@Validated Role role, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        role.setId(id);
        return new Result<>(roleService.update(role, onlineUser), "修改成功");
    }

    /**
     * 保存系统角色信息
     *
     * @param role 系统角色实体信息
     * @return 保存后的系统角色信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.role.save", name = "保存系统角色", description = "保存系统角色信息接口")
    public Result<Role> save(@Validated Role role) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(roleService.save(role, onlineUser), "保存成功");
    }

    /**
     * 根据系统角色id删除系统角色信息
     *
     * @param id 角色id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.role.delete", name = "删除系统角色", description = "删除系统角色信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
