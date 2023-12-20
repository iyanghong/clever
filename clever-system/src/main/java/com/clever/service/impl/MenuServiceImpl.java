package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.MenuMapper;
import com.clever.bean.system.Menu;
import com.clever.service.MenuService;

import javax.annotation.Resource;

/**
 * 导航菜单服务
 *
 * @Author xixi
 * @Date 2023-12-20 09:33:24
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
    public Page<Menu> selectPage(Integer pageNumber, Integer pageSize, String platformId, String name, String code) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(platformId)) {
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
     * 根据id获取导航菜单信息
     *
     * @param id id
     * @return List<Menu> 导航菜单信息
     */
    @Override
    public Menu selectById(String id) {
        return menuMapper.selectById(id);
    }

    /**
     * 根据平台ID获取导航菜单列表
     *
     * @param platformId 平台ID
     * @return List<Menu> 导航菜单列表
     */
    @Override
    public List<Menu> selectListByPlatformId(Integer platformId) {
        return menuMapper.selectList(new QueryWrapper<Menu>().eq("platform_id", platformId).orderByAsc("id"));
    }

    /**
     * 保存导航菜单信息
     *
     * @param menu       导航菜单实体信息
     * @param onlineUser 当前登录用户
     */
    @Override
    public void save(Menu menu, OnlineUser onlineUser) {
        if (StringUtils.isBlank(menu.getId())) {
            menuMapper.insert(menu);
            log.info("导航菜单, 导航菜单信息创建成功: userId={}, menuId={}", onlineUser.getId(), menu.getId());
        } else {
            menuMapper.updateById(menu);
            log.info("导航菜单, 导航菜单信息修改成功: userId={}, menuId={}", onlineUser.getId(), menu.getId());
        }
    }

    /**
     * 根据id获取导航菜单列表
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
     * 根据平台ID删除导航菜单
     *
     * @param platformId 平台ID
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        menuMapper.delete(new QueryWrapper<Menu>().eq("platform_id", platformId));
        log.info("导航菜单, 导航菜单信息根据平台ID删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }
}
