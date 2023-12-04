package fun.xiaorang.microservice.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:00
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements BaseEnum {
    SUCCESS("00000", "请求成功"),
    USER_NOT_EXIST("A0201", "用户不存在"),
    USERNAME_OR_PASSWORD_ERROR("A0210", "用户名或密码错误"),
    CLIENT_AUTHENTICATION_FAILED("A0212", "客户端认证失败"),
    PARAM_ERROR("A0400", "用户请求参数错误"),
    RESOURCE_NOT_FOUND("A0401", "请求资源不存在"),
    PARAM_IS_NULL("A0410", "请求必填参数为空"),
    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错"),
    ;

    private final String code;
    private final String name;
}
