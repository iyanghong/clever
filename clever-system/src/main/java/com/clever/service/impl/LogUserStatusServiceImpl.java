package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.User;
import com.clever.bean.system.UserStatusLog;
import com.clever.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.clever.mapper.LogUserStatusMapper;
import com.clever.bean.system.LogUserStatus;
import com.clever.service.LogUserStatusService;

import javax.annotation.Resource;

/**
 * 用户状态日志服务
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@Service
public class LogUserStatusServiceImpl implements LogUserStatusService {

    private final static Logger log = LoggerFactory.getLogger(LogUserStatusServiceImpl.class);

    @Resource
    private LogUserStatusMapper logUserStatusMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询用户状态日志列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param platformId    平台id
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return Page<LogUserStatus>
     */
    @Override
    public Page<LogUserStatus> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String userId, Integer currentStatus, Integer changeStatus) {
        QueryWrapper<LogUserStatus> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (currentStatus != null) {
            queryWrapper.eq("current_status", currentStatus);
        }
        if (changeStatus != null) {
            queryWrapper.eq("change_status", changeStatus);
        }
        return logUserStatusMapper.selectPage(new Page<LogUserStatus>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据自增id获取用户状态日志
     *
     * @param id 自增id
     * @return LogUserStatus 用户状态日志信息
     */
    @Override
    public LogUserStatus selectById(String id) {
        return logUserStatusMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<LogUserStatus> 用户状态日志列表
     */
    @Override
    public List<LogUserStatus> selectListByPlatformId(Integer platformId) {
        return logUserStatusMapper.selectList(new QueryWrapper<LogUserStatus>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<LogUserStatus> 用户状态日志列表
     */
    @Override
    public List<LogUserStatus> selectListByUserId(String userId) {
        return logUserStatusMapper.selectList(new QueryWrapper<LogUserStatus>().eq("user_id", userId).orderByAsc("id"));
    }

    /**
     * 新建用户状态日志
     *
     * @param logUserStatus 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return LogUserStatus 新建后的用户状态日志信息
     */
    @Override
    public LogUserStatus create(LogUserStatus logUserStatus, OnlineUser onlineUser) {
        logUserStatusMapper.insert(logUserStatus);
        log.info("用户状态日志, 用户状态日志信息创建成功: userId={}, logUserStatusId={}", onlineUser.getId(), logUserStatus.getId());
        return logUserStatus;
    }

    /**
     * 修改用户状态日志
     *
     * @param logUserStatus 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return LogUserStatus 修改后的用户状态日志信息
     */
    @Override
    public LogUserStatus update(LogUserStatus logUserStatus, OnlineUser onlineUser) {
        logUserStatusMapper.updateById(logUserStatus);
        log.info("用户状态日志, 用户状态日志信息修改成功: userId={}, logUserStatusId={}", onlineUser.getId(), logUserStatus.getId());
        return logUserStatus;
    }

    /**
     * 保存用户状态日志
     *
     * @param logUserStatus 用户状态日志实体信息
     * @param onlineUser    当前登录用户
     * @return LogUserStatus 保存后的用户状态日志信息
     */
    @Override
    public LogUserStatus save(LogUserStatus logUserStatus, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(logUserStatus.getId())) {
            return create(logUserStatus, onlineUser);
        }
        return update(logUserStatus, onlineUser);
    }

    /**
     * 根据自增id删除用户状态日志信息
     *
     * @param id         自增id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        logUserStatusMapper.deleteById(id);
        log.info("用户状态日志, 用户状态日志信息删除成功: userId={}, logUserStatusId={}", onlineUser.getId(), id);
    }

    /**
     * 根据自增id列表删除用户状态日志信息
     *
     * @param ids        自增id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        logUserStatusMapper.deleteBatchIds(ids);
        log.info("用户状态日志, 用户状态日志信息批量删除成功: userId={}, count={}, logUserStatusIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        logUserStatusMapper.delete(new QueryWrapper<LogUserStatus>().eq("platform_id", platformId));
        log.info("用户状态日志, 用户状态日志信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        logUserStatusMapper.delete(new QueryWrapper<LogUserStatus>().eq("user_id", userId));
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
        LogUserStatus statusLog = new LogUserStatus();
        statusLog.setUserId(userId);
        statusLog.setDuration(duration);
        statusLog.setRemark(remake);
        statusLog.setCurrentStatus(user.getStatus());
        statusLog.setChangeStatus(changeStatus);
        //记录状态变更记录
        logUserStatusMapper.insert(statusLog);
        //修改用户状态
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setStatus(changeStatus);
        userMapper.updateById(updateUser);
        log.info("用户状态记录, 用户状态修改: user={}, currentStatus={}, changeStatus={}", userId, user.getStatus(), changeStatus);
    }
}
