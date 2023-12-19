package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Menu;


/**
 * 导航菜单Mapper
 *
 * @Author xixi
 * @Date 2023-12-19 05:52:43
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
