package com.clever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.system.DictionaryType;
import com.clever.bean.system.projo.DictionaryVo;
import com.clever.exception.BaseException;
import com.clever.exception.ConstantException;
import com.clever.service.DictionaryTypeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.clever.mapper.DictionaryMapper;
import com.clever.bean.system.Dictionary;
import com.clever.service.DictionaryService;

import javax.annotation.Resource;

/**
 * 字典服务
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final static Logger log = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 分页查询字典列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param typeId     字典类型id
     * @param parentId   上级字典id
     * @param name       字典名称
     * @param code       字典标识
     * @return Page<Dictionary>
     */
    @Override
    public Page<Dictionary> selectPage(Integer pageNumber, Integer pageSize, String platformId, String typeId, String parentId, String name, String code) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(platformId)) {
            queryWrapper.eq("platform_id", platformId);
        }
        if (StringUtils.isNotBlank(typeId)) {
            queryWrapper.eq("type_id", typeId);
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
        return dictionaryMapper.selectPage(new Page<Dictionary>(pageNumber, pageSize), queryWrapper);
    }

    /**
     * 根据字典id获取字典
     *
     * @param id 字典id
     * @return Dictionary 字典信息
     */
    @Override
    public Dictionary selectById(String id) {
        return dictionaryMapper.selectById(id);
    }

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Dictionary> 字典列表
     */
    @Override
    public List<Dictionary> selectListByPlatformId(String platformId) {
        return dictionaryMapper.selectList(new QueryWrapper<Dictionary>().eq("platform_id", platformId).orderByAsc("sort"));
    }

    /**
     * 根据字典类型id获取列表
     *
     * @param typeId 字典类型id
     * @return List<Dictionary> 字典列表
     */
    @Override
    public List<Dictionary> selectListByTypeId(String typeId) {
        return dictionaryMapper.selectList(new QueryWrapper<Dictionary>().eq("type_id", typeId).orderByAsc("sort"));
    }


    private List<DictionaryVo> getChildren(List<Dictionary> dictionaries, String parentId) {
        return dictionaries.stream().filter(dictionary -> dictionary.getParentId().equals(parentId)).map(dictionary -> {
            DictionaryVo dictionaryVo = new DictionaryVo();
            dictionaryVo.setDictionary(dictionary);
            dictionaryVo.setChildren(getChildren(dictionaries, dictionary.getId()));
            return dictionaryVo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据上级字典id获取列表
     *
     * @param parentId 上级字典id
     * @return List<Dictionary> 字典列表
     */
    @Override
    public List<Dictionary> selectListByParentId(String parentId) {
        return dictionaryMapper.selectList(new QueryWrapper<Dictionary>().eq("parent_id", parentId).orderByAsc("sort"));
    }

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Dictionary> 字典列表
     */
    @Override
    public List<Dictionary> selectListByCreator(String creator) {
        return dictionaryMapper.selectList(new QueryWrapper<Dictionary>().eq("creator", creator).orderByAsc("sort"));
    }

    /**
     * 新建字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 新建后的字典信息
     */
    @Override
    public Dictionary create(Dictionary dictionary, OnlineUser onlineUser) {
        if (dictionaryMapper.selectCount(new QueryWrapper<Dictionary>().eq("platform_id", dictionary.getPlatformId()).eq("code", dictionary.getCode())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("字典标识"));
        }
        dictionaryMapper.insert(dictionary);
        log.info("字典, 字典信息创建成功: userId={}, dictionaryId={}", onlineUser.getId(), dictionary.getId());
        return dictionary;
    }

    /**
     * 修改字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 修改后的字典信息
     */
    @Override
    public Dictionary update(Dictionary dictionary, OnlineUser onlineUser) {
        if (dictionaryMapper.selectCount(new QueryWrapper<Dictionary>().eq("platform_id", dictionary.getPlatformId()).eq("code", dictionary.getCode()).ne("id", dictionary.getId())) > 0) {
            throw new BaseException(ConstantException.DATA_IS_EXIST.format("字典标识"));
        }
        dictionaryMapper.updateById(dictionary);
        log.info("字典, 字典信息修改成功: userId={}, dictionaryId={}", onlineUser.getId(), dictionary.getId());
        return dictionary;
    }

    /**
     * 保存字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 保存后的字典信息
     */
    @Override
    public Dictionary save(Dictionary dictionary, OnlineUser onlineUser) {
        if (StringUtils.isNotBlank(dictionary.getId())) {
            return create(dictionary, onlineUser);
        }
        return update(dictionary, onlineUser);
    }

    /**
     * 根据字典id删除字典信息
     *
     * @param id         字典id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void delete(String id, OnlineUser onlineUser) {
        dictionaryMapper.deleteById(id);
        log.info("字典, 字典信息删除成功: userId={}, dictionaryId={}", onlineUser.getId(), id);
    }

    /**
     * 根据字典id列表删除字典信息
     *
     * @param ids        字典id列表
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteBatchIds(List<String> ids, OnlineUser onlineUser) {
        dictionaryMapper.deleteBatchIds(ids);
        log.info("字典, 字典信息批量删除成功: userId={}, count={}, dictionaryIds={}", onlineUser.getId(), ids.size(), ids.toString());
    }

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByPlatformId(String platformId, OnlineUser onlineUser) {
        dictionaryMapper.delete(new QueryWrapper<Dictionary>().eq("platform_id", platformId));
        log.info("字典, 字典信息根据platformId删除成功: userId={}, platformId={}", onlineUser.getId(), platformId);
    }

    /**
     * 根据字典类型id删除
     *
     * @param typeId     字典类型id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByTypeId(String typeId, OnlineUser onlineUser) {
        dictionaryMapper.delete(new QueryWrapper<Dictionary>().eq("type_id", typeId));
        log.info("字典, 字典信息根据typeId删除成功: userId={}, typeId={}", onlineUser.getId(), typeId);
    }

    /**
     * 根据上级字典id删除
     *
     * @param parentId   上级字典id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByParentId(String parentId, OnlineUser onlineUser) {
        dictionaryMapper.delete(new QueryWrapper<Dictionary>().eq("parent_id", parentId));
        log.info("字典, 字典信息根据parentId删除成功: userId={}, parentId={}", onlineUser.getId(), parentId);
    }

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    @Override
    public void deleteByCreator(String creator, OnlineUser onlineUser) {
        dictionaryMapper.delete(new QueryWrapper<Dictionary>().eq("creator", creator));
        log.info("字典, 字典信息根据creator删除成功: userId={}, creator={}", onlineUser.getId(), creator);
    }


    /**
     * 获取树型列表
     *
     * @param typeId 字典类型id
     * @return List<DictionaryVo> 字典列表
     */
    public List<DictionaryVo> selectTreeDataByTypeId(String typeId) {
        List<Dictionary> dictionaries = dictionaryMapper.selectList(new QueryWrapper<Dictionary>().eq("type_id", typeId).orderByAsc("sort"));
        return getChildren(dictionaries, "-1");
    }

    /**
     * 获取树型列表
     *
     * @param platformId 平台id
     * @param typeCode   字典类型编码
     * @return List<DictionaryVo> 字典列表
     */
    public List<DictionaryVo> selectTreeDataByTypeCode(String platformId, String typeCode) {
        DictionaryType dictionaryType = dictionaryTypeService.selectByCode(platformId, typeCode);
        if (dictionaryType == null) {
            throw new BaseException(ConstantException.DATA_NOT_EXIST.format("字典类型不存在"));
        }
        return selectTreeDataByTypeId(dictionaryType.getId());
    }

    /**
     * 根据平台id和字典类型编码获取列表
     *
     * @param platformId 平台id
     * @param typeCode   字典类型编码
     * @return List<Dictionary> 字典列表
     */
    public List<Dictionary> selectByTypeCode(String platformId, String typeCode) {
        DictionaryType dictionaryType = dictionaryTypeService.selectByCode(platformId, typeCode);
        if (dictionaryType == null) {
            throw new BaseException(ConstantException.DATA_NOT_EXIST.format("字典类型不存在"));
        }
        return selectListByTypeId(dictionaryType.getId());
    }
}
