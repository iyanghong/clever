package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.CityMapper;
import com.clever.bean.system.City;
import com.clever.service.CityService;
import javax.annotation.Resource;

/**
 * 城市服务
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
@Service
public class CityServiceImpl implements CityService {

	private final static Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

	@Resource
	private CityMapper cityMapper;

	/**
	 * 分页查询城市列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 城市名称
	 * @param provinceId 省份编号
	 * @return Page<City>
	 */
	@Override
	public Page<City> selectPage(Integer pageNumber, Integer pageSize, String name, String provinceId) {
		QueryWrapper<City> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		if (StringUtils.isNotBlank(provinceId)) {
			queryWrapper.eq("province_id", provinceId);
		}
		return cityMapper.selectPage(new Page<City>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据城市编号获取城市信息
	 *
	 * @param id 城市编号
	 * @return List<City> 城市信息
	 */
	@Override
	public City selectById(Integer id) {
		return cityMapper.selectById(id);
	}

	/**
	 * 根据省份编号获取城市列表
	 *
	 * @param provinceId 省份编号
	 * @return List<City> 城市列表
	 */
	@Override
	public List<City> getListByProvinceId(Integer provinceId) {
		return cityMapper.selectList(new QueryWrapper<City>().eq("province_id", provinceId).orderByAsc("id"));
	}

	/**
	 * 保存城市信息
	 *
	 * @param city 城市实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(City city, OnlineUser onlineUser) {
		if (city.getId() == null) {
			cityMapper.insert(city);
			log.info("城市, 城市信息创建成功: userId={}, cityId={}", onlineUser.getId(), city.getId());
		} else {
			cityMapper.updateById(city);
			log.info("城市, 城市信息修改成功: userId={}, cityId={}", onlineUser.getId(), city.getId());
		}
	}

	/**
	 * 根据城市编号获取城市列表
	 *
	 * @param id 城市编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(Integer id, OnlineUser onlineUser) {
		cityMapper.deleteById(id);
		log.info("城市, 城市信息删除成功: userId={}, cityId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据城市编号列表删除城市信息
	 *
	 * @param ids 城市编号列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
		cityMapper.deleteBatchIds(ids);
		log.info("城市, 城市信息批量删除成功: userId={}, count={}, cityIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据省份编号删除城市
	 *
	 * @param provinceId 省份编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByProvinceId(String provinceId, OnlineUser onlineUser) {
		cityMapper.delete(new QueryWrapper<City>().eq("province_id", provinceId));
		log.info("城市, 城市信息根据省份编号删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
	}
}
