package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Platform;

/**
 * 平台服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
public interface PlatformService {

    /**
     * 分页查询平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       平台名称
     * @return Page<Platform>
     */
    Page<Platform> selectPage(Integer pageNumber, Integer pageSize, String name);

    /**
     * 根据平台id获取平台信息
     *
     * @param id 平台id
     * @return List<Platform> 平台信息
     */
    Platform selectById(Integer id);

    /**
     * 保存平台信息
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     */
    void save(Platform platform, OnlineUser onlineUser);

    /**
     * 根据平台id获取平台列表
     *
     * @param id         平台id
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据平台id列表删除平台信息
     *
     * @param ids        平台id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);
}
