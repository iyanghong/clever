package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.RoleMenuRel;
import com.clever.service.RoleMenuRelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色菜单接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/roleMenuRel")
@AuthGroup(name = "角色菜单模块", description = "角色菜单模块权限组")
public class RoleMenuRelController {

    @Resource
    private RoleMenuRelService roleMenuRelService;


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
    @Auth(value = "clever-system.roleMenuRel.page", name = "角色菜单分页", description = "角色菜单分页接口")
    public Result<Page<RoleMenuRel>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String menuId, String roleId) {
        return new Result<>(roleMenuRelService.selectPage(pageNumber, pageSize, menuId, roleId), "分页数据查询成功");
    }

    /**
     * 根据菜单唯一标识获取列表
     *
     * @param menuId 菜单唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    @GetMapping("/listByMenuId/{menuId}")
    @Auth(value = "clever-system.roleMenuRel.listByMenuId", name = "根据菜单唯一标识获取角色菜单列表", description = "根据菜单唯一标识获取角色菜单列表接口")
    public List<RoleMenuRel> selectListByMenuId(@PathVariable("menuId") String menuId) {
        return roleMenuRelService.selectListByMenuId(menuId);
    }

    /**
     * 根据角色唯一标识获取列表
     *
     * @param roleId 角色唯一标识
     * @return List<RoleMenuRel> 角色菜单列表
     */
    @GetMapping("/listByRoleId/{roleId}")
    @Auth(value = "clever-system.roleMenuRel.listByRoleId", name = "根据角色唯一标识获取角色菜单列表", description = "根据角色唯一标识获取角色菜单列表接口")
    public List<RoleMenuRel> selectListByRoleId(@PathVariable("roleId") String roleId) {
        return roleMenuRelService.selectListByRoleId(roleId);
    }

    /**
     * 根据编号获取角色菜单信息
     *
     * @param id 编号
     * @return 角色菜单信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据编号获取角色菜单信息", description = "根据编号获取角色菜单信息接口")
    public Result<RoleMenuRel> selectById(@PathVariable("id") String id) {
        return new Result<>(roleMenuRelService.selectById(id), "查询成功");
    }

    /**
     * 创建角色菜单信息
     *
     * @param roleMenuRel 角色菜单实体信息
     * @return 创建后的角色菜单信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.roleMenuRel.create", name = "创建角色菜单", description = "创建角色菜单信息接口")
    public Result<RoleMenuRel> create(@Validated RoleMenuRel roleMenuRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(roleMenuRelService.create(roleMenuRel, onlineUser), "创建成功");
    }

    /**
     * 修改角色菜单信息
     *
     * @param roleMenuRel 角色菜单实体信息
     * @return 修改后的角色菜单信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.roleMenuRel.update", name = "修改角色菜单", description = "修改角色菜单信息接口")
    public Result<RoleMenuRel> update(@Validated RoleMenuRel roleMenuRel, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleMenuRel.setId(id);
        return new Result<>(roleMenuRelService.update(roleMenuRel, onlineUser), "修改成功");
    }

    /**
     * 保存角色菜单信息
     *
     * @param roleMenuRel 角色菜单实体信息
     * @return 保存后的角色菜单信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.roleMenuRel.save", name = "保存角色菜单", description = "保存角色菜单信息接口")
    public Result<RoleMenuRel> save(@Validated RoleMenuRel roleMenuRel) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(roleMenuRelService.save(roleMenuRel, onlineUser), "保存成功");
    }

    /**
     * 根据角色菜单id删除角色菜单信息
     *
     * @param id 编号
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.roleMenuRel.delete", name = "删除角色菜单", description = "删除角色菜单信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        roleMenuRelService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
