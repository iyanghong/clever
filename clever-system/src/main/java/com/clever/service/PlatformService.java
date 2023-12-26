package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Platform;

/**
 * 平台服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface PlatformService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       平台名称
     * @param code       邀请码
     * @return Page<Platform>
     */
    Page<Platform> selectPage(Integer pageNumber, Integer pageSize, String name, String code);

    /**
     * 根据平台id获取平台
     *
     * @param id 平台id
     * @return Platform 平台id信息
     */
    Platform selectById(Integer id);

    /**
     * 根据邀请码获取信息
     *
     * @param code 邀请码
     * @return Platform 平台信息
     */
    Platform selectByCode(String code);

    /**
     * 新建平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 新建后的平台信息
     */
    Platform create(Platform platform, OnlineUser onlineUser);

    /**
     * 修改平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 修改后的平台信息
     */
    Platform update(Platform platform, OnlineUser onlineUser);

    /**
     * 保存平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 保存后的平台信息
     */
    Platform save(Platform platform, OnlineUser onlineUser);

    /**
     * 根据平台id删除信息
     *
     * @param id         平台id
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据平台id列表删除信息
     *
     * @param ids        平台id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);
    /**
     * 根据用户id查询平台列表
     *
     * @param userId 用户id
     * @return 平台列表
     */
    List<Platform> selectByUserId(String userId);
}
