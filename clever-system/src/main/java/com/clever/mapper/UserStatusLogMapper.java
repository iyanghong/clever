package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.UserStatusLog;


/**
 * 用户状态日志Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface UserStatusLogMapper extends BaseMapper<UserStatusLog> {

}
