package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Area;

/**
 * 城区地址服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface AreaService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       地区名称
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return Page<Area>
     */
    Page<Area> selectPage(Integer pageNumber, Integer pageSize, String name, Integer cityId, Integer provinceId);

    /**
     * 根据地区编号获取城区地址
     *
     * @param id 地区编号
     * @return Area 地区编号信息
     */
    Area selectById(Integer id);

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Area> 城区地址列表
     */
    List<Area> selectListByCityId(Integer cityId);

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Area> 城区地址列表
     */
    List<Area> selectListByProvinceId(Integer provinceId);

    /**
     * 新建城区地址
     *
     * @param area       城区地址实体信息
     * @param onlineUser 当前登录用户
     * @return Area 新建后的城区地址信息
     */
    Area create(Area area, OnlineUser onlineUser);

    /**
     * 修改城区地址
     *
     * @param area       城区地址实体信息
     * @param onlineUser 当前登录用户
     * @return Area 修改后的城区地址信息
     */
    Area update(Area area, OnlineUser onlineUser);

    /**
     * 保存城区地址
     *
     * @param area       城区地址实体信息
     * @param onlineUser 当前登录用户
     * @return Area 保存后的城区地址信息
     */
    Area save(Area area, OnlineUser onlineUser);

    /**
     * 根据地区编号删除信息
     *
     * @param id         地区编号
     * @param onlineUser 当前登录用户
     */
    void delete(Integer id, OnlineUser onlineUser);

    /**
     * 根据地区编号列表删除信息
     *
     * @param ids        地区编号列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser);

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
