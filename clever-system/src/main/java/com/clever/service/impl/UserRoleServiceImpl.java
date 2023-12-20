package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserRoleMapper;
import com.clever.bean.system.UserRole;
import com.clever.service.UserRoleService;

import javax.annotation.Resource;

/**
 * 用户-角色服务
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final static Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 分页查询用户-角色列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户
     * @param roleId     角色
     * @return Page<UserRole>
     */
    @Override
    public Page<UserRole> selectPage(Integer pageNumber, Integer pageSize, String userId, String roleId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.eq("role_id", roleId);
        }
        return userRoleMapper.selectPage(new Page<UserRole>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据用户角色中间表获取用户-角色信息
     *
     * @param id 用户角色中间表
     * @return List<UserRole> 用户-角色信息
     */
    @Override
    public UserRole selectById(String id) {
        return userRoleMapper.selectById(id);
    }

    /**
     * 根据用户获取用户-角色列表
     *
     * @param userId 用户
     * @return List<UserRole> 用户-角色列表
     */
    @Override
    public List<UserRole> getListByUserId(String userId) {
        return userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId).orderByAsc("id"));
    }

    /**
     * 根据角色获取用户-角色列表
     *
     * @param roleId 角色
     * @return List<UserRole> 用户-角色列表
     */
    @Override
    public List<UserRole> getListByRoleId(String roleId) {
        return userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("role_id", roleId).orderByAsc("id"));
    }

    /**
     * 保存用户-角色信息
     *
     * @param userRole   用户-角色实体信息
     * @param onlineUser 当前登录用户
     */
    @Override
    public void save(UserRole userRole, OnlineUser onlineUser) {
        if (StringUtils.isBlank(userRole.getId())) {
            userRoleMapper.insert(userRole);
            log.info("用户-角色, 用户-角色信息创建成功: userId={}, userRoleId={}", onlineUser.getId(), userRole.getId());
        } else {
            userRoleMapper.updateById(userRole);
            log.info("用户-角色, 用户-角色信息修改成功: userId={}, userRoleId={}", onlineUser.getId(), userRole.getId());
        }
    }

    /**
     * 根据用户角色中间表获取用户-角色列表
     *
     * @param id         用户角色中间表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userRoleMapper.deleteById(id);
        log.info("用户-角色, 用户-角色信息删除成功: userId={}, userRoleId={}", onlineUser.getId(), id);
    }

    /**
     * 根据用户角色中间表列表删除用户-角色信息
     *
     * @param ids        用户角色中间表列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userRoleMapper.deleteBatchIds(ids);
        log.info("用户-角色, 用户-角色信息批量删除成功: userId={}, count={}, userRoleIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户删除用户-角色
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));
        log.info("用户-角色, 用户-角色信息根据用户删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 根据角色删除用户-角色
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("role_id", roleId));
        log.info("用户-角色, 用户-角色信息根据角色删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
    }
}
