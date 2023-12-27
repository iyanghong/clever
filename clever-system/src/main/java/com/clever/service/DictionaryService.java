package com.clever.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.bean.model.OnlineUser;

import java.util.List;

import com.clever.bean.system.Dictionary;
import com.clever.bean.system.projo.DictionaryVo;

/**
 * 字典服务接口
 *
 * @Author xixi
 * @Date 2023-12-27 09:17:16
 */
public interface DictionaryService {

    /**
     * 分页查询列表
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
    Page<Dictionary> selectPage(Integer pageNumber, Integer pageSize, String platformId, String typeId, String parentId, String name, String code);

    /**
     * 根据字典id获取字典
     *
     * @param id 字典id
     * @return Dictionary 字典id信息
     */
    Dictionary selectById(String id);

    /**
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<Dictionary> 字典列表
     */
    List<Dictionary> selectListByPlatformId(String platformId);

    /**
     * 根据字典类型id获取列表
     *
     * @param typeId 字典类型id
     * @return List<Dictionary> 字典列表
     */
    List<Dictionary> selectListByTypeId(String typeId);

    /**
     * 根据上级字典id获取列表
     *
     * @param parentId 上级字典id
     * @return List<Dictionary> 字典列表
     */
    List<Dictionary> selectListByParentId(String parentId);

    /**
     * 根据创建者id获取列表
     *
     * @param creator 创建者id
     * @return List<Dictionary> 字典列表
     */
    List<Dictionary> selectListByCreator(String creator);

    /**
     * 新建字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 新建后的字典信息
     */
    Dictionary create(Dictionary dictionary, OnlineUser onlineUser);

    /**
     * 修改字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 修改后的字典信息
     */
    Dictionary update(Dictionary dictionary, OnlineUser onlineUser);

    /**
     * 保存字典
     *
     * @param dictionary 字典实体信息
     * @param onlineUser 当前登录用户
     * @return Dictionary 保存后的字典信息
     */
    Dictionary save(Dictionary dictionary, OnlineUser onlineUser);

    /**
     * 根据字典id删除信息
     *
     * @param id         字典id
     * @param onlineUser 当前登录用户
     */
    void delete(String id, OnlineUser onlineUser);

    /**
     * 根据字典id列表删除信息
     *
     * @param ids        字典id列表
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
     * 根据字典类型id删除
     *
     * @param typeId     字典类型id
     * @param onlineUser 当前登录用户
     */
    void deleteByTypeId(String typeId, OnlineUser onlineUser);

    /**
     * 根据上级字典id删除
     *
     * @param parentId   上级字典id
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
     * 获取树型列表
     *
     * @param typeId 字典类型id
     * @return List<DictionaryVo> 字典列表
     */
    List<DictionaryVo> selectTreeDataByTypeId(String typeId);

    /**
     * 获取树型列表
     *
     * @param platformId 平台id
     * @param typeCode   字典类型编码
     * @return List<DictionaryVo> 字典列表
     */
    List<DictionaryVo> selectTreeDataByTypeCode(String platformId, String typeCode);

    /**
     * 根据平台id和字典类型编码获取列表
     *
     * @param platformId 平台id
     * @param typeCode   字典类型编码
     * @return List<Dictionary> 字典列表
     */
    List<Dictionary> selectByTypeCode(String platformId, String typeCode);

}
