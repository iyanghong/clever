package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.RoleMenu;

/**
 * 角色菜单服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
public interface RoleMenuService {

    /**
     * 分页查询角色菜单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param menuId     菜单唯一标识
     * @param roleId     角色唯一标识
     * @return Page<RoleMenu>
     */
    Page<RoleMenu> selectPage(Integer pageNumber, Integer pageSize, String menuId, String roleId);

    /**
     * 根据编号获取角色菜单信息
     *
     * @param id 编号
     * @return RoleMenu 角色菜单信息
     */
    RoleMenu selectById(String id);

    /**
     * 根据菜单唯一标识获取角色菜单列表
     *
     * @param menuId 菜单唯一标识
     * @return List<RoleMenu> 角色菜单列表
     */
    List<RoleMenu> selectListByMenuId(String menuId);

    /**
     * 根据角色唯一标识获取角色菜单列表
     *
     * @param roleId 角色唯一标识
     * @return List<RoleMenu> 角色菜单列表
     */
    List<RoleMenu> selectListByRoleId(String roleId);

    /**
     * 保存角色菜单信息
     *
     * @param roleMenu   角色菜单实体信息
     * @param onlineUser 当前登录用户
     */
    void save(RoleMenu roleMenu, OnlineUser onlineUser);

    /**
     * 根据编号删除角色菜单信息
     *
     * @param id         编号
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据编号列表删除角色菜单信息
     *
     * @param ids        编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据菜单唯一标识删除角色菜单
     *
     * @param menuId     菜单唯一标识
     * @param onlineUser 当前登录用户
     */
    void deleteByMenuId(String menuId, OnlineUser onlineUser);

    /**
     * 根据角色唯一标识删除角色菜单
     *
     * @param roleId     角色唯一标识
     * @param onlineUser 当前登录用户
     */
    void deleteByRoleId(String roleId, OnlineUser onlineUser);
}
