package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.Village;

/**
 * Village服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
public interface VillageService {

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
	Page<Village> selectPage(Integer pageNumber, Integer pageSize, String name, String streetId, String provinceId, String cityId, String areaId);

	/**
	 * 根据id获取信息
	 * @param id 
	 * @return List<Village> village信息
	 */
	Village selectById(Long id);

	/**
	 * 根据street_id获取列表
	 * @param streetId 
	 * @return List<Village> village列表
	 */
	List<Village> getListByStreetId(Integer streetId);

	/**
	 * 根据province_id获取列表
	 * @param provinceId 
	 * @return List<Village> village列表
	 */
	List<Village> getListByProvinceId(Integer provinceId);

	/**
	 * 根据city_id获取列表
	 * @param cityId 
	 * @return List<Village> village列表
	 */
	List<Village> getListByCityId(Integer cityId);

	/**
	 * 根据area_id获取列表
	 * @param areaId 
	 * @return List<Village> village列表
	 */
	List<Village> getListByAreaId(Integer areaId);

	/**
	 * 保存village信息
	 * @param village village实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(Village village, OnlineUser onlineUser);

	/**
	 * 根据id获取列表
	 * @param id 
	 * @param onlineUser 当前登录用户
	 */
	void delete(Long id, OnlineUser onlineUser);

	/**
	 * 根据id列表删除信息
	 * @param ids 列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<Long> ids, OnlineUser onlineUser);

	/**
	 * 根据street_id删除
	 * @param streetId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByStreetId(String streetId, OnlineUser onlineUser);

	/**
	 * 根据province_id删除
	 * @param provinceId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByProvinceId(String provinceId, OnlineUser onlineUser);

	/**
	 * 根据city_id删除
	 * @param cityId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByCityId(String cityId, OnlineUser onlineUser);

	/**
	 * 根据area_id删除
	 * @param areaId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByAreaId(String areaId, OnlineUser onlineUser);
}
