package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.AddressLevel4;
import com.clever.service.AddressLevel4Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * VIEW接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/AddressLevel4")
@AuthGroup(name = "VIEW模块", description = "VIEW模块权限组")
public class AddressLevel4Controller {

    @Resource
    private AddressLevel4Service addressLevel4Service;


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
     * @param streetId
     * @param streetName
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.addressLevel4.page", name = "VIEW分页", description = "VIEW分页接口")
    public Result<Page<AddressLevel4>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer provinceId, String provinceName, Integer cityId, String cityName, Integer areaId, String areaName, Integer streetId, String streetName) {
        return new Result<>(addressLevel4Service.selectPage(pageNumber, pageSize, provinceId, provinceName, cityId, cityName, areaId, areaName, streetId, streetName), "分页数据查询成功");
    }

    /**
     * 根据province_id获取VIEW列表
     *
     * @param provinceId province_id
     * @return VIEW列表
     */
    @GetMapping("/getByProvinceId/{provinceId}")
    @Auth(value = "clever-system.addressLevel4.getByProvinceId", name = "根据province_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel4>> selectByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(addressLevel4Service.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 根据city_id获取VIEW列表
     *
     * @param cityId city_id
     * @return VIEW列表
     */
    @GetMapping("/getByCityId/{cityId}")
    @Auth(value = "clever-system.addressLevel4.getByCityId", name = "根据city_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel4>> selectByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(addressLevel4Service.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据area_id获取VIEW列表
     *
     * @param areaId area_id
     * @return VIEW列表
     */
    @GetMapping("/getByAreaId/{areaId}")
    @Auth(value = "clever-system.addressLevel4.getByAreaId", name = "根据area_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel4>> selectByAreaId(@PathVariable("areaId") Integer areaId) {
        return new Result<>(addressLevel4Service.selectListByAreaId(areaId), "查询成功");
    }

    /**
     * 根据street_id获取VIEW列表
     *
     * @param streetId street_id
     * @return VIEW列表
     */
    @GetMapping("/getByStreetId/{streetId}")
    @Auth(value = "clever-system.addressLevel4.getByStreetId", name = "根据street_id获取VIEW列表", description = "VIEW列表接口")
    public Result<List<AddressLevel4>> selectByStreetId(@PathVariable("streetId") Integer streetId) {
        return new Result<>(addressLevel4Service.selectListByStreetId(streetId), "查询成功");
    }

    /**
     * 保存VIEW信息
     *
     * @param addressLevel4 VIEW实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.addressLevel4.save", name = "保存VIEW", description = "保存VIEW信息接口")
    public Result<String> save(AddressLevel4 addressLevel4) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        addressLevel4Service.save(addressLevel4, onlineUser);
        return Result.ofSuccess("保存成功");
    }

}