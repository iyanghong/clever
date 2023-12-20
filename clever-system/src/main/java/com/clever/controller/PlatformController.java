package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Platform;
import com.clever.service.PlatformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 平台接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
@RestController
@RequestMapping("/platform")
@AuthGroup(name = "平台模块", description = "平台模块权限组")
public class PlatformController {

    @Resource
    private PlatformService platformService;


    /**
     * 分页查询平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       平台名称
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.platform.page", name = "平台分页", description = "平台分页接口")
    public Result<Page<Platform>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name) {
        return new Result<>(platformService.selectPage(pageNumber, pageSize, name), "分页数据查询成功");
    }

    /**
     * 根据平台id获取平台信息
     *
     * @param id 平台id
     * @return 平台信息
     */
    @GetMapping("/get/{id}")
    public Result<Platform> selectById(@PathVariable("id") Integer id) {
        return new Result<>(platformService.selectById(id), "查询成功");
    }

    /**
     * 保存平台信息
     *
     * @param platform 平台实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.platform.save", name = "保存平台", description = "保存平台信息接口")
    public Result<String> save(Platform platform) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        platformService.save(platform, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据平台id获取平台列表
     *
     * @param id 平台id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.platform.delete", name = "删除平台", description = "删除平台信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        platformService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
