package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import java.util.Date;
/**
 * 邮箱主体
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public class EmailSubject implements Serializable {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 平台id
	 */
	private Integer platformId;
	/**
	 * 邮箱驱动
	 */
	private String driver;
	/**
	 * host
	 */
	private String host;
	/**
	 * 端口
	 */
	private Integer port;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 加密
	 */
	private String encryption;
	/**
	 * 邮箱地址
	 */
	private String address;
	/**
	 * 发件人
	 */
	private String subjectName;
	/**
	 * 创建人
	 */
	@TableField(value = "creator", fill = FieldFill.INSERT)
	private String creator;
	/**
	 * 添加时间
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
	 * 邮箱驱动
	 */
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * host
	 */
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 端口
	 */
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * 账号
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 密码
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 加密
	 */
	public String getEncryption() {
		return encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	/**
	 * 邮箱地址
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 发件人
	 */
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * 创建人
	 */
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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