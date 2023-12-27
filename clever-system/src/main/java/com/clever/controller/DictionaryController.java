package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Dictionary;
import com.clever.service.DictionaryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 字典接口
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@RestController
@Validated
@RequestMapping("/dictionary")
@AuthGroup(name = "字典模块", description = "字典模块权限组")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;


    /**
     * 分页查询字典列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param typeId 字典类型id
     * @param parentId 上级字典id
     * @param name 字典名称
     * @param code 字典标识
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.dictionary.page", name = "字典分页", description = "字典分页接口")
    public Result<Page<Dictionary>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize,String platformId,String typeId,String parentId,String name,String code) {
        return new Result<>(dictionaryService.selectPage(pageNumber, pageSize, platformId, typeId, parentId, name, code), "分页数据查询成功");
    }
    /**
    * 根据平台id获取列表
    *
    * @param platformId 平台id
    * @return List<Dictionary> 字典列表
    */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.dictionary.listByPlatformId", name = "根据平台id获取字典列表", description = "根据平台id获取字典列表接口")
    public List<Dictionary> selectListByPlatformId(@PathVariable("platformId") String platformId) {
        return dictionaryService.selectListByPlatformId(platformId);
    }
    /**
    * 根据字典类型id获取列表
    *
    * @param typeId 字典类型id
    * @return List<Dictionary> 字典列表
    */
    @GetMapping("/listByTypeId/{typeId}")
    @Auth(value = "clever-system.dictionary.listByTypeId", name = "根据字典类型id获取字典列表", description = "根据字典类型id获取字典列表接口")
    public List<Dictionary> selectListByTypeId(@PathVariable("typeId") String typeId) {
        return dictionaryService.selectListByTypeId(typeId);
    }
    /**
    * 根据上级字典id获取列表
    *
    * @param parentId 上级字典id
    * @return List<Dictionary> 字典列表
    */
    @GetMapping("/listByParentId/{parentId}")
    @Auth(value = "clever-system.dictionary.listByParentId", name = "根据上级字典id获取字典列表", description = "根据上级字典id获取字典列表接口")
    public List<Dictionary> selectListByParentId(@PathVariable("parentId") String parentId) {
        return dictionaryService.selectListByParentId(parentId);
    }
    /**
    * 根据创建者id获取列表
    *
    * @param creator 创建者id
    * @return List<Dictionary> 字典列表
    */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.dictionary.listByCreator", name = "根据创建者id获取字典列表", description = "根据创建者id获取字典列表接口")
    public List<Dictionary> selectListByCreator(@PathVariable("creator") String creator) {
        return dictionaryService.selectListByCreator(creator);
    }

    /**
    * 根据字典id获取字典信息
    *
    * @param id 字典id
    * @return 字典信息
    */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据字典id获取字典信息", description = "根据字典id获取字典信息接口")
    public Result<Dictionary> selectById(@PathVariable("id") String id) {
    return new Result<>(dictionaryService.selectById(id), "查询成功");
    }
    /**
    * 创建字典信息
    *
    * @param dictionary 字典实体信息
    * @return 创建后的字典信息
    */
    @PostMapping("")
    @Auth(value = "clever-system.dictionary.create", name = "创建字典", description = "创建字典信息接口")
    public Result<Dictionary> create(@Validated Dictionary dictionary) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(dictionaryService.create(dictionary, onlineUser), "创建成功");
    }
    /**
    * 修改字典信息
    *
    * @param dictionary 字典实体信息
    * @return 修改后的字典信息
    */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.dictionary.update", name = "修改字典", description = "修改字典信息接口")
    public Result<Dictionary> update(@Validated Dictionary dictionary, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        dictionary.setId(id);
        return new Result<>(dictionaryService.update(dictionary, onlineUser), "修改成功");
    }

    /**
     * 保存字典信息
     *
     * @param dictionary 字典实体信息
     * @return 保存后的字典信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.dictionary.save", name = "保存字典", description = "保存字典信息接口")
    public Result<Dictionary> save(@Validated Dictionary dictionary) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(dictionaryService.save(dictionary, onlineUser), "保存成功");
    }

    /**
     * 根据字典id删除字典信息
     *
     * @param id 字典id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.dictionary.delete", name = "删除字典", description = "删除字典信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        dictionaryService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
