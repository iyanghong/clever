package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.User;
import com.clever.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.clever.mapper.UserStatusLogMapper;
import com.clever.bean.system.UserStatusLog;
import com.clever.service.UserStatusLogService;

import javax.annotation.Resource;

/**
 * 用户状态日志服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class UserStatusLogServiceImpl implements UserStatusLogService {

    private final static Logger log = LoggerFactory.getLogger(UserStatusLogServiceImpl.class);

    @Resource
    private UserStatusLogMapper userStatusLogMapper;
    @Resource
    private UserMapper userMapper;
    /**
     * 分页查询用户状态日志列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return Page<UserStatusLog>
     */
    @Override
    public Page<UserStatusLog> selectPage(Integer pageNumber, Integer pageSize, String userId, Integer currentStatus, Integer changeStatus) {
        QueryWrapper<UserStatusLog> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (currentStatus != null) {
            queryWrapper.eq("current_status", currentStatus);
        }
        if (changeStatus != null) {
            queryWrapper.eq("change_status", changeStatus);
        }
        return userStatusLogMapper.selectPage(new Page<UserStatusLog>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据自增id获取用户状态日志
     *
     * @param id 自增id
     * @return UserStatusLog 用户状态日志信息
     */
    @Override
    public UserStatusLog selectById(String id) {
        return userStatusLogMapper.selectById(id);
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserStatusLog> 用户状态日志列表
     */
    @Override
    public List<UserStatusLog> selectListByUserId(String userId) {
        return userStatusLogMapper.selectList(new QueryWrapper<UserStatusLog>().eq("user_id", userId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 新建后的用户状态日志信息
     */
    @Override
    public UserStatusLog create(UserStatusLog userStatusLog, OnlineUser onlineUser) {
        userStatusLogMapper.insert(userStatusLog);
        log.info("用户状态日志, 用户状态日志信息创建成功: userId={}, userStatusLogId={}", onlineUser.getId(), userStatusLog.getId());
        return userStatusLog;
    }

    /**
     * 修改用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 修改后的用户状态日志信息
     */
    @Override
    public UserStatusLog update(UserStatusLog userStatusLog, OnlineUser onlineUser) {
        userStatusLogMapper.updateById(userStatusLog);
        log.info("用户状态日志, 用户状态日志信息修改成功: userId={}, userStatusLogId={}", onlineUser.getId(), userStatusLog.getId());
        return userStatusLog;
    }

    /**
     * 保存用户状态日志
     *
     * @param userStatusLog 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return UserStatusLog 保存后的用户状态日志信息
     */
    @Override
    public UserStatusLog save(UserStatusLog userStatusLog, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(userStatusLog.getId())) {
            return create(userStatusLog, onlineUser);
        }
        return update(userStatusLog, onlineUser);
    }

    /**
     * 根据自增id删除用户状态日志信息
     *
     * @param id         自增id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userStatusLogMapper.deleteById(id);
        log.info("用户状态日志, 用户状态日志信息删除成功: userId={}, userStatusLogId={}", onlineUser.getId(), id);
    }

    /**
     * 根据自增id列表删除用户状态日志信息
     *
     * @param ids        自增id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userStatusLogMapper.deleteBatchIds(ids);
        log.info("用户状态日志, 用户状态日志信息批量删除成功: userId={}, count={}, userStatusLogIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userStatusLogMapper.delete(new QueryWrapper<UserStatusLog>().eq("user_id", userId));
        log.info("用户状态日志, 用户状态日志信息根据userId删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 记录用户状态变更
     *
     * @param userId       用户
     * @param changeStatus 更改的壮观
     * @param duration     结束时间
     * @param remake       备注
     */
    @Override
    public void logUserStatusChange(String userId, Integer changeStatus, Date duration, String remake) {
        User user = userMapper.selectById(userId);
        UserStatusLog statusLog = new UserStatusLog();
        statusLog.setUserId(userId);
        statusLog.setDuration(duration);
        statusLog.setRemark(remake);
        statusLog.setCurrentStatus(user.getStatus());
        statusLog.setChangeStatus(changeStatus);
        //记录状态变更记录
        userStatusLogMapper.insert(statusLog);
        //修改用户状态
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setStatus(changeStatus);
        userMapper.updateById(updateUser);
        log.info("用户状态记录, 用户状态修改: user={}, currentStatus={}, changeStatus={}", userId, user.getStatus(), changeStatus);
    }
}
