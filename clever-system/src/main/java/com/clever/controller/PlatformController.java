package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.service.UserPlatformRelService;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Platform;
import com.clever.service.PlatformService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 平台接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/platform")
@AuthGroup(name = "平台模块", description = "平台模块权限组")
public class PlatformController {

    @Resource
    private PlatformService platformService;

    @Resource
    private UserPlatformRelService userPlatformRelService;

    /**
     * 分页查询平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       平台名称
     * @param code       邀请码
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.platform.page", name = "平台分页", description = "平台分页接口")
    public Result<Page<Platform>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, String code) {
        return new Result<>(platformService.selectPage(pageNumber, pageSize, name, code), "分页数据查询成功");
    }

    /**
     * 根据邀请码邀请码获取平台信息
     *
     * @param code 邀请码
     * @return 平台信息
     */
    @GetMapping("/code/{code}")
    @Auth(value = "clever-system.platform.selectByCode", name = "根据平台id获取平台信息", description = "根据平台id获取平台信息接口")
    public Result<Platform> selectByCode(@PathVariable("code") String code) {
        return new Result<>(platformService.selectByCode(code), "查询成功");
    }

    /**
     * 根据平台id获取平台信息
     *
     * @param id 平台id
     * @return 平台信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据平台id获取平台信息", description = "根据平台id获取平台信息接口")
    public Result<Platform> selectById(@PathVariable("id") Integer id) {
        return new Result<>(platformService.selectById(id), "查询成功");
    }

    /**
     * 创建平台信息
     *
     * @param platform 平台实体信息
     * @return 创建后的平台信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.platform.create", name = "创建平台", description = "创建平台信息接口")
    public Result<Platform> create(@Validated Platform platform) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(platformService.create(platform, onlineUser), "创建成功");
    }

    /**
     * 修改平台信息
     *
     * @param platform 平台实体信息
     * @return 修改后的平台信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.platform.update", name = "修改平台", description = "修改平台信息接口")
    public Result<Platform> update(@Validated Platform platform, @PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        platform.setId(id);
        return new Result<>(platformService.update(platform, onlineUser), "修改成功");
    }

    /**
     * 保存平台信息
     *
     * @param platform 平台实体信息
     * @return 保存后的平台信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.platform.save", name = "保存平台", description = "保存平台信息接口")
    public Result<Platform> save(@Validated Platform platform) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(platformService.save(platform, onlineUser), "保存成功");
    }

    /**
     * 根据平台id删除平台信息
     *
     * @param id 平台id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.platform.delete", name = "删除平台", description = "删除平台信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        platformService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

    /**
     * 加入平台
     *
     * @param code 邀请码
     */
    @PostMapping("/join/{code}")
    @Auth(value = "clever-system.platform.join", name = "加入平台", description = "加入平台接口")
    public Result<String> join(@PathVariable("code") String code) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformRelService.joinPlatform(onlineUser.getId(), code);
        return Result.ofSuccess("加入成功");
    }

    /**
     * 根据平台id退出平台
     *
     * @param platformId 平台id
     */
    @PostMapping("/exit/{platformId}")
    @Auth(value = "clever-system.platform.exit", name = "退出平台", description = "退出平台接口")
    public Result<String> exit(@PathVariable("platformId") Integer platformId) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userPlatformRelService.exitPlatform(onlineUser.getId(), platformId);
        return Result.ofSuccess("退出成功");
    }
}
