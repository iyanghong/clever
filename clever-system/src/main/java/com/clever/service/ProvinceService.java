package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import java.util.List;
import com.clever.bean.system.Province;

/**
 * 省份服务接口
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
public interface ProvinceService {

	/**
	 * 分页查询省份列表
	 * @param pageNumber 页码
	 * @param pageSize 每页记录数
	 * @param name 省份名称
	 * @return Page<Province>
	 */
	Page<Province> selectPage(Integer pageNumber, Integer pageSize, String name);

	/**
	 * 根据省份编号获取省份信息
	 * @param id 省份编号
	 * @return List<Province> 省份信息
	 */
	Province selectById(Integer id);

	/**
	 * 保存省份信息
	 * @param province 省份实体信息
	 * @param onlineUser 当前登录用户
	 */
	void save(Province province, OnlineUser onlineUser);

	/**
	 * 根据省份编号获取省份列表
	 * @param id 省份编号
	 * @param onlineUser 当前登录用户
	 */
	void delete(Integer id, OnlineUser onlineUser);

	/**
	 * 根据省份编号列表删除省份信息
	 * @param ids 省份编号列表
	 * @param onlineUser 当前登录用户
	 */
	void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);
}
