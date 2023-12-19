package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.PermissionGroup;


/**
 * 系统权限组Mapper
 *
 * @Author xixi
 * @Date 2023-12-19 11:45:47
 */
@Mapper
public interface PermissionGroupMapper extends BaseMapper<PermissionGroup> {
}
