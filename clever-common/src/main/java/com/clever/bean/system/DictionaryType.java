package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 字典类型
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
public class DictionaryType implements Serializable {

    /**
     * 字典类型id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台id
     */
    @NotBlank(message = "平台id不能为空")
    private String platformId;
    /**
     * 字典类型名称
     */
    @NotBlank(message = "字典类型名称不能为空")
    private String name;
    /**
     * 字典类型标识
     */
    @NotBlank(message = "字典类型标识不能为空")
    private String code;
    /**
     * 字典类型描述
     */
    private String description;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 是否树形结构:0-否:1-是
     */
    private Integer tree;
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
     * 字典类型id
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
    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
    /**
     * 字典类型名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 字典类型标识
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 字典类型描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 排序号
     */
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    /**
     * 是否树形结构:0-否:1-是
     */
    public Integer getTree() {
        return tree;
    }

    public void setTree(Integer tree) {
        this.tree = tree;
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