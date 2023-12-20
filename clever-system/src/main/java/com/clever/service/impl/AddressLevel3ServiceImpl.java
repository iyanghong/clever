package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.AddressLevel3Mapper;
import com.clever.bean.system.AddressLevel3;
import com.clever.service.AddressLevel3Service;

import javax.annotation.Resource;

/**
 * VIEW服务
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Service
public class AddressLevel3ServiceImpl implements AddressLevel3Service {

    private final static Logger log = LoggerFactory.getLogger(AddressLevel3ServiceImpl.class);

    @Resource
    private AddressLevel3Mapper addressLevel3Mapper;

    /**
     * 分页查询VIEW列表
     *
     * @param pageNumber   页码
     * @param pageSize     每页记录数
     * @param provinceId
     * @param provinceName
     * @param cityId
     * @param cityName
     * @param areaId
     * @param areaName
     * @return Page<AddressLevel3>
     */
    @Override
    public Page<AddressLevel3> selectPage(Integer pageNumber, Integer pageSize, String provinceId, String provinceName, String cityId, String cityName, String areaId, String areaName) {
        QueryWrapper<AddressLevel3> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(provinceId)) {
            queryWrapper.eq("province_id", provinceId);
        }
        if (StringUtils.isNotBlank(provinceName)) {
            queryWrapper.eq("province_name", provinceName);
        }
        if (StringUtils.isNotBlank(cityId)) {
            queryWrapper.eq("city_id", cityId);
        }
        if (StringUtils.isNotBlank(cityName)) {
            queryWrapper.eq("city_name", cityName);
        }
        if (StringUtils.isNotBlank(areaId)) {
            queryWrapper.eq("area_id", areaId);
        }
        if (StringUtils.isNotBlank(areaName)) {
            queryWrapper.eq("area_name", areaName);
        }
        return addressLevel3Mapper.selectPage(new Page<AddressLevel3>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据province_id获取VIEW列表
     *
     * @param provinceId
     * @return List<AddressLevel3> VIEW列表
     */
    @Override
    public List<AddressLevel3> getListByProvinceId(Integer provinceId) {
        return addressLevel3Mapper.selectList(new QueryWrapper<AddressLevel3>().eq("province_id", provinceId));
    }

    /**
     * 根据city_id获取VIEW列表
     *
     * @param cityId
     * @return List<AddressLevel3> VIEW列表
     */
    @Override
    public List<AddressLevel3> getListByCityId(Integer cityId) {
        return addressLevel3Mapper.selectList(new QueryWrapper<AddressLevel3>().eq("city_id", cityId));
    }

    /**
     * 根据area_id获取VIEW列表
     *
     * @param areaId
     * @return List<AddressLevel3> VIEW列表
     */
    @Override
    public List<AddressLevel3> getListByAreaId(Integer areaId) {
        return addressLevel3Mapper.selectList(new QueryWrapper<AddressLevel3>().eq("area_id", areaId));
    }

    /**
     * 根据province_id删除VIEW
     *
     * @param provinceId
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByProvinceId(String provinceId, OnlineUser onlineUser) {
        addressLevel3Mapper.delete(new QueryWrapper<AddressLevel3>().eq("province_id", provinceId));
        log.info("VIEW, VIEW信息根据province_id删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
    }

    /**
     * 根据city_id删除VIEW
     *
     * @param cityId
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCityId(String cityId, OnlineUser onlineUser) {
        addressLevel3Mapper.delete(new QueryWrapper<AddressLevel3>().eq("city_id", cityId));
        log.info("VIEW, VIEW信息根据city_id删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
    }

    /**
     * 根据area_id删除VIEW
     *
     * @param areaId
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByAreaId(String areaId, OnlineUser onlineUser) {
        addressLevel3Mapper.delete(new QueryWrapper<AddressLevel3>().eq("area_id", areaId));
        log.info("VIEW, VIEW信息根据area_id删除成功: userId={}, areaId={}", onlineUser.getId(), areaId);
    }
}
