package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.RolePermissionRelMapper;
import com.clever.bean.system.RolePermissionRel;
import com.clever.service.RolePermissionRelService;

import javax.annotation.Resource;

/**
 * 角色-权限服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class RolePermissionRelServiceImpl implements RolePermissionRelService {

    private final static Logger log = LoggerFactory.getLogger(RolePermissionRelServiceImpl.class);

    @Resource
    private RolePermissionRelMapper rolePermissionRelMapper;

    /**
     * 分页查询角色-权限列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param roleId       角色
     * @param permissionId 权限
     * @return Page<RolePermissionRel>
     */
    @Override
    public Page<RolePermissionRel> selectPage(Integer pageNumber, Integer pageSize, String roleId, String permissionId) {
        QueryWrapper<RolePermissionRel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.eq("role_id", roleId);
        }
        if (StringUtils.isNotBlank(permissionId)) {
            queryWrapper.eq("permission_id", permissionId);
        }
        return rolePermissionRelMapper.selectPage(new Page<RolePermissionRel>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据角色权限中间表获取角色-权限
     *
     * @param id 角色权限中间表
     * @return RolePermissionRel 角色-权限信息
     */
    @Override
    public RolePermissionRel selectById(String id) {
        return rolePermissionRelMapper.selectById(id);
    }

    /**
     * 根据角色获取列表
     *
     * @param roleId 角色
     * @return List<RolePermissionRel> 角色-权限列表
     */
    @Override
    public List<RolePermissionRel> selectListByRoleId(String roleId) {
        return rolePermissionRelMapper.selectList(new QueryWrapper<RolePermissionRel>().eq("role_id", roleId).orderByAsc("id"));
    }

    /**
     * 根据权限获取列表
     *
     * @param permissionId 权限
     * @return List<RolePermissionRel> 角色-权限列表
     */
    @Override
    public List<RolePermissionRel> selectListByPermissionId(String permissionId) {
        return rolePermissionRelMapper.selectList(new QueryWrapper<RolePermissionRel>().eq("permission_id", permissionId).orderByAsc("id"));
    }

    /**
     * 新建角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 新建后的角色-权限信息
     */
    @Override
    public RolePermissionRel create(RolePermissionRel rolePermissionRel, OnlineUser onlineUser) {
        rolePermissionRelMapper.insert(rolePermissionRel);
        log.info("角色-权限, 角色-权限信息创建成功: userId={}, rolePermissionRelId={}", onlineUser.getId(), rolePermissionRel.getId());
        return rolePermissionRel;
    }

    /**
     * 修改角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 修改后的角色-权限信息
     */
    @Override
    public RolePermissionRel update(RolePermissionRel rolePermissionRel, OnlineUser onlineUser) {
        rolePermissionRelMapper.updateById(rolePermissionRel);
        log.info("角色-权限, 角色-权限信息修改成功: userId={}, rolePermissionRelId={}", onlineUser.getId(), rolePermissionRel.getId());
        return rolePermissionRel;
    }

    /**
     * 保存角色-权限
     *
     * @param rolePermissionRel 角色-权限实体信息
     * @param onlineUser        当前登录用户
     * @return RolePermissionRel 保存后的角色-权限信息
     */
    @Override
    public RolePermissionRel save(RolePermissionRel rolePermissionRel, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(rolePermissionRel.getId())) {
            return create(rolePermissionRel, onlineUser);
        }
        return update(rolePermissionRel, onlineUser);
    }

    /**
     * 根据角色权限中间表删除角色-权限信息
     *
     * @param id         角色权限中间表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        rolePermissionRelMapper.deleteById(id);
        log.info("角色-权限, 角色-权限信息删除成功: userId={}, rolePermissionRelId={}", onlineUser.getId(), id);
    }

    /**
     * 根据角色权限中间表列表删除角色-权限信息
     *
     * @param ids        角色权限中间表列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        rolePermissionRelMapper.deleteBatchIds(ids);
        log.info("角色-权限, 角色-权限信息批量删除成功: userId={}, count={}, rolePermissionRelIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据角色删除
     *
     * @param roleId     角色
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
        rolePermissionRelMapper.delete(new QueryWrapper<RolePermissionRel>().eq("role_id", roleId));
        log.info("角色-权限, 角色-权限信息根据roleId删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
    }

    /**
     * 根据权限删除
     *
     * @param permissionId 权限
     * @param onlineUser   当前登录用户
     */
    @Override
    public void deleteByPermissionId(String permissionId, OnlineUser onlineUser) {
        rolePermissionRelMapper.delete(new QueryWrapper<RolePermissionRel>().eq("permission_id", permissionId));
        log.info("角色-权限, 角色-权限信息根据permissionId删除成功: userId={}, permissionId={}", onlineUser.getId(), permissionId);
    }
}
