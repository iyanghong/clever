package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.Street;

/**
 * 街道服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
public interface StreetService {

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
	Page<Street> selectPage(Integer pageNumber, Integer pageSize, String name, String areaId, String cityId, String provinceId);

	/**
	 * 根据街道编号获取街道信息
	 * @param id 街道编号
	 * @return List<Street> 街道信息
	 */
	Street selectById(Integer id);

	/**
	 * 根据地区编号获取街道列表
	 * @param areaId 地区编号
	 * @return List<Street> 街道列表
	 */
	List<Street> getListByAreaId(Integer areaId);

	/**
	 * 根据城市编号获取街道列表
	 * @param cityId 城市编号
	 * @return List<Street> 街道列表
	 */
	List<Street> getListByCityId(Integer cityId);

	/**
	 * 根据省份编号获取街道列表
	 * @param provinceId 省份编号
	 * @return List<Street> 街道列表
	 */
	List<Street> getListByProvinceId(Integer provinceId);

	/**
	 * 保存街道信息
	 * @param street 街道实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(Street street, OnlineUser onlineUser);

	/**
	 * 根据街道编号获取街道列表
	 * @param id 街道编号
	 * @param onlineUser 当前登录用户
	 */
	void delete(Integer id, OnlineUser onlineUser);

	/**
	 * 根据街道编号列表删除街道信息
	 * @param ids 街道编号列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

	/**
	 * 根据地区编号删除街道
	 * @param areaId 地区编号
	 * @param onlineUser 当前登录用户
	 */
	void deleteByAreaId(String areaId, OnlineUser onlineUser);

	/**
	 * 根据城市编号删除街道
	 * @param cityId 城市编号
	 * @param onlineUser 当前登录用户
	 */
	void deleteByCityId(String cityId, OnlineUser onlineUser);

	/**
	 * 根据省份编号删除街道
	 * @param provinceId 省份编号
	 * @param onlineUser 当前登录用户
	 */
	void deleteByProvinceId(String provinceId, OnlineUser onlineUser);
}
