package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 城市
 *
 * @Author xixi
 * @Date 2023-12-26 11:13:55
 */
public class City implements Serializable {

    /**
     * 城市编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 城市名称
     */
    @NotBlank(message = "城市名称不能为空")
    private String name;
    /**
     * 省份编号
     */
    @NotNull(message = "省份编号不能为空")
    private Integer provinceId;


    /**
     * 城市编号
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 城市名称
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
}