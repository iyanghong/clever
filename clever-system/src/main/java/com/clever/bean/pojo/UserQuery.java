package com.clever.bean.pojo;

import java.io.Serializable;

/**
 * UserQuery类，实现Serializable接口，用于用户查询。
 *
 * @Author xixi
 * @Date 2023-12-18 10:09
 **/
public class UserQuery implements Serializable {
    // 用户账号
    private String account;
    // 用户昵称
    private String nickname;
    // 用户状态
    private Integer status;
    // 用户邮箱
    private String email;
    // 用户电话
    private String phone;
    // 用户平台
    private Integer platformId;

    /**
     * 获取用户账号
     *
     * @return 用户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户账号
     *
     * @param account 用户账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户昵称
     *
     * @return 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户状态
     *
     * @return 用户状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取用户邮箱
     *
     * @return 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户电话
     *
     * @return 用户电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户电话
     *
     * @param phone 用户电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户平台
     *
     * @return 用户平台
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置用户平台
     *
     * @param platform 用户平台
     */
    public void setPlatformId(Integer platform) {
        this.platformId = platform;
    }
}
