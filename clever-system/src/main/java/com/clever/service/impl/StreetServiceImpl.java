package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.StreetMapper;
import com.clever.bean.system.Street;
import com.clever.service.StreetService;
import javax.annotation.Resource;

/**
 * 街道服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
@Service
public class StreetServiceImpl implements StreetService {

	private final static Logger log = LoggerFactory.getLogger(StreetServiceImpl.class);

	@Resource
	private StreetMapper streetMapper;

	/**
	 * 分页查询街道列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 街道名称
	 * @param areaId 地区编号
	 * @param cityId 城市编号
	 * @param provinceId 省份编号
	 * @return Page<Street>
	 */
	@Override
	public Page<Street> selectPage(Integer pageNumber, Integer pageSize, String name, String areaId, String cityId, String provinceId) {
		QueryWrapper<Street> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		if (StringUtils.isNotBlank(areaId)) {
			queryWrapper.eq("area_id", areaId);
		}
		if (StringUtils.isNotBlank(cityId)) {
			queryWrapper.eq("city_id", cityId);
		}
		if (StringUtils.isNotBlank(provinceId)) {
			queryWrapper.eq("province_id", provinceId);
		}
		return streetMapper.selectPage(new Page<Street>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据街道编号获取街道信息
	 * @param id 街道编号
	 * @return List<Street> 街道信息
	 */
	@Override
	public Street selectById(Integer id) {
		return streetMapper.selectById(id);
	}

	/**
	 * 根据地区编号获取街道列表
	 * @param areaId 地区编号
	 * @return List<Street> 街道列表
	 */
	@Override
	public List<Street> getListByAreaId(Integer areaId) {
		return streetMapper.selectList(new QueryWrapper<Street>().eq("area_id", areaId).orderByAsc("id"));
	}

	/**
	 * 根据城市编号获取街道列表
	 * @param cityId 城市编号
	 * @return List<Street> 街道列表
	 */
	@Override
	public List<Street> getListByCityId(Integer cityId) {
		return streetMapper.selectList(new QueryWrapper<Street>().eq("city_id", cityId).orderByAsc("id"));
	}

	/**
	 * 根据省份编号获取街道列表
	 * @param provinceId 省份编号
	 * @return List<Street> 街道列表
	 */
	@Override
	public List<Street> getListByProvinceId(Integer provinceId) {
		return streetMapper.selectList(new QueryWrapper<Street>().eq("province_id", provinceId).orderByAsc("id"));
	}

	/**
	 * 保存街道信息
	 * @param street 街道实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(Street street, OnlineUser onlineUser) {
		if (street.getId() == null) {
			streetMapper.insert(street);
			log.info("街道, 街道信息创建成功: userId={}, streetId={}", onlineUser.getId(), street.getId());
		} else {
			streetMapper.updateById(street);
			log.info("街道, 街道信息修改成功: userId={}, streetId={}", onlineUser.getId(), street.getId());
		}
	}

	/**
	 * 根据街道编号获取街道列表
	 * @param id 街道编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(Integer id, OnlineUser onlineUser) {
		streetMapper.deleteById(id);
		log.info("街道, 街道信息删除成功: userId={}, streetId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据街道编号列表删除街道信息
	 * @param ids 街道编号列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
		streetMapper.deleteBatchIds(ids);
		log.info("街道, 街道信息批量删除成功: userId={}, count={}, streetIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据地区编号删除街道
	 * @param areaId 地区编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByAreaId(String areaId, OnlineUser onlineUser) {
		streetMapper.delete(new QueryWrapper<Street>().eq("area_id", areaId));
		log.info("街道, 街道信息根据地区编号删除成功: userId={}, areaId={}", onlineUser.getId(), areaId);
	}

	/**
	 * 根据城市编号删除街道
	 * @param cityId 城市编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByCityId(String cityId, OnlineUser onlineUser) {
		streetMapper.delete(new QueryWrapper<Street>().eq("city_id", cityId));
		log.info("街道, 街道信息根据城市编号删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
	}

	/**
	 * 根据省份编号删除街道
	 * @param provinceId 省份编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByProvinceId(String provinceId, OnlineUser onlineUser) {
		streetMapper.delete(new QueryWrapper<Street>().eq("province_id", provinceId));
		log.info("街道, 街道信息根据省份编号删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
	}
}
