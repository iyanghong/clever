package com.clever.config;

import com.clever.Constant;
import com.clever.service.SystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
    @Value("${spring.application.url}")
    private String appUrl;

    @Value("${spring.application.client}")
    private String client;

    @Value("${spring.application.key}")
    private String key;

    private final SystemConfigService systemConfigService;

    public SystemConfigInitRunner(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Constant.APP_URL = appUrl;
        Constant.CLIENT_URL = client;
        Constant.KEY = key;
        systemConfigService.initConfig();
        log.info("系统启动, 系统所需配置加载完成");
    }
}
