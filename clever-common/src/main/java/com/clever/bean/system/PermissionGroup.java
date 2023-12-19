package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
/**
 * 系统权限组
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
public class PermissionGroup implements Serializable {

	/**
	 * 权限组id
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 平台id
	 */
	private String platformId;
	/**
	 * 上级id
	 */
	private String parentId;
	/**
	 * 权限组名称
	 */
	private String name;
	/**
	 * 权限组描述
	 */
	private String description;
	/**
	 * 创建者id
	 */
	private String creator;
	/**
	 * 排序号
	 */
	private Integer sortCode;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 修改时间
	 */
	private Date updatedAt;

	/**
	 * 权限组id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 平台id
	 */
	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	/**
	 * 上级id
	 */
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 权限组名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 权限组描述
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建者id
	 */
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 排序号
	 */
	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 * 创建时间
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 修改时间
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}