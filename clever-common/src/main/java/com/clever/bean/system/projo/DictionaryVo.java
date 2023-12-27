package com.clever.bean.system.projo;

import com.clever.bean.system.Dictionary;

import java.util.List;

/**
 * @Author xixi
 * @Date 2023-12-27 11:17
 **/
public class DictionaryVo extends Dictionary {
    /**
     * 子节点
     */
    private List<DictionaryVo> children;

    /**
     * 获取子节点
     *
     * @return List<DictionaryVo>
     */
    public List<DictionaryVo> getChildren() {
        return children;
    }

    public void setChildren(List<DictionaryVo> children) {
        this.children = children;
    }

    public void setDictionary(Dictionary dictionary) {
        this.setId(dictionary.getId());
        this.setPlatformId(dictionary.getPlatformId());
        this.setTypeId(dictionary.getTypeId());
        this.setParentId(dictionary.getParentId());
        this.setName(dictionary.getName());
        this.setCode(dictionary.getCode());
        this.setValue(dictionary.getValue());
        this.setDescription(dictionary.getDescription());
        this.setSort(dictionary.getSort());
        this.setCreator(dictionary.getCreator());
        this.setCreatedAt(dictionary.getCreatedAt());
        this.setUpdatedAt(dictionary.getUpdatedAt());
    }
}
