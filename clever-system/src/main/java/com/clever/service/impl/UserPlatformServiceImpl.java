package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.Platform;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.service.PlatformService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserPlatformMapper;
import com.clever.bean.system.UserPlatform;
import com.clever.service.UserPlatformService;

import javax.annotation.Resource;

/**
 * 用户-平台服务
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
@Service
public class UserPlatformServiceImpl implements UserPlatformService {

    private final static Logger log = LoggerFactory.getLogger(UserPlatformServiceImpl.class);

    @Resource
    private UserPlatformMapper userPlatformMapper;

    @Resource
    private PlatformService platformService;

    /**
     * 分页查询用户-平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param platformId 平台id
     * @return Page<UserPlatform>
     */
    @Override
    public Page<UserPlatform> selectPage(Integer pageNumber, Integer pageSize, String userId, Integer platformId) {
        QueryWrapper<UserPlatform> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        return userPlatformMapper.selectPage(new Page<UserPlatform>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据id获取用户-平台信息
     *
     * @param id id
     * @return UserPlatform 用户-平台信息
     */
    @Override
    public UserPlatform selectById(String id) {
        return userPlatformMapper.selectById(id);
    }

    /**
     * 根据用户id获取用户-平台列表
     *
     * @param userId 用户id
     * @return List<UserPlatform> 用户-平台列表
     */
    @Override
    public List<UserPlatform> selectListByUserId(String userId) {
        return userPlatformMapper.selectList(new QueryWrapper<UserPlatform>().eq("user_id", userId).orderByAsc("id"));
    }

    /**
     * 根据平台id获取用户-平台列表
     *
     * @param platformId 平台id
     * @return List<UserPlatform> 用户-平台列表
     */
    @Override
    public List<UserPlatform> selectListByPlatformId(Integer platformId) {
        return userPlatformMapper.selectList(new QueryWrapper<UserPlatform>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 保存用户-平台信息
     *
     * @param userPlatform 用户-平台实体信息
     * @param onlineUser   当前登录用户
     */
    @Override
    public void save(UserPlatform userPlatform, OnlineUser onlineUser) {
        if (StringUtils.isBlank(userPlatform.getId())) {
            userPlatformMapper.insert(userPlatform);
            log.info("用户-平台, 用户-平台信息创建成功: userId={}, userPlatformId={}", onlineUser.getId(), userPlatform.getId());
        } else {
            userPlatformMapper.updateById(userPlatform);
            log.info("用户-平台, 用户-平台信息修改成功: userId={}, userPlatformId={}", onlineUser.getId(), userPlatform.getId());
        }
    }

    /**
     * 根据id删除用户-平台信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userPlatformMapper.deleteById(id);
        log.info("用户-平台, 用户-平台信息删除成功: userId={}, userPlatformId={}", onlineUser.getId(), id);
    }

    /**
     * 根据id列表删除用户-平台信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userPlatformMapper.deleteBatchIds(ids);
        log.info("用户-平台, 用户-平台信息批量删除成功: userId={}, count={}, userPlatformIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户id删除用户-平台
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userPlatformMapper.delete(new QueryWrapper<UserPlatform>().eq("user_id", userId));
        log.info("用户-平台, 用户-平台信息根据用户id删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 根据平台id删除用户-平台
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        userPlatformMapper.delete(new QueryWrapper<UserPlatform>().eq("platform_id", platformId));
        log.info("用户-平台, 用户-平台信息根据平台id删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 加入平台
     *
     * @param userId 用户id
     * @param code   邀请码
     */
    @Override
    public void joinPlatform(String userId, String code) {
        Platform platform = platformService.selectByCode(code);
        if (platform == null) {
            log.info("加入平台失败，邀请码无效，userId={}, code={}", userId, code);
            throw new BaseException(ConstantException.DATA_INVALID.format("邀请码"));

        }
        QueryWrapper<UserPlatform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("platform_id", platform.getId());
        if (userPlatformMapper.selectCount(queryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.reset("您已在该平台"));
        }
        UserPlatform userPlatform = new UserPlatform();
        userPlatform.setPlatformId(platform.getId());
        userPlatform.setUserId(userId);
        userPlatformMapper.insert(userPlatform);
        log.info("加入平台成功，userId={}, platformId={}", userId, platform.getId());
    }

    /**
     * 退出平台
     *
     * @param userId     用户id
     * @param platformId 平台id
     */
    @Override
    public void exitPlatform(String userId, Integer platformId) {
        QueryWrapper<UserPlatform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("platform_id", platformId);
        if (userPlatformMapper.selectCount(queryWrapper) == 0) {
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("您已不在该平台"));
        }
        userPlatformMapper.delete(queryWrapper);
        log.info("退出平台成功，userId={}, platformId={}", userId, platformId);

    }
}
