package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserStatusLogMapper;
import com.clever.bean.system.UserStatusLog;
import com.clever.service.UserStatusLogService;

import javax.annotation.Resource;

/**
 * UserStatusLog服务
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Service
public class UserStatusLogServiceImpl implements UserStatusLogService {

    private final static Logger log = LoggerFactory.getLogger(UserStatusLogServiceImpl.class);

    @Resource
    private UserStatusLogMapper userStatusLogMapper;

    /**
     * 分页查询列表
     *
     * @param pageNumber    页码
     * @param pageSize      每页记录数
     * @param userId        用户
     * @param currentStatus 当前状态
     * @param changeStatus  变更后状态
     * @return Page<UserStatusLog>
     */
    @Override
    public Page<UserStatusLog> selectPage(Integer pageNumber, Integer pageSize, String userId, String currentStatus, String changeStatus) {
        QueryWrapper<UserStatusLog> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(currentStatus)) {
            queryWrapper.eq("current_status", currentStatus);
        }
        if (StringUtils.isNotBlank(changeStatus)) {
            queryWrapper.eq("change_status", changeStatus);
        }
        return userStatusLogMapper.selectPage(new Page<UserStatusLog>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据自增id获取信息
     *
     * @param id 自增id
     * @return List<UserStatusLog> user_status_log信息
     */
    @Override
    public UserStatusLog selectById(String id) {
        return userStatusLogMapper.selectById(id);
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<UserStatusLog> user_status_log列表
     */
    @Override
    public List<UserStatusLog> getListByUserId(String userId) {
        return userStatusLogMapper.selectList(new QueryWrapper<UserStatusLog>().eq("user_id", userId).orderByAsc("id"));
    }

    /**
     * 保存user_status_log信息
     *
     * @param userStatusLog user_status_log实体信息
     * @param onlineUser    当前登录用户
     */
    @Override
    public void save(UserStatusLog userStatusLog, OnlineUser onlineUser) {
        if (StringUtils.isBlank(userStatusLog.getId())) {
            userStatusLogMapper.insert(userStatusLog);
            log.info(", 信息创建成功: userId={}, userStatusLogId={}", onlineUser.getId(), userStatusLog.getId());
        } else {
            userStatusLogMapper.updateById(userStatusLog);
            log.info(", 信息修改成功: userId={}, userStatusLogId={}", onlineUser.getId(), userStatusLog.getId());
        }
    }

    /**
     * 根据自增id获取列表
     *
     * @param id         自增id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userStatusLogMapper.deleteById(id);
        log.info(", 信息删除成功: userId={}, userStatusLogId={}", onlineUser.getId(), id);
    }

    /**
     * 根据自增id列表删除信息
     *
     * @param ids        自增id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userStatusLogMapper.deleteBatchIds(ids);
        log.info(", 信息批量删除成功: userId={}, count={}, userStatusLogIds={}", onlineUser.getId(), ids.size(), ids.toString());
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
        log.info(", 信息根据用户删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }
}
