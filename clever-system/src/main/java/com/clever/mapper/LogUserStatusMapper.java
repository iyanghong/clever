package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.LogUserStatus;


/**
 * 用户状态日志Mapper
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@Mapper
public interface LogUserStatusMapper extends BaseMapper<LogUserStatus> {

}
