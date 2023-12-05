package fun.xiaorang.microservice.admin.controller;

import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">用户控制器<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 20:04
 */
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
    @GetMapping("/{username}/authInfo")
    public UserAuthInfo getUserAuthInfo(@PathVariable String username) {
        return sysUserService.getUserAuthInfo(username);
    }
}
