package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.EmailTemplate;

/**
 * 邮箱模板服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public interface EmailTemplateService {

	/**
	 * 分页查询邮箱模板列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param platformId 平台id
	 * @param name 邮件模板名称
	 * @param code 模板key
	 * @param subjectId 邮箱主体
	 * @return Page<EmailTemplate>
	 */
	Page<EmailTemplate> selectPage(Integer pageNumber, Integer pageSize, String platformId, String name, String code, String subjectId);

	/**
	 * 根据id获取邮箱模板信息
	 *
	 * @param id id
	 * @return List<EmailTemplate> 邮箱模板信息
	 */
	EmailTemplate selectById(String id);

	/**
	 * 根据平台id获取邮箱模板列表
	 *
	 * @param platformId 平台id
	 * @return List<EmailTemplate> 邮箱模板列表
	 */
	List<EmailTemplate> getListByPlatformId(Integer platformId);

	/**
	 * 根据邮箱主体获取邮箱模板列表
	 *
	 * @param subjectId 邮箱主体
	 * @return List<EmailTemplate> 邮箱模板列表
	 */
	List<EmailTemplate> getListBySubjectId(String subjectId);

	/**
	 * 保存邮箱模板信息
	 *
	 * @param emailTemplate 邮箱模板实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(EmailTemplate emailTemplate, OnlineUser onlineUser);

	/**
	 * 根据id获取邮箱模板列表
	 *
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据id列表删除邮箱模板信息
	 *
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据平台id删除邮箱模板
	 *
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByPlatformId(String platformId, OnlineUser onlineUser);

	/**
	 * 根据邮箱主体删除邮箱模板
	 *
	 * @param subjectId 邮箱主体
	 * @param onlineUser 当前登录用户
	 */
	void deleteBySubjectId(String subjectId, OnlineUser onlineUser);
}
