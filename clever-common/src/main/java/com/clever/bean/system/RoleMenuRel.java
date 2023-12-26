package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 角色菜单
 *
 * @Author xixi
 * @Date 2023-12-26 10:47:41
 */
public class RoleMenuRel implements Serializable {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 菜单唯一标识
     */
    @NotBlank(message = "菜单唯一标识不能为空")
    private String menuId;
    /**
     * 角色唯一标识
     */
    @NotBlank(message = "角色唯一标识不能为空")
    private String roleId;
    /**
     * 添加时间
     */
    private Date createdAt;


    /**
     * 编号
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 菜单唯一标识
     */
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 角色唯一标识
     */
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 添加时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}