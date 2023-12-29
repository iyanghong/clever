package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.RolePermissionRel;

import java.util.List;


/**
 * 角色-权限Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface RolePermissionRelMapper extends BaseMapper<RolePermissionRel> {

    /**
     * 根据规则组id删除角色-权限关系
     *
     * @param permissionGroupIds 规则租id列表
     */
    void deleteByPermissionGroupIds(List<String> permissionGroupIds);
}
