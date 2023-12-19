package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.AddressLevel3;

/**
 * VIEW服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public interface AddressLevel3Service {

	/**
	 * 分页查询VIEW列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param provinceId 
	 * @param provinceName 
	 * @param cityId 
	 * @param cityName 
	 * @param areaId 
	 * @param areaName 
	 * @return Page<AddressLevel3>
	 */
	Page<AddressLevel3> selectPage(Integer pageNumber, Integer pageSize, String provinceId, String provinceName, String cityId, String cityName, String areaId, String areaName);

	/**
	 * 根据province_id获取VIEW列表
	 *
	 * @param provinceId 
	 * @return List<AddressLevel3> VIEW列表
	 */
	List<AddressLevel3> getListByProvinceId(Integer provinceId);

	/**
	 * 根据city_id获取VIEW列表
	 *
	 * @param cityId 
	 * @return List<AddressLevel3> VIEW列表
	 */
	List<AddressLevel3> getListByCityId(Integer cityId);

	/**
	 * 根据area_id获取VIEW列表
	 *
	 * @param areaId 
	 * @return List<AddressLevel3> VIEW列表
	 */
	List<AddressLevel3> getListByAreaId(Integer areaId);

	/**
	 * 根据province_id删除VIEW
	 *
	 * @param provinceId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByProvinceId(String provinceId, OnlineUser onlineUser);

	/**
	 * 根据city_id删除VIEW
	 *
	 * @param cityId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByCityId(String cityId, OnlineUser onlineUser);

	/**
	 * 根据area_id删除VIEW
	 *
	 * @param areaId 
	 * @param onlineUser 当前登录用户
	 */
	void deleteByAreaId(String areaId, OnlineUser onlineUser);
}
