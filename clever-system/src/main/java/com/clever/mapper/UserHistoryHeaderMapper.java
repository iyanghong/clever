package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.UserHistoryHeader;


/**
 * 用户历史头像表Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface UserHistoryHeaderMapper extends BaseMapper<UserHistoryHeader> {
}
