package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import javax.validation.constraints.NotBlank;
/**
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
public class Village implements Serializable {

	@TableId
	private Long id;
	@NotBlank(message = "name不能为空")
	private String name;
	@NotBlank(message = "street_id不能为空")
	private Integer streetId;
	@NotBlank(message = "province_id不能为空")
	private Integer provinceId;
	@NotBlank(message = "city_id不能为空")
	private Integer cityId;
	@NotBlank(message = "area_id不能为空")
	private Integer areaId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

}