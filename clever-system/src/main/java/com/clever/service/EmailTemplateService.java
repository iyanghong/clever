package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.EmailTemplate;

/**
 * 邮箱模板服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface EmailTemplateService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param name       邮件模板名称
     * @param code       模板key
     * @param subjectId  邮箱主体
     * @return Page<EmailTemplate>
     */
    Page<EmailTemplate> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code, String subjectId);

    /**
     * 根据id获取邮箱模板
     *
     * @param id id
     * @return EmailTemplate id信息
     */
    EmailTemplate selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<EmailTemplate> 邮箱模板列表
     */
    List<EmailTemplate> selectListByPlatformId(Integer platformId);

    /**
     * 根据邮箱主体获取列表
     *
     * @param subjectId 邮箱主体
     * @return List<EmailTemplate> 邮箱模板列表
     */
    List<EmailTemplate> selectListBySubjectId(String subjectId);

    /**
     * 根据模板创建者获取列表
     *
     * @param creator 模板创建者
     * @return List<EmailTemplate> 邮箱模板列表
     */
    List<EmailTemplate> selectListByCreator(String creator);

    /**
     * 新建邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 新建后的邮箱模板信息
     */
    EmailTemplate create(EmailTemplate emailTemplate, OnlineUser onlineUser);

    /**
     * 修改邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 修改后的邮箱模板信息
     */
    EmailTemplate update(EmailTemplate emailTemplate, OnlineUser onlineUser);

    /**
     * 保存邮箱模板
     *
     * @param emailTemplate 邮箱模板实体信息
     * @param onlineUser    当前登录用户
     * @return EmailTemplate 保存后的邮箱模板信息
     */
    EmailTemplate save(EmailTemplate emailTemplate, OnlineUser onlineUser);

    /**
     * 根据id删除信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据id列表删除信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(Integer platformId, OnlineUser onlineUser);

    /**
     * 根据邮箱主体删除
     *
     * @param subjectId  邮箱主体
     * @param onlineUser 当前登录用户
     */
    void deleteBySubjectId(String subjectId, OnlineUser onlineUser);

    /**
     * 根据模板创建者删除
     *
     * @param creator    模板创建者
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

}
