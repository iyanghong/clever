package com.clever.bean.model;

import com.clever.bean.system.Platform;
import com.clever.bean.system.Role;
import com.clever.bean.system.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author xixi
 * @Date 2023-12-14 17:01
 **/
public class OnlineUser implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 账号
     */
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
     * 用户token
     */
    private String token;
    /**
     * 所有角色信息
     */
    private List<Role> roles;
    /**
     * 所有权限信息
     */
    private List<String> permissions;

    /**
     * 所有平台信息
     */
    private List<Platform> platforms;

    /**
     * ip归属
     */
    private IpAttribution ipAttribution;


    public OnlineUser() {
    }

    public OnlineUser(User user, String token, List<Role> roles, List<String> permissions, List<Platform> platforms, IpAttribution ipAttribution) {
        setUser(user);

        this.token = token;
        this.roles = roles;
        this.permissions = permissions;
        this.platforms = platforms;
        this.ipAttribution = ipAttribution;
    }

    public void setUser(User user){
        this.id = user.getId();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.nickname = user.getNickname();
        this.header = user.getHeader();
        this.diskId = user.getDiskId();
        this.status = user.getStatus();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.signature = user.getSignature();
        this.loginIp = user.getLoginIp();
        this.lastLoginTime = user.getLastLoginTime();
        this.passwordErrorNum = user.getPasswordErrorNum();
        this.onlineTime = user.getOnlineTime();
        this.address = user.getAddress();
        this.addressText = user.getAddressText();
        this.source = user.getSource();
        this.sourcePlatform = user.getSourcePlatform();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDiskId() {
        return diskId;
    }

    public void setDiskId(String diskId) {
        this.diskId = diskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getPasswordErrorNum() {
        return passwordErrorNum;
    }

    public void setPasswordErrorNum(Integer passwordErrorNum) {
        this.passwordErrorNum = passwordErrorNum;
    }

    public Integer getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Integer onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }
}