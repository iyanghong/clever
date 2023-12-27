package com.clever.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.Constant;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.User;
import com.clever.enums.UserStatus;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.manager.EmailManager;
import com.clever.mapper.UserMapper;
import com.clever.util.RegularUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.clever.mapper.EmailTemplateMapper;
import com.clever.bean.system.EmailTemplate;
import com.clever.service.EmailTemplateService;

import javax.annotation.Resource;

/**
 * 邮箱模板服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final static Logger log = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

    private final EmailTemplateMapper emailTemplateMapper;
    private final ThreadPoolTaskExecutor executor;
    private final EmailManager emailManager;

    private final UserMapper userMapper;

    public EmailTemplateServiceImpl(EmailTemplateMapper emailTemplateMapper, @Qualifier("threadPoolTaskExecutor") ThreadPoolTaskExecutor executor, EmailManager emailManager, UserMapper userMapper) {
        this.emailTemplateMapper = emailTemplateMapper;
        this.executor = executor;
        this.emailManager = emailManager;
        this.userMapper = userMapper;
    }

    /**
     * 分页查询邮箱模板列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param name       邮件模板名称
     * @param code       模板key
     * @param subjectId  邮箱主体
     * @return Page<EmailTemplate>
     */
    @Override
    public Page<EmailTemplate> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code, String subjectId) {
        QueryWrapper<EmailTemplate> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
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
     * 根据id获取邮箱模板
     *
     * @param id id
     * @return EmailTemplate 邮箱模板信息
     */
    @Override
    public EmailTemplate selectById(String id) {
        return emailTemplateMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @Override
    public List<EmailTemplate> selectListByPlatformId(Integer platformId) {
        return emailTemplateMapper.selectList(new QueryWrapper<EmailTemplate>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 根据邮箱主体获取列表
     *
     * @param subjectId 邮箱主体
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @Override
    public List<EmailTemplate> selectListBySubjectId(String subjectId) {
        return emailTemplateMapper.selectList(new QueryWrapper<EmailTemplate>().eq("subject_id", subjectId).orderByAsc("id"));
    }

    /**
     * 根据模板创建者获取列表
     *
     * @param creator 模板创建者
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @Override
    public List<EmailTemplate> selectListByCreator(String creator) {
        return emailTemplateMapper.selectList(new QueryWrapper<EmailTemplate>().eq("creator", creator).orderByAsc("id"));
    }

    /**
     * 新建邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 新建后的邮箱模板信息
     */
    @Override
    public EmailTemplate create(EmailTemplate emailTemplate, OnlineUser onlineUser) {
        if (emailTemplateMapper.selectCount(new QueryWrapper<EmailTemplate>().eq("code", emailTemplate.getCode()).eq("platform_id", emailTemplate.getPlatformId())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.reset("存在同一个Key的邮箱模板"));
        }
        emailTemplateMapper.insert(emailTemplate);
        log.info("邮箱模板, 邮箱模板信息创建成功: userId={}, emailTemplateId={}", onlineUser.getId(), emailTemplate.getId());
        return emailTemplate;
    }

    /**
     * 修改邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 修改后的邮箱模板信息
     */
    @Override
    public EmailTemplate update(EmailTemplate emailTemplate, OnlineUser onlineUser) {
        if (emailTemplateMapper.selectCount(new QueryWrapper<EmailTemplate>().ne("id", emailTemplate.getId()).eq("code", emailTemplate.getCode()).eq("platform_id", emailTemplate.getPlatformId())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.reset("存在同一个Key的邮箱模板"));
        }
        emailTemplateMapper.updateById(emailTemplate);
        log.info("邮箱模板, 邮箱模板信息修改成功: userId={}, emailTemplateId={}", onlineUser.getId(), emailTemplate.getId());
        return emailTemplate;
    }

    /**
     * 保存邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 保存后的邮箱模板信息
     */
    @Override
    public EmailTemplate save(EmailTemplate emailTemplate, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(emailTemplate.getId())) {
            return create(emailTemplate, onlineUser);
        }
        return update(emailTemplate, onlineUser);
    }

    /**
     * 根据id删除邮箱模板信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        emailTemplateMapper.deleteById(id);
        log.info("邮箱模板, 邮箱模板信息删除成功: userId={}, emailTemplateId={}", onlineUser.getId(), id);
    }

    /**
     * 根据id列表删除邮箱模板信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        emailTemplateMapper.deleteBatchIds(ids);
        log.info("邮箱模板, 邮箱模板信息批量删除成功: userId={}, count={}, emailTemplateIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        emailTemplateMapper.delete(new QueryWrapper<EmailTemplate>().eq("platform_id", platformId));
        log.info("邮箱模板, 邮箱模板信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据邮箱主体删除
     *
     * @param subjectId  邮箱主体
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBySubjectId(String subjectId, OnlineUser onlineUser) {
        emailTemplateMapper.delete(new QueryWrapper<EmailTemplate>().eq("subject_id", subjectId));
        log.info("邮箱模板, 邮箱模板信息根据subjectId删除成功: userId={}, subjectId={}", onlineUser.getId(), subjectId);
    }

    /**
     * 根据模板创建者删除
     *
     * @param creator    模板创建者
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        emailTemplateMapper.delete(new QueryWrapper<EmailTemplate>().eq("creator", creator));
        log.info("邮箱模板, 邮箱模板信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }

    /**
     * 渲染模板内容
     *
     * @param content           模板内容
     * @param placeholderFormat 占位符格式
     * @param variables         变量
     * @return 渲染后的内容
     */
    private String renderEmailTemplate(String content, String placeholderFormat, Map<String, String> variables) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        if (StringUtils.isBlank(placeholderFormat) || !placeholderFormat.contains("%s")) {
            placeholderFormat = "%s";
        }
        for (String key : variables.keySet()) {
            String value = variables.get(key);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                content = content.replaceAll(RegularUtil.makeQueryStringAllRegExp(String.format(placeholderFormat, key)), value);
            }
        }
        return content;
    }

    private Map<String, String> loadBaseTemplateVariables(String receiveEmail) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", receiveEmail));
        if (user == null) {
            user = new User();
        }
        Map<String, String> variables = new HashMap<>();
        variables.put("receiveEmail", receiveEmail);
        variables.put("account", user.getAccount());
        variables.put("nickname", user.getNickname());
        variables.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        variables.put("datetime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        variables.put("appUrl", Constant.APP_URL);
        return variables;
    }


    /**
     * 发送邮件
     *
     * @param email      接收邮箱
     * @param templateId 模板id
     * @param variables  变量
     */
    @Override
    public void sendEmail(String email, String templateId, Map<String, String> variables) {
        executor.execute(() -> {
            try {
                // 根据模板ID查询模板
                EmailTemplate emailTemplate = emailTemplateMapper.selectById(templateId);
                if (emailTemplate != null) {
                    // 将模板中的占位符替换为变量
                    JSONObject jsonObject = JSONObject.parseObject(emailTemplate.getPlaceholder());
                    Map<String, String> replacementMap = loadBaseTemplateVariables(email);
                    if (!jsonObject.isEmpty()) {
                        for (String key : jsonObject.keySet()) {
                            String value = StringUtils.isBlank(variables.get(key)) ? jsonObject.getString(key) : variables.get(key);
                            replacementMap.put(key, value);
                        }
                    }
                    // 渲染邮件模板
                    emailTemplate.setContent(renderEmailTemplate(emailTemplate.getContent(), "${%s}", replacementMap));
                    // 发送邮件
                    emailManager.sendEmail(email, emailTemplate);
                    log.info("发送邮件, 邮件发送成功: receiveMail={}, template={}", email, templateId);
                } else {
                    log.error("发送邮件, 发送模板为空: receiveMail={}, template={}", email, templateId);
                }
            } catch (Exception e) {
                log.error("发送邮件, 邮件发送失败: receiveMail={}, template={}", email, templateId, e);
            }
        });
    }

    /**
     * 根据code和平台id获取邮箱模板
     *
     * @param platformId 平台id
     * @param code       模板code
     * @return EmailTemplate
     */
    @Override
    public EmailTemplate selectByCodeAndPlatform(Integer platformId, String code) {
        return emailTemplateMapper.selectOne(new QueryWrapper<EmailTemplate>().eq("platform_id", platformId).eq("code", code));
    }
}
