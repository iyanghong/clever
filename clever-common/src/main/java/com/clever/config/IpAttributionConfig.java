package com.clever.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xixi
 * @Date 2023-12-27 10:47
 **/
@Configuration
@EnableCaching
public class IpAttributionConfig {
    @Value("${ip-attribution.app-code}")
    public String appCode;

}
