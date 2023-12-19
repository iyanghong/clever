package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.EmailTemplateMapper;
import com.clever.bean.system.EmailTemplate;
import com.clever.service.EmailTemplateService;
import javax.annotation.Resource;

/**
 * 邮箱模板服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	private final static Logger log = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

	@Resource
	private EmailTemplateMapper emailTemplateMapper;

	/**
	 * 分页查询邮箱模板列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param platformId 平台id
	 * @param name 邮件模板名称
	 * @param code 模板key
	 * @param subjectId 邮箱主体
	 * @return Page<EmailTemplate>
	 */
	@Override
	public Page<EmailTemplate> selectPage(Integer pageNumber, Integer pageSize, String platformId, String name, String code, String subjectId) {
		QueryWrapper<EmailTemplate> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(platformId)) {
			queryWrapper.eq("platform_id", platformId);
		}
		if (StringUtils.isNotBlank(name)) {
			queryWrapper.eq("name", name);
		}
		if (StringUtils.isNotBlank(code)) {
			queryWrapper.eq("code", code);
		}
		if (StringUtils.isNotBlank(subjectId)) {
			queryWrapper.eq("subject_id", subjectId);
		}
		return emailTemplateMapper.selectPage(new Page<EmailTemplate>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据id获取邮箱模板信息
	 * @param id id
	 * @return List<EmailTemplate> 邮箱模板信息
	 */
	@Override
	public EmailTemplate selectById(String id) {
		return emailTemplateMapper.selectById(id);
	}

	/**
	 * 根据平台id获取邮箱模板列表
	 * @param platformId 平台id
	 * @return List<EmailTemplate> 邮箱模板列表
	 */
	@Override
	public List<EmailTemplate> getListByPlatformId(Integer platformId) {
		return emailTemplateMapper.selectList(new QueryWrapper<EmailTemplate>().eq("platform_id", platformId).orderByAsc("id"));
	}

	/**
	 * 根据邮箱主体获取邮箱模板列表
	 * @param subjectId 邮箱主体
	 * @return List<EmailTemplate> 邮箱模板列表
	 */
	@Override
	public List<EmailTemplate> getListBySubjectId(String subjectId) {
		return emailTemplateMapper.selectList(new QueryWrapper<EmailTemplate>().eq("subject_id", subjectId).orderByAsc("id"));
	}

	/**
	 * 保存邮箱模板信息
	 * @param emailTemplate 邮箱模板实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(EmailTemplate emailTemplate, OnlineUser onlineUser) {
		if (StringUtils.isBlank(emailTemplate.getId())) {
			emailTemplateMapper.insert(emailTemplate);
			log.info("邮箱模板, 邮箱模板信息创建成功: userId={}, emailTemplateId={}", onlineUser.getId(), emailTemplate.getId());
		} else {
			emailTemplateMapper.updateById(emailTemplate);
			log.info("邮箱模板, 邮箱模板信息修改成功: userId={}, emailTemplateId={}", onlineUser.getId(), emailTemplate.getId());
		}
	}

	/**
	 * 根据id获取邮箱模板列表
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(String id, OnlineUser onlineUser) {
		emailTemplateMapper.deleteById(id);
		log.info("邮箱模板, 邮箱模板信息删除成功: userId={}, emailTemplateId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据id列表删除邮箱模板信息
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
		emailTemplateMapper.deleteBatchIds(ids);
		log.info("邮箱模板, 邮箱模板信息批量删除成功: userId={}, count={}, emailTemplateIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据平台id删除邮箱模板
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
		emailTemplateMapper.delete(new QueryWrapper<EmailTemplate>().eq("platform_id", platformId));
		log.info("邮箱模板, 邮箱模板信息根据平台id删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
	}

	/**
	 * 根据邮箱主体删除邮箱模板
	 * @param subjectId 邮箱主体
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBySubjectId(String subjectId, OnlineUser onlineUser) {
		emailTemplateMapper.delete(new QueryWrapper<EmailTemplate>().eq("subject_id", subjectId));
		log.info("邮箱模板, 邮箱模板信息根据邮箱主体删除成功: userId={}, subjectId={}", onlineUser.getId(), subjectId);
	}
}
