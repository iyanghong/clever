package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.SystemConfig;

/**
 * SystemConfig服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
public interface SystemConfigService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       系统配置名
     * @param code       缓存key
     * @param type       类型：0-字符串,1-数组,2-json对象,3-数字,4-布尔值,5-加密
     * @return Page<SystemConfig>
     */
    Page<SystemConfig> selectPage(Integer pageNumber, Integer pageSize, String platformId, String name, String code, String type);

    /**
     * 根据配置id获取信息
     *
     * @param id 配置id
     * @return List<SystemConfig> system_config信息
     */
    SystemConfig selectById(String id);

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<SystemConfig> system_config列表
     */
    List<SystemConfig> getListByPlatformId(Integer platformId);

    /**
     * 保存system_config信息
     *
     * @param systemConfig system_config实体信息
     * @param onlineUser   当前登录用户
     */
    void save(SystemConfig systemConfig, OnlineUser onlineUser);

    /**
     * 根据配置id获取列表
     *
     * @param id         配置id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据配置id列表删除信息
     *
     * @param ids        配置id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台ID删除
     *
     * @param platformId 平台ID
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(String platformId, OnlineUser onlineUser);
}