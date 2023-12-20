package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.User;

/**
 * 用户服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
public interface UserService {

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
    Page<User> selectPage(Integer pageNumber, Integer pageSize, String account, String email, String phone, String nickname, String diskId, Integer status, Integer gender);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return User 用户信息
     */
    User selectById(String id);

    /**
     * 根据磁盘id获取用户列表
     *
     * @param diskId 磁盘id
     * @return List<User> 用户列表
     */
    List<User> selectListByDiskId(String diskId);

    /**
     * 保存用户信息
     *
     * @param user       用户实体信息
     * @param onlineUser 当前登录用户
     */
    void save(User user, OnlineUser onlineUser);

    /**
     * 根据用户id删除用户信息
     *
     * @param id         用户id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据用户id列表删除用户信息
     *
     * @param ids        用户id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据磁盘id删除用户
     *
     * @param diskId     磁盘id
     * @param onlineUser 当前登录用户
     */
    void deleteByDiskId(String diskId, OnlineUser onlineUser);
}
