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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 城市接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/city")
@AuthGroup(value = "clever-system.city", name = "城市模块", description = "城市模块权限组")
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
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<City> 城市列表
     */
    @GetMapping("/listByProvinceId/{provinceId}")
    @Auth(value = "clever-system.city.listByProvinceId", name = "根据省份编号获取城市列表", description = "根据省份编号获取城市列表接口")
    public List<City> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return cityService.selectListByProvinceId(provinceId);
    }

    /**
     * 根据城市编号获取城市信息
     *
     * @param id 城市编号
     * @return 城市信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.city.selectById", name = "根据城市编号获取城市信息", description = "根据城市编号获取城市信息接口")
    public Result<City> selectById(@PathVariable("id") Integer id) {
        return new Result<>(cityService.selectById(id), "查询成功");
    }

    /**
     * 创建城市信息
     *
     * @param city 城市实体信息
     * @return 创建后的城市信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.city.create", name = "创建城市", description = "创建城市信息接口")
    public Result<City> create(@Validated City city) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(cityService.create(city, onlineUser), "创建成功");
    }

    /**
     * 修改城市信息
     *
     * @param city 城市实体信息
     * @return 修改后的城市信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.city.update", name = "修改城市", description = "修改城市信息接口")
    public Result<City> update(@Validated City city, @PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        city.setId(id);
        return new Result<>(cityService.update(city, onlineUser), "修改成功");
    }

    /**
     * 保存城市信息
     *
     * @param city 城市实体信息
     * @return 保存后的城市信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.city.save", name = "保存城市", description = "保存城市信息接口")
    public Result<City> save(@Validated City city) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(cityService.save(city, onlineUser), "保存成功");
    }

    /**
     * 根据城市id删除城市信息
     *
     * @param id 城市编号
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.city.delete", name = "删除城市", description = "删除城市信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        cityService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
