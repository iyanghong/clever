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
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
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
     * 根据地区编号获取列表
     *
     * @param areaId 地区编号
     * @return List<Street> 街道列表
     */
    @GetMapping("/listByAreaId/{areaId}")
    @Auth(value = "clever-system.street.listByAreaId", name = "根据地区编号获取街道列表", description = "根据地区编号获取街道列表接口")
    public List<Street> selectListByAreaId(@PathVariable("areaId") Integer areaId) {
        return streetService.selectListByAreaId(areaId);
    }

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Street> 街道列表
     */
    @GetMapping("/listByCityId/{cityId}")
    @Auth(value = "clever-system.street.listByCityId", name = "根据城市编号获取街道列表", description = "根据城市编号获取街道列表接口")
    public List<Street> selectListByCityId(@PathVariable("cityId") Integer cityId) {
        return streetService.selectListByCityId(cityId);
    }

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Street> 街道列表
     */
    @GetMapping("/listByProvinceId/{provinceId}")
    @Auth(value = "clever-system.street.listByProvinceId", name = "根据省份编号获取街道列表", description = "根据省份编号获取街道列表接口")
    public List<Street> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return streetService.selectListByProvinceId(provinceId);
    }

    /**
     * 根据街道编号获取街道信息
     *
     * @param id 街道编号
     * @return 街道信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据街道编号获取街道信息", description = "根据街道编号获取街道信息接口")
    public Result<Street> selectById(@PathVariable("id") Integer id) {
        return new Result<>(streetService.selectById(id), "查询成功");
    }

    /**
     * 创建街道信息
     *
     * @param street 街道实体信息
     * @return 创建后的街道信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.street.create", name = "创建街道", description = "创建街道信息接口")
    public Result<Street> create(@Validated Street street) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(streetService.create(street, onlineUser), "创建成功");
    }

    /**
     * 修改街道信息
     *
     * @param street 街道实体信息
     * @return 修改后的街道信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.street.update", name = "修改街道", description = "修改街道信息接口")
    public Result<Street> update(@Validated Street street, @PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        street.setId(id);
        return new Result<>(streetService.update(street, onlineUser), "修改成功");
    }

    /**
     * 保存街道信息
     *
     * @param street 街道实体信息
     * @return 保存后的街道信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.street.save", name = "保存街道", description = "保存街道信息接口")
    public Result<Street> save(@Validated Street street) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(streetService.save(street, onlineUser), "保存成功");
    }

    /**
     * 根据街道id删除街道信息
     *
     * @param id 街道编号
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.street.delete", name = "删除街道", description = "删除街道信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        streetService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
