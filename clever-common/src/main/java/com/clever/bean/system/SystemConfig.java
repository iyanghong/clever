package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
/**
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
public class SystemConfig implements Serializable {

	/**
	 * 配置id
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 平台ID
	 */
	private Integer platformId;
	/**
	 * 系统配置名
	 */
	private String name;
	/**
	 * 缓存key
	 */
	private String code;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 类型
0-字符串
1-数组
2-json对象
3-数字
4-布尔值
5-加密
	 */
	private Integer type;
	/**
	 * 配置项
	 */
	private String options;
	/**
	 * 是否启用此配置
0-不启用
1-启用
	 */
	private Integer enable;
	/**
	 * 配置介绍
	 */
	private String description;
	/**
	 * 创建者
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 修改时间
	 */
	private Date updatedAt;

	/**
	 * 配置id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 平台ID
	 */
	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	/**
	 * 系统配置名
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 缓存key
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 值
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 类型
0-字符串
1-数组
2-json对象
3-数字
4-布尔值
5-加密
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 配置项
	 */
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	/**
	 * 是否启用此配置
0-不启用
1-启用
	 */
	public Integer getEnable() {
		return enable;
	}

	public boolean isEnable() {
		return enable == 1;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * 配置介绍
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建者
	 */
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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