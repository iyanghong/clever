package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.Constant;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.EmailTemplate;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.mapper.EmailTemplateMapper;
import com.clever.util.DesUtil;
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
 * @Date 2023-12-20 05:08:09
 */
@Service
public class EmailSubjectServiceImpl implements EmailSubjectService {

    private final static Logger log = LoggerFactory.getLogger(EmailSubjectServiceImpl.class);

    @Resource
    private EmailSubjectMapper emailSubjectMapper;

    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    /**
     * 分页查询邮箱主体列表
     *
     * @param pageNumber  页码
     * @param pageSize    每页记录数
     * @param platformId  平台id
     * @param host        host
     * @param username    账号
     * @param subjectName 发件人
     * @return Page<EmailSubject>
     */
    @Override
    public Page<EmailSubject> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String host, String username, String subjectName) {
        QueryWrapper<EmailSubject> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
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
     * @return EmailSubject 邮箱主体信息
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
    public List<EmailSubject> selectListByPlatformId(Integer platformId) {
        return emailSubjectMapper.selectList(new QueryWrapper<EmailSubject>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 保存邮箱主体信息
     *
     * @param emailSubject 邮箱主体实体信息
     * @param onlineUser   当前登录用户
     */
    @Override
    public void save(EmailSubject emailSubject, OnlineUser onlineUser) {
        QueryWrapper<EmailSubject> queryWrapper = getEmailSubjectQueryWrapper(emailSubject, onlineUser);
        if (StringUtils.isNotBlank(emailSubject.getId())) {
            queryWrapper.ne("id", emailSubject.getId());
        }
        if (emailSubjectMapper.selectCount(queryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.reset("您已存在同一个邮箱主体"));
        }
        if (StringUtils.isBlank(emailSubject.getId())) {
            emailSubject.setPassword(DesUtil.safeEncrypt(emailSubject.getPassword(), Constant.KEY));
            emailSubjectMapper.insert(emailSubject);
            log.info("邮箱主体, 邮箱主体信息创建成功: userId={}, emailSubjectId={}", onlineUser.getId(), emailSubject.getId());
        } else {
            EmailSubject oldEmailSubject = emailSubjectMapper.selectById(emailSubject.getId());
            if (oldEmailSubject == null) {
                throw new BaseException(ConstantException.DATA_NOT_EXIST.format("邮箱主体"));
            }
            // 防止重复加密
            if (!oldEmailSubject.getPassword().equals(emailSubject.getPassword())) {
                emailSubject.setPassword(DesUtil.safeEncrypt(emailSubject.getPassword(), Constant.KEY));
            }

            emailSubjectMapper.updateById(emailSubject);
            log.info("邮箱主体, 邮箱主体信息修改成功: userId={}, emailSubjectId={}", onlineUser.getId(), emailSubject.getId());
        }
    }

    private static QueryWrapper<EmailSubject> getEmailSubjectQueryWrapper(EmailSubject emailSubject, OnlineUser onlineUser) {
        QueryWrapper<EmailSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("platform_id", emailSubject.getPlatformId());
        queryWrapper.eq("creator", onlineUser.getId());
        queryWrapper.eq("host", emailSubject.getHost());
        queryWrapper.eq("driver", emailSubject.getDriver());
        queryWrapper.eq("port", emailSubject.getPort());
        queryWrapper.eq("encryption", emailSubject.getEncryption());
        queryWrapper.eq("username", emailSubject.getUsername());
        queryWrapper.eq("subject_name", emailSubject.getSubjectName());
        return queryWrapper;
    }

    /**
     * 根据id删除邮箱主体信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        if (emailTemplateMapper.selectCount(new QueryWrapper<EmailTemplate>().eq("subject_id", id)) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("该邮箱主体下还有邮件模板正在使用!!!"));
        }
        emailSubjectMapper.deleteById(id);
        log.info("邮箱主体, 邮箱主体信息删除成功: userId={}, emailSubjectId={}", onlineUser.getId(), id);
    }
}
