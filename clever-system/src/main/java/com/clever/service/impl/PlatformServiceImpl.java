package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.PlatformMapper;
import com.clever.bean.system.Platform;
import com.clever.service.PlatformService;
import javax.annotation.Resource;

/**
 * 平台服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
@Service
public class PlatformServiceImpl implements PlatformService {

	private final static Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

	@Resource
	private PlatformMapper platformMapper;

	/**
	 * 分页查询平台列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 平台名称
	 * @return Page<Platform>
	 */
	@Override
	public Page<Platform> selectPage(Integer pageNumber, Integer pageSize, String name) {
		QueryWrapper<Platform> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		return platformMapper.selectPage(new Page<Platform>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据平台id获取平台信息
	 * @param id 平台id
	 * @return List<Platform> 平台信息
	 */
	@Override
	public Platform selectById(Integer id) {
		return platformMapper.selectById(id);
	}

	/**
	 * 保存平台信息
	 * @param platform 平台实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(Platform platform, OnlineUser onlineUser) {
		if (platform.getId() == null) {
			platformMapper.insert(platform);
			log.info("平台, 平台信息创建成功: userId={}, platformId={}", onlineUser.getId(), platform.getId());
		} else {
			platformMapper.updateById(platform);
			log.info("平台, 平台信息修改成功: userId={}, platformId={}", onlineUser.getId(), platform.getId());
		}
	}

	/**
	 * 根据平台id获取平台列表
	 * @param id 平台id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(Integer id, OnlineUser onlineUser) {
		platformMapper.deleteById(id);
		log.info("平台, 平台信息删除成功: userId={}, platformId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据平台id列表删除平台信息
	 * @param ids 平台id列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
		platformMapper.deleteBatchIds(ids);
		log.info("平台, 平台信息批量删除成功: userId={}, count={}, platformIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}
}
