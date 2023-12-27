package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.DictionaryType;
import com.clever.service.DictionaryTypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 字典类型接口
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@RestController
@Validated
@RequestMapping("/dictionaryType")
@AuthGroup(name = "字典类型模块", description = "字典类型模块权限组")
public class DictionaryTypeController {

    @Resource
    private DictionaryTypeService dictionaryTypeService;


    /**
     * 分页查询字典类型列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param name 字典类型名称
     * @param code 字典类型标识
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.dictionaryType.page", name = "字典类型分页", description = "字典类型分页接口")
    public Result<Page<DictionaryType>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize,String platformId,String name,String code) {
        return new Result<>(dictionaryTypeService.selectPage(pageNumber, pageSize, platformId, name, code), "分页数据查询成功");
    }
    /**
    * 根据平台id获取列表
    *
    * @param platformId 平台id
    * @return List<DictionaryType> 字典类型列表
    */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.dictionaryType.listByPlatformId", name = "根据平台id获取字典类型列表", description = "根据平台id获取字典类型列表接口")
    public List<DictionaryType> selectListByPlatformId(@PathVariable("platformId") String platformId) {
        return dictionaryTypeService.selectListByPlatformId(platformId);
    }
    /**
    * 根据创建者id获取列表
    *
    * @param creator 创建者id
    * @return List<DictionaryType> 字典类型列表
    */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.dictionaryType.listByCreator", name = "根据创建者id获取字典类型列表", description = "根据创建者id获取字典类型列表接口")
    public List<DictionaryType> selectListByCreator(@PathVariable("creator") String creator) {
        return dictionaryTypeService.selectListByCreator(creator);
    }

    /**
    * 根据字典类型id获取字典类型信息
    *
    * @param id 字典类型id
    * @return 字典类型信息
    */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据字典类型id获取字典类型信息", description = "根据字典类型id获取字典类型信息接口")
    public Result<DictionaryType> selectById(@PathVariable("id") String id) {
    return new Result<>(dictionaryTypeService.selectById(id), "查询成功");
    }
    /**
    * 创建字典类型信息
    *
    * @param dictionaryType 字典类型实体信息
    * @return 创建后的字典类型信息
    */
    @PostMapping("")
    @Auth(value = "clever-system.dictionaryType.create", name = "创建字典类型", description = "创建字典类型信息接口")
    public Result<DictionaryType> create(@Validated DictionaryType dictionaryType) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(dictionaryTypeService.create(dictionaryType, onlineUser), "创建成功");
    }
    /**
    * 修改字典类型信息
    *
    * @param dictionaryType 字典类型实体信息
    * @return 修改后的字典类型信息
    */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.dictionaryType.update", name = "修改字典类型", description = "修改字典类型信息接口")
    public Result<DictionaryType> update(@Validated DictionaryType dictionaryType, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        dictionaryType.setId(id);
        return new Result<>(dictionaryTypeService.update(dictionaryType, onlineUser), "修改成功");
    }

    /**
     * 保存字典类型信息
     *
     * @param dictionaryType 字典类型实体信息
     * @return 保存后的字典类型信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.dictionaryType.save", name = "保存字典类型", description = "保存字典类型信息接口")
    public Result<DictionaryType> save(@Validated DictionaryType dictionaryType) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(dictionaryTypeService.save(dictionaryType, onlineUser), "保存成功");
    }

    /**
     * 根据字典类型id删除字典类型信息
     *
     * @param id 字典类型id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.dictionaryType.delete", name = "删除字典类型", description = "删除字典类型信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        dictionaryTypeService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
