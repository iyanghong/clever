package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.City;

/**
 * 城市服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface CityService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       城市名称
     * @param provinceId 省份编号
     * @return Page<City>
     */
    Page<City> selectPage(Integer pageNumber, Integer pageSize, String name, Integer provinceId);

    /**
     * 根据城市编号获取城市
     *
     * @param id 城市编号
     * @return City 城市编号信息
     */
    City selectById(Integer id);

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<City> 城市列表
     */
    List<City> selectListByProvinceId(Integer provinceId);

    /**
     * 新建城市
     *
     * @param city       城市实体信息
     * @param onlineUser 当前登录用户
     * @return City 新建后的城市信息
     */
    City create(City city, OnlineUser onlineUser);

    /**
     * 修改城市
     *
     * @param city       城市实体信息
     * @param onlineUser 当前登录用户
     * @return City 修改后的城市信息
     */
    City update(City city, OnlineUser onlineUser);

    /**
     * 保存城市
     *
     * @param city       城市实体信息
     * @param onlineUser 当前登录用户
     * @return City 保存后的城市信息
     */
    City save(City city, OnlineUser onlineUser);

    /**
     * 根据城市编号删除信息
     *
     * @param id         城市编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据城市编号列表删除信息
     *
     * @param ids        城市编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

    /**
     * 根据省份编号删除
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    void deleteByProvinceId(Integer provinceId, OnlineUser onlineUser);

}
