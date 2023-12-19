package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.clever.mapper.ThirdAccountMapper;
import com.clever.bean.system.ThirdAccount;
import com.clever.service.ThirdAccountService;
import javax.annotation.Resource;

/**
 * 第三方平台账号服务
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
@Service
public class ThirdAccountServiceImpl implements ThirdAccountService {

	private final static Logger log = LoggerFactory.getLogger(ThirdAccountServiceImpl.class);

	@Resource
	private ThirdAccountMapper thirdAccountMapper;

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
	@Override
	public Page<ThirdAccount> selectPage(Integer pageNumber, Integer pageSize, String type, String openId, String nickname, String userId) {
		QueryWrapper<ThirdAccount> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(type)) {
			queryWrapper.eq("type", type);
		}
		if (StringUtils.isNotBlank(openId)) {
			queryWrapper.eq("open_id", openId);
		}
		if (StringUtils.isNotBlank(nickname)) {
			queryWrapper.eq("nickname", nickname);
		}
		if (StringUtils.isNotBlank(userId)) {
			queryWrapper.eq("user_id", userId);
		}
		return thirdAccountMapper.selectPage(new Page<ThirdAccount>(pageNumber, pageSize), queryWrapper);
	}

	/**
	 * 根据id获取第三方平台账号信息
	 * @param id id
	 * @return List<ThirdAccount> 第三方平台账号信息
	 */
	@Override
	public ThirdAccount selectById(String id) {
		return thirdAccountMapper.selectById(id);
	}

	/**
	 * 根据open_id获取第三方平台账号列表
	 * @param openId open_id
	 * @return List<ThirdAccount> 第三方平台账号列表
	 */
	@Override
	public List<ThirdAccount> getListByOpenId(String openId) {
		return thirdAccountMapper.selectList(new QueryWrapper<ThirdAccount>().eq("open_id", openId).orderByAsc("id"));
	}

	/**
	 * 根据用户id获取第三方平台账号列表
	 * @param userId 用户id
	 * @return List<ThirdAccount> 第三方平台账号列表
	 */
	@Override
	public List<ThirdAccount> getListByUserId(String userId) {
		return thirdAccountMapper.selectList(new QueryWrapper<ThirdAccount>().eq("user_id", userId).orderByAsc("id"));
	}

	/**
	 * 保存第三方平台账号信息
	 * @param thirdAccount 第三方平台账号实体信息
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void save(ThirdAccount thirdAccount, OnlineUser onlineUser) {
		if (StringUtils.isBlank(thirdAccount.getId())) {
			thirdAccountMapper.insert(thirdAccount);
			log.info("第三方平台账号, 第三方平台账号信息创建成功: userId={}, thirdAccountId={}", onlineUser.getId(), thirdAccount.getId());
		} else {
			thirdAccountMapper.updateById(thirdAccount);
			log.info("第三方平台账号, 第三方平台账号信息修改成功: userId={}, thirdAccountId={}", onlineUser.getId(), thirdAccount.getId());
		}
	}

	/**
	 * 根据id获取第三方平台账号列表
	 * @param id id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void delete(String id, OnlineUser onlineUser) {
		thirdAccountMapper.deleteById(id);
		log.info("第三方平台账号, 第三方平台账号信息删除成功: userId={}, thirdAccountId={}", onlineUser.getId(), id);
	}

	/**
	 * 根据id列表删除第三方平台账号信息
	 * @param ids id列表
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
		thirdAccountMapper.deleteBatchIds(ids);
		log.info("第三方平台账号, 第三方平台账号信息批量删除成功: userId={}, count={}, thirdAccountIds={}", onlineUser.getId(), ids.size(), ids.toString());
	}

	/**
	 * 根据open_id删除第三方平台账号
	 * @param openId open_id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByOpenId(String openId, OnlineUser onlineUser) {
		thirdAccountMapper.delete(new QueryWrapper<ThirdAccount>().eq("open_id", openId));
		log.info("第三方平台账号, 第三方平台账号信息根据open_id删除成功: userId={}, openId={}", onlineUser.getId(), openId);
	}

	/**
	 * 根据用户id删除第三方平台账号
	 * @param userId 用户id
	 * @param onlineUser 当前登录用户
	 */
	@Override
	public void deleteByUserId(String userId, OnlineUser onlineUser) {
		thirdAccountMapper.delete(new QueryWrapper<ThirdAccount>().eq("user_id", userId));
		log.info("第三方平台账号, 第三方平台账号信息根据用户id删除成功: userId={}, userId={}", onlineUser.getId(), userId);
	}
}
