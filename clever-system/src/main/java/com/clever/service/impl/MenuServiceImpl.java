package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.projo.MenuTreeVo;
import com.clever.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.clever.mapper.MenuMapper;
import com.clever.bean.system.Menu;
import com.clever.service.MenuService;

import javax.annotation.Resource;

/**
 * 导航菜单服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private MenuMapper menuMapper;

    /**
     * 分页查询导航菜单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台ID
     * @param name       菜单名称
     * @param code       菜单Code
     * @return Page<Menu>
     */
    @Override
    public Page<Menu> selectPage(Integer pageNumber, Integer pageSize, Integer platformId, String name, String code) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (platformId != null) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.eq("code", code);
        }
        return menuMapper.selectPage(new Page<Menu>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据id获取导航菜单
     *
     * @param id id
     * @return Menu 导航菜单信息
     */
    @Override
    public Menu selectById(String id) {
        return menuMapper.selectById(id);
    }

    /**
     * 根据平台ID获取列表
     *
     * @param platformId 平台ID
     * @return List<Menu> 导航菜单列表
     */
    @Override
    public List<Menu> selectListByPlatformId(Integer platformId) {
        return menuMapper.selectList(new QueryWrapper<Menu>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 根据创建人获取列表
     *
     * @param creator 创建人
     * @return List<Menu> 导航菜单列表
     */
    @Override
    public List<Menu> selectListByCreator(String creator) {
        return menuMapper.selectList(new QueryWrapper<Menu>().eq("creator", creator).orderByAsc("id"));
    }

    /**
     * 新建导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 新建后的导航菜单信息
     */
    @Override
    public Menu create(Menu menu, OnlineUser onlineUser) {
        menuMapper.insert(menu);
        log.info("导航菜单, 导航菜单信息创建成功: userId={}, menuId={}", onlineUser.getId(), menu.getId());
        return menu;
    }

    /**
     * 修改导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 修改后的导航菜单信息
     */
    @Override
    public Menu update(Menu menu, OnlineUser onlineUser) {
        menuMapper.updateById(menu);
        log.info("导航菜单, 导航菜单信息修改成功: userId={}, menuId={}", onlineUser.getId(), menu.getId());
        return menu;
    }

    /**
     * 保存导航菜单
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     * @return Menu 保存后的导航菜单信息
     */
    @Override
    public Menu save(Menu menu, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(menu.getId())) {
            return create(menu, onlineUser);
        }
        return update(menu, onlineUser);
    }

    /**
     * 根据id删除导航菜单信息
     *
     * @param id         id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        menuMapper.deleteById(id);
        log.info("导航菜单, 导航菜单信息删除成功: userId={}, menuId={}", onlineUser.getId(), id);
    }

    /**
     * 根据id列表删除导航菜单信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        menuMapper.deleteBatchIds(ids);
        log.info("导航菜单, 导航菜单信息批量删除成功: userId={}, count={}, menuIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台ID删除
     *
     * @param platformId 平台ID
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(Integer platformId, OnlineUser onlineUser) {
        menuMapper.delete(new QueryWrapper<Menu>().eq("platform_id", platformId));
        log.info("导航菜单, 导航菜单信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据创建人删除
     *
     * @param creator    创建人
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        menuMapper.delete(new QueryWrapper<Menu>().eq("creator", creator));
        log.info("导航菜单, 导航菜单信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }

    /**
     * 获取当前用户菜单，树型结构
     *
     * @param platformId 平台ID
     * @return List<MenuTreeVo> 菜单列表
     */
    @Override
    public List<MenuTreeVo> getCurrentUserMenu(Integer platformId) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        List<Menu> menus = menuMapper.selectUserHasMenu(platformId, onlineUser.getId());
        return recursiveTraversalMenu(menus, "-1");
    }

    private List<MenuTreeVo> recursiveTraversalMenu(List<Menu> menus, String parentId) {
        // 递归获取菜单树形结构
        List<MenuTreeVo> menuTreeVos = new ArrayList<>();
        for (Menu menu : menus) {
            if (parentId.equals(menu.getParent())) {
                MenuTreeVo menuTreeVo = new MenuTreeVo(menu);
                menuTreeVo.setChildren(recursiveTraversalMenu(menus, menu.getId()));
                menuTreeVos.add(menuTreeVo);
            }
        }
        return menuTreeVos.stream().sorted(Comparator.comparing(MenuTreeVo::getSort).reversed()).collect(Collectors.toList());
    }
}
