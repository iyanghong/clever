package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;
import com.clever.bean.system.SystemConfig;
import com.clever.service.SystemConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author xixi
 * @Date 2023-12-20 11:14
 **/
@RestController
@RequestMapping("/system/config")
@AuthGroup(name = "系统配置模块", description = "系统配置模块权限组")
public class SystemConfigController {

    @Resource
    private SystemConfigService systemConfigService;


    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   条数
     * @param platformId 平台id
     * @param name       名称
     * @param code       编码
     * @param type       类型
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.system.config.page", name = "系统配置分页", description = "系统配置分页接口")
    public Result<Page<SystemConfig>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String platformId, String name, String code, String type) {
        return new Result<>(systemConfigService.selectPage(pageNumber, pageSize, platformId, name, code, type), "系统配置分页数据查询成功");
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 查询结果
     */
    @GetMapping("/get/{id}")
    public Result<SystemConfig> selectById(Integer platformId, @PathVariable("id") String id) {
        return new Result<>(systemConfigService.selectById(platformId, id), "查询成功");
    }

    /**
     * 根据code查询
     *
     * @param code code
     * @return 查询结果
     */
    @GetMapping("/code")
    public Result<SystemConfig> selectByCode(Integer platformId, String code) {
        return new Result<>(systemConfigService.selectByCode(platformId, code), "查询成功");
    }

    /**
     * 保存,并更新缓存
     *
     * @param systemConfig 系统配置
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.system.config.save", name = "系统配置保存", description = "系统配置保存接口")
    public Result<String> save(SystemConfig systemConfig, OnlineUser onlineUser) {
        systemConfigService.save(systemConfig, onlineUser);
        // 更新缓存
        systemConfigService.updateSysConfigCache(systemConfig.getPlatformId(), systemConfig.getCode());
        return Result.ofSuccess("保存成功");
    }

    /**
     * 删除
     *
     * @param configId configId
     */
    @PostMapping("/delete/{id}")
    @Auth(value = "clever-system.system.config.delete", name = "系统配置删除", description = "系统配置删除接口")
    public Result<String> delete(@PathVariable("id") String configId, OnlineUser onlineUser) {
        systemConfigService.delete(configId, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
