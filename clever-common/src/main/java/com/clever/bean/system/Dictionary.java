package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 字典
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
public class Dictionary implements Serializable {

    /**
     * 字典id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台id
     */
    @NotBlank(message = "平台id不能为空")
    private String platformId;
    /**
     * 字典类型id
     */
    @NotBlank(message = "字典类型id不能为空")
    private String typeId;
    /**
     * 上级字典id
     */
    private String parentId;
    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String name;
    /**
     * 字典标识
     */
    @NotBlank(message = "字典标识不能为空")
    private String code;
    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    private String value;
    /**
     * 字典描述
     */
    private String description;
    /**
     * 排序号
     */
    private Integer sort;
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
     * 字典id
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
     * 字典类型id
     */
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    /**
     * 上级字典id
     */
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * 字典名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 字典标识
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 字典值
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    /**
     * 字典描述
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