package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.LogUserLogin;
import com.clever.service.LogUserLoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录日志接口
 *
 * @Author xixi
 * @Date 2023-12-27 10:57:55
 */
@RestController
@Validated
@RequestMapping("/logUserLogin")
@AuthGroup(name = "用户登录日志模块", description = "用户登录日志模块权限组")
public class LogUserLoginController {

    @Resource
    private LogUserLoginService logUserLoginService;


    /**
     * 分页查询用户登录日志列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param userId 用户
     * @param addressCode 登录地址编码
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.logUserLogin.page", name = "用户登录日志分页", description = "用户登录日志分页接口")
    public Result<Page<LogUserLogin>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize,Integer platformId,String userId,String addressCode) {
        return new Result<>(logUserLoginService.selectPage(pageNumber, pageSize, platformId, userId, addressCode), "分页数据查询成功");
    }
    /**
    * 根据平台id获取列表
    *
    * @param platformId 平台id
    * @return List<LogUserLogin> 用户登录日志列表
    */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.logUserLogin.listByPlatformId", name = "根据平台id获取用户登录日志列表", description = "根据平台id获取用户登录日志列表接口")
    public List<LogUserLogin> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return logUserLoginService.selectListByPlatformId(platformId);
    }
    /**
    * 根据用户获取列表
    *
    * @param userId 用户
    * @return List<LogUserLogin> 用户登录日志列表
    */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.logUserLogin.listByUserId", name = "根据用户获取用户登录日志列表", description = "根据用户获取用户登录日志列表接口")
    public List<LogUserLogin> selectListByUserId(@PathVariable("userId") String userId) {
        return logUserLoginService.selectListByUserId(userId);
    }

    /**
    * 根据自增id获取用户登录日志信息
    *
    * @param id 自增id
    * @return 用户登录日志信息
    */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据自增id获取用户登录日志信息", description = "根据自增id获取用户登录日志信息接口")
    public Result<LogUserLogin> selectById(@PathVariable("id") String id) {
    return new Result<>(logUserLoginService.selectById(id), "查询成功");
    }
    /**
    * 创建用户登录日志信息
    *
    * @param logUserLogin 用户登录日志实体信息
    * @return 创建后的用户登录日志信息
    */
    @PostMapping("")
    @Auth(value = "clever-system.logUserLogin.create", name = "创建用户登录日志", description = "创建用户登录日志信息接口")
    public Result<LogUserLogin> create(@Validated LogUserLogin logUserLogin) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(logUserLoginService.create(logUserLogin, onlineUser), "创建成功");
    }
    /**
    * 修改用户登录日志信息
    *
    * @param logUserLogin 用户登录日志实体信息
    * @return 修改后的用户登录日志信息
    */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.logUserLogin.update", name = "修改用户登录日志", description = "修改用户登录日志信息接口")
    public Result<LogUserLogin> update(@Validated LogUserLogin logUserLogin, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        logUserLogin.setId(id);
        return new Result<>(logUserLoginService.update(logUserLogin, onlineUser), "修改成功");
    }

    /**
     * 保存用户登录日志信息
     *
     * @param logUserLogin 用户登录日志实体信息
     * @return 保存后的用户登录日志信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.logUserLogin.save", name = "保存用户登录日志", description = "保存用户登录日志信息接口")
    public Result<LogUserLogin> save(@Validated LogUserLogin logUserLogin) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(logUserLoginService.save(logUserLogin, onlineUser), "保存成功");
    }

    /**
     * 根据用户登录日志id删除用户登录日志信息
     *
     * @param id 自增id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.logUserLogin.delete", name = "删除用户登录日志", description = "删除用户登录日志信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        logUserLoginService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
