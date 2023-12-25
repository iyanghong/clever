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
 * @Date 2023-12-25 17:40:26
 */
@RestController
@Validated
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
     * 根据open_id获取列表
     *
     * @param openId open_id
     * @return List<ThirdAccount> 第三方平台账号列表
     */
    @GetMapping("/listByOpenId/{openId}")
    @Auth(value = "clever-system.thirdAccount.listByOpenId", name = "根据open_id获取第三方平台账号列表", description = "根据open_id获取第三方平台账号列表接口")
    public List<ThirdAccount> selectListByOpenId(@PathVariable("openId") String openId) {
        return thirdAccountService.selectListByOpenId(openId);
    }

    /**
     * 根据用户id获取列表
     *
     * @param userId 用户id
     * @return List<ThirdAccount> 第三方平台账号列表
     */
    @GetMapping("/listByUserId/{userId}")
    @Auth(value = "clever-system.thirdAccount.listByUserId", name = "根据用户id获取第三方平台账号列表", description = "根据用户id获取第三方平台账号列表接口")
    public List<ThirdAccount> selectListByUserId(@PathVariable("userId") String userId) {
        return thirdAccountService.selectListByUserId(userId);
    }

    /**
     * 根据id获取第三方平台账号信息
     *
     * @param id id
     * @return 第三方平台账号信息
     */
    @GetMapping("/{id}")
    @Auth(value = "clever-system.platform.selectById", name = "根据id获取第三方平台账号信息", description = "根据id获取第三方平台账号信息接口")
    public Result<ThirdAccount> selectById(@PathVariable("id") String id) {
        return new Result<>(thirdAccountService.selectById(id), "查询成功");
    }

    /**
     * 创建第三方平台账号信息
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @return 创建后的第三方平台账号信息
     */
    @PostMapping("")
    @Auth(value = "clever-system.thirdAccount.create", name = "创建第三方平台账号", description = "创建第三方平台账号信息接口")
    public Result<ThirdAccount> create(@Validated ThirdAccount thirdAccount) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(thirdAccountService.create(thirdAccount, onlineUser), "创建成功");
    }

    /**
     * 修改第三方平台账号信息
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @return 修改后的第三方平台账号信息
     */
    @PatchMapping("/{id}")
    @Auth(value = "clever-system.thirdAccount.update", name = "修改第三方平台账号", description = "修改第三方平台账号信息接口")
    public Result<ThirdAccount> update(@Validated ThirdAccount thirdAccount, @PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        thirdAccount.setId(id);
        return new Result<>(thirdAccountService.update(thirdAccount, onlineUser), "修改成功");
    }

    /**
     * 保存第三方平台账号信息
     *
     * @param thirdAccount 第三方平台账号实体信息
     * @return 保存后的第三方平台账号信息
     */
    @PostMapping("/save")
    @Auth(value = "clever-system.thirdAccount.save", name = "保存第三方平台账号", description = "保存第三方平台账号信息接口")
    public Result<ThirdAccount> save(@Validated ThirdAccount thirdAccount) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        return new Result<>(thirdAccountService.save(thirdAccount, onlineUser), "保存成功");
    }

    /**
     * 根据第三方平台账号id删除第三方平台账号信息
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @Auth(value = "clever-system.thirdAccount.delete", name = "删除第三方平台账号", description = "删除第三方平台账号信息接口")
    public Result<String> delete(@PathVariable("id") String id) {
        OnlineUser onlineUser = SpringUtil.getOnlineUser();
        thirdAccountService.delete(id, onlineUser);
        return Result.ofSuccess("删除成功");
    }
}
