package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * 街道
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
public class Street implements Serializable {

	/**
	 * 街道编号
	 */
	@TableId
	private Integer id;
	/**
	 * 街道名称
	 */
	private String name;
	/**
	 * 地区编号
	 */
	private Integer areaId;
	/**
	 * 城市编号
	 */
	private Integer cityId;
	/**
	 * 省份编号
	 */
	private Integer provinceId;

	/**
	 * 街道编号
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 街道名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 地区编号
	 */
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * 城市编号
	 */
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 省份编号
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}