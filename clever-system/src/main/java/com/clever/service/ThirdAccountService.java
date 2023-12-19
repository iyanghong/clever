package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.ThirdAccount;

/**
 * 第三方平台账号服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
public interface ThirdAccountService {

	/**
	 * 分页查询第三方平台账号列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param type 第三方平台：qq|weixin|dingtalk|sina|tiktok
	 * @param openId open_id
	 * @param nickname 第三方平台昵称
	 * @param userId 用户id
	 * @return Page<ThirdAccount>
	 */
	Page<ThirdAccount> selectPage(Integer pageNumber, Integer pageSize, String type, String openId, String nickname, String userId);

	/**
	 * 根据id获取第三方平台账号信息
	 * @param id id
	 * @return List<ThirdAccount> 第三方平台账号信息
	 */
	ThirdAccount selectById(String id);

	/**
	 * 根据open_id获取第三方平台账号列表
	 * @param openId open_id
	 * @return List<ThirdAccount> 第三方平台账号列表
	 */
	List<ThirdAccount> getListByOpenId(String openId);

	/**
	 * 根据用户id获取第三方平台账号列表
	 * @param userId 用户id
	 * @return List<ThirdAccount> 第三方平台账号列表
	 */
	List<ThirdAccount> getListByUserId(String userId);

	/**
	 * 保存第三方平台账号信息
	 * @param thirdAccount 第三方平台账号实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(ThirdAccount thirdAccount, OnlineUser onlineUser);

	/**
	 * 根据id获取第三方平台账号列表
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	void delete(String id, OnlineUser onlineUser);

	/**
	 * 根据id列表删除第三方平台账号信息
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

	/**
	 * 根据open_id删除第三方平台账号
	 * @param openId open_id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByOpenId(String openId, OnlineUser onlineUser);

	/**
	 * 根据用户id删除第三方平台账号
	 * @param userId 用户id
	 * @param onlineUser 当前登录用户
	 */
	void deleteByUserId(String userId, OnlineUser onlineUser);
}
