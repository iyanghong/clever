package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.SystemConfigMapper;
import com.clever.bean.system.SystemConfig;
import com.clever.service.SystemConfigService;

import javax.annotation.Resource;

/**
 * 系统配置服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    private final static Logger log = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

    @Resource
    private SystemConfigMapper systemConfigMapper;

    /**
     * 分页查询系统配置列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       系统配置名
     * @param code       缓存key
     * @param type       类型,0-字符串,1-数组,2-json对象,3-数字,4-布尔值,5-加密
     * @return Page<SystemConfig>
     */
    @Override
    public Page<SystemConfig> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code, Integer type) {
        QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.eq("code", code);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return systemConfigMapper.selectPage(new Page<SystemConfig>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据配置id获取系统配置
     *
     * @param id 配置id
     * @return SystemConfig 系统配置信息
     */
    @Override
    public SystemConfig selectById(String id) {
        return systemConfigMapper.selectById(id);
    }

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<SystemConfig> 系统配置列表
     */
    @Override
    public List<SystemConfig> selectListByPlatformId(Integer platformId) {
        return systemConfigMapper.selectList(new QueryWrapper<SystemConfig>().eq("platform_id", platformId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据创建者获取列表
     *
     * @param creator 创建者
     * @return List<SystemConfig> 系统配置列表
     */
    @Override
    public List<SystemConfig> selectListByCreator(String creator) {
        return systemConfigMapper.selectList(new QueryWrapper<SystemConfig>().eq("creator", creator).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 新建后的系统配置信息
     */
    @Override
    public SystemConfig create(SystemConfig systemConfig, OnlineUser onlineUser) {
        systemConfigMapper.insert(systemConfig);
        log.info("系统配置, 系统配置信息创建成功: userId={}, systemConfigId={}", onlineUser.getId(), systemConfig.getId());
        return systemConfig;
    }

    /**
     * 修改系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 修改后的系统配置信息
     */
    @Override
    public SystemConfig update(SystemConfig systemConfig, OnlineUser onlineUser) {
        systemConfigMapper.updateById(systemConfig);
        log.info("系统配置, 系统配置信息修改成功: userId={}, systemConfigId={}", onlineUser.getId(), systemConfig.getId());
        return systemConfig;
    }

    /**
     * 保存系统配置
     *
     * @param systemConfig 系统配置实体信息
     * @param onlineUser   当前登录用户
     * @return SystemConfig 保存后的系统配置信息
     */
    @Override
    public SystemConfig save(SystemConfig systemConfig, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(systemConfig.getId())) {
            return create(systemConfig, onlineUser);
        }
        return update(systemConfig, onlineUser);
    }

    /**
     * 根据配置id删除系统配置信息
     *
     * @param id         配置id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        systemConfigMapper.deleteById(id);
        log.info("系统配置, 系统配置信息删除成功: userId={}, systemConfigId={}", onlineUser.getId(), id);
    }

    /**
     * 根据配置id列表删除系统配置信息
     *
     * @param ids        配置id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        systemConfigMapper.deleteBatchIds(ids);
        log.info("系统配置, 系统配置信息批量删除成功: userId={}, count={}, systemConfigIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台ID删除
     *
     * @param platformId 平台ID
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        systemConfigMapper.delete(new QueryWrapper<SystemConfig>().eq("platform_id", platformId));
        log.info("系统配置, 系统配置信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据创建者删除
     *
     * @param creator    创建者
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        systemConfigMapper.delete(new QueryWrapper<SystemConfig>().eq("creator", creator));
        log.info("系统配置, 系统配置信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }
}
