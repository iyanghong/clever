package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 导航菜单
 *
 * @Author xixi
 * @Date 2023-12-25 17:35:27
 */
public class Menu implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台ID
     */
    @NotNull(message = "平台ID不能为空")
    private Integer platformId;
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;
    /**
     * 菜单Code
     */
    private String code;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单路径
     */
    private String view;
    /**
     * 打开方式,1-当前标签,2-新标签
     */
    private Integer target;
    /**
     * 上级菜单
     */
    private String parent;
    /**
     * 权重
     */
    private Integer sort;
    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String creator;
    /**
     * 添加时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;


    /**
     * id
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
     * 菜单名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 菜单Code
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 菜单图标
     */
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 菜单路径
     */
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    /**
     * 打开方式,1-当前标签,2-新标签
     */
    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    /**
     * 上级菜单
     */
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * 权重
     */
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建人
     */
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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