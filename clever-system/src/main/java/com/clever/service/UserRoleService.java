package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.UserRole;

/**
 * 用户-角色服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:44
 */
public interface UserRoleService {

	/**
	 * 分页查询用户-角色列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param userId 用户
	 * @param roleId 角色
	 * @return Page<UserRole>
	 */
	Page<UserRole> selectPage(Integer pageNumber, Integer pageSize, String userId, String roleId);

	/**
	 * 根据用户角色中间表获取用户-角色信息
	 *
	 * @param id 用户角色中间表
	 * @return List<UserRole> 用户-角色信息
	 */
	UserRole selectById(String id);

	/**
	 * 根据用户获取用户-角色列表
	 *
	 * @param userId 用户
	 * @return List<UserRole> 用户-角色列表
	 */
	List<UserRole> getListByUserId(String userId);

	/**
	 * 根据角色获取用户-角色列表
	 *
	 * @param roleId 角色
	 * @return List<UserRole> 用户-角色列表
	 */
	List<UserRole> getListByRoleId(String roleId);

	/**
	 * 保存用户-角色信息
	 *
	 * @param userRole 用户-角色实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(UserRole userRole, OnlineUser onlineUser);

	/**
	 * 根据用户角色中间表获取用户-角色列表
	 *
	 * @param id 用户角色中间表
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据用户角色中间表列表删除用户-角色信息
	 *
	 * @param ids 用户角色中间表列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据用户删除用户-角色
	 *
	 * @param userId 用户
	 * @param onlineUser 当前登录用户
	 */
	void deleteByUserId(String userId, OnlineUser onlineUser);

	/**
	 * 根据角色删除用户-角色
	 *
	 * @param roleId 角色
	 * @param onlineUser 当前登录用户
	 */
	void deleteByRoleId(String roleId, OnlineUser onlineUser);
}
