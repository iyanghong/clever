package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.UserMapper;
import com.clever.bean.system.User;
import com.clever.service.UserService;

import javax.annotation.Resource;

/**
 * 用户服务
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

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
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return User 用户信息
     */
    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据磁盘id获取用户列表
     *
     * @param diskId 磁盘id
     * @return List<User> 用户列表
     */
    @Override
    public List<User> selectListByDiskId(String diskId) {
        return userMapper.selectList(new QueryWrapper<User>().eq("disk_id", diskId).orderByAsc("id"));
    }

    /**
     * 保存用户信息
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     */
    @Override
    public void save(User user, OnlineUser onlineUser) {
        if (StringUtils.isBlank(user.getId())) {
            userMapper.insert(user);
            log.info("用户, 用户信息创建成功: userId={}, userId={}", onlineUser.getId(), user.getId());
        } else {
            userMapper.updateById(user);
            log.info("用户, 用户信息修改成功: userId={}, userId={}", onlineUser.getId(), user.getId());
        }
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
     * 根据磁盘id删除用户
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByDiskId(String diskId, OnlineUser onlineUser) {
        userMapper.delete(new QueryWrapper<User>().eq("disk_id", diskId));
        log.info("用户, 用户信息根据磁盘id删除成功: userId={}, diskId={}", onlineUser.getId(), diskId);
    }
}
