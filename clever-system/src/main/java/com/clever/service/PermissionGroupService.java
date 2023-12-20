package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.PermissionGroup;

/**
 * 系统权限组服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 09:33:24
 */
public interface PermissionGroupService {

    /**
     * 分页查询系统权限组列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param parentId   上级id
     * @param name       权限组名称
     * @param sortCode   排序号
     * @return Page<PermissionGroup>
     */
    Page<PermissionGroup> selectPage(Integer pageNumber, Integer pageSize, String platformId, String parentId, String name, String sortCode);

    /**
     * 根据权限组id获取系统权限组信息
     *
     * @param id 权限组id
     * @return List<PermissionGroup> 系统权限组信息
     */
    PermissionGroup selectById(String id);

    /**
     * 根据平台id获取系统权限组列表
     *
     * @param platformId 平台id
     * @return List<PermissionGroup> 系统权限组列表
     */
    List<PermissionGroup> selectListByPlatformId(String platformId);

    /**
     * 根据上级id获取系统权限组列表
     *
     * @param parentId 上级id
     * @return List<PermissionGroup> 系统权限组列表
     */
    List<PermissionGroup> selectListByParentId(String parentId);

    /**
     * 保存系统权限组信息
     *
     * @param permissionGroup 系统权限组实体信息
     * @param onlineUser      当前登录用户
     */
    void save(PermissionGroup permissionGroup, OnlineUser onlineUser);

    /**
     * 根据权限组id获取系统权限组列表
     *
     * @param id         权限组id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据权限组id列表删除系统权限组信息
     *
     * @param ids        权限组id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台id删除系统权限组
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(String platformId, OnlineUser onlineUser);

    /**
     * 根据上级id删除系统权限组
     *
     * @param parentId   上级id
     * @param onlineUser 当前登录用户
     */
    void deleteByParentId(String parentId, OnlineUser onlineUser);
}
