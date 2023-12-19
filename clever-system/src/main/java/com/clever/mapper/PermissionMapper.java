package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Permission;


/**
 * 系统权限Mapper
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
