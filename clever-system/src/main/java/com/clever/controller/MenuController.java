package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.system.projo.MenuTreeVo;
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
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/menu")
@AuthGroup(value = "clever-system.menu", name = "导航菜单模块", description = "导航菜单模块权限组")
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
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<Menu> 导航菜单列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.menu.listByPlatformId", name = "根据平台ID获取导航菜单列表", description = "根据平台ID获取导航菜单列表接口")
    public List<Menu> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return menuService.selectListByPlatformId(platformId);
    }

    /**
     * 根据创建人获取列表
     *
     * @param creator 创建人
     * @return List<Menu> 导航菜单列表
     */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.menu.listByCreator", name = "根据创建人获取导航菜单列表", description = "根据创建人获取导航菜单列表接口")
    public List<Menu> selectListByCreator(@PathVariable("creator") String creator) {
        return menuService.selectListByCreator(creator);
    }

    /**
     * 根据id获取导航菜单信息
     *
     * @param id id
     * @return 导航菜单信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.menu.selectById", name = "根据id获取导航菜单信息", description = "根据id获取导航菜单信息接口")
    public Result<Menu> selectById(@PathVariable("id") String id) {
        return new Result<>(menuService.selectById(id), "查询成功");
    }

    /**
     * 创建导航菜单信息
     *
     * @param menu 导航菜单实体信息
     * @return 创建后的导航菜单信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.menu.create", name = "创建导航菜单", description = "创建导航菜单信息接口")
    public Result<Menu> create(@Validated Menu menu) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(menuService.create(menu, onlineUser), "创建成功");
    }

    /**
     * 修改导航菜单信息
     *
     * @param menu 导航菜单实体信息
     * @return 修改后的导航菜单信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.menu.update", name = "修改导航菜单", description = "修改导航菜单信息接口")
    public Result<Menu> update(@Validated Menu menu, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        menu.setId(id);
        return new Result<>(menuService.update(menu, onlineUser), "修改成功");
    }

    /**
     * 保存导航菜单信息
     *
     * @param menu 导航菜单实体信息
     * @return 保存后的导航菜单信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.menu.save", name = "保存导航菜单", description = "保存导航菜单信息接口")
    public Result<Menu> save(@Validated Menu menu) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(menuService.save(menu, onlineUser), "保存成功");
    }

    /**
     * 根据导航菜单id删除导航菜单信息
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.menu.delete", name = "删除导航菜单", description = "删除导航菜单信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        menuService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }


    @GetMapping("/getCurrentUserMenu")
    @Auth(value = "clever-system.menu.getCurrentUserMenu", name = "获取当前用户菜单", description = "获取当前用户菜单接口")
    public Result<List<MenuTreeVo>> getCurrentUserMenu(Integer platformId) {
        return new Result<>(menuService.getCurrentUserMenu(platformId), "查询成功");
    }
}
