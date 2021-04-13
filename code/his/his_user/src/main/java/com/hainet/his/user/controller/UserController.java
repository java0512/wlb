package com.hainet.his.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hainet.common.response.Result;
import com.hainet.his.user.service.UserInfoService;
import com.hainet.model.user.UserInfo;
import com.hainet.model.user.vo.UserInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Api(description = "用户接口")
@RequestMapping("admin/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "//用户列表（条件查询带分页）")
    @GetMapping("/{page}/{limit")
    public Result list(@PathVariable(value = "page") Integer page,
                       @PathVariable(value = "limit") Integer limit,
                       UserInfoQueryVo userInfoQueryVo) {
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel =  userInfoService.selectPage(pageParam, userInfoQueryVo);
        return Result.ok(pageModel);

    }

    @ApiOperation(value = "用户锁定")
    @GetMapping("lock/{userId}/{status}")
    public Result lock(@PathVariable Long userId,
                       @PathVariable Integer status) {
        userInfoService.lock(userId, status);
        return Result.ok();
    }


    @ApiOperation(value = "用户详情")
    @GetMapping("show/{userId}")
    public Result show(@PathVariable Long userId) {
        Map<String, Object> map = userInfoService.show(userId);
        return Result.ok(map);
    }

    @ApiOperation(value = "认证审批")
    @GetMapping("approval/{userId}/{authStatus}")
    public Result approval(@PathVariable Long userId,
                           @PathVariable Integer authStatus) {
        userInfoService.approval(userId, authStatus);
        return Result.ok();
    }
}
