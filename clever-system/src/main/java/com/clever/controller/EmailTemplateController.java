package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.EmailTemplate;
import com.clever.service.EmailTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 邮箱模板接口
 *
 * @Author xixi
 * @Date 2023-12-20 05:02:03
 */
@RestController
@RequestMapping("/EmailTemplate")
@AuthGroup(name = "邮箱模板模块", description = "邮箱模板模块权限组")
public class EmailTemplateController {

    @Resource
    private EmailTemplateService emailTemplateService;


    /**
     * 分页查询邮箱模板列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param platformId 平台id
     * @param name       邮件模板名称
     * @param code       模板key
     * @param subjectId  邮箱主体
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.emailTemplate.page", name = "邮箱模板分页", description = "邮箱模板分页接口")
    public Result<Page<EmailTemplate>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, Integer platformId, String name, String code, String subjectId) {
        return new Result<>(emailTemplateService.selectPage(pageNumber, pageSize, platformId, name, code, subjectId), "分页数据查询成功");
    }

    /**
     * 根据id获取邮箱模板信息
     *
     * @param id id
     * @return 邮箱模板信息
     */
    @GetMapping("/get/{id}")
    public Result<EmailTemplate> selectById(@PathVariable("id") String id) {
        return new Result<>(emailTemplateService.selectById(id), "查询成功");
    }

    /**
     * 根据平台id获取邮箱模板列表
     *
     * @param platformId 平台id
     * @return 邮箱模板列表
     */
    @GetMapping("/getByPlatformId/{platformId}")
    @Auth(value = "clever-system.emailTemplate.getByPlatformId", name = "根据platform_id获取邮箱模板列表", description = "邮箱模板列表接口")
    public Result<List<EmailTemplate>> selectByPlatformId(@PathVariable("platformId") Integer platformId) {
        return new Result<>(emailTemplateService.selectListByPlatformId(platformId), "查询成功");
    }

    /**
     * 根据邮箱主体获取邮箱模板列表
     *
     * @param subjectId 邮箱主体
     * @return 邮箱模板列表
     */
    @GetMapping("/getBySubjectId/{subjectId}")
    @Auth(value = "clever-system.emailTemplate.getBySubjectId", name = "根据subject_id获取邮箱模板列表", description = "邮箱模板列表接口")
    public Result<List<EmailTemplate>> selectBySubjectId(@PathVariable("subjectId") String subjectId) {
        return new Result<>(emailTemplateService.selectListBySubjectId(subjectId), "查询成功");
    }

    /**
     * 保存邮箱模板信息
     *
     * @param emailTemplate 邮箱模板实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.emailTemplate.save", name = "保存邮箱模板", description = "保存邮箱模板信息接口")
    public Result<String> save(EmailTemplate emailTemplate) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailTemplateService.save(emailTemplate, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id获取邮箱模板列表
     *
     * @param id id
     */
    @GetMapping("/delete/{id}")
    @Auth(value = "clever-system.emailTemplate.delete", name = "删除邮箱模板", description = "删除邮箱模板信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailTemplateService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
