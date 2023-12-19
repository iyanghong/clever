package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.Role;

/**
 * 系统角色服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:39
 */
public interface RoleService {

	/**
	 * 分页查询系统角色列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 角色名
	 * @param platformId 平台id
	 * @return Page<Role>
	 */
	Page<Role> selectPage(Integer pageNumber, Integer pageSize, String name, String platformId);

	/**
	 * 根据角色id获取系统角色信息
	 * @param id 角色id
	 * @return List<Role> 系统角色信息
	 */
	Role selectById(String id);

	/**
	 * 根据平台id获取系统角色列表
	 * @param platformId 平台id
	 * @return List<Role> 系统角色列表
	 */
	List<Role> getListByPlatformId(Integer platformId);

	/**
	 * 保存系统角色信息
	 * @param role 系统角色实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(Role role, OnlineUser onlineUser);

	/**
	 * 根据角色id获取系统角色列表
	 * @param id 角色id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据角色id列表删除系统角色信息
	 * @param ids 角色id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据平台id删除系统角色
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByPlatformId(String platformId, OnlineUser onlineUser);
}
