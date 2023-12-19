package com.clever.bean.system;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
/**
 * VIEW
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public class AddressLevel3 implements Serializable {

	private Integer id;
	@NotBlank(message = "province_id不能为空")
	private Integer provinceId;
	@NotBlank(message = "province_name不能为空")
	private String provinceName;
	@NotBlank(message = "city_id不能为空")
	private Integer cityId;
	@NotBlank(message = "city_name不能为空")
	private String cityName;
	@NotBlank(message = "area_id不能为空")
	private Integer areaId;
	@NotBlank(message = "area_name不能为空")
	private String areaName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}