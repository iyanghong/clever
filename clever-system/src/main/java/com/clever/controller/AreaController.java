package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Area;
import com.clever.service.AreaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 城区地址接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:41:46
 */
@RestController
@RequestMapping("/area")
@AuthGroup(name = "城区地址模块", description = "城区地址模块权限组")
public class AreaController {

    @Resource
    private AreaService areaService;


    /**
     * 分页查询城区地址列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       地区名称
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.area.page", name = "城区地址分页", description = "城区地址分页接口")
    public Result<Page<Area>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer cityId, Integer provinceId) {
        return new Result<>(areaService.selectPage(pageNumber, pageSize, name, cityId, provinceId), "分页数据查询成功");
    }

    /**
     * 根据地区编号获取城区地址信息
     *
     * @param id 地区编号
     * @return 城区地址信息
     */
    @GetMapping("/get/{id}")
    public Result<Area> selectById(@PathVariable("id") Integer id) {
        return new Result<>(areaService.selectById(id), "查询成功");
    }

    /**
     * 根据城市编号获取城区地址列表
     *
     * @param cityId 城市编号
     * @return 城区地址列表
     */
    @GetMapping("/getListByCityId/{cityId}")
    @Auth(value = "clever-system.area.getByCityId", name = "根据city_id获取城区地址列表", description = "城区地址列表接口")
    public Result<List<Area>> selectListByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(areaService.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据省份编号获取城区地址列表
     *
     * @param provinceId 省份编号
     * @return 城区地址列表
     */
    @GetMapping("/getListByProvinceId/{provinceId}")
    @Auth(value = "clever-system.area.getByProvinceId", name = "根据province_id获取城区地址列表", description = "城区地址列表接口")
    public Result<List<Area>> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(areaService.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 保存城区地址信息
     *
     * @param area 城区地址实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.area.save", name = "保存城区地址", description = "保存城区地址信息接口")
    public Result<String> save(@Validated Area area) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        areaService.save(area, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据地区编号获取城区地址列表
     *
     * @param id 地区编号
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.area.delete", name = "删除城区地址", description = "删除城区地址信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        areaService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
