package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
/**
 * 角色菜单
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
public class RoleMenu implements Serializable {

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 菜单唯一标识
	 */
	private String menuId;
	/**
	 * 角色唯一标识
	 */
	private String roleId;
	/**
	 * 添加时间
	 */
	private Date createdAt;

	/**
	 * 编号
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 菜单唯一标识
	 */
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 角色唯一标识
	 */
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 添加时间
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}