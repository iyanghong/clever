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
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
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
     * 根据磁盘id获取列表
     *
     * @param diskId 磁盘id
     * @return List<User> 用户列表
     */
    @GetMapping("/listByDiskId/{diskId}")
    @Auth(value = "clever-system.user.listByDiskId", name = "根据磁盘id获取用户列表", description = "根据磁盘id获取用户列表接口")
    public List<User> selectListByDiskId(@PathVariable("diskId") String diskId) {
        return userService.selectListByDiskId(diskId);
    }

    /**
     * 根据账号邀请码获取用户信息
     *
     * @param account 账号
     * @return 用户信息
     */
    @GetMapping("/account/{account}")
    @Auth(value = "clever-system.platform.selectByAccount", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
    public Result<User> selectByAccount(@PathVariable("account") String account) {
        return new Result<>(userService.selectByAccount(account), "查询成功");
    }

    /**
     * 根据邮箱邀请码获取用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    @GetMapping("/email/{email}")
    @Auth(value = "clever-system.platform.selectByEmail", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
    public Result<User> selectByEmail(@PathVariable("email") String email) {
        return new Result<>(userService.selectByEmail(email), "查询成功");
    }

    /**
     * 根据手机邀请码获取用户信息
     *
     * @param phone 手机
     * @return 用户信息
     */
    @GetMapping("/phone/{phone}")
    @Auth(value = "clever-system.platform.selectByPhone", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
    public Result<User> selectByPhone(@PathVariable("phone") String phone) {
        return new Result<>(userService.selectByPhone(phone), "查询成功");
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
    public Result<User> selectById(@PathVariable("id") String id) {
        return new Result<>(userService.selectById(id), "查询成功");
    }

    /**
     * 创建用户信息
     *
     * @param user 用户实体信息
     * @return 创建后的用户信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.user.create", name = "创建用户", description = "创建用户信息接口")
    public Result<User> create(@Validated User user) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userService.create(user, onlineUser), "创建成功");
    }

    /**
     * 修改用户信息
     *
     * @param user 用户实体信息
     * @return 修改后的用户信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.user.update", name = "修改用户", description = "修改用户信息接口")
    public Result<User> update(@Validated User user, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        user.setId(id);
        return new Result<>(userService.update(user, onlineUser), "修改成功");
    }

    /**
     * 保存用户信息
     *
     * @param user 用户实体信息
     * @return 保存后的用户信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.user.save", name = "保存用户", description = "保存用户信息接口")
    public Result<User> save(@Validated User user) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(userService.save(user, onlineUser), "保存成功");
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.user.delete", name = "删除用户", description = "删除用户信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
