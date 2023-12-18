package com.clever.util.generate.config;

/**
 * GenerateConfig类用于配置数据库连接和包名。
 * @Author xixi
 * @Date 2023-12-18 11:37
 **/
public class GenerateConfig {
    // 数据库URL
    public final String DB_URL;
    // 数据库名称
    public String DB_DATABASE;
    // 数据库用户名
    public final String DB_USERNAME;
    // 数据库密码
    public final String DB_PASSWORD;

    // 实体类包名
    private String entityPackageName;

    // 映射类包名
    private String mapperPackageName;

    private String createUserColumndName = "creator";
    private String updateUserColumndName = "";

    /**
     * 构造函数，初始化数据库URL、用户名和密码。
     * 同时从数据库URL中解析出数据库名称。
     * @param DB_URL 数据库URL
     * @param DB_USERNAME 数据库用户名
     * @param DB_PASSWORD 数据库密码
     */
    public GenerateConfig(String DB_URL, String DB_USERNAME, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
        int lastSlashIndex = DB_URL.lastIndexOf('/');

        if (lastSlashIndex != -1 && lastSlashIndex < DB_URL.length() - 1) {
            this.DB_DATABASE = DB_URL.substring(lastSlashIndex + 1);
        }
    }

    /**
     * 获取实体类包名
     * @return 实体类包名
     */
    public String getEntityPackageName() {
        return entityPackageName;
    }

    /**
     * 设置实体类包名
     * @param entityPackageName 实体类包名
     */
    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    /**
     * 获取映射类包名
     * @return 映射类包名
     */
    public String getMapperPackageName() {
        return mapperPackageName;
    }

    /**
     * 设置映射类包名
     * @param mapperPackageName 映射类包名
     */
    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public String getCreateUserColumndName() {
        return createUserColumndName;
    }

    public void setCreateUserColumndName(String createUserColumndName) {
        this.createUserColumndName = createUserColumndName;
    }

    public String getUpdateUserColumndName() {
        return updateUserColumndName;
    }

    public void setUpdateUserColumndName(String updateUserColumndName) {
        this.updateUserColumndName = updateUserColumndName;
    }
}