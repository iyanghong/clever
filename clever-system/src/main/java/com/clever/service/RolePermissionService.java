package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.RolePermission;

/**
 * 角色-权限服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
public interface RolePermissionService {

    /**
     * 分页查询角色-权限列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param roleId       角色
     * @param permissionId 权限
     * @return Page<RolePermission>
     */
    Page<RolePermission> selectPage(Integer pageNumber, Integer pageSize, String roleId, String permissionId);

    /**
     * 根据角色权限中间表获取角色-权限信息
     *
     * @param id 角色权限中间表
     * @return RolePermission 角色-权限信息
     */
    RolePermission selectById(String id);

    /**
     * 根据角色获取角色-权限列表
     *
     * @param roleId 角色
     * @return List<RolePermission> 角色-权限列表
     */
    List<RolePermission> selectListByRoleId(String roleId);

    /**
     * 根据权限获取角色-权限列表
     *
     * @param permissionId 权限
     * @return List<RolePermission> 角色-权限列表
     */
    List<RolePermission> selectListByPermissionId(String permissionId);

    /**
     * 保存角色-权限信息
     *
     * @param rolePermission 角色-权限实体信息
     * @param onlineUser     当前登录用户
     */
    void save(RolePermission rolePermission, OnlineUser onlineUser);

    /**
     * 根据角色权限中间表删除角色-权限信息
     *
     * @param id         角色权限中间表
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据角色权限中间表列表删除角色-权限信息
     *
     * @param ids        角色权限中间表列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据角色删除角色-权限
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    void deleteByRoleId(String roleId, OnlineUser onlineUser);

    /**
     * 根据权限删除角色-权限
     *
     * @param permissionId 权限
     * @param onlineUser   当前登录用户
     */
    void deleteByPermissionId(String permissionId, OnlineUser onlineUser);
}
