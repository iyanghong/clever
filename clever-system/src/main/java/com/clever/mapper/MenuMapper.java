package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Menu;

import java.util.List;


/**
 * 导航菜单Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 获取用户拥有的菜单
     *
     * @param platformId 平台ID
     * @param userId     用户ID
     * @return List<Menu>
     */
    List<Menu> selectUserHasMenu(Integer platformId, String userId);
}
