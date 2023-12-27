package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.Date;
import java.util.List;

import com.clever.bean.system.LogUserStatus;

/**
 * 用户状态日志服务接口
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
public interface LogUserStatusService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param userId 用户
     * @param currentStatus 当前状态
     * @param changeStatus 变更后状态
     * @return Page<LogUserStatus>
     */
    Page<LogUserStatus> selectPage(Integer pageNumber, Integer pageSize,Integer platformId,String userId,Integer currentStatus,Integer changeStatus);

    /**
     * 根据自增id获取用户状态日志
     *
     * @param id 自增id
     * @return LogUserStatus 自增id信息
     */
    LogUserStatus selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<LogUserStatus> 用户状态日志列表
     */
    List<LogUserStatus> selectListByPlatformId(Integer platformId);

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<LogUserStatus> 用户状态日志列表
     */
    List<LogUserStatus> selectListByUserId(String userId);

    /**
     * 新建用户状态日志
     *
     * @param logUserStatus 用户状态日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserStatus 新建后的用户状态日志信息
     */
    LogUserStatus create(LogUserStatus logUserStatus, OnlineUser onlineUser);

    /**
    * 修改用户状态日志
    *
    * @param logUserStatus 用户状态日志实体信息
    * @param onlineUser   当前登录用户
    * @return LogUserStatus 修改后的用户状态日志信息
    */
    LogUserStatus update(LogUserStatus logUserStatus, OnlineUser onlineUser);

    /**
    * 保存用户状态日志
    *
    * @param logUserStatus 用户状态日志实体信息
    * @param onlineUser 当前登录用户
    * @return LogUserStatus 保存后的用户状态日志信息
    */
    LogUserStatus save(LogUserStatus logUserStatus, OnlineUser onlineUser);

    /**
     * 根据自增id删除信息
     *
     * @param id 自增id
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
     * @param userId 用户
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);
    /**
     * 记录用户状态变更
     *
     * @param userId       用户
     * @param changeStatus 更改的壮观
     * @param duration     结束时间
     * @param remake       备注
     */
    void logUserStatusChange(String userId, Integer changeStatus, Date duration, String remake);
}
