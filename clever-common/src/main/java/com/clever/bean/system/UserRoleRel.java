package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 用户-角色
 *
 * @Author xixi
 * @Date 2023-12-25 17:35:27
 */
public class UserRoleRel implements Serializable {

    /**
     * 用户角色中间表
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String userId;
    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空")
    private String roleId;
    /**
     * 授权时间
     */
    private Date createdAt;


    /**
     * 用户角色中间表
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 用户
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
     * 授权时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}