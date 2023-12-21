package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Street;
import com.clever.service.StreetService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 街道接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:41:46
 */
@RestController
@RequestMapping("/street")
@AuthGroup(name = "街道模块", description = "街道模块权限组")
public class StreetController {

    @Resource
    private StreetService streetService;


    /**
     * 分页查询街道列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       街道名称
     * @param areaId     地区编号
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.street.page", name = "街道分页", description = "街道分页接口")
    public Result<Page<Street>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer areaId, Integer cityId, Integer provinceId) {
        return new Result<>(streetService.selectPage(pageNumber, pageSize, name, areaId, cityId, provinceId), "分页数据查询成功");
    }

    /**
     * 根据街道编号获取街道信息
     *
     * @param id 街道编号
     * @return 街道信息
     */
    @GetMapping("/get/{id}")
    public Result<Street> selectById(@PathVariable("id") Integer id) {
        return new Result<>(streetService.selectById(id), "查询成功");
    }

    /**
     * 根据地区编号获取街道列表
     *
     * @param areaId 地区编号
     * @return 街道列表
     */
    @GetMapping("/getListByAreaId/{areaId}")
    @Auth(value = "clever-system.street.getByAreaId", name = "根据area_id获取街道列表", description = "街道列表接口")
    public Result<List<Street>> selectListByAreaId(@PathVariable("areaId") Integer areaId) {
        return new Result<>(streetService.selectListByAreaId(areaId), "查询成功");
    }

    /**
     * 根据城市编号获取街道列表
     *
     * @param cityId 城市编号
     * @return 街道列表
     */
    @GetMapping("/getListByCityId/{cityId}")
    @Auth(value = "clever-system.street.getByCityId", name = "根据city_id获取街道列表", description = "街道列表接口")
    public Result<List<Street>> selectListByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(streetService.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据省份编号获取街道列表
     *
     * @param provinceId 省份编号
     * @return 街道列表
     */
    @GetMapping("/getListByProvinceId/{provinceId}")
    @Auth(value = "clever-system.street.getByProvinceId", name = "根据province_id获取街道列表", description = "街道列表接口")
    public Result<List<Street>> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(streetService.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 保存街道信息
     *
     * @param street 街道实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.street.save", name = "保存街道", description = "保存街道信息接口")
    public Result<String> save(@Validated Street street) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        streetService.save(street, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据街道编号获取街道列表
     *
     * @param id 街道编号
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.street.delete", name = "删除街道", description = "删除街道信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        streetService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
