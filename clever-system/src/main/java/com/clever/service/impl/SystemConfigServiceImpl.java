package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.Constant;
import com.clever.bean.model.OnlineUser;
import com.clever.constant.CacheConstant;
import com.clever.enums.SystemConfigType;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.service.RedisService;
import com.clever.util.DesUtil;
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

    @Resource
    private RedisService redis;

    /**
     * 初始化系统配置
     */
    public void initConfig() {
        List<SystemConfig> configs = systemConfigMapper.selectList(new QueryWrapper<>());
        for (SystemConfig config : configs) {
            // 初始化顶层全局config
            if (config.getPlatformId() == -1) {
                redis.setString(CacheConstant.formatKey("config", config.getCode()), config);
            } else {
                // 初始化各个平台config
                redis.setString(CacheConstant.formatKey("config", String.format("%s:%s", config.getPlatformId(), config.getCode())), config);
            }
        }
    }

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
     * 根据配置code获取信息
     *
     * @param platformId 平台ID
     * @param code       配置code
     * @return SystemConfig system_config信息
     */
    @Override
    public SystemConfig selectByCode(Integer platformId, String code) {
        SystemConfig config = redis.getString(String.format("config:%s:%s", platformId, code));
        if (config == null) {
            config = systemConfigMapper.selectOne(new QueryWrapper<SystemConfig>().eq("code", code).eq("platform_id", platformId));
        }
        if (config == null) {
            return null;
        }
        //如果是加密类型，先解密再返回
        if (config.getType().equals(SystemConfigType.ENCRYPT_STRING.type)) {
            config.setValue(DesUtil.safeDecrypt(config.getValue(), Constant.KEY));
        }
        return config;
    }

    /**
     * 根据配置code获取信息
     *
     * @param code 配置code
     * @return SystemConfig system_config信息
     */
    @Override
    public SystemConfig selectByCode(String code) {
        SystemConfig config = redis.getString(String.format("config:%s:", code));
        if (config == null) {
            config = systemConfigMapper.selectOne(new QueryWrapper<SystemConfig>().eq("code", code).eq("platform_id", -1));
        }
        if (config == null) {
            return null;
        }
        //如果是加密类型，先解密再返回
        if (config.getType().equals(SystemConfigType.ENCRYPT_STRING.type)) {
            config.setValue(DesUtil.safeDecrypt(config.getValue(), Constant.KEY));
        }
        return config;
    }

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<SystemConfig> 系统配置列表
     */
    @Override
    public List<SystemConfig> selectListByPlatformId(Integer platformId) {
        return systemConfigMapper.selectList(new QueryWrapper<SystemConfig>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 根据创建者获取列表
     *
     * @param creator 创建者
     * @return List<SystemConfig> 系统配置列表
     */
    @Override
    public List<SystemConfig> selectListByCreator(String creator) {
        return systemConfigMapper.selectList(new QueryWrapper<SystemConfig>().eq("creator", creator).orderByAsc("id"));
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
        //如果是加密类型，先加密再入库
        if (systemConfig.getType().equals(SystemConfigType.ENCRYPT_STRING.type)) {
            SystemConfig oldConfig = this.selectByCode(systemConfig.getPlatformId(), systemConfig.getCode());
            //防止重复加密
            if (oldConfig == null || !oldConfig.getValue().equals(systemConfig.getValue())) {
                systemConfig.setValue(DesUtil.safeEncrypt(systemConfig.getValue(), Constant.KEY));
            }
        }
        // 检查当前code在当前平台唯一性
        QueryWrapper<SystemConfig> configQueryWrapper = new QueryWrapper<>();
        configQueryWrapper.eq("code", systemConfig.getCode()).eq("platform_id", systemConfig.getPlatformId());
        if (systemConfigMapper.selectCount(configQueryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("配置code"));
        }
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
        //如果是加密类型，先加密再入库
        if (systemConfig.getType().equals(SystemConfigType.ENCRYPT_STRING.type)) {
            SystemConfig oldConfig = this.selectByCode(systemConfig.getPlatformId(), systemConfig.getCode());
            //防止重复加密
            if (oldConfig == null || !oldConfig.getValue().equals(systemConfig.getValue())) {
                systemConfig.setValue(DesUtil.safeEncrypt(systemConfig.getValue(), Constant.KEY));
            }
        }
        // 检查当前code在当前平台唯一性
        QueryWrapper<SystemConfig> configQueryWrapper = new QueryWrapper<>();
        configQueryWrapper.eq("code", systemConfig.getCode())
                .eq("platform_id", systemConfig.getPlatformId())
                .ne("id", systemConfig.getId());
        if (systemConfigMapper.selectCount(configQueryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("配置code"));
        }
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

    /**
     * 更新指定code的缓存
     *
     * @param platformId 平台ID
     * @param code       配置code
     */
    @Override
    public void updateSysConfigCache(Integer platformId, String code) {
        SystemConfig config = systemConfigMapper.selectOne(new QueryWrapper<SystemConfig>().eq("code", code).eq("platform_id", platformId));
        if (config != null) {
            if (config.getPlatformId() == -1) {
                redis.setString(CacheConstant.formatKey("config", config.getCode()), config);
            } else {
                // 初始化各个平台config
                redis.setString(CacheConstant.formatKey("config", String.format("%s:%s", config.getPlatformId(), config.getCode())), config);
            }
        }
    }
}
