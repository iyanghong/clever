package com.clever;

/**
 * @Author xixi
 * @Date 2023-12-18 11:06
 **/
public class SystemConfigConstant {
    /**
     * 用户登陆最大密码连续错误次数
     */
    public static final String USER_LOGIN_MAX_ERROR_COUNT = "sys:config:user:passwordMaxErrorNum";
    /**
     * 用户默认密码
     */
    public static final String USER_DEFAULT_PASSWORD = "sys:config:user:defaultPassword";
    /**
     * 用户默认登录过期时间
     */
    public static final String USER_DEFAULT_LOGIN_EXPIRE_TIME = "sys:config:user:defaultLoginExpireTime";
    /**
     * 用户默认激活邮箱模板code
     */
    public static final String DEFAULT_ACTIVATION_EMAIL_TEMPLATE_CODE = "sys:config:user:activeEmailTemplate";

    /**
     * 用户默认角色
     */
    public static final String USER_DEFAULT_ROLE = "sys:config:user:defaultRole";

    /**
     * 用户默认头像
     */
    public static final String USER_DEFAULT_AVATAR = "sys:config:user:defaultAvatar";
    /**
     * 是否开启邮箱注册
     */
    public static final String USER_REGISTER_EMAIL_ENABLE = "sys:config:user:registerEmailEnable";
    /**
     * 是否开启手机注册
     */
    public static final String USER_REGISTER_PHONE_ENABLE = "sys:config:user:registerPhoneEnable";

}
