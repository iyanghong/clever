package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.EmailSubject;
import com.clever.service.EmailSubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 邮箱主体接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/EmailSubject")
@AuthGroup(name = "邮箱主体模块", description = "邮箱主体模块权限组")
public class EmailSubjectController {

    @Resource
    private EmailSubjectService emailSubjectService;


    /**
     * 分页查询邮箱主体列表
     *
     * @param pageNumber  页码
     * @param pageSize    每页记录数
     * @param platformId  平台id
     * @param host        host
     * @param username    账号
     * @param subjectName 发件人
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.emailSubject.page", name = "邮箱主体分页", description = "邮箱主体分页接口")
    public Result<Page<EmailSubject>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String host, String username, String subjectName) {
        return new Result<>(emailSubjectService.selectPage(pageNumber, pageSize, platformId, host, username, subjectName), "分页数据查询成功");
    }

    /**
     * 根据id获取邮箱主体信息
     *
     * @param id id
     * @return 邮箱主体信息
     */
    @GetMapping("/get/{id}")
    public Result<EmailSubject> selectById(@PathVariable("id") String id) {
        return new Result<>(emailSubjectService.selectById(id), "查询成功");
    }

    /**
     * 根据平台id获取邮箱主体列表
     *
     * @param platformId 平台id
     * @return 邮箱主体列表
     */
    @GetMapping("/getByPlatformId/{platformId}")
    @Auth(value = "clever-system.emailSubject.getByPlatformId", name = "根据platform_id获取邮箱主体列表", description = "邮箱主体列表接口")
    public Result<List<EmailSubject>> selectByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(emailSubjectService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 保存邮箱主体信息
     *
     * @param emailSubject 邮箱主体实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.emailSubject.save", name = "保存邮箱主体", description = "保存邮箱主体信息接口")
    public Result<String> save(EmailSubject emailSubject) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailSubjectService.save(emailSubject, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id获取邮箱主体列表
     *
     * @param id id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.emailSubject.delete", name = "删除邮箱主体", description = "删除邮箱主体信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailSubjectService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
