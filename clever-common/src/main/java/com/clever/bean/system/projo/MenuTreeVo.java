package com.clever.bean.system.projo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.clever.bean.system.Menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author xixi
 * @Date 2024-01-12 15:36
 **/
public class MenuTreeVo {

    /**
     * id
     */
    private String id;
    /**
     * 平台ID
     */
    private Integer platformId;
    /**
     * 菜单名称
     */
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
     * 打开方式:1-当前标签:2-新标签
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
     * 子菜单
     */
    private List<MenuTreeVo> children;

    public MenuTreeVo() {
    }

    public MenuTreeVo(Menu menu) {
        this.id = menu.getId();
        this.platformId = menu.getPlatformId();
        this.name = menu.getName();
        this.code = menu.getCode();
        this.icon = menu.getIcon();
        this.view = menu.getView();
        this.target = menu.getTarget();
        this.parent = menu.getParent();
        this.sort = menu.getSort();
        this.creator = menu.getCreator();
        this.createdAt = menu.getCreatedAt();
        this.updatedAt = menu.getUpdatedAt();
    }

    public List<MenuTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeVo> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
