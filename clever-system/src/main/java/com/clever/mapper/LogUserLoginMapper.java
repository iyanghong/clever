package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.LogUserLogin;


/**
 * 用户登录日志Mapper
 *
 * @Author xixi
 * @Date 2023-12-27 10:57:55
 */
@Mapper
public interface LogUserLoginMapper extends BaseMapper<LogUserLogin> {

}
