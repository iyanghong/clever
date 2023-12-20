package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.Province;
import com.clever.service.ProvinceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 省份接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/Province")
@AuthGroup(name = "省份模块", description = "省份模块权限组")
public class ProvinceController {

    @Resource
    private ProvinceService provinceService;


    /**
     * 分页查询省份列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param name       省份名称
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.province.page", name = "省份分页", description = "省份分页接口")
    public Result<Page<Province>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String name) {
        return new Result<>(provinceService.selectPage(pageNumber, pageSize, name), "分页数据查询成功");
    }

    /**
     * 根据省份编号获取省份信息
     *
     * @param id 省份编号
     * @return 省份信息
     */
    @GetMapping("/get/{id}")
    public Result<Province> selectById(@PathVariable("id") Integer id) {
        return new Result<>(provinceService.selectById(id), "查询成功");
    }

    /**
     * 保存省份信息
     *
     * @param province 省份实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.province.save", name = "保存省份", description = "保存省份信息接口")
    public Result<String> save(Province province) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        provinceService.save(province, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据省份编号获取省份列表
     *
     * @param id 省份编号
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.province.delete", name = "删除省份", description = "删除省份信息接口")
    public Result<String> delete(@PathVariable("id") Integer id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        provinceService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
