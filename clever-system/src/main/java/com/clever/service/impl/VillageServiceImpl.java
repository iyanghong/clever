package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.VillageMapper;
import com.clever.bean.system.Village;
import com.clever.service.VillageService;

import javax.annotation.Resource;

/**
 * 村庄服务
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:25
 */
@Service
public class VillageServiceImpl implements VillageService {

    private final static Logger log = LoggerFactory.getLogger(VillageServiceImpl.class);

    @Resource
    private VillageMapper villageMapper;

    /**
     * 分页查询村庄列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       村庄名称
     * @param provinceId 省份编号
     * @param cityId     城市编号
     * @param areaId     区/县编号
     * @param streetId   街道编号
     * @return Page<Village>
     */
    @Override
    public Page<Village> selectPage(Integer pageNumber, Integer pageSize, String name, Integer provinceId, Integer cityId, Integer areaId, Integer streetId) {
        QueryWrapper<Village> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (provinceId != null) {
            queryWrapper.eq("province_id", provinceId);
        }
        if (cityId != null) {
            queryWrapper.eq("city_id", cityId);
        }
        if (areaId != null) {
            queryWrapper.eq("area_id", areaId);
        }
        if (streetId != null) {
            queryWrapper.eq("street_id", streetId);
        }
        return villageMapper.selectPage(new Page<Village>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据id获取村庄
     *
     * @param id
     * @return Village 村庄信息
     */
    @Override
    public Village selectById(Long id) {
        return villageMapper.selectById(id);
    }

    /**
     * 根据省份编号获取列表
     *
     * @param provinceId 省份编号
     * @return List<Village> 村庄列表
     */
    @Override
    public List<Village> selectListByProvinceId(Integer provinceId) {
        return villageMapper.selectList(new QueryWrapper<Village>().eq("province_id", provinceId).orderByAsc("id"));
    }

    /**
     * 根据城市编号获取列表
     *
     * @param cityId 城市编号
     * @return List<Village> 村庄列表
     */
    @Override
    public List<Village> selectListByCityId(Integer cityId) {
        return villageMapper.selectList(new QueryWrapper<Village>().eq("city_id", cityId).orderByAsc("id"));
    }

    /**
     * 根据区/县编号获取列表
     *
     * @param areaId 区/县编号
     * @return List<Village> 村庄列表
     */
    @Override
    public List<Village> selectListByAreaId(Integer areaId) {
        return villageMapper.selectList(new QueryWrapper<Village>().eq("area_id", areaId).orderByAsc("id"));
    }

    /**
     * 根据街道编号获取列表
     *
     * @param streetId 街道编号
     * @return List<Village> 村庄列表
     */
    @Override
    public List<Village> selectListByStreetId(Integer streetId) {
        return villageMapper.selectList(new QueryWrapper<Village>().eq("street_id", streetId).orderByAsc("id"));
    }

    /**
     * 新建村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 新建后的村庄信息
     */
    @Override
    public Village create(Village village, OnlineUser onlineUser) {
        villageMapper.insert(village);
        log.info("村庄, 村庄信息创建成功: userId={}, villageId={}", onlineUser.getId(), village.getId());
        return village;
    }

    /**
     * 修改村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 修改后的村庄信息
     */
    @Override
    public Village update(Village village, OnlineUser onlineUser) {
        villageMapper.updateById(village);
        log.info("村庄, 村庄信息修改成功: userId={}, villageId={}", onlineUser.getId(), village.getId());
        return village;
    }

    /**
     * 保存村庄
     *
     * @param village    村庄实体信息
     * @param onlineUser 当前登录用户
     * @return Village 保存后的村庄信息
     */
    @Override
    public Village save(Village village, OnlineUser onlineUser) {
        if (village.getId() != null) {
            return create(village, onlineUser);
        }
        return update(village, onlineUser);
    }

    /**
     * 根据id删除村庄信息
     *
     * @param id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(Long id, OnlineUser onlineUser) {
        villageMapper.deleteById(id);
        log.info("村庄, 村庄信息删除成功: userId={}, villageId={}", onlineUser.getId(), id);
    }

    /**
     * 根据id列表删除村庄信息
     *
     * @param ids        id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<Long> ids, OnlineUser onlineUser) {
        villageMapper.deleteBatchIds(ids);
        log.info("村庄, 村庄信息批量删除成功: userId={}, count={}, villageIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据省份编号删除
     *
     * @param provinceId 省份编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByProvinceId(Integer provinceId, OnlineUser onlineUser) {
        villageMapper.delete(new QueryWrapper<Village>().eq("province_id", provinceId));
        log.info("村庄, 村庄信息根据provinceId删除成功: userId={}, provinceId={}", onlineUser.getId(), provinceId);
    }

    /**
     * 根据城市编号删除
     *
     * @param cityId     城市编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCityId(Integer cityId, OnlineUser onlineUser) {
        villageMapper.delete(new QueryWrapper<Village>().eq("city_id", cityId));
        log.info("村庄, 村庄信息根据cityId删除成功: userId={}, cityId={}", onlineUser.getId(), cityId);
    }

    /**
     * 根据区/县编号删除
     *
     * @param areaId     区/县编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByAreaId(Integer areaId, OnlineUser onlineUser) {
        villageMapper.delete(new QueryWrapper<Village>().eq("area_id", areaId));
        log.info("村庄, 村庄信息根据areaId删除成功: userId={}, areaId={}", onlineUser.getId(), areaId);
    }

    /**
     * 根据街道编号删除
     *
     * @param streetId   街道编号
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByStreetId(Integer streetId, OnlineUser onlineUser) {
        villageMapper.delete(new QueryWrapper<Village>().eq("street_id", streetId));
        log.info("村庄, 村庄信息根据streetId删除成功: userId={}, streetId={}", onlineUser.getId(), streetId);
    }
}
