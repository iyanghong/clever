package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 用户-平台
 *
 * @Author xixi
 * @Date 2023-12-26 10:47:41
 */
public class UserPlatformRel implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    private String userId;
    /**
     * 平台id
     */
    @NotNull(message = "平台id不能为空")
    private Integer platformId;
    /**
     * 加入平台时间
     */
    private Date createdAt;


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
     * 用户id
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
     * 加入平台时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}