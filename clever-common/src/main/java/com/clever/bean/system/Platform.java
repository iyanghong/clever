package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 * 平台
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public class Platform implements Serializable {

	/**
	 * 平台id
	 */
	@TableId
	private Integer id;
	/**
	 * 平台名称
	 */
	private String name;
	/**
	 * 描述
	 */
	@NotBlank(message = "描述不能为空")
	private String description;
	/**
	 * 平台管理人
	 */
	private String master;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 修改时间
	 */
	private Date updatedAt;

	/**
	 * 平台id
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 平台名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 平台管理人
	 */
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
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