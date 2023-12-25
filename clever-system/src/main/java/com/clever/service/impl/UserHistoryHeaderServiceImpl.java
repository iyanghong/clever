package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserHistoryHeaderMapper;
import com.clever.bean.system.UserHistoryHeader;
import com.clever.service.UserHistoryHeaderService;

import javax.annotation.Resource;

/**
 * 用户历史头像表服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class UserHistoryHeaderServiceImpl implements UserHistoryHeaderService {

    private final static Logger log = LoggerFactory.getLogger(UserHistoryHeaderServiceImpl.class);

    @Resource
    private UserHistoryHeaderMapper userHistoryHeaderMapper;

    /**
     * 分页查询用户历史头像表列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param diskId     磁盘id
     * @return Page<UserHistoryHeader>
     */
    @Override
    public Page<UserHistoryHeader> selectPage(Integer pageNumber, Integer pageSize, String userId, String diskId) {
        QueryWrapper<UserHistoryHeader> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(diskId)) {
            queryWrapper.eq("disk_id", diskId);
        }
        return userHistoryHeaderMapper.selectPage(new Page<UserHistoryHeader>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据历史头像id获取用户历史头像表
     *
     * @param id 历史头像id
     * @return UserHistoryHeader 用户历史头像表信息
     */
    @Override
    public UserHistoryHeader selectById(String id) {
        return userHistoryHeaderMapper.selectById(id);
    }

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<UserHistoryHeader> 用户历史头像表列表
     */
    @Override
    public List<UserHistoryHeader> selectListByUserId(String userId) {
        return userHistoryHeaderMapper.selectList(new QueryWrapper<UserHistoryHeader>().eq("user_id", userId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据磁盘id获取列表
     *
     * @param diskId 磁盘id
     * @return List<UserHistoryHeader> 用户历史头像表列表
     */
    @Override
    public List<UserHistoryHeader> selectListByDiskId(String diskId) {
        return userHistoryHeaderMapper.selectList(new QueryWrapper<UserHistoryHeader>().eq("disk_id", diskId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 新建后的用户历史头像表信息
     */
    @Override
    public UserHistoryHeader create(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser) {
        userHistoryHeaderMapper.insert(userHistoryHeader);
        log.info("用户历史头像表, 用户历史头像表信息创建成功: userId={}, userHistoryHeaderId={}", onlineUser.getId(), userHistoryHeader.getId());
        return userHistoryHeader;
    }

    /**
     * 修改用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 修改后的用户历史头像表信息
     */
    @Override
    public UserHistoryHeader update(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser) {
        userHistoryHeaderMapper.updateById(userHistoryHeader);
        log.info("用户历史头像表, 用户历史头像表信息修改成功: userId={}, userHistoryHeaderId={}", onlineUser.getId(), userHistoryHeader.getId());
        return userHistoryHeader;
    }

    /**
     * 保存用户历史头像表
     *
     * @param userHistoryHeader 用户历史头像表实体信息
     * @param onlineUser        当前登录用户
     * @return UserHistoryHeader 保存后的用户历史头像表信息
     */
    @Override
    public UserHistoryHeader save(UserHistoryHeader userHistoryHeader, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(userHistoryHeader.getId())) {
            return create(userHistoryHeader, onlineUser);
        }
        return update(userHistoryHeader, onlineUser);
    }

    /**
     * 根据历史头像id删除用户历史头像表信息
     *
     * @param id         历史头像id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userHistoryHeaderMapper.deleteById(id);
        log.info("用户历史头像表, 用户历史头像表信息删除成功: userId={}, userHistoryHeaderId={}", onlineUser.getId(), id);
    }

    /**
     * 根据历史头像id列表删除用户历史头像表信息
     *
     * @param ids        历史头像id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userHistoryHeaderMapper.deleteBatchIds(ids);
        log.info("用户历史头像表, 用户历史头像表信息批量删除成功: userId={}, count={}, userHistoryHeaderIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户id删除
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userHistoryHeaderMapper.delete(new QueryWrapper<UserHistoryHeader>().eq("user_id", userId));
        log.info("用户历史头像表, 用户历史头像表信息根据userId删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 根据磁盘id删除
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByDiskId(String diskId, OnlineUser onlineUser) {
        userHistoryHeaderMapper.delete(new QueryWrapper<UserHistoryHeader>().eq("disk_id", diskId));
        log.info("用户历史头像表, 用户历史头像表信息根据diskId删除成功: userId={}, diskId={}", onlineUser.getId(), diskId);
    }
}
