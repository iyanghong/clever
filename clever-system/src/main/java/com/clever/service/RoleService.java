package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Role;

/**
 * 系统角色服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface RoleService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       角色名
     * @param platformId 平台id
     * @return Page<Role>
     */
    Page<Role> selectPage(Integer pageNumber, Integer pageSize, String name, Integer platformId);

    /**
     * 根据角色id获取系统角色
     *
     * @param id 角色id
     * @return Role 角色id信息
     */
    Role selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Role> 系统角色列表
     */
    List<Role> selectListByPlatformId(Integer platformId);

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Role> 系统角色列表
     */
    List<Role> selectListByCreator(String creator);

    /**
     * 新建系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 新建后的系统角色信息
     */
    Role create(Role role, OnlineUser onlineUser);

    /**
     * 修改系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 修改后的系统角色信息
     */
    Role update(Role role, OnlineUser onlineUser);

    /**
     * 保存系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 保存后的系统角色信息
     */
    Role save(Role role, OnlineUser onlineUser);

    /**
     * 根据角色id删除信息
     *
     * @param id         角色id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据角色id列表删除信息
     *
     * @param ids        角色id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(Integer platformId, OnlineUser onlineUser);

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

}
