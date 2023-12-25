package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.ThirdAccount;

/**
 * 第三方平台账号服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface ThirdAccountService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param type       第三方平台：qq|weixin|dingtalk|sina|tiktok
     * @param openId     open_id
     * @param nickname   第三方平台昵称
     * @param userId     用户id
     * @return Page<ThirdAccount>
     */
    Page<ThirdAccount> selectPage(Integer pageNumber, Integer pageSize, String type, String openId, String nickname, String userId);

    /**
     * 根据id获取第三方平台账号
     *
     * @param id id
     * @return ThirdAccount id信息
     */
    ThirdAccount selectById(String id);

    /**
     * 根据open_id获取列表
     *
     * @param openId open_id
     * @return List<ThirdAccount> 第三方平台账号列表
     */
    List<ThirdAccount> selectListByOpenId(String openId);

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<ThirdAccount> 第三方平台账号列表
     */
    List<ThirdAccount> selectListByUserId(String userId);

    /**
     * 新建第三方平台账号
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @param onlineUser   当前登录用户
     * @return ThirdAccount 新建后的第三方平台账号信息
     */
    ThirdAccount create(ThirdAccount thirdAccount, OnlineUser onlineUser);

    /**
     * 修改第三方平台账号
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @param onlineUser   当前登录用户
     * @return ThirdAccount 修改后的第三方平台账号信息
     */
    ThirdAccount update(ThirdAccount thirdAccount, OnlineUser onlineUser);

    /**
     * 保存第三方平台账号
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @param onlineUser   当前登录用户
     * @return ThirdAccount 保存后的第三方平台账号信息
     */
    ThirdAccount save(ThirdAccount thirdAccount, OnlineUser onlineUser);

    /**
     * 根据id删除信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据id列表删除信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据open_id删除
     *
     * @param openId     open_id
     * @param onlineUser 当前登录用户
     */
    void deleteByOpenId(String openId, OnlineUser onlineUser);

    /**
     * 根据用户id删除
     *
     * @param userId     用户id
     * @param onlineUser 当前登录用户
     */
    void deleteByUserId(String userId, OnlineUser onlineUser);

}
