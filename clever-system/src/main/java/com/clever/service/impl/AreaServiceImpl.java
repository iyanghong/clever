package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.AreaMapper;
import com.clever.bean.system.Area;
import com.clever.service.AreaService;

import javax.annotation.Resource;

/**
 * 城区地址服务
 *
 * @Author xixi
 * @Date 2023-12-20 05:08:09
 */
@Service
public class AreaServiceImpl implements AreaService {

    private final static Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Resource
    private AreaMapper areaMapper;

    /**
     * 分页查询城区地址列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       地区名称
     * @param cityId     城市编号
     * @param provinceId 省份编号
     * @return Page<Area>
     */
    @Override
    public Page<Area> selectPage(Integer pageNumber, Integer pageSize, String name, Integer cityId, Integer provinceId) {
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (cityId != null) {
            queryWrapper.eq("city_id", cityId);
        }
        if (provinceId != null) {
            queryWrapper.eq("province_id", provinceId);
        }
        return areaMapper.selectPage(new Page<Area>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据地区编号获取城区地址信息
     *
     * @param id 地区编号
     * @return Area 城区地址信息
     */
    @Override
    public Area selectById(Integer id) {
        return areaMapper.selectById(id);
    }

    /**
     * 根据城市编号获取城区地址列表
     *
     * @param cityId 城市编号
     * @return List<Area> 城区地址列表
     */
    @Override
    public List<Area> selectListByCityId(Integer cityId) {
        return areaMapper.selectList(new QueryWrapper<Area>().eq("city_id", cityId).orderByAsc("id"));
    }

    /**
     * 根据省份编号获取城区地址列表
     *
     * @param provinceId 省份编号
     * @return List<Area> 城区地址列表
     */
    @Override
    public List<Area> selectListByProvinceId(Integer provinceId) {
        return areaMapper.selectList(new QueryWrapper<Area>().eq("province_id", provinceId).orderByAsc("id"));
    }

    /**
     * 保存城区地址信息
     *
     * @param area       城区地址实体信息
     * @param onlineUser 当前登录用户
     */
    @Override
    public void save(Area area, OnlineUser onlineUser) {
        if (area.getId() == null) {
            areaMapper.insert(area);
            log.info("城区地址, 城区地址信息创建成功: userId={}, areaId={}", onlineUser.getId(), area.getId());
        } else {
            areaMapper.updateById(area);
            log.info("城区地址, 城区地址信息修改成功: userId={}, areaId={}", onlineUser.getId(), area.getId());
        }
    }

    /**
     * 根据地区编号删除城区地址信息
     *
     * @param id         地区编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(Integer id, OnlineUser onlineUser) {
        areaMapper.deleteById(id);
        log.info("城区地址, 城区地址信息删除成功: userId={}, areaId={}", onlineUser.getId(), id);
    }

    /**
     * 根据地区编号列表删除城区地址信息
     *
     * @param ids        地区编号列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<Integer> ids, OnlineUser onlineUser) {
        areaMapper.deleteBatchIds(ids);
        log.info("城区地址, 城区地址信息批量删除成功: userId={}, count={}, areaIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据城市编号删除城区地址
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCityId(String cityId, OnlineUser onlineUser) {
        areaMapper.delete(new QueryWrapper<Area>().eq("city_id", cityId));
        log.info("城区地址, 城区地址信息根据城市编号删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
    }

    /**
     * 根据省份编号删除城区地址
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByProvinceId(String provinceId, OnlineUser onlineUser) {
        areaMapper.delete(new QueryWrapper<Area>().eq("province_id", provinceId));
        log.info("城区地址, 城区地址信息根据省份编号删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
    }
}
