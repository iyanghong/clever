package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.Street;


/**
 * 街道Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface StreetMapper extends BaseMapper<Street> {
}