package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
/**
 * 用户历史头像表
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public class UserHistoryHeader implements Serializable {

	/**
	 * 历史头像id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 头像
	 */
	private String header;
	/**
	 * 磁盘id
	 */
	private String diskId;
	/**
	 * 创建时间
	 */
	private Date createdAt;

	/**
	 * 历史头像id
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 用户id
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 头像
	 */
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * 磁盘id
	 */
	public String getDiskId() {
		return diskId;
	}

	public void setDiskId(String diskId) {
		this.diskId = diskId;
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

}