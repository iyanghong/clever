package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.RoleMapper;
import com.clever.bean.system.Role;
import com.clever.service.RoleService;

import javax.annotation.Resource;

/**
 * 系统角色服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    /**
     * 分页查询系统角色列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       角色名
     * @param platformId 平台id
     * @return Page<Role>
     */
    @Override
    public Page<Role> selectPage(Integer pageNumber, Integer pageSize, String name, Integer platformId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        return roleMapper.selectPage(new Page<Role>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据角色id获取系统角色
     *
     * @param id 角色id
     * @return Role 系统角色信息
     */
    @Override
    public Role selectById(String id) {
        return roleMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Role> 系统角色列表
     */
    @Override
    public List<Role> selectListByPlatformId(Integer platformId) {
        return roleMapper.selectList(new QueryWrapper<Role>().eq("platform_id", platformId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Role> 系统角色列表
     */
    @Override
    public List<Role> selectListByCreator(String creator) {
        return roleMapper.selectList(new QueryWrapper<Role>().eq("creator", creator).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 新建后的系统角色信息
     */
    @Override
    public Role create(Role role, OnlineUser onlineUser) {
        roleMapper.insert(role);
        log.info("系统角色, 系统角色信息创建成功: userId={}, roleId={}", onlineUser.getId(), role.getId());
        return role;
    }

    /**
     * 修改系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 修改后的系统角色信息
     */
    @Override
    public Role update(Role role, OnlineUser onlineUser) {
        roleMapper.updateById(role);
        log.info("系统角色, 系统角色信息修改成功: userId={}, roleId={}", onlineUser.getId(), role.getId());
        return role;
    }

    /**
     * 保存系统角色
     *
     * @param role       系统角色实体信息
     * @param onlineUser 当前登录用户
     * @return Role 保存后的系统角色信息
     */
    @Override
    public Role save(Role role, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(role.getId())) {
            return create(role, onlineUser);
        }
        return update(role, onlineUser);
    }

    /**
     * 根据角色id删除系统角色信息
     *
     * @param id         角色id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        roleMapper.deleteById(id);
        log.info("系统角色, 系统角色信息删除成功: userId={}, roleId={}", onlineUser.getId(), id);
    }

    /**
     * 根据角色id列表删除系统角色信息
     *
     * @param ids        角色id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        roleMapper.deleteBatchIds(ids);
        log.info("系统角色, 系统角色信息批量删除成功: userId={}, count={}, roleIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        roleMapper.delete(new QueryWrapper<Role>().eq("platform_id", platformId));
        log.info("系统角色, 系统角色信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        roleMapper.delete(new QueryWrapper<Role>().eq("creator", creator));
        log.info("系统角色, 系统角色信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }
}
