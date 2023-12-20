package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Area;

/**
 * 城区地址服务接口
 *
 * @Author xixi
 * @Date 2023-12-20 09:33:24
 */
public interface AreaService {

    /**
     * 分页查询城区地址列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       地区名称
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return Page<Area>
     */
    Page<Area> selectPage(Integer pageNumber, Integer pageSize, String name, String cityId, String provinceId);

    /**
     * 根据地区编号获取城区地址信息
     *
     * @param id 地区编号
     * @return List<Area> 城区地址信息
     */
    Area selectById(Integer id);

    /**
     * 根据城市编号获取城区地址列表
     *
     * @param cityId 城市编号
     * @return List<Area> 城区地址列表
     */
    List<Area> selectListByCityId(Integer cityId);

    /**
     * 根据省份编号获取城区地址列表
     *
     * @param provinceId 省份编号
     * @return List<Area> 城区地址列表
     */
    List<Area> selectListByProvinceId(Integer provinceId);

    /**
     * 保存城区地址信息
     *
     * @param area       城区地址实体信息
     * @param onlineUser 当前登录用户
     */
    void save(Area area, OnlineUser onlineUser);

    /**
     * 根据地区编号获取城区地址列表
     *
     * @param id         地区编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据地区编号列表删除城区地址信息
     *
     * @param ids        地区编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

    /**
     * 根据城市编号删除城区地址
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    void deleteByCityId(String cityId, OnlineUser onlineUser);

    /**
     * 根据省份编号删除城区地址
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    void deleteByProvinceId(String provinceId, OnlineUser onlineUser);
}
