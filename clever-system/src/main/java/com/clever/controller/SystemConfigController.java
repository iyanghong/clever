package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.SystemConfig;
import com.clever.service.SystemConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * SystemConfig接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/SystemConfig")
@AuthGroup(name = "system_config模块", description = "system_config模块权限组")
public class SystemConfigController {

    @Resource
    private SystemConfigService systemConfigService;


    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       系统配置名
     * @param code       缓存key
     * @param type       类型：0-字符串,1-数组,2-json对象,3-数字,4-布尔值,5-加密
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.systemConfig.page", name = "system_config分页", description = "system_config分页接口")
    public Result<Page<SystemConfig>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String name, String code, Integer type) {
        return new Result<>(systemConfigService.selectPage(pageNumber, pageSize, platformId, name, code, type), "分页数据查询成功");
    }

    /**
     * 根据配置id获取system_config信息
     *
     * @param id 配置id
     * @return system_config信息
     */
    @GetMapping("/get/{id}")
    public Result<SystemConfig> selectById(@PathVariable("id") String id) {
        return new Result<>(systemConfigService.selectById(id), "查询成功");
    }

    /**
     * 根据平台ID获取system_config列表
     *
     * @param platformId 平台ID
     * @return system_config列表
     */
    @GetMapping("/getByPlatformId/{platformId}")
    @Auth(value = "clever-system.systemConfig.getByPlatformId", name = "根据platform_id获取system_config列表", description = "system_config列表接口")
    public Result<List<SystemConfig>> selectByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(systemConfigService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 保存system_config信息
     *
     * @param systemConfig system_config实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.systemConfig.save", name = "保存system_config", description = "保存system_config信息接口")
    public Result<String> save(SystemConfig systemConfig) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        systemConfigService.save(systemConfig, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据配置id获取system_config列表
     *
     * @param id 配置id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.systemConfig.delete", name = "删除system_config", description = "删除system_config信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        systemConfigService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
