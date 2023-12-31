package com.clever.bean.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;


/**
 * 省份
 *
 * @Author xixi
 * @Date 2023-12-26 11:13:55
 */
public class Province implements Serializable {

    /**
     * 省份编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 省份名称
     */
    @NotBlank(message = "省份名称不能为空")
    private String name;


    /**
     * 省份编号
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 省份名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}