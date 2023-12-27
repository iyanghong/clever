package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 用户登录日志
 *
 * @Author xixi
 * @Date 2023-12-27 10:57:55
 */
public class LogUserLogin implements Serializable {

    /**
     * 自增id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 平台id
     */
    @NotNull(message = "平台id不能为空")
    private Integer platformId;
    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String userId;
    /**
     * 登录设备
     */
    @NotBlank(message = "登录设备不能为空")
    private String userAgent;
    /**
     * 登录ip
     */
    @NotBlank(message = "登录ip不能为空")
    private String ip;
    /**
     * 登录国家
     */
    private String nation;
    /**
     * 登录省份
     */
    private String province;
    /**
     * 登录城市
     */
    private String city;
    /**
     * 登录地区
     */
    private String district;
    /**
     * 登录地址编码
     */
    private String addressCode;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 运营商
     */
    private String isp;
    /**
     * 登录时间
     */
    @NotNull(message = "登录时间不能为空")
    private Date loginTime;



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
     * 平台id
     */
    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
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
     * 登录设备
     */
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    /**
     * 登录ip
     */
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    /**
     * 登录国家
     */
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
    /**
     * 登录省份
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 登录城市
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 登录地区
     */
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    /**
     * 登录地址编码
     */
    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }
    /**
     * 经度
     */
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    /**
     * 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    /**
     * 运营商
     */
    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
    /**
     * 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}