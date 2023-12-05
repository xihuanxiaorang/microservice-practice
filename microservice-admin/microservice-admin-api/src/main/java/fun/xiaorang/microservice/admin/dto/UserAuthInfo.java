package fun.xiaorang.microservice.admin.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">用户认证信息传输层对象<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 16:45
 */
@Data
public class UserAuthInfo {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 账号状态((0-正常, 1-禁用))
     *
     * @see fun.xiaorang.microservice.common.base.enums.StatusEnum
     */
    private Integer status;
    /**
     * 是否锁定（0-未锁定，1-已锁定）
     */
    private Integer locked;
    /**
     * 用户角色编码集合 ["ROOT","ADMIN"]
     */
    private Set<String> roles;
}
