package com.clever.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clever.util.SpringUtil;
import com.clever.annotation.Auth;
import com.clever.annotation.AuthGroup;
import com.clever.bean.model.OnlineUser;
import com.clever.bean.model.Result;

import java.util.List;

import com.clever.bean.system.ThirdAccount;
import com.clever.service.ThirdAccountService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 第三方平台账号接口
 *
 * @Author xixi
 * @Date 2023-12-21 04:39:08
 */
@RestController
@RequestMapping("/thirdAccount")
@AuthGroup(name = "第三方平台账号模块", description = "第三方平台账号模块权限组")
public class ThirdAccountController {

    @Resource
    private ThirdAccountService thirdAccountService;


    /**
     * 分页查询第三方平台账号列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param type       第三方平台：qq|weixin|dingtalk|sina|tiktok
     * @param openId     open_id
     * @param nickname   第三方平台昵称
     * @param userId     用户id
     * @return 当前页数据
     */
    @GetMapping("/page/{pageNumber}/{pageSize}")
    @Auth(value = "clever-system.thirdAccount.page", name = "第三方平台账号分页", description = "第三方平台账号分页接口")
    public Result<Page<ThirdAccount>> selectPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, String type, String openId, String nickname, String userId) {
        return new Result<>(thirdAccountService.selectPage(pageNumber, pageSize, type, openId, nickname, userId), "分页数据查询成功");
    }

    /**
     * 根据id获取第三方平台账号信息
     *
     * @param id id
     * @return 第三方平台账号信息
     */
    @GetMapping("/get/{id}")
    public Result<ThirdAccount> selectById(@PathVariable("id") String id) {
        return new Result<>(thirdAccountService.selectById(id), "查询成功");
    }

    /**
     * 根据open_id获取第三方平台账号列表
     *
     * @param openId open_id
     * @return 第三方平台账号列表
     */
    @GetMapping("/getByOpenId/{openId}")
    @Auth(value = "clever-system.thirdAccount.getByOpenId", name = "根据open_id获取第三方平台账号列表", description = "第三方平台账号列表接口")
    public Result<List<ThirdAccount>> selectByOpenId(@PathVariable("openId") String openId) {
        return new Result<>(thirdAccountService.selectListByOpenId(openId), "查询成功");
    }

    /**
     * 根据用户id获取第三方平台账号列表
     *
     * @param userId 用户id
     * @return 第三方平台账号列表
     */
    @GetMapping("/getByUserId/{userId}")
    @Auth(value = "clever-system.thirdAccount.getByUserId", name = "根据user_id获取第三方平台账号列表", description = "第三方平台账号列表接口")
    public Result<List<ThirdAccount>> selectByUserId(@PathVariable("userId") String userId) {
        return new Result<>(thirdAccountService.selectListByUserId(userId), "查询成功");
    }

    /**
     * 保存第三方平台账号信息
     *
     * @param thirdAccount 第三方平台账号实体信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.thirdAccount.save", name = "保存第三方平台账号", description = "保存第三方平台账号信息接口")
    public Result<String> save(@Validated ThirdAccount thirdAccount) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        thirdAccountService.save(thirdAccount, onlineUser);
        return Result.ofSuccess("保存成功");
    }

    /**
     * 根据id获取第三方平台账号列表
     *
     * @param id id
     */
    @DeleteMapping("/delete/{id}")
    @Auth(value = "clever-system.thirdAccount.delete", name = "删除第三方平台账号", description = "删除第三方平台账号信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        thirdAccountService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }

}
