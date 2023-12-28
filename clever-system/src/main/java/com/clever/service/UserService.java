package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;
import java.util.Map;

import com.clever.bean.system.EmailTemplate;
import com.clever.bean.system.User;
import com.clever.bean.system.projo.UserBaseDataInput;
import com.clever.bean.system.projo.UserRegisterInput;

/**
 * 用户服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface UserService {

    /**
     * 分页查询列表
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
     * @return Page<User>
     */
    Page<User> selectPage(Integer pageNumber, Integer pageSize, String account, String email, String phone, String nickname, String diskId, Integer status, Integer gender);

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return User 用户id信息
     */
    User selectById(String id);

    /**
     * 根据账号获取信息
     *
     * @param account 账号
     * @return User 用户信息
     */
    User selectByAccount(String account);

    /**
     * 根据邮箱获取信息
     *
     * @param email 邮箱
     * @return User 用户信息
     */
    User selectByEmail(String email);

    /**
     * 根据手机获取信息
     *
     * @param phone 手机
     * @return User 用户信息
     */
    User selectByPhone(String phone);

    /**
     * 根据磁盘id获取列表
     *
     * @param diskId 磁盘id
     * @return List<User> 用户列表
     */
    List<User> selectListByDiskId(String diskId);

    /**
     * 新建用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 新建后的用户信息
     */
    User create(User user, OnlineUser onlineUser);

    /**
     * 修改用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 修改后的用户信息
     */
    User update(User user, OnlineUser onlineUser);

    /**
     * 保存用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 保存后的用户信息
     */
    User save(User user, OnlineUser onlineUser);

    /**
     * 根据用户id删除信息
     *
     * @param id         用户id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据用户id列表删除信息
     *
     * @param ids        用户id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据磁盘id删除
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    void deleteByDiskId(String diskId, OnlineUser onlineUser);

    /**
     * 用户登录
     *
     * @param account    账户(可为邮箱或者系统账号)
     * @param password   密码
     * @param platformId 平台id
     * @return user
     */
    OnlineUser login(String account, String password, Integer platformId);

    /**
     * 登录退出
     *
     * @param onlineUser user
     */
    void logout(OnlineUser onlineUser);

    /**
     * 发送邮件
     *
     * @param platformId   平台id
     * @param email        邮箱
     * @param templateCode 模板code
     * @param variables    模板变量
     */
    void sendEmail(Integer platformId, String templateCode, String email, Map<String, String> variables);

    /**
     * 发送邮箱验证码
     *
     * @param email      邮箱
     * @param platformId 平台id
     */
    void sendEmailVerifyCode(String email, Integer platformId, Map<String, String> variables);
    /**
     * 激活邮件发送
     *
     * @param email      接收邮箱
     * @param templateId 模板id
     * @param variables  邮件占位值
     */
    void sendActivationEmail(String email, String templateId, Map<String, String> variables);
    /**
     * 根据账号获取用户基本信息
     *
     * @param account 账号
     * @return 用户信息
     */
    User getLoginUserInfo(String account);

    /**
     * 修改用户基本信息
     *
     * @param userBaseDataInput 用户基本信息
     * @param onlineUser        在线用户
     * @return OnlineUser
     */
    OnlineUser updateUserBaseData(UserBaseDataInput userBaseDataInput, OnlineUser onlineUser);

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String oldPassword, String newPassword);

    /**
     * 管理员批量重置用户密码
     *
     * @param platformId 平台id
     * @param userIds    用户id列表
     */
    void resetPassword(Integer platformId, List<String> userIds);

    /**
     * 忘记密码
     *
     * @param account 账号
     */
    void forgetPassword(String account);

    /**
     * 保存用户的角色
     *
     * @param userId     用户id
     * @param roleIds    角色ids
     * @param onlineUser 操作用户
     */
    void updateUserRoles(String userId, List<String> roleIds, OnlineUser onlineUser);

    /**
     * 更新当前在线用户的信息
     *
     * @param token token
     */
    OnlineUser refreshOnlineUser(String token);

    /**
     * 用户注册
     *
     * @param userRegisterInput 用户注册信息
     */
    void register(UserRegisterInput userRegisterInput);

    /**
     * 激活专用，获取激活邮箱模板
     * @param platformId 平台id
     * @param code 模板code
     * @return EmailTemplate
     */
    EmailTemplate getEmailTemplateByActivation(Integer platformId, String code);
    /**
     * 用户激活
     *
     * @param activationFlag 激活码
     */
    void activationAccount(String activationFlag);

    /**
     * 管理批量激活用户
     *
     * @param platformId 平台id
     * @param userIds    用户id列表
     */
    void adminActivation(Integer platformId, List<String> userIds);

    /**
     * 根据token获取登录用户信息
     * @param token token
     * @return OnlineUser
     */
    OnlineUser getOnlineUserByToken(String token);
}
