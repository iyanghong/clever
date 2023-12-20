package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.RolePermissionMapper;
import com.clever.bean.system.RolePermission;
import com.clever.service.RolePermissionService;

import javax.annotation.Resource;

/**
 * 角色-权限服务
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final static Logger log = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 分页查询角色-权限列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param roleId       角色
     * @param permissionId 权限
     * @return Page<RolePermission>
     */
    @Override
    public Page<RolePermission> selectPage(Integer pageNumber, Integer pageSize, String roleId, String permissionId) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.eq("role_id", roleId);
        }
        if (StringUtils.isNotBlank(permissionId)) {
            queryWrapper.eq("permission_id", permissionId);
        }
        return rolePermissionMapper.selectPage(new Page<RolePermission>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据角色权限中间表获取角色-权限信息
     *
     * @param id 角色权限中间表
     * @return RolePermission 角色-权限信息
     */
    @Override
    public RolePermission selectById(String id) {
        return rolePermissionMapper.selectById(id);
    }

    /**
     * 根据角色获取角色-权限列表
     *
     * @param roleId 角色
     * @return List<RolePermission> 角色-权限列表
     */
    @Override
    public List<RolePermission> selectListByRoleId(String roleId) {
        return rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().eq("role_id", roleId).orderByAsc("id"));
    }

    /**
     * 根据权限获取角色-权限列表
     *
     * @param permissionId 权限
     * @return List<RolePermission> 角色-权限列表
     */
    @Override
    public List<RolePermission> selectListByPermissionId(String permissionId) {
        return rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().eq("permission_id", permissionId).orderByAsc("id"));
    }

    /**
     * 保存角色-权限信息
     *
     * @param rolePermission 角色-权限实体信息
     * @param onlineUser     当前登录用户
     */
    @Override
    public void save(RolePermission rolePermission, OnlineUser onlineUser) {
        if (StringUtils.isBlank(rolePermission.getId())) {
            rolePermissionMapper.insert(rolePermission);
            log.info("角色-权限, 角色-权限信息创建成功: userId={}, rolePermissionId={}", onlineUser.getId(), rolePermission.getId());
        } else {
            rolePermissionMapper.updateById(rolePermission);
            log.info("角色-权限, 角色-权限信息修改成功: userId={}, rolePermissionId={}", onlineUser.getId(), rolePermission.getId());
        }
    }

    /**
     * 根据角色权限中间表删除角色-权限信息
     *
     * @param id         角色权限中间表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        rolePermissionMapper.deleteById(id);
        log.info("角色-权限, 角色-权限信息删除成功: userId={}, rolePermissionId={}", onlineUser.getId(), id);
    }

    /**
     * 根据角色权限中间表列表删除角色-权限信息
     *
     * @param ids        角色权限中间表列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        rolePermissionMapper.deleteBatchIds(ids);
        log.info("角色-权限, 角色-权限信息批量删除成功: userId={}, count={}, rolePermissionIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据角色删除角色-权限
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        log.info("角色-权限, 角色-权限信息根据角色删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
    }

    /**
     * 根据权限删除角色-权限
     *
     * @param permissionId 权限
     * @param onlineUser   当前登录用户
     */
    @Override
    public void deleteByPermissionId(String permissionId, OnlineUser onlineUser) {
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("permission_id", permissionId));
        log.info("角色-权限, 角色-权限信息根据权限删除成功: userId={}, permissionId={}", onlineUser.getId(), permissionId);
    }
}
