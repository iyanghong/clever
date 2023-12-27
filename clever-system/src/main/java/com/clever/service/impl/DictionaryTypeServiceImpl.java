package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.clever.mapper.DictionaryTypeMapper;
import com.clever.bean.system.DictionaryType;
import com.clever.service.DictionaryTypeService;

import javax.annotation.Resource;

/**
 * 字典类型服务
 *
 * @Author xixi
 * @Date 2023-12-27 11:15:13
 */
@Service
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

    private final static Logger log = LoggerFactory.getLogger(DictionaryTypeServiceImpl.class);

    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;

    /**
     * 分页查询字典类型列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param parentId   上级分类id
     * @param name       字典类型名称
     * @param code       字典类型标识
     * @return Page<DictionaryType>
     */
    @Override
    public Page<DictionaryType> selectPage(Integer pageNumber, Integer pageSize, String platformId, String parentId, String name, String code) {
        QueryWrapper<DictionaryType> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(platformId)) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(parentId)) {
            queryWrapper.eq("parent_id", parentId);
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            queryWrapper.eq("code", code);
        }
        return dictionaryTypeMapper.selectPage(new Page<DictionaryType>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据字典类型id获取字典类型
     *
     * @param id 字典类型id
     * @return DictionaryType 字典类型信息
     */
    @Override
    public DictionaryType selectById(String id) {
        return dictionaryTypeMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<DictionaryType> 字典类型列表
     */
    @Override
    public List<DictionaryType> selectListByPlatformId(String platformId) {
        return dictionaryTypeMapper.selectList(new QueryWrapper<DictionaryType>().eq("platform_id", platformId).orderByAsc("sort"));
    }

    /**
     * 根据上级分类id获取列表
     *
     * @param parentId 上级分类id
     * @return List<DictionaryType> 字典类型列表
     */
    @Override
    public List<DictionaryType> selectListByParentId(String parentId) {
        return dictionaryTypeMapper.selectList(new QueryWrapper<DictionaryType>().eq("parent_id", parentId).orderByAsc("sort"));
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<DictionaryType> 字典类型列表
     */
    @Override
    public List<DictionaryType> selectListByCreator(String creator) {
        return dictionaryTypeMapper.selectList(new QueryWrapper<DictionaryType>().eq("creator", creator).orderByAsc("sort"));
    }

    /**
     * 新建字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 新建后的字典类型信息
     */
    @Override
    public DictionaryType create(DictionaryType dictionaryType, OnlineUser onlineUser) {
        if (dictionaryTypeMapper.selectCount(new QueryWrapper<DictionaryType>().eq("platform_id", dictionaryType.getPlatformId()).eq("code", dictionaryType.getCode())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("字典类型标识"));
        }
        dictionaryTypeMapper.insert(dictionaryType);
        log.info("字典类型, 字典类型信息创建成功: userId={}, dictionaryTypeId={}", onlineUser.getId(), dictionaryType.getId());
        return dictionaryType;
    }

    /**
     * 修改字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 修改后的字典类型信息
     */
    @Override
    public DictionaryType update(DictionaryType dictionaryType, OnlineUser onlineUser) {
        if (dictionaryTypeMapper.selectCount(new QueryWrapper<DictionaryType>().eq("platform_id", dictionaryType.getPlatformId()).eq("code", dictionaryType.getCode()).ne("id", dictionaryType.getId())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("字典类型标识"));
        }
        dictionaryTypeMapper.updateById(dictionaryType);
        log.info("字典类型, 字典类型信息修改成功: userId={}, dictionaryTypeId={}", onlineUser.getId(), dictionaryType.getId());
        return dictionaryType;
    }

    /**
     * 保存字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 保存后的字典类型信息
     */
    @Override
    public DictionaryType save(DictionaryType dictionaryType, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(dictionaryType.getId())) {
            return create(dictionaryType, onlineUser);
        }
        return update(dictionaryType, onlineUser);
    }

    /**
     * 根据字典类型id删除字典类型信息
     *
     * @param id         字典类型id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        dictionaryTypeMapper.deleteById(id);
        log.info("字典类型, 字典类型信息删除成功: userId={}, dictionaryTypeId={}", onlineUser.getId(), id);
    }

    /**
     * 根据字典类型id列表删除字典类型信息
     *
     * @param ids        字典类型id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        dictionaryTypeMapper.deleteBatchIds(ids);
        log.info("字典类型, 字典类型信息批量删除成功: userId={}, count={}, dictionaryTypeIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        dictionaryTypeMapper.delete(new QueryWrapper<DictionaryType>().eq("platform_id", platformId));
        log.info("字典类型, 字典类型信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据上级分类id删除
     *
     * @param parentId   上级分类id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByParentId(String parentId, OnlineUser onlineUser) {
        dictionaryTypeMapper.delete(new QueryWrapper<DictionaryType>().eq("parent_id", parentId));
        log.info("字典类型, 字典类型信息根据parentId删除成功: userId={}, parentId={}", onlineUser.getId(), parentId);
    }

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        dictionaryTypeMapper.delete(new QueryWrapper<DictionaryType>().eq("creator", creator));
        log.info("字典类型, 字典类型信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }

    /**
     * 根据平台id和code获取字典类型信息
     *
     * @param platformId 平台id
     * @param code       字典类型标识
     * @return DictionaryType 字典类型信息
     */
    public DictionaryType selectByCode(String platformId, String code) {
        return dictionaryTypeMapper.selectOne(new QueryWrapper<DictionaryType>().eq("platform_id", platformId).eq("code", code).orderByAsc("sort"));
    }
}
