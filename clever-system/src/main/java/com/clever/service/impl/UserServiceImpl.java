package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.Constant;
import com.clever.SystemConfigConstant;
import com.clever.bean.model.IpAttribution;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.*;
import com.clever.constant.CacheConstant;
import com.clever.enums.UserStatus;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.service.*;
import com.clever.util.RegularUtil;
import com.clever.util.SpringUtil;
import com.clever.util.StringEncryptUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.clever.mapper.UserMapper;
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
    private SystemConfigService systemConfigService;

    @Resource
    private EmailTemplateService emailTemplateService;

    @Resource
    private LogUserLoginService logUserLoginService;

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
     * 发送邮箱验证码
     *
     * @param email      邮箱
     * @param platformId 平台id
     */
    @Override
    public void sendEmailVerifyCode(String email, Integer platformId) {
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
        Map<String, String> variables = new HashMap<>();
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
}
