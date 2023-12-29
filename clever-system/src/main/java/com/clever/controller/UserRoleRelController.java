package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserRoleRel;
import com.clever.service.UserRoleRelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户-角色接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/userRoleRel")
@AuthGroup(value = "clever-system.userRoleRel", name = "用户-角色模块", description = "用户-角色模块权限组")
public class UserRoleRelController {

    @Resource
    private UserRoleRelService userRoleRelService;


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
    @Auth(value = "clever-system.userRoleRel.page", name = "用户-角色分页", description = "用户-角色分页接口")
    public Result<Page<UserRoleRel>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, String roleId) {
        return new Result<>(userRoleRelService.selectPage(pageNumber, pageSize, userId, roleId), "分页数据查询成功");
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserRoleRel> 用户-角色列表
     */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.userRoleRel.listByUserId", name = "根据用户获取用户-角色列表", description = "根据用户获取用户-角色列表接口")
    public List<UserRoleRel> selectListByUserId(@PathVariable("userId") String userId) {
        return userRoleRelService.selectListByUserId(userId);
    }

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<UserRoleRel> 用户-角色列表
     */
    @GetMapping("/listByRoleId/{roleId}")
    @Auth(value = "clever-system.userRoleRel.listByRoleId", name = "根据角色获取用户-角色列表", description = "根据角色获取用户-角色列表接口")
    public List<UserRoleRel> selectListByRoleId(@PathVariable("roleId") String roleId) {
        return userRoleRelService.selectListByRoleId(roleId);
    }

    /**
     * 根据用户角色中间表获取用户-角色信息
     *
     * @param id 用户角色中间表
     * @return 用户-角色信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.userRoleRel.selectById", name = "根据用户角色中间表获取用户-角色信息", description = "根据用户角色中间表获取用户-角色信息接口")
    public Result<UserRoleRel> selectById(@PathVariable("id") String id) {
        return new Result<>(userRoleRelService.selectById(id), "查询成功");
    }

    /**
     * 创建用户-角色信息
     *
     * @param userRoleRel 用户-角色实体信息
     * @return 创建后的用户-角色信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.userRoleRel.create", name = "创建用户-角色", description = "创建用户-角色信息接口")
    public Result<UserRoleRel> create(@Validated UserRoleRel userRoleRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userRoleRelService.create(userRoleRel, onlineUser), "创建成功");
    }

    /**
     * 修改用户-角色信息
     *
     * @param userRoleRel 用户-角色实体信息
     * @return 修改后的用户-角色信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.userRoleRel.update", name = "修改用户-角色", description = "修改用户-角色信息接口")
    public Result<UserRoleRel> update(@Validated UserRoleRel userRoleRel, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userRoleRel.setId(id);
        return new Result<>(userRoleRelService.update(userRoleRel, onlineUser), "修改成功");
    }

    /**
     * 保存用户-角色信息
     *
     * @param userRoleRel 用户-角色实体信息
     * @return 保存后的用户-角色信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userRoleRel.save", name = "保存用户-角色", description = "保存用户-角色信息接口")
    public Result<UserRoleRel> save(@Validated UserRoleRel userRoleRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userRoleRelService.save(userRoleRel, onlineUser), "保存成功");
    }

    /**
     * 根据用户-角色id删除用户-角色信息
     *
     * @param id 用户角色中间表
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.userRoleRel.delete", name = "删除用户-角色", description = "删除用户-角色信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userRoleRelService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
