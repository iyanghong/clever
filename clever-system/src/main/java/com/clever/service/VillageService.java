package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Village;

/**
 * 村庄服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface VillageService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       村庄名称
     * @param provinceId 省份编号
     * @param cityId     城市编号
     * @param areaId     区/县编号
     * @param streetId   街道编号
     * @return Page<Village>
     */
    Page<Village> selectPage(Integer pageNumber, Integer pageSize, String name, Integer provinceId, Integer cityId, Integer areaId, Integer streetId);

    /**
     * 根据id获取村庄
     *
     * @param id
     * @return Village id信息
     */
    Village selectById(Long id);

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Village> 村庄列表
     */
    List<Village> selectListByProvinceId(Integer provinceId);

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Village> 村庄列表
     */
    List<Village> selectListByCityId(Integer cityId);

    /**
     * 根据区/县编号获取列表
     *
     * @param areaId 区/县编号
     * @return List<Village> 村庄列表
     */
    List<Village> selectListByAreaId(Integer areaId);

    /**
     * 根据街道编号获取列表
     *
     * @param streetId 街道编号
     * @return List<Village> 村庄列表
     */
    List<Village> selectListByStreetId(Integer streetId);

    /**
     * 新建村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 新建后的村庄信息
     */
    Village create(Village village, OnlineUser onlineUser);

    /**
     * 修改村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 修改后的村庄信息
     */
    Village update(Village village, OnlineUser onlineUser);

    /**
     * 保存村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 保存后的村庄信息
     */
    Village save(Village village, OnlineUser onlineUser);

    /**
     * 根据id删除信息
     *
     * @param id
     * @param onlineUser 当前登录用户
     */
    void delete(Long id, OnlineUser onlineUser);

    /**
     * 根据id列表删除信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<Long> ids, OnlineUser onlineUser);

    /**
     * 根据省份编号删除
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    void deleteByProvinceId(Integer provinceId, OnlineUser onlineUser);

    /**
     * 根据城市编号删除
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    void deleteByCityId(Integer cityId, OnlineUser onlineUser);

    /**
     * 根据区/县编号删除
     *
     * @param areaId     区/县编号
     * @param onlineUser 当前登录用户
     */
    void deleteByAreaId(Integer areaId, OnlineUser onlineUser);

    /**
     * 根据街道编号删除
     *
     * @param streetId   街道编号
     * @param onlineUser 当前登录用户
     */
    void deleteByStreetId(Integer streetId, OnlineUser onlineUser);

}
