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
 * SystemConfig服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	private final static Logger log = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

	@Resource
	private SystemConfigMapper systemConfigMapper;

	/**
	 * 分页查询列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param platformId 平台ID
	 * @param name 系统配置名
	 * @param code 缓存key
	 * @param type 类型
0-字符串
1-数组
2-json对象
3-数字
4-布尔值
5-加密
	 * @return Page<SystemConfig>
	 */
	@Override
	public Page<SystemConfig> selectPage(Integer pageNumber, Integer pageSize, String platformId, String name, String code, String type) {
		QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(platformId)) {
			queryWrapper.eq("platform_id", platformId);
		}
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		if (StringUtils.isNotBlank(code)) {
			queryWrapper.eq("code", code);
		}
		if (StringUtils.isNotBlank(type)) {
			queryWrapper.eq("type", type);
		}
		return systemConfigMapper.selectPage(new Page<SystemConfig>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据配置id获取信息
	 * @param id 配置id
	 * @return List<SystemConfig> system_config信息
	 */
	@Override
	public SystemConfig selectById(String id) {
		return systemConfigMapper.selectById(id);
	}

	/**
	 * 根据平台ID获取列表
	 * @param platformId 平台ID
	 * @return List<SystemConfig> system_config列表
	 */
	@Override
	public List<SystemConfig> getListByPlatformId(Integer platformId) {
		return systemConfigMapper.selectList(new QueryWrapper<SystemConfig>().eq("platform_id", platformId).orderByAsc("id"));
	}

	/**
	 * 保存system_config信息
	 * @param systemConfig system_config实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(SystemConfig systemConfig, OnlineUser onlineUser) {
		if (StringUtils.isBlank(systemConfig.getId())) {
			systemConfigMapper.insert(systemConfig);
			log.info(", 信息创建成功: userId={}, systemConfigId={}", onlineUser.getId(), systemConfig.getId());
		} else {
			systemConfigMapper.updateById(systemConfig);
			log.info(", 信息修改成功: userId={}, systemConfigId={}", onlineUser.getId(), systemConfig.getId());
		}
	}

	/**
	 * 根据配置id获取列表
	 * @param id 配置id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(String id, OnlineUser onlineUser) {
		systemConfigMapper.deleteById(id);
		log.info(", 信息删除成功: userId={}, systemConfigId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据配置id列表删除信息
	 * @param ids 配置id列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
		systemConfigMapper.deleteBatchIds(ids);
		log.info(", 信息批量删除成功: userId={}, count={}, systemConfigIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据平台ID删除
	 * @param platformId 平台ID
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
		systemConfigMapper.delete(new QueryWrapper<SystemConfig>().eq("platform_id", platformId));
		log.info(", 信息根据平台ID删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
	}
}
