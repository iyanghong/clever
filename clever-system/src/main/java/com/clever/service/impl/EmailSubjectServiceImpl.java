package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.EmailSubjectMapper;
import com.clever.bean.system.EmailSubject;
import com.clever.service.EmailSubjectService;
import javax.annotation.Resource;

/**
 * 邮箱主体服务
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
@Service
public class EmailSubjectServiceImpl implements EmailSubjectService {

	private final static Logger log = LoggerFactory.getLogger(EmailSubjectServiceImpl.class);

	@Resource
	private EmailSubjectMapper emailSubjectMapper;

	/**
	 * 分页查询邮箱主体列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param platformId 平台id
	 * @param host host
	 * @param username 账号
	 * @param subjectName 发件人
	 * @return Page<EmailSubject>
	 */
	@Override
	public Page<EmailSubject> selectPage(Integer pageNumber, Integer pageSize, String platformId, String host, String username, String subjectName) {
		QueryWrapper<EmailSubject> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(platformId)) {
			queryWrapper.eq("platform_id", platformId);
		}
		if (StringUtils.isNotBlank(host)) {
			queryWrapper.eq("host", host);
		}
		if (StringUtils.isNotBlank(username)) {
			queryWrapper.eq("username", username);
		}
		if (StringUtils.isNotBlank(subjectName)) {
			queryWrapper.eq("subject_name", subjectName);
		}
		return emailSubjectMapper.selectPage(new Page<EmailSubject>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据id获取邮箱主体信息
	 *
	 * @param id id
	 * @return List<EmailSubject> 邮箱主体信息
	 */
	@Override
	public EmailSubject selectById(String id) {
		return emailSubjectMapper.selectById(id);
	}

	/**
	 * 根据平台id获取邮箱主体列表
	 *
	 * @param platformId 平台id
	 * @return List<EmailSubject> 邮箱主体列表
	 */
	@Override
	public List<EmailSubject> getListByPlatformId(Integer platformId) {
		return emailSubjectMapper.selectList(new QueryWrapper<EmailSubject>().eq("platform_id", platformId).orderByAsc("id"));
	}

	/**
	 * 保存邮箱主体信息
	 *
	 * @param emailSubject 邮箱主体实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(EmailSubject emailSubject, OnlineUser onlineUser) {
		if (StringUtils.isBlank(emailSubject.getId())) {
			emailSubjectMapper.insert(emailSubject);
			log.info("邮箱主体, 邮箱主体信息创建成功: userId={}, emailSubjectId={}", onlineUser.getId(), emailSubject.getId());
		} else {
			emailSubjectMapper.updateById(emailSubject);
			log.info("邮箱主体, 邮箱主体信息修改成功: userId={}, emailSubjectId={}", onlineUser.getId(), emailSubject.getId());
		}
	}

	/**
	 * 根据id获取邮箱主体列表
	 *
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(String id, OnlineUser onlineUser) {
		emailSubjectMapper.deleteById(id);
		log.info("邮箱主体, 邮箱主体信息删除成功: userId={}, emailSubjectId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据id列表删除邮箱主体信息
	 *
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
		emailSubjectMapper.deleteBatchIds(ids);
		log.info("邮箱主体, 邮箱主体信息批量删除成功: userId={}, count={}, emailSubjectIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据平台id删除邮箱主体
	 *
	 * @param platformId 平台id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
		emailSubjectMapper.delete(new QueryWrapper<EmailSubject>().eq("platform_id", platformId));
		log.info("邮箱主体, 邮箱主体信息根据平台id删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
	}
}
