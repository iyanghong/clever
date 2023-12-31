package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.UserHistoryHeader;

/**
 * 用户历史头像表服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface UserHistoryHeaderService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param diskId     磁盘id
     * @return Page<UserHistoryHeader>
     */
    Page<UserHistoryHeader> selectPage(Integer pageNumber, Integer pageSize, String userId, String diskId);

    /**
     * 根据历史头像id获取用户历史头像表
     *
     * @param id 历史头像id
     * @return UserHistoryHeader 历史头像id信息
     */
    UserHistoryHeader selectById(String id);

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<UserHistoryHeader> 用户历史头像表列表
     */
    List<UserHistoryHeader> selectListByUserId(String userId);

    /**
     * 根据磁盘id获取列表
     *
     * @param diskId 磁盘id
     * @return List<UserHistoryHeader> 用户历史头像表列表
     */
    List<UserHistoryHeader> selectListByDiskId(String diskId);

    /**
     * 新建用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 新建后的用户历史头像表信息
     */
    UserHistoryHeader create(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser);

    /**
     * 修改用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 修改后的用户历史头像表信息
     */
    UserHistoryHeader update(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser);

    /**
     * 保存用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 保存后的用户历史头像表信息
     */
    UserHistoryHeader save(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser);

    /**
     * 根据历史头像id删除信息
     *
     * @param id         历史头像id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据历史头像id列表删除信息
     *
     * @param ids        历史头像id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据用户id删除
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

    /**
     * 根据磁盘id删除
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    void deleteByDiskId(String diskId, OnlineUser onlineUser);

}
