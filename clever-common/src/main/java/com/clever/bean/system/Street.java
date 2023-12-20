package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

/**
 * 街道
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
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
    @NotBlank(message = "街道名称不能为空")
    private String name;
    /**
     * 地区编号
     */
    @NotBlank(message = "地区编号不能为空")
    private Integer areaId;
    /**
     * 城市编号
     */
    @NotBlank(message = "城市编号不能为空")
    private Integer cityId;
    /**
     * 省份编号
     */
    @NotBlank(message = "省份编号不能为空")
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