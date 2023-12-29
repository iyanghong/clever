package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.LogUserStatus;
import com.clever.service.LogUserStatusService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户状态日志接口
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@RestController
@Validated
@RequestMapping("/logUserStatus")
@AuthGroup(value = "clever-system.logUserStatus", name = "用户状态日志模块", description = "用户状态日志模块权限组")
public class LogUserStatusController {

    @Resource
    private LogUserStatusService logUserStatusService;


    /**
     * 分页查询用户状态日志列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param platformId    平台id
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.logUserStatus.page", name = "用户状态日志分页", description = "用户状态日志分页接口")
    public Result<Page<LogUserStatus>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String userId, Integer currentStatus, Integer changeStatus) {
        return new Result<>(logUserStatusService.selectPage(pageNumber, pageSize, platformId, userId, currentStatus, changeStatus), "分页数据查询成功");
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<LogUserStatus> 用户状态日志列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.logUserStatus.listByPlatformId", name = "根据平台id获取用户状态日志列表", description = "根据平台id获取用户状态日志列表接口")
    public List<LogUserStatus> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return logUserStatusService.selectListByPlatformId(platformId);
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<LogUserStatus> 用户状态日志列表
     */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.logUserStatus.listByUserId", name = "根据用户获取用户状态日志列表", description = "根据用户获取用户状态日志列表接口")
    public List<LogUserStatus> selectListByUserId(@PathVariable("userId") String userId) {
        return logUserStatusService.selectListByUserId(userId);
    }

    /**
     * 根据自增id获取用户状态日志信息
     *
     * @param id 自增id
     * @return 用户状态日志信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.logUserStatus.selectById", name = "根据自增id获取用户状态日志信息", description = "根据自增id获取用户状态日志信息接口")
    public Result<LogUserStatus> selectById(@PathVariable("id") String id) {
        return new Result<>(logUserStatusService.selectById(id), "查询成功");
    }

    /**
     * 创建用户状态日志信息
     *
     * @param logUserStatus 用户状态日志实体信息
     * @return 创建后的用户状态日志信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.logUserStatus.create", name = "创建用户状态日志", description = "创建用户状态日志信息接口")
    public Result<LogUserStatus> create(@Validated LogUserStatus logUserStatus) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(logUserStatusService.create(logUserStatus, onlineUser), "创建成功");
    }

    /**
     * 修改用户状态日志信息
     *
     * @param logUserStatus 用户状态日志实体信息
     * @return 修改后的用户状态日志信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.logUserStatus.update", name = "修改用户状态日志", description = "修改用户状态日志信息接口")
    public Result<LogUserStatus> update(@Validated LogUserStatus logUserStatus, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        logUserStatus.setId(id);
        return new Result<>(logUserStatusService.update(logUserStatus, onlineUser), "修改成功");
    }

    /**
     * 保存用户状态日志信息
     *
     * @param logUserStatus 用户状态日志实体信息
     * @return 保存后的用户状态日志信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.logUserStatus.save", name = "保存用户状态日志", description = "保存用户状态日志信息接口")
    public Result<LogUserStatus> save(@Validated LogUserStatus logUserStatus) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(logUserStatusService.save(logUserStatus, onlineUser), "保存成功");
    }

    /**
     * 根据用户状态日志id删除用户状态日志信息
     *
     * @param id 自增id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.logUserStatus.delete", name = "删除用户状态日志", description = "删除用户状态日志信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        logUserStatusService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
