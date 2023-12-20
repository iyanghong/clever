package com.clever.enums;

/**
 * 系统配置类型
 *
 * @Author xixi
 * @Date 2023-12-20 10:58
 */
public enum SystemConfigType {

    /**
     * 字符串
     */
    STRING(0, "字符串"),

    /**
     * 数组
     */
    ARRAY(1, "数组"),

    /**
     * JSON
     */
    JSON(2, "JSON"),

    /**
     * 数字
     */
    NUMBER(3, "数字"),

    /**
     * 布尔值
     */
    BOOLEAN(4, "Boolean"),

    /**
     * 加密字符串
     */
    ENCRYPT_STRING(5, "加密字符串");

    public final Integer type;
    public final String info;

    SystemConfigType(Integer type, String info) {
        this.type = type;
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}
