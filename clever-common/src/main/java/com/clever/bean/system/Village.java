package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 村庄
 *
 * @Author xixi
 * @Date 2023-12-25 17:35:27
 */
public class Village implements Serializable {

    @TableId
    private Long id;
    /**
     * 村庄名称
     */
    private String name;
    /**
     * 省份编号
     */
    private Integer provinceId;
    /**
     * 城市编号
     */
    private Integer cityId;
    /**
     * 区/县编号
     */
    private Integer areaId;
    /**
     * 街道编号
     */
    private Integer streetId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 村庄名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * 区/县编号
     */
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 街道编号
     */
    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }
}