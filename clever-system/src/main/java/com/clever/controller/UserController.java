package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.system.EmailTemplate;
import com.clever.bean.system.projo.UserBaseDataInput;
import com.clever.bean.system.projo.UserRegisterInput;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.HashMap;
import java.util.List;

import com.clever.bean.system.User;
import com.clever.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/user")
@AuthGroup(value = "clever-system.user", name = "用户模块", description = "用户模块权限组")
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
    @Auth(value = "clever-system.user.selectByAccount", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
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
    @Auth(value = "clever-system.user.selectByEmail", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
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
    @Auth(value = "clever-system.user.selectByPhone", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
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
    @Auth(value = "clever-system.user.selectById", name = "根据用户id获取用户信息", description = "根据用户id获取用户信息接口")
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

    /**
     * 登录
     *
     * @param account    账号
     * @param password   密码
     * @param platformId 平台id
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public Result<OnlineUser> login(@NotBlank(message = "登录账号不能为空") String account, @NotBlank(message = "登录密码不能为空") String password, Integer platformId) {
        return new Result<OnlineUser>(userService.login(account, password, platformId), "登录成功");
    }

    /**
     * 发送邮箱验证码
     *
     * @param platformId 平台id
     * @return 验证码
     */
    @PostMapping("/sendEmailVerifyCode")
    public Result<String> sendEmailVerifyCode(@NotNull(message = "平台id不能为空") Integer platformId, @RequestParam HashMap<String, String> variables) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        userService.sendEmailVerifyCode(onlineUser.getEmail(), platformId, variables);
        return Result.ofSuccess("发送成功");
    }


    /**
     * 发送邮件
     *
     * @param platformId   平台id
     * @param templateCode 模板code
     * @param email        邮箱
     * @param variables    占位变量
     */
    @PostMapping("/sendEmail/{platformId}/{templateCode}/{email}")
    @Auth(value = "clever-system.user.sendEmail", name = "发送邮箱", description = "发送邮箱接口")
    public Result<String> sendEmail(@PathVariable("platformId") Integer platformId, @PathVariable("templateCode") String templateCode, @Email(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "请输入正确的邮箱") @PathVariable("email") String email, @RequestParam HashMap<String, String> variables) {
        userService.sendEmail(platformId, templateCode, email, variables);
        return Result.ofSuccess("发送成功");
    }

    @PostMapping("/register")
    @Auth(enable = false, value = "clever-system.user.register", name = "用户注册", description = "用户注册接口")
    public Result<String> register(UserRegisterInput userRegisterInput) {
        userService.register(userRegisterInput);
        return Result.ofSuccess("注册成功, 请前往邮箱激活账号");
    }

    @PostMapping("/online")
    public Result<OnlineUser> getOnlineUser(String token) {
        return new Result<>(userService.getOnlineUserByToken(token));
    }

    @PostMapping("/logout")
    public Result<String> logout(OnlineUser onlineUser) {
        userService.logout(onlineUser);
        return Result.ofSuccess("已安全退出");
    }

    /**
     * 发送激活邮件
     *
     * @param email 邮箱
     */
    @PostMapping("/send/activation")
    public Result<String> sendActivation(@Email(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "请输入正确的邮箱") String email, String templateCode, @RequestParam HashMap<String, String> variables) {
        User activeUser = userService.selectByEmail(email);
        if (activeUser == null) return new Result<>("该邮箱未注册");
        EmailTemplate emailTemplate = userService.getEmailTemplateByActivation(activeUser.getSourcePlatform(), templateCode);
        userService.sendActivationEmail(email, emailTemplate.getId(), variables);
        return new Result<>("激活邮件发送成功");
    }

    /**
     * (邮件)激活账号
     *
     * @param activationFlag 激活账户
     */
    @GetMapping("/activation")
    @Auth(enable = false, value = "clever-system.user.activationAccount", name = "(邮件)激活账号", description = "(邮件)激活账号接口")
    public Result<String> activationAccount(String activationFlag) {
        userService.activationAccount(activationFlag);
        return Result.ofSuccess("账户激活成功");
    }

    /**
     * 管理员批量激活用户
     *
     * @param userIds 用户ids
     */
    @PostMapping("/admin/activation/{platformId}")
    @Auth(value = "clever-system.user.activation", name = "用户激活", description = "用户服务用户激活接口")
    public Result<String> adminActivation(@PathVariable("platformId") Integer platformId, @RequestBody List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ValidationException("激活的用户不能为空");
        }
        userService.adminActivation(platformId, userIds);
        return Result.ofSuccess("账户激活成功");
    }

    @PostMapping("/reset/password/{platformId}")
    @Auth(value = "user-service.user.reset-password", name = "用户重置密码", description = "用户服务用户重置密码接口")
    public Result<String> resetPassword(@PathVariable("platformId") Integer platformId, @RequestBody List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ValidationException("重置密码的用户不能为空");
        }
        userService.resetPassword(platformId, userIds);
        return Result.ofSuccess("操作成功");
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @PostMapping("/update/password")
    public Result<String> updatePassword(String oldPassword, @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{6,20}$", message = "密码格式为6-20位,必须包含一个英文或数字") String newPassword) {
        userService.updatePassword(oldPassword, newPassword);
        return Result.ofSuccess("密码修改成功");
    }

    @PostMapping("/update/role/{id}")
    @Auth(value = "user-service.user.role", name = "用户角色设置", description = "用户服务用户角色设置接口")
    public Result<String> updateUserRoles(@PathVariable("id") String userId, @RequestBody List<String> roleIds, OnlineUser onlineUser) {
        userService.updateUserRoles(userId, roleIds, onlineUser);
        return Result.ofSuccess("授权成功");
    }

    /**
     * 修改用户基本信息
     *
     * @param userBaseDataInput 用户基本信息
     * @param onlineUser        在线用户
     * @return OnlineUser
     */
    @PostMapping("/update/baseData")
    @Auth(isOnlyLogin = true, value = "user-service.user.updateUserBaseData", name = "修改用户基本信息", description = "修改用户基本信息接口")
    public Result<OnlineUser> updateUserBaseData(UserBaseDataInput userBaseDataInput, OnlineUser onlineUser) {
        return new Result<>(userService.updateUserBaseData(userBaseDataInput, onlineUser), "修改成功");
    }

    /**
     * 获取当前登录用户信息
     *
     * @return OnlineUser
     */
    @PostMapping("/getCurrentUser")
    @Auth(isOnlyLogin = true, value = "user-service.user.getCurrentUser", name = "获取当前登录用户信息", description = "获取当前登录用户信息接口")
    public Result<OnlineUser> getCurrentUser() {
        return new Result<>(SpringUtil.getOnlineUser(), "已登录");
    }
}
