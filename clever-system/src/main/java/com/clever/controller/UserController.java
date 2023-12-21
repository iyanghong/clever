package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.User;
import com.clever.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:41:46
 */
@RestController
@RequestMapping("/user")
@AuthGroup(name = "用户模块", description = "用户模块权限组")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 分页查询用户列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param account    账号
     * @param email      邮箱
     * @param phone      手机
     * @param nickname   昵称
     * @param diskId     磁盘id
     * @param status     账号状态：0-未激活,1-正常,2-密码冻结,3-违规,4-注销
     * @param gender     性别：0-未知,1-男,2-女
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.user.page", name = "用户分页", description = "用户分页接口")
    public Result<Page<User>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String account, String email, String phone, String nickname, String diskId, Integer status, Integer gender) {
        return new Result<>(userService.selectPage(pageNumber, pageSize, account, email, phone, nickname, diskId, status, gender), "分页数据查询成功");
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/get/{id}")
    public Result<User> selectById(@PathVariable("id") String id) {
        return new Result<>(userService.selectById(id), "查询成功");
    }

    /**
     * 根据磁盘id获取用户列表
     *
     * @param diskId 磁盘id
     * @return 用户列表
     */
    @GetMapping("/getListByDiskId/{diskId}")
    @Auth(value = "clever-system.user.getByDiskId", name = "根据disk_id获取用户列表", description = "用户列表接口")
    public Result<List<User>> selectListByDiskId(@PathVariable("diskId") String diskId) {
        return new Result<>(userService.selectListByDiskId(diskId), "查询成功");
    }

    /**
     * 保存用户信息
     *
     * @param user 用户实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.user.save", name = "保存用户", description = "保存用户信息接口")
    public Result<String> save(@Validated User user) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userService.save(user, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据用户id获取用户列表
     *
     * @param id 用户id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.user.delete", name = "删除用户", description = "删除用户信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
