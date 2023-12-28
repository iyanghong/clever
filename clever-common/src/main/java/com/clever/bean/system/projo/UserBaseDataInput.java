package com.clever.bean.system.projo;

import java.util.Date;

/**
 * @Author xixi
 * @Date 2023-12-28 08:42
 **/
public class UserBaseDataInput {
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
}
