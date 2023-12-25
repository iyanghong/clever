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
 * 村庄接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/village")
@AuthGroup(name = "村庄模块", description = "村庄模块权限组")
public class VillageController {

    @Resource
    private VillageService villageService;


    /**
     * 分页查询村庄列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       村庄名称
     * @param provinceId 省份编号
     * @param cityId     城市编号
     * @param areaId     区/县编号
     * @param streetId   街道编号
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.village.page", name = "村庄分页", description = "村庄分页接口")
    public Result<Page<Village>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name, Integer provinceId, Integer cityId, Integer areaId, Integer streetId) {
        return new Result<>(villageService.selectPage(pageNumber, pageSize, name, provinceId, cityId, areaId, streetId), "分页数据查询成功");
    }

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Village> 村庄列表
     */
    @GetMapping("/listByProvinceId/{provinceId}")
    @Auth(value = "clever-system.village.listByProvinceId", name = "根据省份编号获取村庄列表", description = "根据省份编号获取村庄列表接口")
    public List<Village> selectListByProvinceId(@PathVariable("provinceId") Integer provinceId) {
        return villageService.selectListByProvinceId(provinceId);
    }

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Village> 村庄列表
     */
    @GetMapping("/listByCityId/{cityId}")
    @Auth(value = "clever-system.village.listByCityId", name = "根据城市编号获取村庄列表", description = "根据城市编号获取村庄列表接口")
    public List<Village> selectListByCityId(@PathVariable("cityId") Integer cityId) {
        return villageService.selectListByCityId(cityId);
    }

    /**
     * 根据区/县编号获取列表
     *
     * @param areaId 区/县编号
     * @return List<Village> 村庄列表
     */
    @GetMapping("/listByAreaId/{areaId}")
    @Auth(value = "clever-system.village.listByAreaId", name = "根据区/县编号获取村庄列表", description = "根据区/县编号获取村庄列表接口")
    public List<Village> selectListByAreaId(@PathVariable("areaId") Integer areaId) {
        return villageService.selectListByAreaId(areaId);
    }

    /**
     * 根据街道编号获取列表
     *
     * @param streetId 街道编号
     * @return List<Village> 村庄列表
     */
    @GetMapping("/listByStreetId/{streetId}")
    @Auth(value = "clever-system.village.listByStreetId", name = "根据街道编号获取村庄列表", description = "根据街道编号获取村庄列表接口")
    public List<Village> selectListByStreetId(@PathVariable("streetId") Integer streetId) {
        return villageService.selectListByStreetId(streetId);
    }

    /**
     * 根据id获取村庄信息
     *
     * @param id id
     * @return 村庄信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据id获取村庄信息", description = "根据id获取村庄信息接口")
    public Result<Village> selectById(@PathVariable("id") Long id) {
        return new Result<>(villageService.selectById(id), "查询成功");
    }

    /**
     * 创建村庄信息
     *
     * @param village 村庄实体信息
     * @return 创建后的村庄信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.village.create", name = "创建村庄", description = "创建村庄信息接口")
    public Result<Village> create(@Validated Village village) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(villageService.create(village, onlineUser), "创建成功");
    }

    /**
     * 修改村庄信息
     *
     * @param village 村庄实体信息
     * @return 修改后的村庄信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.village.update", name = "修改村庄", description = "修改村庄信息接口")
    public Result<Village> update(@Validated Village village, @PathVariable("id") Long id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        village.setId(id);
        return new Result<>(villageService.update(village, onlineUser), "修改成功");
    }

    /**
     * 保存村庄信息
     *
     * @param village 村庄实体信息
     * @return 保存后的村庄信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.village.save", name = "保存村庄", description = "保存村庄信息接口")
    public Result<Village> save(@Validated Village village) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(villageService.save(village, onlineUser), "保存成功");
    }

    /**
     * 根据村庄id删除村庄信息
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.village.delete", name = "删除村庄", description = "删除村庄信息接口")
    public Result<String> delete(@PathVariable("id") Long id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        villageService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
