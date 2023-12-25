package com.clever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.clever.bean.system.ThirdAccount;


/**
 * 第三方平台账号Mapper
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Mapper
public interface ThirdAccountMapper extends BaseMapper<ThirdAccount> {

}
