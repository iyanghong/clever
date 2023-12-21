package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Menu;
import com.clever.service.MenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 导航菜单接口
 *
 * @Author xixi
 * @Date 2023-12-21 05:07:38
 */
@RestController
@RequestMapping("/menu")
@AuthGroup(name = "导航菜单模块", description = "导航菜单模块权限组")
public class MenuController {

    @Resource
    private MenuService menuService;


    /**
     * 分页查询导航菜单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       菜单名称
     * @param code       菜单Code
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.menu.page", name = "导航菜单分页", description = "导航菜单分页接口")
    public Result<Page<Menu>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String name, String code) {
        return new Result<>(menuService.selectPage(pageNumber, pageSize, platformId, name, code), "分页数据查询成功");
    }

    /**
     * 根据id获取导航菜单信息
     *
     * @param id id
     * @return 导航菜单信息
     */
    @GetMapping("/get/{id}")
    public Result<Menu> selectById(@PathVariable("id") String id) {
        return new Result<>(menuService.selectById(id), "查询成功");
    }

    /**
     * 根据平台ID获取导航菜单列表
     *
     * @param platformId 平台ID
     * @return 导航菜单列表
     */
    @GetMapping("/getListByPlatformId/{platformId}")
    @Auth(value = "clever-system.menu.getByPlatformId", name = "根据platform_id获取导航菜单列表", description = "导航菜单列表接口")
    public Result<List<Menu>> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(menuService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 保存导航菜单信息
     *
     * @param menu 导航菜单实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.menu.save", name = "保存导航菜单", description = "保存导航菜单信息接口")
    public Result<String> save(@Validated Menu menu) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        menuService.save(menu, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id删除导航菜单信息
     *
     * @param id id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.menu.delete", name = "删除导航菜单", description = "删除导航菜单信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        menuService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
