package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;

import java.util.Date;

/**
 * 第三方平台账号
 *
 * @Author xixi
 * @Date 2023-12-26 11:13:55
 */
public class ThirdAccount implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 第三方平台:qq|weixin|dingtalk|sina|tiktok
     */
    @NotBlank(message = "第三方平台不能为空")
    private String type;
    /**
     * open_id
     */
    @NotBlank(message = "open_id不能为空")
    private String openId;
    /**
     * 第三方平台昵称
     */
    private String nickname;
    /**
     * 第三方平台头像
     */
    private String header;
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    private String userId;
    /**
     * 绑定时间
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
     * 第三方平台:qq|weixin|dingtalk|sina|tiktok
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * open_id
     */
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 第三方平台昵称
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 第三方平台头像
     */
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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
     * 绑定时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}