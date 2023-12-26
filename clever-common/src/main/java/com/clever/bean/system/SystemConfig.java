package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 系统配置
 *
 * @Author xixi
 * @Date 2023-12-26 10:47:41
 */
public class SystemConfig implements Serializable {

    /**
     * 配置id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台ID
     */
    @NotNull(message = "平台ID不能为空")
    private Integer platformId;
    /**
     * 系统配置名
     */
    @NotBlank(message = "系统配置名不能为空")
    private String name;
    /**
     * 缓存key
     */
    @NotBlank(message = "缓存key不能为空")
    private String code;
    /**
     * 值
     */
    @NotBlank(message = "值不能为空")
    private String value;
    /**
     * 类型:0-字符串:1-数组:2-json对象:3-数字:4-布尔值:5-加密
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
    /**
     * 配置项
     */
    @NotBlank(message = "配置项不能为空")
    private String options;
    /**
     * 是否启用此配置:0-不启用:1-启用
     */
    private Integer enable;
    /**
     * 配置介绍
     */
    @NotBlank(message = "配置介绍不能为空")
    private String description;
    /**
     * 创建者
     */
    @NotBlank(message = "创建者不能为空")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;


    /**
     * 配置id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 平台ID
     */
    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 系统配置名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 缓存key
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 值
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 类型:0-字符串:1-数组:2-json对象:3-数字:4-布尔值:5-加密
     */
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 配置项
     */
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * 是否启用此配置:0-不启用:1-启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 是否启用此配置:0-不启用:1-启用
     */
    public boolean isEnable() {
        return enable == 1;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 配置介绍
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 创建者
     */
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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