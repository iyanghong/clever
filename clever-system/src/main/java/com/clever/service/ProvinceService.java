package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Province;

/**
 * 省份服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface ProvinceService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       省份名称
     * @return Page<Province>
     */
    Page<Province> selectPage(Integer pageNumber, Integer pageSize, String name);

    /**
     * 根据省份编号获取省份
     *
     * @param id 省份编号
     * @return Province 省份编号信息
     */
    Province selectById(Integer id);

    /**
     * 新建省份
     *
     * @param province   省份实体信息
     * @param onlineUser 当前登录用户
     * @return Province 新建后的省份信息
     */
    Province create(Province province, OnlineUser onlineUser);

    /**
     * 修改省份
     *
     * @param province   省份实体信息
     * @param onlineUser 当前登录用户
     * @return Province 修改后的省份信息
     */
    Province update(Province province, OnlineUser onlineUser);

    /**
     * 保存省份
     *
     * @param province   省份实体信息
     * @param onlineUser 当前登录用户
     * @return Province 保存后的省份信息
     */
    Province save(Province province, OnlineUser onlineUser);

    /**
     * 根据省份编号删除信息
     *
     * @param id         省份编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据省份编号列表删除信息
     *
     * @param ids        省份编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

}
