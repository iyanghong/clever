package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.RolePermission;


/**
 * 角色-权限Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 10:35:35
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}
