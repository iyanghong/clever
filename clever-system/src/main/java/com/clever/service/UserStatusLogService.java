package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.UserStatusLog;

/**
 * 用户状态日志服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface UserStatusLogService {

    /**
     * 分页查询列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return Page<UserStatusLog>
     */
    Page<UserStatusLog> selectPage(Integer pageNumber, Integer pageSize, String userId, Integer currentStatus, Integer changeStatus);

    /**
     * 根据自增id获取用户状态日志
     *
     * @param id 自增id
     * @return UserStatusLog 自增id信息
     */
    UserStatusLog selectById(String id);

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserStatusLog> 用户状态日志列表
     */
    List<UserStatusLog> selectListByUserId(String userId);

    /**
     * 新建用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 新建后的用户状态日志信息
     */
    UserStatusLog create(UserStatusLog userStatusLog, OnlineUser onlineUser);

    /**
     * 修改用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 修改后的用户状态日志信息
     */
    UserStatusLog update(UserStatusLog userStatusLog, OnlineUser onlineUser);

    /**
     * 保存用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 保存后的用户状态日志信息
     */
    UserStatusLog save(UserStatusLog userStatusLog, OnlineUser onlineUser);

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
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

}
