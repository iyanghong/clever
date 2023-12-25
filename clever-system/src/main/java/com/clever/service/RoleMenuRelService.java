package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.RoleMenuRel;

/**
 * 角色菜单服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface RoleMenuRelService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param menuId     菜单唯一标识
     * @param roleId     角色唯一标识
     * @return Page<RoleMenuRel>
     */
    Page<RoleMenuRel> selectPage(Integer pageNumber, Integer pageSize, String menuId, String roleId);

    /**
     * 根据编号获取角色菜单
     *
     * @param id 编号
     * @return RoleMenuRel 编号信息
     */
    RoleMenuRel selectById(String id);

    /**
     * 根据菜单唯一标识获取列表
     *
     * @param menuId 菜单唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    List<RoleMenuRel> selectListByMenuId(String menuId);

    /**
     * 根据角色唯一标识获取列表
     *
     * @param roleId 角色唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    List<RoleMenuRel> selectListByRoleId(String roleId);

    /**
     * 新建角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 新建后的角色菜单信息
     */
    RoleMenuRel create(RoleMenuRel roleMenuRel, OnlineUser onlineUser);

    /**
     * 修改角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 修改后的角色菜单信息
     */
    RoleMenuRel update(RoleMenuRel roleMenuRel, OnlineUser onlineUser);

    /**
     * 保存角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 保存后的角色菜单信息
     */
    RoleMenuRel save(RoleMenuRel roleMenuRel, OnlineUser onlineUser);

    /**
     * 根据编号删除信息
     *
     * @param id         编号
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据编号列表删除信息
     *
     * @param ids        编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据菜单唯一标识删除
     *
     * @param menuId     菜单唯一标识
     * @param onlineUser 当前登录用户
     */
    void deleteByMenuId(String menuId, OnlineUser onlineUser);

    /**
     * 根据角色唯一标识删除
     *
     * @param roleId     角色唯一标识
     * @param onlineUser 当前登录用户
     */
    void deleteByRoleId(String roleId, OnlineUser onlineUser);

}
