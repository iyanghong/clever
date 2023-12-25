package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.PermissionGroupMapper;
import com.clever.bean.system.PermissionGroup;
import com.clever.service.PermissionGroupService;

import javax.annotation.Resource;

/**
 * 系统权限组服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    private final static Logger log = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);

    @Resource
    private PermissionGroupMapper permissionGroupMapper;

    /**
     * 分页查询系统权限组列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param parentId   上级id
     * @param name       权限组名称
     * @param sortCode   排序号
     * @return Page<PermissionGroup>
     */
    @Override
    public Page<PermissionGroup> selectPage(Integer pageNumber, Integer pageSize, String platformId, String parentId, String name, Integer sortCode) {
        QueryWrapper<PermissionGroup> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(platformId)) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(parentId)) {
            queryWrapper.eq("parent_id", parentId);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (sortCode != null) {
            queryWrapper.eq("sort_code", sortCode);
        }
        return permissionGroupMapper.selectPage(new Page<PermissionGroup>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据权限组id获取系统权限组
     *
     * @param id 权限组id
     * @return PermissionGroup 系统权限组信息
     */
    @Override
    public PermissionGroup selectById(String id) {
        return permissionGroupMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<PermissionGroup> 系统权限组列表
     */
    @Override
    public List<PermissionGroup> selectListByPlatformId(String platformId) {
        return permissionGroupMapper.selectList(new QueryWrapper<PermissionGroup>().eq("platform_id", platformId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据上级id获取列表
     *
     * @param parentId 上级id
     * @return List<PermissionGroup> 系统权限组列表
     */
    @Override
    public List<PermissionGroup> selectListByParentId(String parentId) {
        return permissionGroupMapper.selectList(new QueryWrapper<PermissionGroup>().eq("parent_id", parentId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<PermissionGroup> 系统权限组列表
     */
    @Override
    public List<PermissionGroup> selectListByCreator(String creator) {
        return permissionGroupMapper.selectList(new QueryWrapper<PermissionGroup>().eq("creator", creator).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建系统权限组
     *
     * @param permissionGroup 系统权限组实体信息
     * @param onlineUser      当前登录用户
     * @return PermissionGroup 新建后的系统权限组信息
     */
    @Override
    public PermissionGroup create(PermissionGroup permissionGroup, OnlineUser onlineUser) {
        permissionGroupMapper.insert(permissionGroup);
        log.info("系统权限组, 系统权限组信息创建成功: userId={}, permissionGroupId={}", onlineUser.getId(), permissionGroup.getId());
        return permissionGroup;
    }

    /**
     * 修改系统权限组
     *
     * @param permissionGroup 系统权限组实体信息
     * @param onlineUser      当前登录用户
     * @return PermissionGroup 修改后的系统权限组信息
     */
    @Override
    public PermissionGroup update(PermissionGroup permissionGroup, OnlineUser onlineUser) {
        permissionGroupMapper.updateById(permissionGroup);
        log.info("系统权限组, 系统权限组信息修改成功: userId={}, permissionGroupId={}", onlineUser.getId(), permissionGroup.getId());
        return permissionGroup;
    }

    /**
     * 保存系统权限组
     *
     * @param permissionGroup 系统权限组实体信息
     * @param onlineUser      当前登录用户
     * @return PermissionGroup 保存后的系统权限组信息
     */
    @Override
    public PermissionGroup save(PermissionGroup permissionGroup, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(permissionGroup.getId())) {
            return create(permissionGroup, onlineUser);
        }
        return update(permissionGroup, onlineUser);
    }

    /**
     * 根据权限组id删除系统权限组信息
     *
     * @param id         权限组id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        permissionGroupMapper.deleteById(id);
        log.info("系统权限组, 系统权限组信息删除成功: userId={}, permissionGroupId={}", onlineUser.getId(), id);
    }

    /**
     * 根据权限组id列表删除系统权限组信息
     *
     * @param ids        权限组id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        permissionGroupMapper.deleteBatchIds(ids);
        log.info("系统权限组, 系统权限组信息批量删除成功: userId={}, count={}, permissionGroupIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        permissionGroupMapper.delete(new QueryWrapper<PermissionGroup>().eq("platform_id", platformId));
        log.info("系统权限组, 系统权限组信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据上级id删除
     *
     * @param parentId   上级id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByParentId(String parentId, OnlineUser onlineUser) {
        permissionGroupMapper.delete(new QueryWrapper<PermissionGroup>().eq("parent_id", parentId));
        log.info("系统权限组, 系统权限组信息根据parentId删除成功: userId={}, parentId={}", onlineUser.getId(), parentId);
    }

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        permissionGroupMapper.delete(new QueryWrapper<PermissionGroup>().eq("creator", creator));
        log.info("系统权限组, 系统权限组信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }
}
