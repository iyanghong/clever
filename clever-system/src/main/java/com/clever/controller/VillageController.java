package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Village;
import com.clever.service.VillageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Village接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:39:08
 */
@RestController
@RequestMapping("/village")
@AuthGroup(name = "village模块", description = "village模块权限组")
public class VillageController {

    @Resource
    private VillageService villageService;


    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name
     * @param streetId
     * @param provinceId
     * @param cityId
     * @param areaId
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.village.page", name = "village分页", description = "village分页接口")
    public Result<Page<Village>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer streetId, Integer provinceId, Integer cityId, Integer areaId) {
        return new Result<>(villageService.selectPage(pageNumber, pageSize, name, streetId, provinceId, cityId, areaId), "分页数据查询成功");
    }

    /**
     * 根据id获取village信息
     *
     * @param id
     * @return village信息
     */
    @GetMapping("/get/{id}")
    public Result<Village> selectById(@PathVariable("id") Long id) {
        return new Result<>(villageService.selectById(id), "查询成功");
    }

    /**
     * 根据street_id获取village列表
     *
     * @param streetId street_id
     * @return village列表
     */
    @GetMapping("/getByStreetId/{streetId}")
    @Auth(value = "clever-system.village.getByStreetId", name = "根据street_id获取village列表", description = "village列表接口")
    public Result<List<Village>> selectByStreetId(@PathVariable("streetId") Integer streetId) {
        return new Result<>(villageService.selectListByStreetId(streetId), "查询成功");
    }

    /**
     * 根据province_id获取village列表
     *
     * @param provinceId province_id
     * @return village列表
     */
    @GetMapping("/getByProvinceId/{provinceId}")
    @Auth(value = "clever-system.village.getByProvinceId", name = "根据province_id获取village列表", description = "village列表接口")
    public Result<List<Village>> selectByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(villageService.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 根据city_id获取village列表
     *
     * @param cityId city_id
     * @return village列表
     */
    @GetMapping("/getByCityId/{cityId}")
    @Auth(value = "clever-system.village.getByCityId", name = "根据city_id获取village列表", description = "village列表接口")
    public Result<List<Village>> selectByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(villageService.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据area_id获取village列表
     *
     * @param areaId area_id
     * @return village列表
     */
    @GetMapping("/getByAreaId/{areaId}")
    @Auth(value = "clever-system.village.getByAreaId", name = "根据area_id获取village列表", description = "village列表接口")
    public Result<List<Village>> selectByAreaId(@PathVariable("areaId") Integer areaId) {
        return new Result<>(villageService.selectListByAreaId(areaId), "查询成功");
    }

    /**
     * 保存village信息
     *
     * @param village village实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.village.save", name = "保存village", description = "保存village信息接口")
    public Result<String> save(@Validated Village village) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        villageService.save(village, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id获取village列表
     *
     * @param id id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.village.delete", name = "删除village", description = "删除village信息接口")
    public Result<String> delete(@PathVariable("id") Long id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        villageService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
