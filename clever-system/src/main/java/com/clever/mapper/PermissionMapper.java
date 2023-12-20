package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Permission;

import java.util.List;


/**
 * 系统权限Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> selectPermissionsByRoles(List<String> roleIds);
}
