package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.IpAttribution;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.clever.mapper.LogUserLoginMapper;
import com.clever.bean.system.LogUserLogin;
import com.clever.service.LogUserLoginService;

import javax.annotation.Resource;

/**
 * 用户登录日志服务
 *
 * @Author xixi
 * @Date 2023-12-27 10:45:51
 */
@Service
public class LogUserLoginServiceImpl implements LogUserLoginService {

    private final static Logger log = LoggerFactory.getLogger(LogUserLoginServiceImpl.class);

    @Resource
    private LogUserLoginMapper logUserLoginMapper;

    /**
     * 分页查询用户登录日志列表
     *
     * @param pageNumber  页码
     * @param pageSize    每页记录数
     * @param platformId  平台id
     * @param userId      用户
     * @param addressCode 登录地址编码
     * @return Page<LogUserLogin>
     */
    @Override
    public Page<LogUserLogin> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String userId, String addressCode) {
        QueryWrapper<LogUserLogin> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(addressCode)) {
            queryWrapper.eq("address_code", addressCode);
        }
        return logUserLoginMapper.selectPage(new Page<LogUserLogin>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据自增id获取用户登录日志
     *
     * @param id 自增id
     * @return LogUserLogin 用户登录日志信息
     */
    @Override
    public LogUserLogin selectById(String id) {
        return logUserLoginMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<LogUserLogin> 用户登录日志列表
     */
    @Override
    public List<LogUserLogin> selectListByPlatformId(Integer platformId) {
        return logUserLoginMapper.selectList(new QueryWrapper<LogUserLogin>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 根据用户获取列表
     *
     * @param userId 用户
     * @return List<LogUserLogin> 用户登录日志列表
     */
    @Override
    public List<LogUserLogin> selectListByUserId(String userId) {
        return logUserLoginMapper.selectList(new QueryWrapper<LogUserLogin>().eq("user_id", userId).orderByAsc("id"));
    }

    /**
     * 新建用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 新建后的用户登录日志信息
     */
    @Override
    public LogUserLogin create(LogUserLogin logUserLogin, OnlineUser onlineUser) {
        logUserLoginMapper.insert(logUserLogin);
        log.info("用户登录日志, 用户登录日志信息创建成功: userId={}, logUserLoginId={}", onlineUser.getId(), logUserLogin.getId());
        return logUserLogin;
    }

    /**
     * 修改用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 修改后的用户登录日志信息
     */
    @Override
    public LogUserLogin update(LogUserLogin logUserLogin, OnlineUser onlineUser) {
        logUserLoginMapper.updateById(logUserLogin);
        log.info("用户登录日志, 用户登录日志信息修改成功: userId={}, logUserLoginId={}", onlineUser.getId(), logUserLogin.getId());
        return logUserLogin;
    }

    /**
     * 保存用户登录日志
     *
     * @param logUserLogin 用户登录日志实体信息
     * @param onlineUser   当前登录用户
     * @return LogUserLogin 保存后的用户登录日志信息
     */
    @Override
    public LogUserLogin save(LogUserLogin logUserLogin, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(logUserLogin.getId())) {
            return create(logUserLogin, onlineUser);
        }
        return update(logUserLogin, onlineUser);
    }

    /**
     * 根据自增id删除用户登录日志信息
     *
     * @param id         自增id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        logUserLoginMapper.deleteById(id);
        log.info("用户登录日志, 用户登录日志信息删除成功: userId={}, logUserLoginId={}", onlineUser.getId(), id);
    }

    /**
     * 根据自增id列表删除用户登录日志信息
     *
     * @param ids        自增id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        logUserLoginMapper.deleteBatchIds(ids);
        log.info("用户登录日志, 用户登录日志信息批量删除成功: userId={}, count={}, logUserLoginIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        logUserLoginMapper.delete(new QueryWrapper<LogUserLogin>().eq("platform_id", platformId));
        log.info("用户登录日志, 用户登录日志信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据用户删除
     *
     * @param userId     用户
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByUserId(String userId, OnlineUser onlineUser) {
        logUserLoginMapper.delete(new QueryWrapper<LogUserLogin>().eq("user_id", userId));
        log.info("用户登录日志, 用户登录日志信息根据userId删除成功: userId={}, userId={}", onlineUser.getId(), userId);
    }

    /**
     * 记录用户登录
     *
     * @param userId        用户
     * @param platformId    平台id
     * @param ip            ip地址
     * @param userAgent     userAgent
     * @param loginTime     登录时间
     * @param ipAttribution ip归属地
     */
    @Override
    public void recordUserLogin(String userId, Integer platformId, String ip, String userAgent, Date loginTime, IpAttribution ipAttribution) {
        LogUserLogin logUserLogin = new LogUserLogin();
        logUserLogin.setUserId(userId);
        logUserLogin.setPlatformId(platformId);
        logUserLogin.setIp(ip);
        logUserLogin.setUserAgent(userAgent);
        logUserLogin.setLoginTime(loginTime);

        logUserLogin.setNation(ipAttribution.getNation());
        logUserLogin.setProvince(ipAttribution.getProvince());
        logUserLogin.setCity(ipAttribution.getCity());
        logUserLogin.setDistrict(ipAttribution.getDistrict());
        logUserLogin.setIsp(ipAttribution.getIsp());
        logUserLogin.setLongitude(ipAttribution.getLongitude());
        logUserLogin.setLatitude(ipAttribution.getLatitude());
        logUserLogin.setAddressCode(ipAttribution.getAddressCode());

        logUserLoginMapper.insert(logUserLogin);
    }
}
