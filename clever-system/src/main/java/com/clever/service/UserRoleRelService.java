package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.UserRoleRel;

/**
 * 用户-角色服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface UserRoleRelService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户
     * @param roleId     角色
     * @return Page<UserRoleRel>
     */
    Page<UserRoleRel> selectPage(Integer pageNumber, Integer pageSize, String userId, String roleId);

    /**
     * 根据用户角色中间表获取用户-角色
     *
     * @param id 用户角色中间表
     * @return UserRoleRel 用户角色中间表信息
     */
    UserRoleRel selectById(String id);

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserRoleRel> 用户-角色列表
     */
    List<UserRoleRel> selectListByUserId(String userId);

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<UserRoleRel> 用户-角色列表
     */
    List<UserRoleRel> selectListByRoleId(String roleId);

    /**
     * 新建用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 新建后的用户-角色信息
     */
    UserRoleRel create(UserRoleRel userRoleRel, OnlineUser onlineUser);

    /**
     * 修改用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 修改后的用户-角色信息
     */
    UserRoleRel update(UserRoleRel userRoleRel, OnlineUser onlineUser);

    /**
     * 保存用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 保存后的用户-角色信息
     */
    UserRoleRel save(UserRoleRel userRoleRel, OnlineUser onlineUser);

    /**
     * 根据用户角色中间表删除信息
     *
     * @param id         用户角色中间表
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据用户角色中间表列表删除信息
     *
     * @param ids        用户角色中间表列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

    /**
     * 根据角色删除
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    void deleteByRoleId(String roleId, OnlineUser onlineUser);

}
