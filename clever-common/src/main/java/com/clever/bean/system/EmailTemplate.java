package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
/**
 * 邮箱模板
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
public class EmailTemplate implements Serializable {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 平台id
	 */
	private Integer platformId;
	/**
	 * 邮件模板名称
	 */
	private String name;
	/**
	 * 模板key
	 */
	private String code;
	/**
	 * 模板内容
	 */
	private String content;
	/**
	 * 默认占位值
	 */
	private String placeholder;
	/**
	 * 邮箱主体
	 */
	private String subjectId;
	/**
	 * 模板创建者
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
	 * id
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
	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	/**
	 * 邮件模板名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 模板key
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 模板内容
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 默认占位值
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	/**
	 * 邮箱主体
	 */
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * 模板创建者
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