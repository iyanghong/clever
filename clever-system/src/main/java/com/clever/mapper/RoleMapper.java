package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Role;


/**
 * 系统角色Mapper
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
