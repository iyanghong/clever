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
 * 系统权限组
 *
 * @Author xixi
 * @Date 2023-12-29 17:14:55
 */
public class PermissionGroup implements Serializable {

    /**
     * 权限组id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台id
     */
    @NotNull(message = "平台id不能为空")
    private Integer platformId;
    /**
     * 上级id
     */
    @NotBlank(message = "上级id不能为空")
    private String parentId;
    /**
     * 权限组名称
     */
    @NotBlank(message = "权限组名称不能为空")
    private String name;
    /**
     * 权限组标识
     */
    @NotBlank(message = "权限组标识不能为空")
    private String code;
    /**
     * 权限组描述
     */
    @NotBlank(message = "权限组描述不能为空")
    private String description;
    /**
     * 是否启用:0-不启用,1-启用
     */
    private Integer enable;
    /**
     * 创建者id
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;
    /**
     * 排序号
     */
    private Integer sortCode;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;



    /**
     * 权限组id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    /**
     * 平台id
     */
    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }
    /**
     * 上级id
     */
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * 权限组名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 权限组标识
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 权限组描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 是否启用:0-不启用,1-启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 是否启用:0-不启用,1-启用
     */
    public boolean ifEnable() {
    	return enable == 1;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
    /**
     * 创建者id
     */
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * 排序号
     */
    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
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