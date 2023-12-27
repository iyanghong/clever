package com.clever.config;

import com.clever.Constant;
import com.clever.interceptor.MybatisAfterUpdateInterceptor;
import com.clever.interceptor.MybatisPrintSqlInterceptor;
import com.clever.service.SystemConfigService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化系统配置和字典
 *
 * @Author xixi
 * @Date 2023-12-15 10:08
 **/
@Component
@Order(11)
public class SystemConfigInitRunner implements ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(SystemConfigInitRunner.class);
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.url}")
    private String appUrl;

    @Value("${spring.application.client}")
    private String client;

    @Value("${spring.application.key}")
    private String key;

    private final List<SqlSessionFactory> sqlSessionFactoryList;
    private final SystemConfigService systemConfigService;

    public SystemConfigInitRunner(SystemConfigService systemConfigService, List<SqlSessionFactory> sqlSessionFactoryList) {
        this.systemConfigService = systemConfigService;
        this.sqlSessionFactoryList = sqlSessionFactoryList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initMybatisInterceptor();
        Constant.APP_NAME = appName;
        Constant.APP_URL = appUrl;
        Constant.CLIENT_URL = client;
        Constant.KEY = key;
        systemConfigService.initConfig();
        log.info("系统启动, 系统所需配置加载完成");
    }

    private void initMybatisInterceptor() {
        log.debug("系统启动, 添加Mybatis拦截器");
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(new MybatisPrintSqlInterceptor());
            sqlSessionFactory.getConfiguration().addInterceptor(new MybatisAfterUpdateInterceptor());
        }
        log.debug("系统启动, 添加Mybatis拦截器完成");
    }
}
