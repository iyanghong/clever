package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.UserRoleRel;

import java.util.List;


/**
 * 用户-角色Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface UserRoleRelMapper extends BaseMapper<UserRoleRel> {
    /**
     * 查询用户的所有角色id
     *
     * @param userId 用户id
     * @return 角色id
     */
    List<String> selectRoleIdsByUserId(String userId);

    /**
     * 批量插入用户选择的角色
     *
     * @param userId  用户id
     * @param roleIds 角色id
     */
    void insertByList(String userId, List<String> roleIds);
}
