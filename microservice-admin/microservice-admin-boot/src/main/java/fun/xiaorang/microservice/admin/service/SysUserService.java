package fun.xiaorang.microservice.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.pojo.entity.SysUser;
import fun.xiaorang.microservice.admin.pojo.request.UserCreateRequest;

import javax.validation.Valid;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 新增用户
     *
     * @param userCreateRequest 新增用户请求
     */
    void save(@Valid UserCreateRequest userCreateRequest);
}
