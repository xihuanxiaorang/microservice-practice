package fun.xiaorang.microservice.common.base.constants;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 17:19
 */
public interface GlobalConstant {
    /**
     * [ {接口路径:[角色编码]},...]
     */
    String URL_PERM_ROLES_KEY = "permission:url";

    /**
     * [{按钮权限标识:[角色编码]},...]
     */
    String BTN_PERM_ROLES_KEY = "permission:btn";
    /**
     * 用户默认密码
     */
    String USER_DEFAULT_PASSWORD = "123456";
}
