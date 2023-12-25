package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserStatusLog;
import com.clever.service.UserStatusLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户状态日志接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/userStatusLog")
@AuthGroup(name = "用户状态日志模块", description = "用户状态日志模块权限组")
public class UserStatusLogController {

    @Resource
    private UserStatusLogService userStatusLogService;


    /**
     * 分页查询用户状态日志列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.userStatusLog.page", name = "用户状态日志分页", description = "用户状态日志分页接口")
    public Result<Page<UserStatusLog>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, Integer currentStatus, Integer changeStatus) {
        return new Result<>(userStatusLogService.selectPage(pageNumber, pageSize, userId, currentStatus, changeStatus), "分页数据查询成功");
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserStatusLog> 用户状态日志列表
     */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.userStatusLog.listByUserId", name = "根据用户获取用户状态日志列表", description = "根据用户获取用户状态日志列表接口")
    public List<UserStatusLog> selectListByUserId(@PathVariable("userId") String userId) {
        return userStatusLogService.selectListByUserId(userId);
    }

    /**
     * 根据自增id获取用户状态日志信息
     *
     * @param id 自增id
     * @return 用户状态日志信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据自增id获取用户状态日志信息", description = "根据自增id获取用户状态日志信息接口")
    public Result<UserStatusLog> selectById(@PathVariable("id") String id) {
        return new Result<>(userStatusLogService.selectById(id), "查询成功");
    }

    /**
     * 创建用户状态日志信息
     *
     * @param userStatusLog 用户状态日志实体信息
     * @return 创建后的用户状态日志信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.userStatusLog.create", name = "创建用户状态日志", description = "创建用户状态日志信息接口")
    public Result<UserStatusLog> create(@Validated UserStatusLog userStatusLog) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userStatusLogService.create(userStatusLog, onlineUser), "创建成功");
    }

    /**
     * 修改用户状态日志信息
     *
     * @param userStatusLog 用户状态日志实体信息
     * @return 修改后的用户状态日志信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.userStatusLog.update", name = "修改用户状态日志", description = "修改用户状态日志信息接口")
    public Result<UserStatusLog> update(@Validated UserStatusLog userStatusLog, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userStatusLog.setId(id);
        return new Result<>(userStatusLogService.update(userStatusLog, onlineUser), "修改成功");
    }

    /**
     * 保存用户状态日志信息
     *
     * @param userStatusLog 用户状态日志实体信息
     * @return 保存后的用户状态日志信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userStatusLog.save", name = "保存用户状态日志", description = "保存用户状态日志信息接口")
    public Result<UserStatusLog> save(@Validated UserStatusLog userStatusLog) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userStatusLogService.save(userStatusLog, onlineUser), "保存成功");
    }

    /**
     * 根据用户状态日志id删除用户状态日志信息
     *
     * @param id 自增id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.userStatusLog.delete", name = "删除用户状态日志", description = "删除用户状态日志信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userStatusLogService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
