package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.PermissionMapper;
import com.clever.bean.system.Permission;
import com.clever.service.PermissionService;

import javax.annotation.Resource;

/**
 * 系统权限服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final static Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 分页查询系统权限列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param groupId    权限组id
     * @param name       权限名称
     * @param code       权限标识
     * @param type       权限类型-字典表
     * @return Page<Permission>
     */
    @Override
    public Page<Permission> selectPage(Integer pageNumber, Integer pageSize, String platformId, String groupId, String name, String code, String type) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(platformId)) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(groupId)) {
            queryWrapper.eq("group_id", groupId);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.eq("code", code);
        }
        if (StringUtils.isNotBlank(type)) {
            queryWrapper.eq("type", type);
        }
        return permissionMapper.selectPage(new Page<Permission>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据权限id获取系统权限
     *
     * @param id 权限id
     * @return Permission 系统权限信息
     */
    @Override
    public Permission selectById(String id) {
        return permissionMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Permission> 系统权限列表
     */
    @Override
    public List<Permission> selectListByPlatformId(String platformId) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("platform_id", platformId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据权限组id获取列表
     *
     * @param groupId 权限组id
     * @return List<Permission> 系统权限列表
     */
    @Override
    public List<Permission> selectListByGroupId(String groupId) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("group_id", groupId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Permission> 系统权限列表
     */
    @Override
    public List<Permission> selectListByCreator(String creator) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("creator", creator).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建系统权限
     *
     * @param permission 系统权限实体信息
     * @param onlineUser 当前登录用户
     * @return Permission 新建后的系统权限信息
     */
    @Override
    public Permission create(Permission permission, OnlineUser onlineUser) {
        permissionMapper.insert(permission);
        log.info("系统权限, 系统权限信息创建成功: userId={}, permissionId={}", onlineUser.getId(), permission.getId());
        return permission;
    }

    /**
     * 修改系统权限
     *
     * @param permission 系统权限实体信息
     * @param onlineUser 当前登录用户
     * @return Permission 修改后的系统权限信息
     */
    @Override
    public Permission update(Permission permission, OnlineUser onlineUser) {
        permissionMapper.updateById(permission);
        log.info("系统权限, 系统权限信息修改成功: userId={}, permissionId={}", onlineUser.getId(), permission.getId());
        return permission;
    }

    /**
     * 保存系统权限
     *
     * @param permission 系统权限实体信息
     * @param onlineUser 当前登录用户
     * @return Permission 保存后的系统权限信息
     */
    @Override
    public Permission save(Permission permission, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(permission.getId())) {
            return create(permission, onlineUser);
        }
        return update(permission, onlineUser);
    }

    /**
     * 根据权限id删除系统权限信息
     *
     * @param id         权限id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        permissionMapper.deleteById(id);
        log.info("系统权限, 系统权限信息删除成功: userId={}, permissionId={}", onlineUser.getId(), id);
    }

    /**
     * 根据权限id列表删除系统权限信息
     *
     * @param ids        权限id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        permissionMapper.deleteBatchIds(ids);
        log.info("系统权限, 系统权限信息批量删除成功: userId={}, count={}, permissionIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        permissionMapper.delete(new QueryWrapper<Permission>().eq("platform_id", platformId));
        log.info("系统权限, 系统权限信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据权限组id删除
     *
     * @param groupId    权限组id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByGroupId(String groupId, OnlineUser onlineUser) {
        permissionMapper.delete(new QueryWrapper<Permission>().eq("group_id", groupId));
        log.info("系统权限, 系统权限信息根据groupId删除成功: userId={}, groupId={}", onlineUser.getId(), groupId);
    }

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        permissionMapper.delete(new QueryWrapper<Permission>().eq("creator", creator));
        log.info("系统权限, 系统权限信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }
}
