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
 * @Date 2023-12-21 04:41:46
 */
@RestController
@RequestMapping("/role")
@AuthGroup(name = "系统角色模块", description = "系统角色模块权限组")
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
     * 根据角色id获取系统角色信息
     *
     * @param id 角色id
     * @return 系统角色信息
     */
    @GetMapping("/get/{id}")
    public Result<Role> selectById(@PathVariable("id") String id) {
        return new Result<>(roleService.selectById(id), "查询成功");
    }

    /**
     * 根据平台id获取系统角色列表
     *
     * @param platformId 平台id
     * @return 系统角色列表
     */
    @GetMapping("/getListByPlatformId/{platformId}")
    @Auth(value = "clever-system.role.getByPlatformId", name = "根据platform_id获取系统角色列表", description = "系统角色列表接口")
    public Result<List<Role>> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(roleService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 保存系统角色信息
     *
     * @param role 系统角色实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.role.save", name = "保存系统角色", description = "保存系统角色信息接口")
    public Result<String> save(@Validated Role role) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleService.save(role, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据角色id获取系统角色列表
     *
     * @param id 角色id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.role.delete", name = "删除系统角色", description = "删除系统角色信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
