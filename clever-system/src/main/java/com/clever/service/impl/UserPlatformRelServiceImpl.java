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

import com.clever.mapper.UserPlatformRelMapper;
import com.clever.bean.system.UserPlatformRel;
import com.clever.service.UserPlatformRelService;

import javax.annotation.Resource;

/**
 * 用户-平台服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class UserPlatformRelServiceImpl implements UserPlatformRelService {

    private final static Logger log = LoggerFactory.getLogger(UserPlatformRelServiceImpl.class);

    @Resource
    private UserPlatformRelMapper userPlatformRelMapper;

    @Resource
    private PlatformService platformService;

    /**
     * 分页查询用户-平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param userId     用户id
     * @param platformId 平台id
     * @return Page<UserPlatformRel>
     */
    @Override
    public Page<UserPlatformRel> selectPage(Integer pageNumber, Integer pageSize, String userId, Integer platformId) {
        QueryWrapper<UserPlatformRel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        return userPlatformRelMapper.selectPage(new Page<UserPlatformRel>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据id获取用户-平台
     *
     * @param id id
     * @return UserPlatformRel 用户-平台信息
     */
    @Override
    public UserPlatformRel selectById(String id) {
        return userPlatformRelMapper.selectById(id);
    }

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    @Override
    public List<UserPlatformRel> selectListByUserId(String userId) {
        return userPlatformRelMapper.selectList(new QueryWrapper<UserPlatformRel>().eq("user_id", userId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<UserPlatformRel> 用户-平台列表
     */
    @Override
    public List<UserPlatformRel> selectListByPlatformId(Integer platformId) {
        return userPlatformRelMapper.selectList(new QueryWrapper<UserPlatformRel>().eq("platform_id", platformId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 新建后的用户-平台信息
     */
    @Override
    public UserPlatformRel create(UserPlatformRel userPlatformRel, OnlineUser onlineUser) {
        userPlatformRelMapper.insert(userPlatformRel);
        log.info("用户-平台, 用户-平台信息创建成功: userId={}, userPlatformRelId={}", onlineUser.getId(), userPlatformRel.getId());
        return userPlatformRel;
    }

    /**
     * 修改用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 修改后的用户-平台信息
     */
    @Override
    public UserPlatformRel update(UserPlatformRel userPlatformRel, OnlineUser onlineUser) {
        userPlatformRelMapper.updateById(userPlatformRel);
        log.info("用户-平台, 用户-平台信息修改成功: userId={}, userPlatformRelId={}", onlineUser.getId(), userPlatformRel.getId());
        return userPlatformRel;
    }

    /**
     * 保存用户-平台
     *
     * @param userPlatformRel 用户-平台实体信息
     * @param onlineUser      当前登录用户
     * @return UserPlatformRel 保存后的用户-平台信息
     */
    @Override
    public UserPlatformRel save(UserPlatformRel userPlatformRel, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(userPlatformRel.getId())) {
            return create(userPlatformRel, onlineUser);
        }
        return update(userPlatformRel, onlineUser);
    }

    /**
     * 根据id删除用户-平台信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        userPlatformRelMapper.deleteById(id);
        log.info("用户-平台, 用户-平台信息删除成功: userId={}, userPlatformRelId={}", onlineUser.getId(), id);
    }

    /**
     * 根据id列表删除用户-平台信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        userPlatformRelMapper.deleteBatchIds(ids);
        log.info("用户-平台, 用户-平台信息批量删除成功: userId={}, count={}, userPlatformRelIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户id删除
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        userPlatformRelMapper.delete(new QueryWrapper<UserPlatformRel>().eq("user_id", userId));
        log.info("用户-平台, 用户-平台信息根据userId删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        userPlatformRelMapper.delete(new QueryWrapper<UserPlatformRel>().eq("platform_id", platformId));
        log.info("用户-平台, 用户-平台信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
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
        QueryWrapper<UserPlatformRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("platform_id", platform.getId());
        if (userPlatformRelMapper.selectCount(queryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.reset("您已在该平台"));
        }
        UserPlatformRel userPlatform = new UserPlatformRel();
        userPlatform.setPlatformId(platform.getId());
        userPlatform.setUserId(userId);
        userPlatformRelMapper.insert(userPlatform);
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
        QueryWrapper<UserPlatformRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("platform_id", platformId);
        if (userPlatformRelMapper.selectCount(queryWrapper) == 0) {
            throw new BaseException(ConstantException.DATA_NOT_EXIST.reset("您已不在该平台"));
        }
        userPlatformRelMapper.delete(queryWrapper);
        log.info("退出平台成功，userId={}, platformId={}", userId, platformId);

    }
}
