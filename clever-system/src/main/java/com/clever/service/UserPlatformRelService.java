package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.UserPlatformRel;

/**
 * 用户-平台服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface UserPlatformRelService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param platformId 平台id
     * @return Page<UserPlatformRel>
     */
    Page<UserPlatformRel> selectPage(Integer pageNumber, Integer pageSize, String userId, Integer platformId);

    /**
     * 根据id获取用户-平台
     *
     * @param id id
     * @return UserPlatformRel id信息
     */
    UserPlatformRel selectById(String id);

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    List<UserPlatformRel> selectListByUserId(String userId);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    List<UserPlatformRel> selectListByPlatformId(Integer platformId);

    /**
     * 新建用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 新建后的用户-平台信息
     */
    UserPlatformRel create(UserPlatformRel userPlatformRel, OnlineUser onlineUser);

    /**
     * 修改用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 修改后的用户-平台信息
     */
    UserPlatformRel update(UserPlatformRel userPlatformRel, OnlineUser onlineUser);

    /**
     * 保存用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 保存后的用户-平台信息
     */
    UserPlatformRel save(UserPlatformRel userPlatformRel, OnlineUser onlineUser);

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
     * 根据用户id删除
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(Integer platformId, OnlineUser onlineUser);

}
