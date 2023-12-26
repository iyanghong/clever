package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Role;

import java.util.List;


/**
 * 系统角色Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> selectRolesByUserId(String userId);
}
