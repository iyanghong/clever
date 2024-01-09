package com.clever.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author xixi
 * @Date 2024-01-09 09:59
 **/
@Configuration
@Order(10)
public class RunInfoRunner implements ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(RunInfoRunner.class);
    @Value("${spring.profiles.active}")
    private String profilesActive;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServiceAddr;
    @Value("${spring.datasource.druid.master.url}")
    private String mysqlUrl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动加载配置文件类型：{}", profilesActive);
        log.info("Nacos地址：{}", nacosServiceAddr);
        log.info("Mysql主库地址：{}", mysqlUrl);
    }
}
