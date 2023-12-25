package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 城区地址
 *
 * @Author xixi
 * @Date 2023-12-25 17:35:27
 */
public class Area implements Serializable {

    /**
     * 地区编号
     */
    @TableId
    private Integer id;
    /**
     * 地区名称
     */
    @NotBlank(message = "地区名称不能为空")
    private String name;
    /**
     * 城市编号
     */
    @NotNull(message = "城市编号不能为空")
    private Integer cityId;
    /**
     * 省份编号
     */
    @NotNull(message = "省份编号不能为空")
    private Integer provinceId;


    /**
     * 地区编号
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 地区名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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