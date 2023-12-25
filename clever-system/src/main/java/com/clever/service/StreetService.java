package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Street;

/**
 * 街道服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface StreetService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       街道名称
     * @param areaId     地区编号
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return Page<Street>
     */
    Page<Street> selectPage(Integer pageNumber, Integer pageSize, String name, Integer areaId, Integer cityId, Integer provinceId);

    /**
     * 根据街道编号获取街道
     *
     * @param id 街道编号
     * @return Street 街道编号信息
     */
    Street selectById(Integer id);

    /**
     * 根据地区编号获取列表
     *
     * @param areaId 地区编号
     * @return List<Street> 街道列表
     */
    List<Street> selectListByAreaId(Integer areaId);

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Street> 街道列表
     */
    List<Street> selectListByCityId(Integer cityId);

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Street> 街道列表
     */
    List<Street> selectListByProvinceId(Integer provinceId);

    /**
     * 新建街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 新建后的街道信息
     */
    Street create(Street street, OnlineUser onlineUser);

    /**
     * 修改街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 修改后的街道信息
     */
    Street update(Street street, OnlineUser onlineUser);

    /**
     * 保存街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 保存后的街道信息
     */
    Street save(Street street, OnlineUser onlineUser);

    /**
     * 根据街道编号删除信息
     *
     * @param id         街道编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据街道编号列表删除信息
     *
     * @param ids        街道编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

    /**
     * 根据地区编号删除
     *
     * @param areaId     地区编号
     * @param onlineUser 当前登录用户
     */
    void deleteByAreaId(Integer areaId, OnlineUser onlineUser);

    /**
     * 根据城市编号删除
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    void deleteByCityId(Integer cityId, OnlineUser onlineUser);

    /**
     * 根据省份编号删除
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    void deleteByProvinceId(Integer provinceId, OnlineUser onlineUser);

}
