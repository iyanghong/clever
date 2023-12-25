package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.RolePermissionRel;

/**
 * 角色-权限服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface RolePermissionRelService {

    /**
     * 分页查询列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param roleId       角色
     * @param permissionId 权限
     * @return Page<RolePermissionRel>
     */
    Page<RolePermissionRel> selectPage(Integer pageNumber, Integer pageSize, String roleId, String permissionId);

    /**
     * 根据角色权限中间表获取角色-权限
     *
     * @param id 角色权限中间表
     * @return RolePermissionRel 角色权限中间表信息
     */
    RolePermissionRel selectById(String id);

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<RolePermissionRel> 角色-权限列表
     */
    List<RolePermissionRel> selectListByRoleId(String roleId);

    /**
     * 根据权限获取列表
     *
     * @param permissionId 权限
     * @return List<RolePermissionRel> 角色-权限列表
     */
    List<RolePermissionRel> selectListByPermissionId(String permissionId);

    /**
     * 新建角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 新建后的角色-权限信息
     */
    RolePermissionRel create(RolePermissionRel rolePermissionRel, OnlineUser onlineUser);

    /**
     * 修改角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 修改后的角色-权限信息
     */
    RolePermissionRel update(RolePermissionRel rolePermissionRel, OnlineUser onlineUser);

    /**
     * 保存角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 保存后的角色-权限信息
     */
    RolePermissionRel save(RolePermissionRel rolePermissionRel, OnlineUser onlineUser);

    /**
     * 根据角色权限中间表删除信息
     *
     * @param id         角色权限中间表
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据角色权限中间表列表删除信息
     *
     * @param ids        角色权限中间表列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据角色删除
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    void deleteByRoleId(String roleId, OnlineUser onlineUser);

    /**
     * 根据权限删除
     *
     * @param permissionId 权限
     * @param onlineUser   当前登录用户
     */
    void deleteByPermissionId(String permissionId, OnlineUser onlineUser);

}
