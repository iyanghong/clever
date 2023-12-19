package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.RoleMenuMapper;
import com.clever.bean.system.RoleMenu;
import com.clever.service.RoleMenuService;
import javax.annotation.Resource;

/**
 * 角色菜单服务
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	private final static Logger log = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

	@Resource
	private RoleMenuMapper roleMenuMapper;

	/**
	 * 分页查询角色菜单列表
	 *
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param menuId 菜单唯一标识
	 * @param roleId 角色唯一标识
	 * @return Page<RoleMenu>
	 */
	@Override
	public Page<RoleMenu> selectPage(Integer pageNumber, Integer pageSize, String menuId, String roleId) {
		QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(menuId)) {
			queryWrapper.eq("menu_id", menuId);
		}
		if (StringUtils.isNotBlank(roleId)) {
			queryWrapper.eq("role_id", roleId);
		}
		return roleMenuMapper.selectPage(new Page<RoleMenu>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据编号获取角色菜单信息
	 *
	 * @param id 编号
	 * @return List<RoleMenu> 角色菜单信息
	 */
	@Override
	public RoleMenu selectById(String id) {
		return roleMenuMapper.selectById(id);
	}

	/**
	 * 根据菜单唯一标识获取角色菜单列表
	 *
	 * @param menuId 菜单唯一标识
	 * @return List<RoleMenu> 角色菜单列表
	 */
	@Override
	public List<RoleMenu> getListByMenuId(String menuId) {
		return roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("menu_id", menuId).orderByAsc("id"));
	}

	/**
	 * 根据角色唯一标识获取角色菜单列表
	 *
	 * @param roleId 角色唯一标识
	 * @return List<RoleMenu> 角色菜单列表
	 */
	@Override
	public List<RoleMenu> getListByRoleId(String roleId) {
		return roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id", roleId).orderByAsc("id"));
	}

	/**
	 * 保存角色菜单信息
	 *
	 * @param roleMenu 角色菜单实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(RoleMenu roleMenu, OnlineUser onlineUser) {
		if (StringUtils.isBlank(roleMenu.getId())) {
			roleMenuMapper.insert(roleMenu);
			log.info("角色菜单, 角色菜单信息创建成功: userId={}, roleMenuId={}", onlineUser.getId(), roleMenu.getId());
		} else {
			roleMenuMapper.updateById(roleMenu);
			log.info("角色菜单, 角色菜单信息修改成功: userId={}, roleMenuId={}", onlineUser.getId(), roleMenu.getId());
		}
	}

	/**
	 * 根据编号获取角色菜单列表
	 *
	 * @param id 编号
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(String id, OnlineUser onlineUser) {
		roleMenuMapper.deleteById(id);
		log.info("角色菜单, 角色菜单信息删除成功: userId={}, roleMenuId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据编号列表删除角色菜单信息
	 *
	 * @param ids 编号列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
		roleMenuMapper.deleteBatchIds(ids);
		log.info("角色菜单, 角色菜单信息批量删除成功: userId={}, count={}, roleMenuIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据菜单唯一标识删除角色菜单
	 *
	 * @param menuId 菜单唯一标识
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByMenuId(String menuId, OnlineUser onlineUser) {
		roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("menu_id", menuId));
		log.info("角色菜单, 角色菜单信息根据菜单唯一标识删除成功: userId={}, menuId={}", onlineUser.getId(), menuId);
	}

	/**
	 * 根据角色唯一标识删除角色菜单
	 *
	 * @param roleId 角色唯一标识
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByRoleId(String roleId, OnlineUser onlineUser) {
		roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
		log.info("角色菜单, 角色菜单信息根据角色唯一标识删除成功: userId={}, roleId={}", onlineUser.getId(), roleId);
	}
}
