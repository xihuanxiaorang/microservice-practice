package fun.xiaorang.microservice.admin.controller;

import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.pojo.request.SysUserCreateRequest;
import fun.xiaorang.microservice.admin.pojo.vo.SysUserVO;
import fun.xiaorang.microservice.admin.service.SysUserService;
import fun.xiaorang.microservice.common.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">用户控制器<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 20:04
 */
@Api(tags = "用户管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class SysUserController {
    private final SysUserService sysUserService;

    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    @ApiOperation(value = "根据用户名获取认证信息", hidden = true)
    @GetMapping("/{username}/authInfo")
    public Result<UserAuthInfo> getUserAuthInfo(@ApiParam("用户名") @PathVariable String username) {
        return Result.success(sysUserService.getUserAuthInfo(username));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping
    public Result<Void> saveSysUser(@Valid @RequestBody SysUserCreateRequest sysUserCreateRequest) {
        final boolean result = sysUserService.save(sysUserCreateRequest);
        return Result.judge(result);
    }

    @ApiOperation(value = "获取用户详情")
    @GetMapping("/{userId}")
    public Result<SysUserVO> getSysUserDetails(@NotNull(message = "用户ID不能为空") @PathVariable Long userId) {
        return Result.success(sysUserService.getSysUserDetails(userId));
    }
}
