package com.clever.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.Constant;
import com.clever.SystemConfigConstant;
import com.clever.bean.model.IpAttribution;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.*;
import com.clever.bean.system.projo.UserBaseDataInput;
import com.clever.bean.system.projo.UserRegisterInput;
import com.clever.constant.CacheConstant;
import com.clever.enums.UserStatus;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.mapper.SystemConfigMapper;
import com.clever.mapper.UserPlatformRelMapper;
import com.clever.mapper.UserRoleRelMapper;
import com.clever.service.*;
import com.clever.util.CollectionUtil;
import com.clever.util.RegularUtil;
import com.clever.util.SpringUtil;
import com.clever.util.StringEncryptUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.clever.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redis;
    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private LogUserStatusService logUserStatusService;

    @Resource
    private PlatformService platformService;

    @Resource
    private UserPlatformRelMapper userPlatformRelMapper;
    @Resource
    private SystemConfigService systemConfigService;

    @Resource
    private EmailTemplateService emailTemplateService;

    @Resource
    private LogUserLoginService logUserLoginService;

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    @Resource
    private SystemConfigMapper systemConfigMapper;

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
     * @return Page<User>
     */
    @Override
    public Page<User> selectPage(Integer pageNumber, Integer pageSize, String account, String email, String phone, String nickname, String diskId, Integer status, Integer gender) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(account)) {
            queryWrapper.eq("account", account);
        }
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.eq("email", email);
        }
        if (StringUtils.isNotBlank(phone)) {
            queryWrapper.eq("phone", phone);
        }
        if (StringUtils.isNotBlank(nickname)) {
            queryWrapper.eq("nickname", nickname);
        }
        if (StringUtils.isNotBlank(diskId)) {
            queryWrapper.eq("disk_id", diskId);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        if (gender != null) {
            queryWrapper.eq("gender", gender);
        }
        return userMapper.selectPage(new Page<User>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return User 用户信息
     */
    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据账号获取信息
     *
     * @param account 账号
     * @return User 用户信息
     */
    @Override
    public User selectByAccount(String account) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
    }

    /**
     * 根据邮箱获取信息
     *
     * @param email 邮箱
     * @return User 用户信息
     */
    @Override
    public User selectByEmail(String email) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("email", email));
    }

    /**
     * 根据手机获取信息
     *
     * @param phone 手机
     * @return User 用户信息
     */
    @Override
    public User selectByPhone(String phone) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
    }

    /**
     * 根据磁盘id获取列表
     *
     * @param diskId 磁盘id
     * @return List<User> 用户列表
     */
    @Override
    public List<User> selectListByDiskId(String diskId) {
        return userMapper.selectList(new QueryWrapper<User>().eq("disk_id", diskId).orderByAsc("id"));
    }

    /**
     * 新建用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 新建后的用户信息
     */
    @Override
    public User create(User user, OnlineUser onlineUser) {
        userMapper.insert(user);
        log.info("用户, 用户信息创建成功: userId={}, userId={}", onlineUser.getId(), user.getId());
        return user;
    }

    /**
     * 修改用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 修改后的用户信息
     */
    @Override
    public User update(User user, OnlineUser onlineUser) {
        userMapper.updateById(user);
        log.info("用户, 用户信息修改成功: userId={}, userId={}", onlineUser.getId(), user.getId());
        return user;
    }

    /**
     * 保存用户
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     * @return User 保存后的用户信息
     */
    @Override
    public User save(User user, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(user.getId())) {
            return create(user, onlineUser);
        }
        return update(user, onlineUser);
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param id         用户id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userMapper.deleteById(id);
        log.info("用户, 用户信息删除成功: userId={}, userId={}", onlineUser.getId(), id);
    }

    /**
     * 根据用户id列表删除用户信息
     *
     * @param ids        用户id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userMapper.deleteBatchIds(ids);
        log.info("用户, 用户信息批量删除成功: userId={}, count={}, userIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据磁盘id删除
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByDiskId(String diskId, OnlineUser onlineUser) {
        userMapper.delete(new QueryWrapper<User>().eq("disk_id", diskId));
        log.info("用户, 用户信息根据diskId删除成功: userId={}, diskId={}", onlineUser.getId(), diskId);
    }


    /**
     * 用户登录
     *
     * @param account    账户(可为邮箱或者系统账号)
     * @param password   密码
     * @param platformId 平台id
     * @return user
     */
    @Override
    public OnlineUser login(String account, String password, Integer platformId) {
        Date now = new Date();
        HttpServletRequest request = SpringUtil.getRequest();
        String ip = request.getRemoteAddr();
        log.info("用户登录, 用户开始登陆: account={}, ip={}", account, ip);
        //连续密码错误检查
        String passwordErrorManyKey = redis.formatKey("User", "passwordErrorMany", account);
        if (StringUtils.isNotBlank(redis.getString(passwordErrorManyKey))) {
            throw new BaseException(ConstantException.USER_PASSWORD_ERROR_SO_MANY);
        }
        // 判断用户使用什么登录
        String loginField = "account";
        if (RegularUtil.isMatching(RegularUtil.REGULAR_EMAIL, account)) {
            loginField = "email"; // 使用邮箱登录
        } else if (RegularUtil.isMatching(RegularUtil.REGULAR_PHONE, account)) {
            loginField = "phone"; // 使用手机登录
        }

        User user = userMapper.selectOne(new QueryWrapper<User>().eq(loginField, account).between("status", UserStatus.AVAILABLE.getStatus(), UserStatus.VIOLATION.getStatus()));
        //账号不存在
        if (user == null) {
            log.error("用户登录, 用户不存在: account={}, ip={}", account, ip);
            throw new BaseException(ConstantException.USER_ACCOUNT_NOT_FOUND);
        }
        List<Platform> platformList = platformService.selectByUserId(user.getId());

        // 密码不正确
        if (!StringEncryptUtil.sha256Encrypt(password).equals(user.getPassword())) {
            log.error("用户登录, 密码不正确: account={}, password={}, ip={}", account, StringEncryptUtil.sha256Encrypt(password), ip);
            if (platformId == null) {
                if (platformList != null && !platformList.isEmpty()) {
                    platformId = platformList.get(0).getId();
                } else {
                    platformId = -1;
                }
            }
            SystemConfig systemConfig = systemConfigService.selectByCode(platformId, SystemConfigConstant.USER_LOGIN_MAX_ERROR_COUNT);
            int maxErrorNum = 5;
            if (systemConfig != null && systemConfig.ifEnable()) {
                maxErrorNum = Integer.parseInt(systemConfig.getValue());
            }
            if (user.getPasswordErrorNum() < maxErrorNum) {
                userMapper.updatePasswordErrorCount(user.getId());
                throw new BaseException(ConstantException.USER_LOGIN_PASSWORD_ERROR);
            } else {
                //错误次数过多设置30分钟登录限制
                redis.setString(passwordErrorManyKey, user.getAccount(), 60 * 30);
                redis.setString(passwordErrorManyKey, user.getEmail(), 60 * 30);
                Date duration = DateUtils.addMinutes(now, 30);
                logUserStatusService.logUserStatusChange(user.getId(), UserStatus.FREEZE.getStatus(), duration, "用户登录密码错误次数过多");
                log.error("用户登录, 用户密码错误次数过多, 已锁定: account={}, ip={}", account, ip);
                throw new BaseException(ConstantException.USER_LOGIN_PASSWORD_ERROR);
            }
        }

        //删除限制登录缓存
        redis.delKeys(passwordErrorManyKey);
        // 生成token
        String token = SpringUtil.getUuid();
        // 更新用户登录时间
        user.setLastLoginTime(now);
        // 更新用户登录ip
        user.setLoginIp(ip);
        // 重置错误次数
        user.setPasswordErrorNum(0);
        // 获取用户角色
        List<Role> roles = roleService.selectRolesByUserId(user.getId());
        // 获取用户权限
        List<String> permissions = permissionService.selectPermissionByRoles(roles);

        // 查询ip归属地
        IpAttribution ipAttribution = IpAttribution.resolve(ip);


        // 创建在线用户
        OnlineUser onlineUser = new OnlineUser(user, token, roles, permissions, platformList, ipAttribution);
        // 设置在线用户
        redis.setString(CacheConstant.getOnlineKeyName(token), onlineUser, user.getOnlineTime(), TimeUnit.HOURS);

        // 更新用户信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setLastLoginTime(now);
        updateUser.setLoginIp(ip);
        updateUser.setPasswordErrorNum(0);
        userMapper.updateById(updateUser);
        logUserLoginService.recordUserLogin(user.getId(), platformId, ip, request.getHeader("User-Agent"), now, ipAttribution);
        log.info("用户登录, 用户登录成功: account={}, ip={}", account, ip);
        return onlineUser;
    }

    /**
     * 用户退出登录
     *
     * @param onlineUser 当前登录用户
     */
    @Override
    public void logout(OnlineUser onlineUser) {
        redis.delKeys(CacheConstant.getOnlineKeyName(onlineUser.getToken()));
        log.info("用户退出登录, 用户退出登录成功: userId={}", onlineUser.getId());
    }

    /**
     * 发送邮箱验证码
     *
     * @param email      邮箱
     * @param platformId 平台id
     */
    @Override
    public void sendEmailVerifyCode(String email, Integer platformId, Map<String, String> variables) {
        if (StringUtils.isBlank(email) || !RegularUtil.isMatching(RegularUtil.REGULAR_EMAIL, email)) {
            throw new BaseException(ConstantException.PARAMETER_VERIFICATION_EMAIL_FAIL);
        }
        String codeKey = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "email:code", email);
        String timeOutKey = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "email:codeTimeOut", email);
        //检查是否在验证码发送冷却时间内
        String flag = redis.getString(timeOutKey);
        if (StringUtils.isNotBlank(flag)) {
            log.error("发送邮件, 邮件发送频繁: receiveEmail={}", email);
            throw new BaseException(ConstantException.EMAIL_SEND_OFTEN);
        }
        String code = RandomStringUtils.randomNumeric(6);
        if (variables == null) variables = new HashMap<>();
        variables.put("captcha", code);
        String templateCode = "VerifyCode";
        EmailTemplate emailTemplate = emailTemplateService.selectByCodeAndPlatform(platformId, templateCode);
        if (emailTemplate == null) {
            log.error("发送邮件, 邮件模板不存在: receiveEmail={}, platformId={}", email, platformId);
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("邮箱模板不存在:" + templateCode));
        }
        emailTemplateService.sendEmail(email, emailTemplate.getId(), variables);
        //验证码有效期600秒
        redis.setString(codeKey, code, 600);
        //90秒后可重新发送
        redis.setString(timeOutKey, code, 90);
    }

    @Override
    public User getLoginUserInfo(String account) {
        return null;
    }

    /**
     * 修改用户基本信息
     *
     * @param userBaseDataInput 用户基本信息
     * @param onlineUser        在线用户
     * @return OnlineUser
     */
    @Override
    public OnlineUser updateUserBaseData(UserBaseDataInput userBaseDataInput, OnlineUser onlineUser) {
        User updateUser = new User();
        updateUser.setId(onlineUser.getId());
        updateUser.setNickname(userBaseDataInput.getNickname());
        updateUser.setHeader(userBaseDataInput.getHeader());
        updateUser.setDiskId(userBaseDataInput.getDiskId());
        updateUser.setGender(userBaseDataInput.getGender());
        updateUser.setBirthday(userBaseDataInput.getBirthday());
        updateUser.setSignature(userBaseDataInput.getSignature());
        updateUser.setAddress(userBaseDataInput.getAddress());
        userMapper.updateById(updateUser);
        log.info("用户, 用户基本信息修改成功: userId={}, userId={}", onlineUser.getId(), onlineUser.getId());
        return refreshOnlineUser(onlineUser.getToken());
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        User user = userMapper.selectById(onlineUser.getId());
        if (!StringEncryptUtil.sha256Encrypt(oldPassword).equals(user.getPassword())) {
            log.error("修改密码, 旧密码错误: user={}", user.getId());
            throw new BaseException(ConstantException.USER_LOGIN_PASSWORD_ERROR.format("密码不正确"));
        } else {
            User updateUser = new User();
            updateUser.setId(onlineUser.getId());
            updateUser.setPassword(StringEncryptUtil.sha256Encrypt(newPassword));
            userMapper.updateById(updateUser);
            log.info("修改密码, 用户密码修改成功: user={}", user.getId());
        }
    }

    /**
     * 管理员批量重置用户密码
     *
     * @param platformId 平台id
     * @param userIds    用户id列表
     */
    @Override
    public void resetPassword(Integer platformId, List<String> userIds) {
        SystemConfig config = systemConfigService.selectByCode(platformId, SystemConfigConstant.USER_DEFAULT_PASSWORD);
        if (config == null) {
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("配置出错"));
        }
        User update = new User();
        update.setPassword(config.getValue());
        userMapper.update(update, new QueryWrapper<User>().in("id", userIds));
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        log.info("管理员重置用户密码, 密码重置完成: admin={}, userIds={}", onlineUser.getId(), userIds);
    }


    @Override
    public void forgetPassword(String account) {

    }

    /**
     * 授权用户角色
     *
     * @param userId     用户id
     * @param roleIds    角色ids
     * @param onlineUser 操作用户
     */
    @Override
    public void updateUserRoles(String userId, List<String> roleIds, OnlineUser onlineUser) {
        List<String> userRoleIds = userRoleRelMapper.selectRoleIdsByUserId(userId);
        CollectionUtil.compareAndRemove(roleIds, userRoleIds);
        if (!CollectionUtils.isEmpty(roleIds)) {
            //需要添加的角色
            userRoleRelMapper.insertByList(userId, roleIds);
        }
        if (!CollectionUtils.isEmpty(userRoleIds)) {
            //需要删除的角色
            userRoleRelMapper.delete(new QueryWrapper<UserRoleRel>().eq("user_id", userId).in("role_id", userRoleIds));
        }
        log.info("用户管理, 用户角色设置成功: user={}, addRole={}, deleteRole={}", userId, roleIds, userRoleIds);
    }

    @Override
    public OnlineUser refreshOnlineUser(String token) {
        OnlineUser onlineUser = redis.getString(CacheConstant.getOnlineKeyName(token));
        if (onlineUser == null) {
            log.error("刷新在线用户, 在线用户不存在: token={}", token);
            throw new BaseException(ConstantException.USER_NO_ONLINE);
        }


        User user = userMapper.selectById(onlineUser.getId());

        // 获取用户角色
        List<Role> roles = roleService.selectRolesByUserId(user.getId());
        // 获取用户权限
        List<String> permissions = permissionService.selectPermissionByRoles(roles);
        List<Platform> platformList = platformService.selectByUserId(user.getId());

        onlineUser.setUser(user);
        onlineUser.setPermissions(permissions);
        onlineUser.setRoles(roles);
        onlineUser.setPlatforms(platformList);
        // 设置在线用户
        redis.setString(CacheConstant.getOnlineKeyName(token), onlineUser, user.getOnlineTime(), TimeUnit.HOURS);
        return onlineUser;
    }

    public void sendEmail(Integer platformId, String templateCode, String email, Map<String, String> variables) {
        String timeOutKey = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "email:sendEmailTimeOut:" + templateCode, email);
        //检查是否在验证码发送冷却时间内
        String flag = redis.getString(timeOutKey);
        if (StringUtils.isNotBlank(flag)) {
            log.error("发送邮件, 邮件发送频繁: receiveEmail={}", email);
            throw new BaseException(ConstantException.EMAIL_SEND_OFTEN);
        }
        EmailTemplate emailTemplate = emailTemplateService.selectByCodeAndPlatform(platformId, templateCode);
        if (emailTemplate == null) {
            log.error("发送邮件, 邮件模板不存在: receiveEmail={}, platformId={}", email, platformId);
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("邮箱模板不存在:" + templateCode));
        }
        if (variables == null) variables = new HashMap<>();
        emailTemplateService.sendEmail(email, emailTemplate.getId(), variables);
        //90秒后可重新发送
        redis.setString(timeOutKey, email, 90);
    }

    /**
     * 用户注册
     *
     * @param userRegisterInput 用户注册信息
     */
    public void register(UserRegisterInput userRegisterInput) {
        User user = userMapper.selectByAccountOrEmail(userRegisterInput.getAccount(), userRegisterInput.getEmail());
        if (user != null) {
            log.error("用户注册, 邮箱已被注册: account={}, email={}", userRegisterInput.getAccount(), userRegisterInput.getEmail());
            throw new BaseException(ConstantException.ACCOUNT_IS_EXISTED);
        }
        EmailTemplate emailTemplate = getEmailTemplateByActivation(userRegisterInput.getPlatformId(), userRegisterInput.getTemplateCode());

        if (emailTemplate == null) {
            log.error("用户注册, 激活邮件模板不存在: account={}, email={}", userRegisterInput.getAccount(), userRegisterInput.getEmail());
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("激活邮件模板不存在"));
        }
        User newUser = new User();
        userRegisterInput.assignUser(newUser);
        newUser.setStatus(UserStatus.INACTIVATED.getStatus());
        if (StringUtils.isBlank(newUser.getNickname())) {
            newUser.setNickname(newUser.getAccount());
        }
        userMapper.insert(newUser);
        log.info("用户注册, 用户基本信息保存成功: email={}", userRegisterInput.getEmail());
        this.sendActivationEmail(userRegisterInput.getEmail(), emailTemplate.getId(), userRegisterInput.getVariables());
    }

    public EmailTemplate getEmailTemplateByActivation(Integer platformId, String code) {
        EmailTemplate emailTemplate;
        if (StringUtils.isBlank(code)) {
            SystemConfig systemConfig = systemConfigService.selectByCode(platformId, SystemConfigConstant.DEFAULT_ACTIVATION_EMAIL_TEMPLATE_CODE);
            if (systemConfig == null || !systemConfig.ifEnable()) {
                log.error("默认激活邮件模板配置错误");
                throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("默认激活邮件模板配置错误"));
            }
            emailTemplate = emailTemplateService.selectByCodeAndPlatform(platformId, systemConfig.getValue());
        } else {
            emailTemplate = emailTemplateService.selectByCodeAndPlatform(platformId, code);
        }
        return emailTemplate;
    }

    /**
     * 激活邮件发送
     *
     * @param email      接收邮箱
     * @param templateId 模板id
     * @param variables  邮件占位值
     */
    @Override
    public void sendActivationEmail(String email, String templateId, Map<String, String> variables) {
        String activationFlag = SpringUtil.getUuid();
        String activationKeyTimeOut = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "user:activationTimeout", email);
        //检查是否在验证码发送冷却时间内
        JSONObject jsonObject = redis.getString(activationKeyTimeOut);
        String sendFlagActivationKey = "activationKey";
        String sendFlagSendTime = "sendTime";
        if (jsonObject != null) {
            int timeOut = 90;
            Long now = System.currentTimeMillis() / 1000;
            //判断是否频繁发送
            if (now - jsonObject.getLong(sendFlagSendTime) > timeOut) {
                log.error("发送邮件, 邮件发送频繁: receiveEmail={}", email);
                throw new BaseException(ConstantException.EMAIL_SEND_OFTEN);
            } else {
                //把之前的激活标识删除
                redis.delKeys(jsonObject.getString(sendFlagActivationKey));
            }
        }
        //发送激活邮件
        if (CollectionUtils.isEmpty(variables)) {
            variables = new HashMap<>(4);
        }
        String activationPlaceholder = "activationFlag";
        if (!variables.containsKey(activationPlaceholder)) {
            variables.put(activationPlaceholder, activationFlag);
        } else {
            activationFlag = variables.get(activationPlaceholder);
        }
        String activationKey = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "user:activation", activationFlag);
        emailTemplateService.sendEmail(email, templateId, variables);
        //用于标识和记录发送时间
        JSONObject sendFlag = new JSONObject();
        sendFlag.put(sendFlagSendTime, System.currentTimeMillis() / 1000);
        sendFlag.put(sendFlagActivationKey, activationKey);
        //激活链接36小时有效
        int effectiveTime = 60 * 60 * 36;
        redis.setString(activationKey, email, effectiveTime);
        redis.setString(activationKeyTimeOut, sendFlag, effectiveTime);
        log.info("用户注册, 激活邮件发送成功: email={}", email);
    }

    /**
     * 用户激活
     *
     * @param activationFlag 激活码
     */
    @Override
    public void activationAccount(String activationFlag) {
        String key = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "user:activation", activationFlag);
        String email = redis.getString(key);
        if (StringUtils.isBlank(email)) {
            log.error("用户激活, 激活链接已失效");
            throw new BaseException(ConstantException.ACTIVATION_LINKS_EXPIRED);
        } else {
            String activationKeyTimeOut = String.format(Constant.KEY_FORMAT, Constant.APP_NAME, "user:activationTimeout", email);
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", email).eq("status", UserStatus.INACTIVATED.getStatus()));
            if (user == null) {
                log.error("用户激活, 用户还未注册: email={}", email);
                throw new BaseException(ConstantException.USER_ACCOUNT_NOT_FOUND);
            }
            userMapper.updateUserToAvailable(email);
            initNewUser(null, user, null);
            log.info("用户激活, 账户激活成功: email={}", email);
            redis.delKeys(key, activationKeyTimeOut);
        }
    }

    /**
     * 管理批量激活用户
     *
     * @param platformId 平台id
     * @param userIds    用户id列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminActivation(Integer platformId, List<String> userIds) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        QueryWrapper<User> query = new QueryWrapper<User>().in("uuid", userIds).eq("status", UserStatus.INACTIVATED.getStatus());
        List<User> users = userMapper.selectList(query);
        if (CollectionUtils.isEmpty(users) || userIds.size() != users.size()) {
            log.error("管理员激活用户, 用户未找到: userIds={}", userIds);
            throw new BaseException(ConstantException.USER_ACCOUNT_NOT_FOUND);
        }
        User update = new User();
        update.setStatus(UserStatus.AVAILABLE.getStatus());
        userMapper.update(update, query);
        log.info("管理员激活用户, 用户状态设置完成: admin={}, userIds={}", onlineUser.getId(), userIds);
        for (User user : users) {
            initNewUser(platformId, user, null);
            log.info("管理员激活用户, 用户激活成功: admin={}, user={}", onlineUser.getId(), user.getId());
        }
    }

    /**
     * 初始化一个新用户
     * 1.设置角色
     *
     * @param platformId 平台id
     * @param user       激活的用户
     * @param roleString 激活用户的角色
     */
    private void initNewUser(Integer platformId, User user, String roleString) {
        if (platformId == null) {
            platformId = user.getSourcePlatform();
        }
        List<String> roleIds;
        if (StringUtils.isNotBlank(roleString)) {
            roleIds = Arrays.asList(roleString.split(","));
        } else {
            SystemConfig config = systemConfigService.selectByCode(platformId, SystemConfigConstant.USER_DEFAULT_ROLE);
            roleIds = Arrays.asList(config.getValue().split(","));
        }
        // 如果是别的平台管理激活则添加平台
        if (!Objects.equals(platformId, user.getSourcePlatform())) {
            UserPlatformRel newUserPlatformRel = new UserPlatformRel();
            newUserPlatformRel.setUserId(user.getId());
            newUserPlatformRel.setPlatformId(platformId);
            userPlatformRelMapper.insert(newUserPlatformRel);
        }
        userRoleRelMapper.insertByList(user.getId(), roleIds);
        log.info("初始化新用户, 角色授权成功: user={}, roles={}", user.getId(), roleIds);
    }

    /**
     * 根据token获取登录用户信息
     *
     * @param token token
     * @return OnlineUser
     */
    public OnlineUser getOnlineUserByToken(String token) {
        OnlineUser onlineUser = redis.getString(CacheConstant.getOnlineKeyName(token));
        if (onlineUser == null) {
            throw new BaseException(ConstantException.USER_NO_ONLINE);
        }
        return onlineUser;
    }
}
