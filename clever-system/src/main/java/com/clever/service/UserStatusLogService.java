package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.UserStatusLog;

/**
 * UserStatusLog服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:44
 */
public interface UserStatusLogService {

	/**
	 * 分页查询列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param userId 用户
	 * @param currentStatus 当前状态
	 * @param changeStatus 变更后状态
	 * @return Page<UserStatusLog>
	 */
	Page<UserStatusLog> selectPage(Integer pageNumber, Integer pageSize, String userId, String currentStatus, String changeStatus);

	/**
	 * 根据自增id获取信息
	 *
	 * @param id 自增id
	 * @return List<UserStatusLog> user_status_log信息
	 */
	UserStatusLog selectById(String id);

	/**
	 * 根据用户获取列表
	 *
	 * @param userId 用户
	 * @return List<UserStatusLog> user_status_log列表
	 */
	List<UserStatusLog> getListByUserId(String userId);

	/**
	 * 保存user_status_log信息
	 *
	 * @param userStatusLog user_status_log实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(UserStatusLog userStatusLog, OnlineUser onlineUser);

	/**
	 * 根据自增id获取列表
	 *
	 * @param id 自增id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据自增id列表删除信息
	 *
	 * @param ids 自增id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据用户删除
	 *
	 * @param userId 用户
	 * @param onlineUser 当前登录用户
	 */
	void deleteByUserId(String userId, OnlineUser onlineUser);
}
