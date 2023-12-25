package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.StreetMapper;
import com.clever.bean.system.Street;
import com.clever.service.StreetService;

import javax.annotation.Resource;

/**
 * 街道服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class StreetServiceImpl implements StreetService {

    private final static Logger log = LoggerFactory.getLogger(StreetServiceImpl.class);

    @Resource
    private StreetMapper streetMapper;

    /**
     * 分页查询街道列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       街道名称
     * @param areaId     地区编号
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return Page<Street>
     */
    @Override
    public Page<Street> selectPage(Integer pageNumber, Integer pageSize, String name, Integer areaId, Integer cityId, Integer provinceId) {
        QueryWrapper<Street> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (areaId != null) {
            queryWrapper.eq("area_id", areaId);
        }
        if (cityId != null) {
            queryWrapper.eq("city_id", cityId);
        }
        if (provinceId != null) {
            queryWrapper.eq("province_id", provinceId);
        }
        return streetMapper.selectPage(new Page<Street>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据街道编号获取街道
     *
     * @param id 街道编号
     * @return Street 街道信息
     */
    @Override
    public Street selectById(Integer id) {
        return streetMapper.selectById(id);
    }

    /**
     * 根据地区编号获取列表
     *
     * @param areaId 地区编号
     * @return List<Street> 街道列表
     */
    @Override
    public List<Street> selectListByAreaId(Integer areaId) {
        return streetMapper.selectList(new QueryWrapper<Street>().eq("area_id", areaId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Street> 街道列表
     */
    @Override
    public List<Street> selectListByCityId(Integer cityId) {
        return streetMapper.selectList(new QueryWrapper<Street>().eq("city_id", cityId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Street> 街道列表
     */
    @Override
    public List<Street> selectListByProvinceId(Integer provinceId) {
        return streetMapper.selectList(new QueryWrapper<Street>().eq("province_id", provinceId).orderByAsc("primaryKeyColumn.columnName"));
    }

    /**
     * 新建街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 新建后的街道信息
     */
    @Override
    public Street create(Street street, OnlineUser onlineUser) {
        streetMapper.insert(street);
        log.info("街道, 街道信息创建成功: userId={}, streetId={}", onlineUser.getId(), street.getId());
        return street;
    }

    /**
     * 修改街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 修改后的街道信息
     */
    @Override
    public Street update(Street street, OnlineUser onlineUser) {
        streetMapper.updateById(street);
        log.info("街道, 街道信息修改成功: userId={}, streetId={}", onlineUser.getId(), street.getId());
        return street;
    }

    /**
     * 保存街道
     *
     * @param street     街道实体信息
     * @param onlineUser 当前登录用户
     * @return Street 保存后的街道信息
     */
    @Override
    public Street save(Street street, OnlineUser onlineUser) {
        if (street.getId() != null) {
            return create(street, onlineUser);
        }
        return update(street, onlineUser);
    }

    /**
     * 根据街道编号删除街道信息
     *
     * @param id         街道编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(Integer id, OnlineUser onlineUser) {
        streetMapper.deleteById(id);
        log.info("街道, 街道信息删除成功: userId={}, streetId={}", onlineUser.getId(), id);
    }

    /**
     * 根据街道编号列表删除街道信息
     *
     * @param ids        街道编号列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
        streetMapper.deleteBatchIds(ids);
        log.info("街道, 街道信息批量删除成功: userId={}, count={}, streetIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据地区编号删除
     *
     * @param areaId     地区编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByAreaId(Integer areaId, OnlineUser onlineUser) {
        streetMapper.delete(new QueryWrapper<Street>().eq("area_id", areaId));
        log.info("街道, 街道信息根据areaId删除成功: userId={}, areaId={}", onlineUser.getId(), areaId);
    }

    /**
     * 根据城市编号删除
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCityId(Integer cityId, OnlineUser onlineUser) {
        streetMapper.delete(new QueryWrapper<Street>().eq("city_id", cityId));
        log.info("街道, 街道信息根据cityId删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
    }

    /**
     * 根据省份编号删除
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByProvinceId(Integer provinceId, OnlineUser onlineUser) {
        streetMapper.delete(new QueryWrapper<Street>().eq("province_id", provinceId));
        log.info("街道, 街道信息根据provinceId删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
    }
}
