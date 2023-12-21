package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserPlatform;
import com.clever.service.UserPlatformService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户-平台接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:39:08
 */
@RestController
@RequestMapping("/userPlatform")
@AuthGroup(name = "用户-平台模块", description = "用户-平台模块权限组")
public class UserPlatformController {

    @Resource
    private UserPlatformService userPlatformService;


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
    @Auth(value = "clever-system.userPlatform.page", name = "用户-平台分页", description = "用户-平台分页接口")
    public Result<Page<UserPlatform>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, Integer platformId) {
        return new Result<>(userPlatformService.selectPage(pageNumber, pageSize, userId, platformId), "分页数据查询成功");
    }

    /**
     * 根据id获取用户-平台信息
     *
     * @param id id
     * @return 用户-平台信息
     */
    @GetMapping("/get/{id}")
    public Result<UserPlatform> selectById(@PathVariable("id") String id) {
        return new Result<>(userPlatformService.selectById(id), "查询成功");
    }

    /**
     * 根据用户id获取用户-平台列表
     *
     * @param userId 用户id
     * @return 用户-平台列表
     */
    @GetMapping("/getByUserId/{userId}")
    @Auth(value = "clever-system.userPlatform.getByUserId", name = "根据user_id获取用户-平台列表", description = "用户-平台列表接口")
    public Result<List<UserPlatform>> selectByUserId(@PathVariable("userId") String userId) {
        return new Result<>(userPlatformService.selectListByUserId(userId), "查询成功");
    }

    /**
     * 根据平台id获取用户-平台列表
     *
     * @param platformId 平台id
     * @return 用户-平台列表
     */
    @GetMapping("/getByPlatformId/{platformId}")
    @Auth(value = "clever-system.userPlatform.getByPlatformId", name = "根据platform_id获取用户-平台列表", description = "用户-平台列表接口")
    public Result<List<UserPlatform>> selectByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(userPlatformService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 保存用户-平台信息
     *
     * @param userPlatform 用户-平台实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userPlatform.save", name = "保存用户-平台", description = "保存用户-平台信息接口")
    public Result<String> save(@Validated UserPlatform userPlatform) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformService.save(userPlatform, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id获取用户-平台列表
     *
     * @param id id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.userPlatform.delete", name = "删除用户-平台", description = "删除用户-平台信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
