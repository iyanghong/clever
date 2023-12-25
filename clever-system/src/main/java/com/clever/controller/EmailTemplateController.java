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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 邮箱模板接口
 *
 * @Author xixi
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
@RequestMapping("/emailTemplate")
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
     * 根据平台id获取列表
     *
     * @param platformId 平台id
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @GetMapping("/listByPlatformId/{platformId}")
    @Auth(value = "clever-system.emailTemplate.listByPlatformId", name = "根据平台id获取邮箱模板列表", description = "根据平台id获取邮箱模板列表接口")
    public List<EmailTemplate> selectListByPlatformId(@PathVariable("platformId") Integer platformId) {
        return emailTemplateService.selectListByPlatformId(platformId);
    }

    /**
     * 根据邮箱主体获取列表
     *
     * @param subjectId 邮箱主体
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @GetMapping("/listBySubjectId/{subjectId}")
    @Auth(value = "clever-system.emailTemplate.listBySubjectId", name = "根据邮箱主体获取邮箱模板列表", description = "根据邮箱主体获取邮箱模板列表接口")
    public List<EmailTemplate> selectListBySubjectId(@PathVariable("subjectId") String subjectId) {
        return emailTemplateService.selectListBySubjectId(subjectId);
    }

    /**
     * 根据模板创建者获取列表
     *
     * @param creator 模板创建者
     * @return List<EmailTemplate> 邮箱模板列表
     */
    @GetMapping("/listByCreator/{creator}")
    @Auth(value = "clever-system.emailTemplate.listByCreator", name = "根据模板创建者获取邮箱模板列表", description = "根据模板创建者获取邮箱模板列表接口")
    public List<EmailTemplate> selectListByCreator(@PathVariable("creator") String creator) {
        return emailTemplateService.selectListByCreator(creator);
    }

    /**
     * 根据id获取邮箱模板信息
     *
     * @param id id
     * @return 邮箱模板信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据id获取邮箱模板信息", description = "根据id获取邮箱模板信息接口")
    public Result<EmailTemplate> selectById(@PathVariable("id") String id) {
        return new Result<>(emailTemplateService.selectById(id), "查询成功");
    }

    /**
     * 创建邮箱模板信息
     *
     * @param emailTemplate 邮箱模板实体信息
     * @return 创建后的邮箱模板信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.emailTemplate.create", name = "创建邮箱模板", description = "创建邮箱模板信息接口")
    public Result<EmailTemplate> create(@Validated EmailTemplate emailTemplate) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(emailTemplateService.create(emailTemplate, onlineUser), "创建成功");
    }

    /**
     * 修改邮箱模板信息
     *
     * @param emailTemplate 邮箱模板实体信息
     * @return 修改后的邮箱模板信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.emailTemplate.update", name = "修改邮箱模板", description = "修改邮箱模板信息接口")
    public Result<EmailTemplate> update(@Validated EmailTemplate emailTemplate, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailTemplate.setId(id);
        return new Result<>(emailTemplateService.update(emailTemplate, onlineUser), "修改成功");
    }

    /**
     * 保存邮箱模板信息
     *
     * @param emailTemplate 邮箱模板实体信息
     * @return 保存后的邮箱模板信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.emailTemplate.save", name = "保存邮箱模板", description = "保存邮箱模板信息接口")
    public Result<EmailTemplate> save(@Validated EmailTemplate emailTemplate) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(emailTemplateService.save(emailTemplate, onlineUser), "保存成功");
    }

    /**
     * 根据邮箱模板id删除邮箱模板信息
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.emailTemplate.delete", name = "删除邮箱模板", description = "删除邮箱模板信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        emailTemplateService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
