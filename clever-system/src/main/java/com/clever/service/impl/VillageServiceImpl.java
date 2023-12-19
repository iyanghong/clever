package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.VillageMapper;
import com.clever.bean.system.Village;
import com.clever.service.VillageService;
import javax.annotation.Resource;

/**
 * Village服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
@Service
public class VillageServiceImpl implements VillageService {

	private final static Logger log = LoggerFactory.getLogger(VillageServiceImpl.class);

	@Resource
	private VillageMapper villageMapper;

	/**
	 * 分页查询列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 
	 * @param streetId 
	 * @param provinceId 
	 * @param cityId 
	 * @param areaId 
	 * @return Page<Village>
	 */
	@Override
	public Page<Village> selectPage(Integer pageNumber, Integer pageSize, String name, String streetId, String provinceId, String cityId, String areaId) {
		QueryWrapper<Village> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		if (StringUtils.isNotBlank(streetId)) {
			queryWrapper.eq("street_id", streetId);
		}
		if (StringUtils.isNotBlank(provinceId)) {
			queryWrapper.eq("province_id", provinceId);
		}
		if (StringUtils.isNotBlank(cityId)) {
			queryWrapper.eq("city_id", cityId);
		}
		if (StringUtils.isNotBlank(areaId)) {
			queryWrapper.eq("area_id", areaId);
		}
		return villageMapper.selectPage(new Page<Village>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据id获取信息
	 * @param id 
	 * @return List<Village> village信息
	 */
	@Override
	public Village selectById(Long id) {
		return villageMapper.selectById(id);
	}

	/**
	 * 根据street_id获取列表
	 * @param streetId 
	 * @return List<Village> village列表
	 */
	@Override
	public List<Village> getListByStreetId(Integer streetId) {
		return villageMapper.selectList(new QueryWrapper<Village>().eq("street_id", streetId).orderByAsc("id"));
	}

	/**
	 * 根据province_id获取列表
	 * @param provinceId 
	 * @return List<Village> village列表
	 */
	@Override
	public List<Village> getListByProvinceId(Integer provinceId) {
		return villageMapper.selectList(new QueryWrapper<Village>().eq("province_id", provinceId).orderByAsc("id"));
	}

	/**
	 * 根据city_id获取列表
	 * @param cityId 
	 * @return List<Village> village列表
	 */
	@Override
	public List<Village> getListByCityId(Integer cityId) {
		return villageMapper.selectList(new QueryWrapper<Village>().eq("city_id", cityId).orderByAsc("id"));
	}

	/**
	 * 根据area_id获取列表
	 * @param areaId 
	 * @return List<Village> village列表
	 */
	@Override
	public List<Village> getListByAreaId(Integer areaId) {
		return villageMapper.selectList(new QueryWrapper<Village>().eq("area_id", areaId).orderByAsc("id"));
	}

	/**
	 * 保存village信息
	 * @param village village实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(Village village, OnlineUser onlineUser) {
		if (StringUtils.isBlank(village.getId())) {
			villageMapper.insert(village);
			log.info(", 信息创建成功: userId={}, villageId={}", onlineUser.getId(), village.getId());
		} else {
			villageMapper.updateById(village);
			log.info(", 信息修改成功: userId={}, villageId={}", onlineUser.getId(), village.getId());
		}
	}

	/**
	 * 根据id获取列表
	 * @param id 
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(Long id, OnlineUser onlineUser) {
		villageMapper.deleteById(id);
		log.info(", 信息删除成功: userId={}, villageId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据id列表删除信息
	 * @param ids 列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<Long> ids, OnlineUser onlineUser) {
		villageMapper.deleteBatchIds(ids);
		log.info(", 信息批量删除成功: userId={}, count={}, villageIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据street_id删除
	 * @param streetId 
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByStreetId(String streetId, OnlineUser onlineUser) {
		villageMapper.delete(new QueryWrapper<Village>().eq("street_id", streetId));
		log.info(", 信息根据street_id删除成功: userId={}, streetId={}", onlineUser.getId(), streetId);
	}

	/**
	 * 根据province_id删除
	 * @param provinceId 
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByProvinceId(String provinceId, OnlineUser onlineUser) {
		villageMapper.delete(new QueryWrapper<Village>().eq("province_id", provinceId));
		log.info(", 信息根据province_id删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
	}

	/**
	 * 根据city_id删除
	 * @param cityId 
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByCityId(String cityId, OnlineUser onlineUser) {
		villageMapper.delete(new QueryWrapper<Village>().eq("city_id", cityId));
		log.info(", 信息根据city_id删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
	}

	/**
	 * 根据area_id删除
	 * @param areaId 
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByAreaId(String areaId, OnlineUser onlineUser) {
		villageMapper.delete(new QueryWrapper<Village>().eq("area_id", areaId));
		log.info(", 信息根据area_id删除成功: userId={}, areaId={}", onlineUser.getId(), areaId);
	}
}
