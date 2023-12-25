package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 用户状态日志
 *
 * @Author xixi
 * @Date 2023-12-25 17:35:27
 */
public class UserStatusLog implements Serializable {

    /**
     * 自增id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String userId;
    /**
     * 当前状态
     */
    @NotNull(message = "当前状态不能为空")
    private Integer currentStatus;
    /**
     * 变更后状态
     */
    @NotNull(message = "变更后状态不能为空")
    private Integer changeStatus;
    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;
    /**
     * 状态结束时间
     */
    @NotNull(message = "状态结束时间不能为空")
    private Date duration;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;


    /**
     * 自增id
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
     * 当前状态
     */
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * 变更后状态
     */
    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 状态结束时间
     */
    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
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