package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 平台
 *
 * @Author xixi
 * @Date 2023-12-26 11:13:55
 */
public class Platform implements Serializable {

    /**
     * 平台id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空")
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 平台管理人
     */
    @NotBlank(message = "平台管理人不能为空")
    private String master;
    /**
     * 邀请码
     */
    @NotBlank(message = "邀请码不能为空")
    private String code;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;


    /**
     * 平台id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 平台名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 平台管理人
     */
    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * 邀请码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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