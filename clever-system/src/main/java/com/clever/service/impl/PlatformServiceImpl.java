package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import com.clever.mapper.PlatformMapper;
import com.clever.bean.system.Platform;
import com.clever.service.PlatformService;

import javax.annotation.Resource;

/**
 * 平台服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class PlatformServiceImpl implements PlatformService {

    private final static Logger log = LoggerFactory.getLogger(PlatformServiceImpl.class);

    @Resource
    private PlatformMapper platformMapper;

    /**
     * 分页查询平台列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       平台名称
     * @param code       邀请码
     * @return Page<Platform>
     */
    @Override
    public Page<Platform> selectPage(Integer pageNumber, Integer pageSize, String name, String code) {
        QueryWrapper<Platform> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.eq("code", code);
        }
        return platformMapper.selectPage(new Page<Platform>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据平台id获取平台
     *
     * @param id 平台id
     * @return Platform 平台信息
     */
    @Override
    public Platform selectById(Integer id) {
        return platformMapper.selectById(id);
    }

    /**
     * 根据邀请码获取信息
     *
     * @param code 邀请码
     * @return Platform 平台信息
     */
    @Override
    public Platform selectByCode(String code) {
        return platformMapper.selectOne(new QueryWrapper<Platform>().eq("code", code));
    }

    /**
     * 新建平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 新建后的平台信息
     */
    @Override
    public Platform create(Platform platform, OnlineUser onlineUser) {
        if (StringUtils.isBlank(platform.getMaster())){
            platform.setMaster(onlineUser.getId());
        }
        QueryWrapper<Platform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", platform.getName());
        queryWrapper.eq("master", platform.getMaster());
        if (platformMapper.selectCount(queryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("您名下该平台名称"));
        }
        // 检查邀请码，保证唯一
        String code = RandomStringUtils.randomAlphabetic(6).toUpperCase(Locale.ROOT);
        while (platformMapper.selectCount(new QueryWrapper<Platform>().eq("code", code)) > 0) {
            code = RandomStringUtils.randomAlphabetic(6).toUpperCase(Locale.ROOT);
        }
        platform.setCode(code);
        platformMapper.insert(platform);
        log.info("平台, 平台信息创建成功: userId={}, platformId={}", onlineUser.getId(), platform.getId());
        return platform;
    }

    /**
     * 修改平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 修改后的平台信息
     */
    @Override
    public Platform update(Platform platform, OnlineUser onlineUser) {
        QueryWrapper<Platform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", platform.getName());
        queryWrapper.eq("master", platform.getMaster());
        queryWrapper.ne("id", platform.getId());
        if (platformMapper.selectCount(queryWrapper) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("您名下该平台名称"));
        }
        platformMapper.updateById(platform);
        log.info("平台, 平台信息修改成功: userId={}, platformId={}", onlineUser.getId(), platform.getId());
        return platform;
    }

    /**
     * 保存平台
     *
     * @param platform   平台实体信息
     * @param onlineUser 当前登录用户
     * @return Platform 保存后的平台信息
     */
    @Override
    public Platform save(Platform platform, OnlineUser onlineUser) {
        if (platform.getId() != null) {
            return create(platform, onlineUser);
        }
        return update(platform, onlineUser);
    }

    /**
     * 根据平台id删除平台信息
     *
     * @param id         平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(Integer id, OnlineUser onlineUser) {
        platformMapper.deleteById(id);
        log.info("平台, 平台信息删除成功: userId={}, platformId={}", onlineUser.getId(), id);
    }

    /**
     * 根据平台id列表删除平台信息
     *
     * @param ids        平台id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
        platformMapper.deleteBatchIds(ids);
        log.info("平台, 平台信息批量删除成功: userId={}, count={}, platformIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据用户id获取平台列表
     *
     * @param userId 用户id
     * @return List<Platform> 平台列表
     */
    @Override
    public List<Platform> selectByUserId(String userId) {
        return platformMapper.selectByUserId(userId);
    }

}
