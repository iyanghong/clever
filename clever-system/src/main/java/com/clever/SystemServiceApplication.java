package com.clever;

import com.clever.config.thread.ThreadPoolConfig;
import com.clever.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author xixi
 * @Date 2023-12-15 08:55
 **/
@EnableAsync
@SpringBootApplication
@MapperScan("com.clever.mapper")
@EnableConfigurationProperties({ThreadPoolConfig.class})
@EnableDiscoveryClient
@EnableRabbit
public class SystemServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SystemServiceApplication.class, args);
        SpringUtil.setApplicationContext(context);
        System.out.println("\n" +
                ":'######::'##:::::::'########:'##::::'##:'########:'########::\n" +
                "'##... ##: ##::::::: ##.....:: ##:::: ##: ##.....:: ##.... ##:\n" +
                " ##:::..:: ##::::::: ##::::::: ##:::: ##: ##::::::: ##:::: ##:\n" +
                " ##::::::: ##::::::: ######::: ##:::: ##: ######::: ########::\n" +
                " ##::::::: ##::::::: ##...::::. ##:: ##:: ##...:::: ##.. ##:::\n" +
                " ##::: ##: ##::::::: ##::::::::. ## ##::: ##::::::: ##::. ##::\n" +
                ". ######:: ########: ########:::. ###:::: ########: ##:::. ##:\n" +
                ":......:::........::........:::::...:::::........::..:::::..::\n");
    }
}
