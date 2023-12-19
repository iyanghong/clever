package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.UserPlatform;

/**
 * 用户-平台服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:44
 */
public interface UserPlatformService {

	/**
	 * 分页查询用户-平台列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return Page<UserPlatform>
	 */
	Page<UserPlatform> selectPage(Integer pageNumber, Integer pageSize, String userId, String platformId);

	/**
	 * 根据id获取用户-平台信息
	 *
	 * @param id id
	 * @return List<UserPlatform> 用户-平台信息
	 */
	UserPlatform selectById(String id);

	/**
	 * 根据用户id获取用户-平台列表
	 *
	 * @param userId 用户id
	 * @return List<UserPlatform> 用户-平台列表
	 */
	List<UserPlatform> getListByUserId(String userId);

	/**
	 * 根据平台id获取用户-平台列表
	 *
	 * @param platformId 平台id
	 * @return List<UserPlatform> 用户-平台列表
	 */
	List<UserPlatform> getListByPlatformId(Integer platformId);

	/**
	 * 保存用户-平台信息
	 *
	 * @param userPlatform 用户-平台实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(UserPlatform userPlatform, OnlineUser onlineUser);

	/**
	 * 根据id获取用户-平台列表
	 *
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据id列表删除用户-平台信息
	 *
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据用户id删除用户-平台
	 *
	 * @param userId 用户id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByUserId(String userId, OnlineUser onlineUser);

	/**
	 * 根据平台id删除用户-平台
	 *
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByPlatformId(String platformId, OnlineUser onlineUser);
}
