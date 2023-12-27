package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.SystemConfig;

/**
 * 系统配置服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface SystemConfigService {
    /**
     * 初始化系统配置
     */
    void initConfig();

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       系统配置名
     * @param code       缓存key
     * @param type       类型,0-字符串,1-数组,2-json对象,3-数字,4-布尔值,5-加密
     * @return Page<SystemConfig>
     */
    Page<SystemConfig> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code, Integer type);

    /**
     * 根据配置id获取系统配置
     *
     * @param id 配置id
     * @return SystemConfig 配置id信息
     */
    SystemConfig selectById(String id);

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<SystemConfig> 系统配置列表
     */
    List<SystemConfig> selectListByPlatformId(Integer platformId);

    /**
     * 根据创建者获取列表
     *
     * @param creator 创建者
     * @return List<SystemConfig> 系统配置列表
     */
    List<SystemConfig> selectListByCreator(String creator);

    /**
     * 新建系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 新建后的系统配置信息
     */
    SystemConfig create(SystemConfig systemConfig, OnlineUser onlineUser);

    /**
     * 修改系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 修改后的系统配置信息
     */
    SystemConfig update(SystemConfig systemConfig, OnlineUser onlineUser);

    /**
     * 保存系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 保存后的系统配置信息
     */
    SystemConfig save(SystemConfig systemConfig, OnlineUser onlineUser);

    /**
     * 根据配置id删除信息
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
    void deleteByPlatformId(Integer platformId, OnlineUser onlineUser);

    /**
     * 根据创建者删除
     *
     * @param creator    创建者
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

    /**
     * 根据配置code获取信息
     *
     * @param platformId 平台ID
     * @param code       配置code
     * @return SystemConfig system_config信息
     */
    SystemConfig selectByCode(Integer platformId, String code);

    /**
     * 根据配置code获取信息
     *
     * @param code 配置code
     * @return SystemConfig system_config信息
     */
    SystemConfig selectByCode(String code);

    /**
     * 更新指定code的缓存
     *
     * @param code 配置code
     */
    void updateSysConfigCache(Integer platformId, String code);
}
