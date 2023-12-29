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
 * 系统权限
 *
 * @Author xixi
 * @Date 2023-12-29 17:14:55
 */
public class Permission implements Serializable {

    /**
     * 权限id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台id
     */
    @NotNull(message = "平台id不能为空")
    private Integer platformId;
    /**
     * 权限组id
     */
    @NotBlank(message = "权限组id不能为空")
    private String groupId;
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String name;
    /**
     * 权限标识
     */
    @NotBlank(message = "权限标识不能为空")
    private String code;
    /**
     * 权限描述
     */
    @NotBlank(message = "权限描述不能为空")
    private String description;
    /**
     * 权限类型-字典表
     */
    @NotBlank(message = "权限类型-字典表不能为空")
    private String type;
    /**
     * 是否启用:0-不启用,1-启用
     */
    private Integer enable;
    /**
     * 是否只能登录:0-否,1-是
     */
    private Integer ifOnlyLogin;
    /**
     * 资源路径
     */
    private String resourcePath;
    /**
     * 创建者id
     */
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
     * 权限id
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
     * 权限组id
     */
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    /**
     * 权限名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 权限标识
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 权限描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 权限类型-字典表
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
     * 是否只能登录:0-否,1-是
     */
    public Integer getIfOnlyLogin() {
        return ifOnlyLogin;
    }

    public void setIfOnlyLogin(Integer ifOnlyLogin) {
        this.ifOnlyLogin = ifOnlyLogin;
    }
    /**
     * 资源路径
     */
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
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