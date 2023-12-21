package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.AddressLevel3;
import com.clever.service.AddressLevel3Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * VIEW接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:39:08
 */
@RestController
@RequestMapping("/addressLevel3")
@AuthGroup(name = "VIEW模块", description = "VIEW模块权限组")
public class AddressLevel3Controller {

    @Resource
    private AddressLevel3Service addressLevel3Service;


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
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.addressLevel3.page", name = "VIEW分页", description = "VIEW分页接口")
    public Result<Page<AddressLevel3>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer provinceId, String provinceName, Integer cityId, String cityName, Integer areaId, String areaName) {
        return new Result<>(addressLevel3Service.selectPage(pageNumber, pageSize, provinceId, provinceName, cityId, cityName, areaId, areaName), "分页数据查询成功");
    }

    /**
     * 根据province_id获取VIEW列表
     *
     * @param provinceId province_id
     * @return VIEW列表
     */
    @GetMapping("/getByProvinceId/{provinceId}")
    @Auth(value = "clever-system.addressLevel3.getByProvinceId", name = "根据province_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel3>> selectByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(addressLevel3Service.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 根据city_id获取VIEW列表
     *
     * @param cityId city_id
     * @return VIEW列表
     */
    @GetMapping("/getByCityId/{cityId}")
    @Auth(value = "clever-system.addressLevel3.getByCityId", name = "根据city_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel3>> selectByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(addressLevel3Service.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据area_id获取VIEW列表
     *
     * @param areaId area_id
     * @return VIEW列表
     */
    @GetMapping("/getByAreaId/{areaId}")
    @Auth(value = "clever-system.addressLevel3.getByAreaId", name = "根据area_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel3>> selectByAreaId(@PathVariable("areaId") Integer areaId) {
        return new Result<>(addressLevel3Service.selectListByAreaId(areaId), "查询成功");
    }

}
