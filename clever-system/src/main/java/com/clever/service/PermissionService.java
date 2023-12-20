package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Permission;
import com.clever.bean.system.Role;

/**
 * 系统权限服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 09:33:24
 */
public interface PermissionService {

    /**
     * 分页查询系统权限列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param groupId    权限组id
     * @param name       权限名称
     * @param code       权限标识
     * @param type       权限类型-字典表
     * @return Page<Permission>
     */
    Page<Permission> selectPage(Integer pageNumber, Integer pageSize, String platformId, String groupId, String name, String code, String type);

    /**
     * 根据权限id获取系统权限信息
     *
     * @param id 权限id
     * @return List<Permission> 系统权限信息
     */
    Permission selectById(String id);

    /**
     * 根据平台id获取系统权限列表
     *
     * @param platformId 平台id
     * @return List<Permission> 系统权限列表
     */
    List<Permission> selectListByPlatformId(String platformId);

    /**
     * 根据权限组id获取系统权限列表
     *
     * @param groupId 权限组id
     * @return List<Permission> 系统权限列表
     */
    List<Permission> selectListByGroupId(String groupId);

    /**
     * 保存系统权限信息
     *
     * @param permission 系统权限实体信息
     * @param onlineUser 当前登录用户
     */
    void save(Permission permission, OnlineUser onlineUser);

    /**
     * 根据权限id获取系统权限列表
     *
     * @param id         权限id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据权限id列表删除系统权限信息
     *
     * @param ids        权限id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台id删除系统权限
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(String platformId, OnlineUser onlineUser);

    /**
     * 根据权限组id删除系统权限
     *
     * @param groupId    权限组id
     * @param onlineUser 当前登录用户
     */
    void deleteByGroupId(String groupId, OnlineUser onlineUser);

    /**
     * 根据角色列表获取权限列表
     *
     * @param roles 角色列表
     * @return List<String> 权限列表
     */
    List<String> selectPermissionByRoles(List<Role> roles);
}
