package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Menu;

/**
 * 导航菜单服务接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
public interface MenuService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       菜单名称
     * @param code       菜单Code
     * @return Page<Menu>
     */
    Page<Menu> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code);

    /**
     * 根据id获取导航菜单
     *
     * @param id id
     * @return Menu id信息
     */
    Menu selectById(String id);

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<Menu> 导航菜单列表
     */
    List<Menu> selectListByPlatformId(Integer platformId);

    /**
     * 根据创建人获取列表
     *
     * @param creator 创建人
     * @return List<Menu> 导航菜单列表
     */
    List<Menu> selectListByCreator(String creator);

    /**
     * 新建导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 新建后的导航菜单信息
     */
    Menu create(Menu menu, OnlineUser onlineUser);

    /**
     * 修改导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 修改后的导航菜单信息
     */
    Menu update(Menu menu, OnlineUser onlineUser);

    /**
     * 保存导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 保存后的导航菜单信息
     */
    Menu save(Menu menu, OnlineUser onlineUser);

    /**
     * 根据id删除信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据id列表删除信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台ID删除
     *
     * @param platformId 平台ID
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(Integer platformId, OnlineUser onlineUser);

    /**
     * 根据创建人删除
     *
     * @param creator    创建人
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

}
