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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * UserStatusLog接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/UserStatusLog")
@AuthGroup(name = "user_status_log模块", description = "user_status_log模块权限组")
public class UserStatusLogController {

    @Resource
    private UserStatusLogService userStatusLogService;


    /**
     * 分页查询列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.userStatusLog.page", name = "user_status_log分页", description = "user_status_log分页接口")
    public Result<Page<UserStatusLog>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, Integer currentStatus, Integer changeStatus) {
        return new Result<>(userStatusLogService.selectPage(pageNumber, pageSize, userId, currentStatus, changeStatus), "分页数据查询成功");
    }

    /**
     * 根据自增id获取user_status_log信息
     *
     * @param id 自增id
     * @return user_status_log信息
     */
    @GetMapping("/get/{id}")
    public Result<UserStatusLog> selectById(@PathVariable("id") String id) {
        return new Result<>(userStatusLogService.selectById(id), "查询成功");
    }

    /**
     * 根据用户获取user_status_log列表
     *
     * @param userId 用户
     * @return user_status_log列表
     */
    @GetMapping("/getByUserId/{userId}")
    @Auth(value = "clever-system.userStatusLog.getByUserId", name = "根据user_id获取user_status_log列表", description = "user_status_log列表接口")
    public Result<List<UserStatusLog>> selectByUserId(@PathVariable("userId") String userId) {
        return new Result<>(userStatusLogService.selectListByUserId(userId), "查询成功");
    }

    /**
     * 保存user_status_log信息
     *
     * @param userStatusLog user_status_log实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userStatusLog.save", name = "保存user_status_log", description = "保存user_status_log信息接口")
    public Result<String> save(UserStatusLog userStatusLog) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userStatusLogService.save(userStatusLog, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据自增id获取user_status_log列表
     *
     * @param id 自增id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.userStatusLog.delete", name = "删除user_status_log", description = "删除user_status_log信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userStatusLogService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
