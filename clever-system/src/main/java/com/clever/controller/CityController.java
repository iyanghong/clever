package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.City;
import com.clever.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 城市接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
@RestController
@RequestMapping("/city")
@AuthGroup(name = "城市模块", description = "城市模块权限组")
public class CityController {

    @Resource
    private CityService cityService;


    /**
     * 分页查询城市列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       城市名称
     * @param provinceId 省份编号
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.city.page", name = "城市分页", description = "城市分页接口")
    public Result<Page<City>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer provinceId) {
        return new Result<>(cityService.selectPage(pageNumber, pageSize, name, provinceId), "分页数据查询成功");
    }

    /**
     * 根据城市编号获取城市信息
     *
     * @param id 城市编号
     * @return 城市信息
     */
    @GetMapping("/get/{id}")
    public Result<City> selectById(@PathVariable("id") Integer id) {
        return new Result<>(cityService.selectById(id), "查询成功");
    }

    /**
     * 根据省份编号获取城市列表
     *
     * @param provinceId 省份编号
     * @return 城市列表
     */
    @GetMapping("/getByProvinceId/{provinceId}")
    @Auth(value = "clever-system.city.getByProvinceId", name = "根据province_id获取城市列表", description = "城市列表接口")
    public Result<List<City>> selectByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(cityService.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 保存城市信息
     *
     * @param city 城市实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.city.save", name = "保存城市", description = "保存城市信息接口")
    public Result<String> save(City city) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        cityService.save(city, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据城市编号获取城市列表
     *
     * @param id 城市编号
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.city.delete", name = "删除城市", description = "删除城市信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        cityService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
