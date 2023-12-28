package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.User;


/**
 * 用户Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 设置密码错误次数+1
     *
     * @param userId 用户id
     */
    void updatePasswordErrorCount(String userId);

    User selectByAccountOrEmail(String account, String email);

    /**
     * 设置账户为正常状态
     *
     * @param email email
     */
    void updateUserToAvailable(String email);
}
