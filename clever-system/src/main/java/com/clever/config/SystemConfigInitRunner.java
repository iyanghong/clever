package com.clever.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化系统配置和字典
 *
 * @Author xixi
 * @Date 2023-12-15 10:08
 **/
@Component
public class SystemConfigInitRunner implements ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(SystemConfigInitRunner.class);
    @Value("${spring.application.url}")
    private String appUrl;

    @Value("${spring.application.client}")
    private String client;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
