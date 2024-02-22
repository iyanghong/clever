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
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/area")
@AuthGroup(value = "clever-system.area", name = "城区地址模块", description = "城区地址模块权限组")
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
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Area> 城区地址列表
     */
    @GetMapping("/listByCityId/{cityId}")
    @Auth(value = "clever-system.area.listByCityId", name = "根据城市编号获取城区地址列表", description = "根据城市编号获取城区地址列表接口")
    public Result<List<Area>> selectListByCityId(@PathVariable("cityId") Integer cityId) {
        return new Result<>(areaService.selectListByCityId(cityId), "查询成功");
    }

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Area> 城区地址列表
     */
    @GetMapping("/listByProvinceId/{provinceId}")
    @Auth(value = "clever-system.area.listByProvinceId", name = "根据省份编号获取城区地址列表", description = "根据省份编号获取城区地址列表接口")
    public Result<List<Area>> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return new Result<>(areaService.selectListByProvinceId(provinceId), "查询成功");
    }

    /**
     * 根据地区编号获取城区地址信息
     *
     * @param id 地区编号
     * @return 城区地址信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.area.selectById", name = "根据地区编号获取城区地址信息", description = "根据地区编号获取城区地址信息接口")
    public Result<Area> selectById(@PathVariable("id") Integer id) {
        return new Result<>(areaService.selectById(id), "查询成功");
    }

    /**
     * 创建城区地址信息
     *
     * @param area 城区地址实体信息
     * @return 创建后的城区地址信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.area.create", name = "创建城区地址", description = "创建城区地址信息接口")
    public Result<Area> create(@Validated Area area) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(areaService.create(area, onlineUser), "创建成功");
    }

    /**
     * 修改城区地址信息
     *
     * @param area 城区地址实体信息
     * @return 修改后的城区地址信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.area.update", name = "修改城区地址", description = "修改城区地址信息接口")
    public Result<Area> update(@Validated Area area, @PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        area.setId(id);
        return new Result<>(areaService.update(area, onlineUser), "修改成功");
    }

    /**
     * 保存城区地址信息
     *
     * @param area 城区地址实体信息
     * @return 保存后的城区地址信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.area.save", name = "保存城区地址", description = "保存城区地址信息接口")
    public Result<Area> save(@Validated Area area) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(areaService.save(area, onlineUser), "保存成功");
    }

    /**
     * 根据城区地址id删除城区地址信息
     *
     * @param id 地区编号
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.area.delete", name = "删除城区地址", description = "删除城区地址信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        areaService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
