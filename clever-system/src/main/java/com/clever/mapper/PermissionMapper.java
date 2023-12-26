package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Permission;

import java.util.List;


/**
 * 系统权限Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id列表
     * @return 权限列表
     */
    List<String> selectPermissionsByRoles(List<String> roleIds);
}
