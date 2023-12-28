package com.clever.bean.system.projo;

import com.clever.bean.system.User;
import com.clever.util.StringEncryptUtil;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @Author xixi
 * @Date 2023-12-28 16:08
 **/
public class UserRegisterInput {

    @NotNull(message = "平台id不能为空")
    private Integer platformId;
    private String templateCode;
    private Map<String, String> variables;
    @NotNull(message = "账号不能为空")
    private String account;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")

    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别:0-未知,1-男,2-女
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
     * 用户所在城市:默认是未知
     */
    private Integer address;
    /**
     * 注册来源
     */
    private String source;

    /**
     * 赋值用户信息
     */
    public void assignUser(User user) {
        user.setAccount(this.account);
        user.setEmail(this.email);
        user.setPassword(StringEncryptUtil.sha256Encrypt(this.password));
        user.setNickname(this.nickname);
        user.setGender(this.gender);
        user.setBirthday(this.birthday);
        user.setSignature(this.signature);
        user.setAddress(this.address);
        user.setSource(this.source);
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
