package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 省份
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
public class Province implements Serializable {

	/**
	 * 省份编号
	 */
	@TableId
	private Integer id;
	/**
	 * 省份名称
	 */
	private String name;

	/**
	 * 省份编号
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 省份名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}