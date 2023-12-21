package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 用户
 *
 * @Author xixi
 * @Date 2023-12-21 02:46:26
 */
public class User implements Serializable {

    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String account;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 头像
     */
    private String header;
    /**
     * 磁盘id
     */
    private String diskId;
    /**
     * 账号状态：0-未激活,1-正常,2-密码冻结,3-违规,4-注销
     */
    private Integer status;
    /**
     * 性别：0-未知,1-男,2-女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 密码错误次数,最大为五
     */
    private Integer passwordErrorNum;
    /**
     * 登录时常,最大12
     */
    private Integer onlineTime;
    /**
     * 用户所在城市,默认是未知
     */
    private Integer address;
    /**
     * 用户地址中文
     */
    private String addressText;
    /**
     * 注册来源
     */
    private String source;
    /**
     * 来源平台
     */
    private Integer sourcePlatform;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 用户id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 账号
     */
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 手机
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 头像
     */
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 磁盘id
     */
    public String getDiskId() {
        return diskId;
    }

    public void setDiskId(String diskId) {
        this.diskId = diskId;
    }

    /**
     * 账号状态：0-未激活,1-正常,2-密码冻结,3-违规,4-注销
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 性别：0-未知,1-男,2-女
     */
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 个性签名
     */
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 最后登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 密码错误次数,最大为五
     */
    public Integer getPasswordErrorNum() {
        return passwordErrorNum;
    }

    public void setPasswordErrorNum(Integer passwordErrorNum) {
        this.passwordErrorNum = passwordErrorNum;
    }

    /**
     * 登录时常,最大12
     */
    public Integer getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Integer onlineTime) {
        this.onlineTime = onlineTime;
    }

    /**
     * 用户所在城市,默认是未知
     */
    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    /**
     * 用户地址中文
     */
    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    /**
     * 注册来源
     */
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 来源平台
     */
    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
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