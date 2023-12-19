package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.EmailSubject;

/**
 * 邮箱主体服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
public interface EmailSubjectService {

	/**
	 * 分页查询邮箱主体列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param platformId 平台id
	 * @param host host
	 * @param username 账号
	 * @param subjectName 发件人
	 * @return Page<EmailSubject>
	 */
	Page<EmailSubject> selectPage(Integer pageNumber, Integer pageSize, String platformId, String host, String username, String subjectName);

	/**
	 * 根据id获取邮箱主体信息
	 * @param id id
	 * @return List<EmailSubject> 邮箱主体信息
	 */
	EmailSubject selectById(String id);

	/**
	 * 根据平台id获取邮箱主体列表
	 * @param platformId 平台id
	 * @return List<EmailSubject> 邮箱主体列表
	 */
	List<EmailSubject> getListByPlatformId(Integer platformId);

	/**
	 * 保存邮箱主体信息
	 * @param emailSubject 邮箱主体实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(EmailSubject emailSubject, OnlineUser onlineUser);

	/**
	 * 根据id获取邮箱主体列表
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据id列表删除邮箱主体信息
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据平台id删除邮箱主体
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByPlatformId(String platformId, OnlineUser onlineUser);
}
