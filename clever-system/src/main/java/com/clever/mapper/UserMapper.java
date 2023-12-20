package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.User;


/**
 * 用户Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
