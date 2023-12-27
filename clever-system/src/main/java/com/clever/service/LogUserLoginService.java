package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.IpAttribution;
import com.clever.bean.model.OnlineUser;

import java.util.Date;
import java.util.List;

import com.clever.bean.system.LogUserLogin;

/**
 * 用户登录日志服务接口
 *
 * @Author xixi
 * @Date 2023-12-27 10:45:51
 */
public interface LogUserLoginService {

    /**
     * 分页查询列表
     *
     * @param pageNumber  页码
     * @param pageSize    每页记录数
     * @param platformId  平台id
     * @param userId      用户
     * @param addressCode 登录地址编码
     * @return Page<LogUserLogin>
     */
    Page<LogUserLogin> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String userId, String addressCode);

    /**
     * 根据自增id获取用户登录日志
     *
     * @param id 自增id
     * @return LogUserLogin 自增id信息
     */
    LogUserLogin selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<LogUserLogin> 用户登录日志列表
     */
    List<LogUserLogin> selectListByPlatformId(Integer platformId);

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<LogUserLogin> 用户登录日志列表
     */
    List<LogUserLogin> selectListByUserId(String userId);

    /**
     * 新建用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 新建后的用户登录日志信息
     */
    LogUserLogin create(LogUserLogin logUserLogin, OnlineUser onlineUser);

    /**
     * 修改用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 修改后的用户登录日志信息
     */
    LogUserLogin update(LogUserLogin logUserLogin, OnlineUser onlineUser);

    /**
     * 保存用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 保存后的用户登录日志信息
     */
    LogUserLogin save(LogUserLogin logUserLogin, OnlineUser onlineUser);

    /**
     * 根据自增id删除信息
     *
     * @param id         自增id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据自增id列表删除信息
     *
     * @param ids        自增id列表
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
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

    /**
     * 记录用户登录
     *
     * @param userId        用户
     * @param platformId    平台id
     * @param ip            ip地址
     * @param userAgent     userAgent
     * @param loginTime     登录时间
     * @param ipAttribution ip归属地
     */
    void recordUserLogin(String userId, Integer platformId, String ip, String userAgent, Date loginTime, IpAttribution ipAttribution);


}
