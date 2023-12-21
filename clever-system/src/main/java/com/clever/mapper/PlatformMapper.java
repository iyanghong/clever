package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Platform;

import java.util.List;


/**
 * 平台Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface PlatformMapper extends BaseMapper<Platform> {
    /**
     * 根据用户id查询平台列表
     *
     * @param userId 用户id
     * @return 平台列表
     */
    List<Platform> selectByUserId(String userId);
}
