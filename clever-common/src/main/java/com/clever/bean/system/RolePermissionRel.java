package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 角色-权限
 *
 * @Author xixi
 * @Date 2023-12-26 10:47:41
 */
public class RolePermissionRel implements Serializable {

    /**
     * 角色权限中间表
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空")
    private String roleId;
    /**
     * 权限
     */
    @NotBlank(message = "权限不能为空")
    private String permissionId;
    /**
     * 授权时间
     */
    private Date createdAt;


    /**
     * 角色权限中间表
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 角色
     */
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 权限
     */
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 授权时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}