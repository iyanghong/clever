package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.DictionaryType;

/**
 * 字典类型服务接口
 *
 * @Author xixi
 * @Date 2023-12-27 11:15:13
 */
public interface DictionaryTypeService {

    /**
     * 分页查询列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param parentId   上级分类id
     * @param name       字典类型名称
     * @param code       字典类型标识
     * @return Page<DictionaryType>
     */
    Page<DictionaryType> selectPage(Integer pageNumber, Integer pageSize, String platformId, String parentId, String name, String code);

    /**
     * 根据字典类型id获取字典类型
     *
     * @param id 字典类型id
     * @return DictionaryType 字典类型id信息
     */
    DictionaryType selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<DictionaryType> 字典类型列表
     */
    List<DictionaryType> selectListByPlatformId(String platformId);

    /**
     * 根据上级分类id获取列表
     *
     * @param parentId 上级分类id
     * @return List<DictionaryType> 字典类型列表
     */
    List<DictionaryType> selectListByParentId(String parentId);

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<DictionaryType> 字典类型列表
     */
    List<DictionaryType> selectListByCreator(String creator);

    /**
     * 新建字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 新建后的字典类型信息
     */
    DictionaryType create(DictionaryType dictionaryType, OnlineUser onlineUser);

    /**
     * 修改字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 修改后的字典类型信息
     */
    DictionaryType update(DictionaryType dictionaryType, OnlineUser onlineUser);

    /**
     * 保存字典类型
     *
     * @param dictionaryType 字典类型实体信息
     * @param onlineUser     当前登录用户
     * @return DictionaryType 保存后的字典类型信息
     */
    DictionaryType save(DictionaryType dictionaryType, OnlineUser onlineUser);

    /**
     * 根据字典类型id删除信息
     *
     * @param id         字典类型id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据字典类型id列表删除信息
     *
     * @param ids        字典类型id列表
     * @param onlineUser 当前登录用户
     */
    void deleteBatchIds(List<String> ids, OnlineUser onlineUser);

    /**
     * 根据平台id删除
     *
     * @param platformId 平台id
     * @param onlineUser 当前登录用户
     */
    void deleteByPlatformId(String platformId, OnlineUser onlineUser);

    /**
     * 根据上级分类id删除
     *
     * @param parentId   上级分类id
     * @param onlineUser 当前登录用户
     */
    void deleteByParentId(String parentId, OnlineUser onlineUser);

    /**
     * 根据创建者id删除
     *
     * @param creator    创建者id
     * @param onlineUser 当前登录用户
     */
    void deleteByCreator(String creator, OnlineUser onlineUser);

    /**
     * 根据平台id和code获取字典类型信息
     *
     * @param platformId 平台id
     * @param code       字典类型标识
     * @return DictionaryType 字典类型信息
     */
    DictionaryType selectByCode(String platformId, String code);
}
