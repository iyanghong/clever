package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserRoleRelMapper;
import com.clever.bean.system.UserRoleRel;
import com.clever.service.UserRoleRelService;

import javax.annotation.Resource;

/**
 * 用户-角色服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class UserRoleRelServiceImpl implements UserRoleRelService {

    private final static Logger log = LoggerFactory.getLogger(UserRoleRelServiceImpl.class);

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    /**
     * 分页查询用户-角色列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户
     * @param roleId     角色
     * @return Page<UserRoleRel>
     */
    @Override
    public Page<UserRoleRel> selectPage(Integer pageNumber, Integer pageSize, String userId, String roleId) {
        QueryWrapper<UserRoleRel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.eq("role_id", roleId);
        }
        return userRoleRelMapper.selectPage(new Page<UserRoleRel>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据用户角色中间表获取用户-角色
     *
     * @param id 用户角色中间表
     * @return UserRoleRel 用户-角色信息
     */
    @Override
    public UserRoleRel selectById(String id) {
        return userRoleRelMapper.selectById(id);
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserRoleRel> 用户-角色列表
     */
    @Override
    public List<UserRoleRel> selectListByUserId(String userId) {
        return userRoleRelMapper.selectList(new QueryWrapper<UserRoleRel>().eq("user_id", userId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<UserRoleRel> 用户-角色列表
     */
    @Override
    public List<UserRoleRel> selectListByRoleId(String roleId) {
        return userRoleRelMapper.selectList(new QueryWrapper<UserRoleRel>().eq("role_id", roleId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 新建后的用户-角色信息
     */
    @Override
    public UserRoleRel create(UserRoleRel userRoleRel, OnlineUser onlineUser) {
        userRoleRelMapper.insert(userRoleRel);
        log.info("用户-角色, 用户-角色信息创建成功: userId={}, userRoleRelId={}", onlineUser.getId(), userRoleRel.getId());
        return userRoleRel;
    }

    /**
     * 修改用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 修改后的用户-角色信息
     */
    @Override
    public UserRoleRel update(UserRoleRel userRoleRel, OnlineUser onlineUser) {
        userRoleRelMapper.updateById(userRoleRel);
        log.info("用户-角色, 用户-角色信息修改成功: userId={}, userRoleRelId={}", onlineUser.getId(), userRoleRel.getId());
        return userRoleRel;
    }

    /**
     * 保存用户-角色
     *
     * @param userRoleRel 用户-角色实体信息
     * @param onlineUser  当前登录用户
     * @return UserRoleRel 保存后的用户-角色信息
     */
    @Override
    public UserRoleRel save(UserRoleRel userRoleRel, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(userRoleRel.getId())) {
            return create(userRoleRel, onlineUser);
        }
        return update(userRoleRel, onlineUser);
    }

    /**
     * 根据用户角色中间表删除用户-角色信息
     *
     * @param id         用户角色中间表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userRoleRelMapper.deleteById(id);
        log.info("用户-角色, 用户-角色信息删除成功: userId={}, userRoleRelId={}", onlineUser.getId(), id);
    }

    /**
     * 根据用户角色中间表列表删除用户-角色信息
     *
     * @param ids        用户角色中间表列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userRoleRelMapper.deleteBatchIds(ids);
        log.info("用户-角色, 用户-角色信息批量删除成功: userId={}, count={}, userRoleRelIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userRoleRelMapper.delete(new QueryWrapper<UserRoleRel>().eq("user_id", userId));
        log.info("用户-角色, 用户-角色信息根据userId删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 根据角色删除
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
        userRoleRelMapper.delete(new QueryWrapper<UserRoleRel>().eq("role_id", roleId));
        log.info("用户-角色, 用户-角色信息根据roleId删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
    }
}
