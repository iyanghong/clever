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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统配置接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/systemConfig")
@AuthGroup(name = "系统配置模块", description = "系统配置模块权限组")
public class SystemConfigController {

    @Resource
    private SystemConfigService systemConfigService;


    /**
     * 分页查询系统配置列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       系统配置名
     * @param code       缓存key
     * @param type       类型,0-字符串,1-数组,2-json对象,3-数字,4-布尔值,5-加密
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.systemConfig.page", name = "系统配置分页", description = "系统配置分页接口")
    public Result<Page<SystemConfig>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String name, String code, Integer type) {
        return new Result<>(systemConfigService.selectPage(pageNumber, pageSize, platformId, name, code, type), "分页数据查询成功");
    }

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<SystemConfig> 系统配置列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.systemConfig.listByPlatformId", name = "根据平台ID获取系统配置列表", description = "根据平台ID获取系统配置列表接口")
    public List<SystemConfig> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return systemConfigService.selectListByPlatformId(platformId);
    }

    /**
     * 根据创建者获取列表
     *
     * @param creator 创建者
     * @return List<SystemConfig> 系统配置列表
     */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.systemConfig.listByCreator", name = "根据创建者获取系统配置列表", description = "根据创建者获取系统配置列表接口")
    public List<SystemConfig> selectListByCreator(@PathVariable("creator") String creator) {
        return systemConfigService.selectListByCreator(creator);
    }

    /**
     * 根据配置id获取系统配置信息
     *
     * @param id 配置id
     * @return 系统配置信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据配置id获取系统配置信息", description = "根据配置id获取系统配置信息接口")
    public Result<SystemConfig> selectById(@PathVariable("id") String id) {
        return new Result<>(systemConfigService.selectById(id), "查询成功");
    }

    /**
     * 创建系统配置信息
     *
     * @param systemConfig 系统配置实体信息
     * @return 创建后的系统配置信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.systemConfig.create", name = "创建系统配置", description = "创建系统配置信息接口")
    public Result<SystemConfig> create(@Validated SystemConfig systemConfig) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(systemConfigService.create(systemConfig, onlineUser), "创建成功");
    }

    /**
     * 修改系统配置信息
     *
     * @param systemConfig 系统配置实体信息
     * @return 修改后的系统配置信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.systemConfig.update", name = "修改系统配置", description = "修改系统配置信息接口")
    public Result<SystemConfig> update(@Validated SystemConfig systemConfig, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        systemConfig.setId(id);
        return new Result<>(systemConfigService.update(systemConfig, onlineUser), "修改成功");
    }

    /**
     * 保存系统配置信息
     *
     * @param systemConfig 系统配置实体信息
     * @return 保存后的系统配置信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.systemConfig.save", name = "保存系统配置", description = "保存系统配置信息接口")
    public Result<SystemConfig> save(@Validated SystemConfig systemConfig) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(systemConfigService.save(systemConfig, onlineUser), "保存成功");
    }

    /**
     * 根据系统配置id删除系统配置信息
     *
     * @param id 配置id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.systemConfig.delete", name = "删除系统配置", description = "删除系统配置信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        systemConfigService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
