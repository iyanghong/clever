package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.EmailSubject;

/**
 * 邮箱主体服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface EmailSubjectService {

    /**
     * 分页查询列表
     *
     * @param pageNumber  页码
     * @param pageSize    每页记录数
     * @param platformId  平台id
     * @param host        host
     * @param username    账号
     * @param subjectName 发件人
     * @return Page<EmailSubject>
     */
    Page<EmailSubject> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String host, String username, String subjectName);

    /**
     * 根据id获取邮箱主体
     *
     * @param id id
     * @return EmailSubject id信息
     */
    EmailSubject selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<EmailSubject> 邮箱主体列表
     */
    List<EmailSubject> selectListByPlatformId(Integer platformId);

    /**
     * 根据创建人获取列表
     *
     * @param creator 创建人
     * @return List<EmailSubject> 邮箱主体列表
     */
    List<EmailSubject> selectListByCreator(String creator);

    /**
     * 新建邮箱主体
     *
     * @param emailSubject 邮箱主体实体信息
     * @param onlineUser   当前登录用户
     * @return EmailSubject 新建后的邮箱主体信息
     */
    EmailSubject create(EmailSubject emailSubject, OnlineUser onlineUser);

    /**
     * 修改邮箱主体
     *
     * @param emailSubject 邮箱主体实体信息
     * @param onlineUser   当前登录用户
     * @return EmailSubject 修改后的邮箱主体信息
     */
    EmailSubject update(EmailSubject emailSubject, OnlineUser onlineUser);

    /**
     * 保存邮箱主体
     *
     * @param emailSubject 邮箱主体实体信息
     * @param onlineUser   当前登录用户
     * @return EmailSubject 保存后的邮箱主体信息
     */
    EmailSubject save(EmailSubject emailSubject, OnlineUser onlineUser);

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
     * 根据创建人删除
     *
     * @param creator    创建人
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

}
