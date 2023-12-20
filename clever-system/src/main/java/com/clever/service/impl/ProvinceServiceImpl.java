package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.ProvinceMapper;
import com.clever.bean.system.Province;
import com.clever.service.ProvinceService;

import javax.annotation.Resource;

/**
 * 省份服务
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final static Logger log = LoggerFactory.getLogger(ProvinceServiceImpl.class);

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 分页查询省份列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       省份名称
     * @return Page<Province>
     */
    @Override
    public Page<Province> selectPage(Integer pageNumber, Integer pageSize, String name) {
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        return provinceMapper.selectPage(new Page<Province>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据省份编号获取省份信息
     *
     * @param id 省份编号
     * @return Province 省份信息
     */
    @Override
    public Province selectById(Integer id) {
        return provinceMapper.selectById(id);
    }

    /**
     * 保存省份信息
     *
     * @param province   省份实体信息
     * @param onlineUser 当前登录用户
     */
    @Override
    public void save(Province province, OnlineUser onlineUser) {
        if (province.getId() == null) {
            provinceMapper.insert(province);
            log.info("省份, 省份信息创建成功: userId={}, provinceId={}", onlineUser.getId(), province.getId());
        } else {
            provinceMapper.updateById(province);
            log.info("省份, 省份信息修改成功: userId={}, provinceId={}", onlineUser.getId(), province.getId());
        }
    }

    /**
     * 根据省份编号删除省份信息
     *
     * @param id         省份编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(Integer id, OnlineUser onlineUser) {
        provinceMapper.deleteById(id);
        log.info("省份, 省份信息删除成功: userId={}, provinceId={}", onlineUser.getId(), id);
    }

    /**
     * 根据省份编号列表删除省份信息
     *
     * @param ids        省份编号列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
        provinceMapper.deleteBatchIds(ids);
        log.info("省份, 省份信息批量删除成功: userId={}, count={}, provinceIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }
}
