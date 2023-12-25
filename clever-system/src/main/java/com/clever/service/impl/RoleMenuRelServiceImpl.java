package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.RoleMenuRelMapper;
import com.clever.bean.system.RoleMenuRel;
import com.clever.service.RoleMenuRelService;

import javax.annotation.Resource;

/**
 * 角色菜单服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class RoleMenuRelServiceImpl implements RoleMenuRelService {

    private final static Logger log = LoggerFactory.getLogger(RoleMenuRelServiceImpl.class);

    @Resource
    private RoleMenuRelMapper roleMenuRelMapper;

    /**
     * 分页查询角色菜单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param menuId     菜单唯一标识
     * @param roleId     角色唯一标识
     * @return Page<RoleMenuRel>
     */
    @Override
    public Page<RoleMenuRel> selectPage(Integer pageNumber, Integer pageSize, String menuId, String roleId) {
        QueryWrapper<RoleMenuRel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menuId)) {
            queryWrapper.eq("menu_id", menuId);
        }
        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.eq("role_id", roleId);
        }
        return roleMenuRelMapper.selectPage(new Page<RoleMenuRel>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据编号获取角色菜单
     *
     * @param id 编号
     * @return RoleMenuRel 角色菜单信息
     */
    @Override
    public RoleMenuRel selectById(String id) {
        return roleMenuRelMapper.selectById(id);
    }

    /**
     * 根据菜单唯一标识获取列表
     *
     * @param menuId 菜单唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    @Override
    public List<RoleMenuRel> selectListByMenuId(String menuId) {
        return roleMenuRelMapper.selectList(new QueryWrapper<RoleMenuRel>().eq("menu_id", menuId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据角色唯一标识获取列表
     *
     * @param roleId 角色唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    @Override
    public List<RoleMenuRel> selectListByRoleId(String roleId) {
        return roleMenuRelMapper.selectList(new QueryWrapper<RoleMenuRel>().eq("role_id", roleId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 新建后的角色菜单信息
     */
    @Override
    public RoleMenuRel create(RoleMenuRel roleMenuRel, OnlineUser onlineUser) {
        roleMenuRelMapper.insert(roleMenuRel);
        log.info("角色菜单, 角色菜单信息创建成功: userId={}, roleMenuRelId={}", onlineUser.getId(), roleMenuRel.getId());
        return roleMenuRel;
    }

    /**
     * 修改角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 修改后的角色菜单信息
     */
    @Override
    public RoleMenuRel update(RoleMenuRel roleMenuRel, OnlineUser onlineUser) {
        roleMenuRelMapper.updateById(roleMenuRel);
        log.info("角色菜单, 角色菜单信息修改成功: userId={}, roleMenuRelId={}", onlineUser.getId(), roleMenuRel.getId());
        return roleMenuRel;
    }

    /**
     * 保存角色菜单
     *
     * @param roleMenuRel 角色菜单实体信息
     * @param onlineUser  当前登录用户
     * @return RoleMenuRel 保存后的角色菜单信息
     */
    @Override
    public RoleMenuRel save(RoleMenuRel roleMenuRel, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(roleMenuRel.getId())) {
            return create(roleMenuRel, onlineUser);
        }
        return update(roleMenuRel, onlineUser);
    }

    /**
     * 根据编号删除角色菜单信息
     *
     * @param id         编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        roleMenuRelMapper.deleteById(id);
        log.info("角色菜单, 角色菜单信息删除成功: userId={}, roleMenuRelId={}", onlineUser.getId(), id);
    }

    /**
     * 根据编号列表删除角色菜单信息
     *
     * @param ids        编号列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        roleMenuRelMapper.deleteBatchIds(ids);
        log.info("角色菜单, 角色菜单信息批量删除成功: userId={}, count={}, roleMenuRelIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据菜单唯一标识删除
     *
     * @param menuId     菜单唯一标识
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByMenuId(String menuId, OnlineUser onlineUser) {
        roleMenuRelMapper.delete(new QueryWrapper<RoleMenuRel>().eq("menu_id", menuId));
        log.info("角色菜单, 角色菜单信息根据menuId删除成功: userId={}, menuId={}", onlineUser.getId(), menuId);
    }

    /**
     * 根据角色唯一标识删除
     *
     * @param roleId     角色唯一标识
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
        roleMenuRelMapper.delete(new QueryWrapper<RoleMenuRel>().eq("role_id", roleId));
        log.info("角色菜单, 角色菜单信息根据roleId删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
    }
}
