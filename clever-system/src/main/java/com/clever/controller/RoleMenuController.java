package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.RoleMenu;
import com.clever.service.RoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色菜单接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/RoleMenu")
@AuthGroup(name = "角色菜单模块", description = "角色菜单模块权限组")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;


    /**
     * 分页查询角色菜单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param menuId     菜单唯一标识
     * @param roleId     角色唯一标识
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.roleMenu.page", name = "角色菜单分页", description = "角色菜单分页接口")
    public Result<Page<RoleMenu>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String menuId, String roleId) {
        return new Result<>(roleMenuService.selectPage(pageNumber, pageSize, menuId, roleId), "分页数据查询成功");
    }

    /**
     * 根据编号获取角色菜单信息
     *
     * @param id 编号
     * @return 角色菜单信息
     */
    @GetMapping("/get/{id}")
    public Result<RoleMenu> selectById(@PathVariable("id") String id) {
        return new Result<>(roleMenuService.selectById(id), "查询成功");
    }

    /**
     * 根据菜单唯一标识获取角色菜单列表
     *
     * @param menuId 菜单唯一标识
     * @return 角色菜单列表
     */
    @GetMapping("/getByMenuId/{menuId}")
    @Auth(value = "clever-system.roleMenu.getByMenuId", name = "根据menu_id获取角色菜单列表", description = "角色菜单列表接口")
    public Result<List<RoleMenu>> selectByMenuId(@PathVariable("menuId") String menuId) {
        return new Result<>(roleMenuService.selectListByMenuId(menuId), "查询成功");
    }

    /**
     * 根据角色唯一标识获取角色菜单列表
     *
     * @param roleId 角色唯一标识
     * @return 角色菜单列表
     */
    @GetMapping("/getByRoleId/{roleId}")
    @Auth(value = "clever-system.roleMenu.getByRoleId", name = "根据role_id获取角色菜单列表", description = "角色菜单列表接口")
    public Result<List<RoleMenu>> selectByRoleId(@PathVariable("roleId") String roleId) {
        return new Result<>(roleMenuService.selectListByRoleId(roleId), "查询成功");
    }

    /**
     * 保存角色菜单信息
     *
     * @param roleMenu 角色菜单实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.roleMenu.save", name = "保存角色菜单", description = "保存角色菜单信息接口")
    public Result<String> save(RoleMenu roleMenu) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleMenuService.save(roleMenu, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据编号获取角色菜单列表
     *
     * @param id 编号
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.roleMenu.delete", name = "删除角色菜单", description = "删除角色菜单信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleMenuService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
