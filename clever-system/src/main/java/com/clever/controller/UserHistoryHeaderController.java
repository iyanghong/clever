package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.UserHistoryHeader;
import com.clever.service.UserHistoryHeaderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户历史头像表接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:39:08
 */
@RestController
@RequestMapping("/userHistoryHeader")
@AuthGroup(name = "用户历史头像表模块", description = "用户历史头像表模块权限组")
public class UserHistoryHeaderController {

    @Resource
    private UserHistoryHeaderService userHistoryHeaderService;


    /**
     * 分页查询用户历史头像表列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param diskId     磁盘id
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.userHistoryHeader.page", name = "用户历史头像表分页", description = "用户历史头像表分页接口")
    public Result<Page<UserHistoryHeader>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String userId, String diskId) {
        return new Result<>(userHistoryHeaderService.selectPage(pageNumber, pageSize, userId, diskId), "分页数据查询成功");
    }

    /**
     * 根据历史头像id获取用户历史头像表信息
     *
     * @param id 历史头像id
     * @return 用户历史头像表信息
     */
    @GetMapping("/get/{id}")
    public Result<UserHistoryHeader> selectById(@PathVariable("id") String id) {
        return new Result<>(userHistoryHeaderService.selectById(id), "查询成功");
    }

    /**
     * 根据用户id获取用户历史头像表列表
     *
     * @param userId 用户id
     * @return 用户历史头像表列表
     */
    @GetMapping("/getByUserId/{userId}")
    @Auth(value = "clever-system.userHistoryHeader.getByUserId", name = "根据user_id获取用户历史头像表列表", description = "用户历史头像表列表接口")
    public Result<List<UserHistoryHeader>> selectByUserId(@PathVariable("userId") String userId) {
        return new Result<>(userHistoryHeaderService.selectListByUserId(userId), "查询成功");
    }

    /**
     * 根据磁盘id获取用户历史头像表列表
     *
     * @param diskId 磁盘id
     * @return 用户历史头像表列表
     */
    @GetMapping("/getByDiskId/{diskId}")
    @Auth(value = "clever-system.userHistoryHeader.getByDiskId", name = "根据disk_id获取用户历史头像表列表", description = "用户历史头像表列表接口")
    public Result<List<UserHistoryHeader>> selectByDiskId(@PathVariable("diskId") String diskId) {
        return new Result<>(userHistoryHeaderService.selectListByDiskId(diskId), "查询成功");
    }

    /**
     * 保存用户历史头像表信息
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.userHistoryHeader.save", name = "保存用户历史头像表", description = "保存用户历史头像表信息接口")
    public Result<String> save(@Validated UserHistoryHeader userHistoryHeader) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userHistoryHeaderService.save(userHistoryHeader, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据历史头像id获取用户历史头像表列表
     *
     * @param id 历史头像id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.userHistoryHeader.delete", name = "删除用户历史头像表", description = "删除用户历史头像表信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userHistoryHeaderService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
