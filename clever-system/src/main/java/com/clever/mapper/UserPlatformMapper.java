package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.UserPlatform;


/**
 * 用户-平台Mapper
 *
 * @Author xixi
 * @Date 2023-12-19 11:38:38
 */
@Mapper
public interface UserPlatformMapper extends BaseMapper<UserPlatform> {
}
