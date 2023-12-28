package com.clever.config;

import cn.hutool.json.JSONUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.context.ApplicationContext;


/**
 * 项目启动初始化权限
 * @Author xixi
 * @Date 2023-12-28 17:35
 **/
@Component
@Order(12)
public class AuthInitRunner implements ApplicationContextAware, CommandLineRunner {
    private final static Logger log = LoggerFactory.getLogger(AuthInitRunner.class);
    /**
     * 上下文
     */
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        //获取controller相关bean
        //获取使用RestController注解标注的的所有controller类
        Map<String, Object> authControllers = applicationContext.getBeansWithAnnotation(AuthGroup.class);
        //遍历每个controller层
        for (Map.Entry<String, Object> entry : authControllers.entrySet()) {
            Object value = entry.getValue();
            Class<?> aClass = AopUtils.getTargetClass(value);
            AuthGroup authGroup = aClass.getAnnotation(AuthGroup.class);
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                //获取方法上的注解
                Auth auth = method.getAnnotation(Auth.class);
                if (auth != null) {
                    addAuth(authGroup, auth);
                }
            }
        }
    }

    private void addAuth(AuthGroup authGroup, Auth auth) {
        log.info("拿到注解：group={}, value={}, name={}", authGroup.name(), auth.value(), auth.name());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
