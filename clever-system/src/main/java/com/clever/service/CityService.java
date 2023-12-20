package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.City;

/**
 * 城市服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
public interface CityService {

    /**
     * 分页查询城市列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       城市名称
     * @param provinceId 省份编号
     * @return Page<City>
     */
    Page<City> selectPage(Integer pageNumber, Integer pageSize, String name, Integer provinceId);

    /**
     * 根据城市编号获取城市信息
     *
     * @param id 城市编号
     * @return City 城市信息
     */
    City selectById(Integer id);

    /**
     * 根据省份编号获取城市列表
     *
     * @param provinceId 省份编号
     * @return List<City> 城市列表
     */
    List<City> selectListByProvinceId(Integer provinceId);

    /**
     * 保存城市信息
     *
     * @param city       城市实体信息
     * @param onlineUser 当前登录用户
     */
    void save(City city, OnlineUser onlineUser);

    /**
     * 根据城市编号删除城市信息
     *
     * @param id         城市编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据城市编号列表删除城市信息
     *
     * @param ids        城市编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

    /**
     * 根据省份编号删除城市
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    void deleteByProvinceId(String provinceId, OnlineUser onlineUser);
}
