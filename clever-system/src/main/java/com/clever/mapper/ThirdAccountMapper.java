package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.ThirdAccount;


/**
 * 第三方平台账号Mapper
 *
 * @Author xixi
 * @Date 2023-12-20 09:27:12
 */
@Mapper
public interface ThirdAccountMapper extends BaseMapper<ThirdAccount> {
}