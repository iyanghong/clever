package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserPlatformRel;
import com.clever.service.UserPlatformRelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户-平台接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/userPlatformRel")
@AuthGroup(name = "用户-平台模块", description = "用户-平台模块权限组")
public class UserPlatformRelController {

    @Resource
    private UserPlatformRelService userPlatformRelService;


    /**
     * 分页查询用户-平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param platformId 平台id
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.userPlatformRel.page", name = "用户-平台分页", description = "用户-平台分页接口")
    public Result<Page<UserPlatformRel>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, Integer platformId) {
        return new Result<>(userPlatformRelService.selectPage(pageNumber, pageSize, userId, platformId), "分页数据查询成功");
    }

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.userPlatformRel.listByUserId", name = "根据用户id获取用户-平台列表", description = "根据用户id获取用户-平台列表接口")
    public List<UserPlatformRel> selectListByUserId(@PathVariable("userId") String userId) {
        return userPlatformRelService.selectListByUserId(userId);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.userPlatformRel.listByPlatformId", name = "根据平台id获取用户-平台列表", description = "根据平台id获取用户-平台列表接口")
    public List<UserPlatformRel> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return userPlatformRelService.selectListByPlatformId(platformId);
    }

    /**
     * 根据id获取用户-平台信息
     *
     * @param id id
     * @return 用户-平台信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据id获取用户-平台信息", description = "根据id获取用户-平台信息接口")
    public Result<UserPlatformRel> selectById(@PathVariable("id") String id) {
        return new Result<>(userPlatformRelService.selectById(id), "查询成功");
    }

    /**
     * 创建用户-平台信息
     *
     * @param userPlatformRel 用户-平台实体信息
     * @return 创建后的用户-平台信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.userPlatformRel.create", name = "创建用户-平台", description = "创建用户-平台信息接口")
    public Result<UserPlatformRel> create(@Validated UserPlatformRel userPlatformRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userPlatformRelService.create(userPlatformRel, onlineUser), "创建成功");
    }

    /**
     * 修改用户-平台信息
     *
     * @param userPlatformRel 用户-平台实体信息
     * @return 修改后的用户-平台信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.userPlatformRel.update", name = "修改用户-平台", description = "修改用户-平台信息接口")
    public Result<UserPlatformRel> update(@Validated UserPlatformRel userPlatformRel, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformRel.setId(id);
        return new Result<>(userPlatformRelService.update(userPlatformRel, onlineUser), "修改成功");
    }

    /**
     * 保存用户-平台信息
     *
     * @param userPlatformRel 用户-平台实体信息
     * @return 保存后的用户-平台信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userPlatformRel.save", name = "保存用户-平台", description = "保存用户-平台信息接口")
    public Result<UserPlatformRel> save(@Validated UserPlatformRel userPlatformRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userPlatformRelService.save(userPlatformRel, onlineUser), "保存成功");
    }

    /**
     * 根据用户-平台id删除用户-平台信息
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.userPlatformRel.delete", name = "删除用户-平台", description = "删除用户-平台信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformRelService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
