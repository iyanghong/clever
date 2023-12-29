package com.clever.config;

import cn.hutool.json.JSONUtil;
import com.clever.Constant;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.system.Permission;
import com.clever.bean.system.PermissionGroup;
import com.clever.service.PermissionGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;


/**
 * 项目启动初始化权限
 *
 * @Author xixi
 * @Date 2023-12-28 17:35
 **/
@Component
@Order(12)
public class AuthInitRunner implements ApplicationContextAware, CommandLineRunner {
    private final static Logger log = LoggerFactory.getLogger(AuthInitRunner.class);

    @Resource
    private PermissionGroupService permissionGroupService;

    private final Integer platformId = -1;
    /**
     * 上下文
     */
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        //获取controller相关bean
        //获取使用RestController注解标注的的所有controller类
        Map<String, Object> authControllers = applicationContext.getBeansWithAnnotation(AuthGroup.class);
        if (authControllers.isEmpty()) {
            log.warn("没有找到使用@AuthGroup注解标注的类");
            return;
        }
        PermissionGroup baseGroup = initBaseGroup();
        List<AuthGroup> authGroupList = new ArrayList<>();
        //遍历每个controller层
        for (Map.Entry<String, Object> entry : authControllers.entrySet()) {
            Object value = entry.getValue();
            Class<?> aClass = AopUtils.getTargetClass(value);
            AuthGroup authGroup = aClass.getAnnotation(AuthGroup.class);
            authGroupList.add(authGroup);
            Method[] methods = aClass.getDeclaredMethods();

            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setPlatformId(platformId);
            permissionGroup.setParentId(baseGroup.getId());
            permissionGroup.setCode(authGroup.value());
            permissionGroup.setName(authGroup.name());
            permissionGroup.setDescription(authGroup.description());
            permissionGroup.setEnable(authGroup.enable() ? 1 : 0);
            permissionGroup.setCreator("system-generate");
            List<Permission> permissionList = getPermissions(methods);
            resolveRemoveGroup(platformId, permissionGroup, permissionList);
        }
        resolveRemoveGroup(authGroupList.stream().map(AuthGroup::name).collect(Collectors.toList()));
    }


    private static List<Permission> getPermissions(Method[] methods) {
        List<Permission> permissionList = new ArrayList<>();
        for (Method method : methods) {
            //获取方法上的注解
            Auth auth = method.getAnnotation(Auth.class);
            if (auth != null) {
                Permission permission = new Permission();
                permission.setCode(auth.value());
                permission.setName(auth.name());
                permission.setDescription(auth.description());
                permission.setType("API");
                permission.setEnable(auth.enable() ? 1 : 0);
                permission.setIfOnlyLogin(auth.isOnlyLogin() ? 1 : 0);
                permission.setCreator("system-generate");
                permissionList.add(permission);
            }
        }
        return permissionList;
    }

    private PermissionGroup initBaseGroup() {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setCode(Constant.APP_NAME);
        permissionGroup.setPlatformId(platformId);
        permissionGroup.setName("基础服务");
        permissionGroup.setDescription("用户、权限、配置等基础服务");
        permissionGroup.setEnable(1);
        permissionGroup.setParentId("-1");
        permissionGroup.setCreator("system-generate");
        return permissionGroupService.initPermissionGroup(permissionGroup);
    }

    private void resolveRemoveGroup(Integer platformId, PermissionGroup permissionGroup, List<Permission> permissionList) {
        log.info("解析权限组：name={}, value={}, permissionCount={}", permissionGroup.getName(), permissionGroup.getCode(), permissionList.size());
        permissionGroupService.resolvePermissionGroup(platformId, permissionGroup, permissionList);
    }

    private void resolveRemoveGroup(List<String> authGroupList) {
        if (authGroupList.isEmpty()) return;
        permissionGroupService.resolvePermissionGroup(platformId, authGroupList);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
