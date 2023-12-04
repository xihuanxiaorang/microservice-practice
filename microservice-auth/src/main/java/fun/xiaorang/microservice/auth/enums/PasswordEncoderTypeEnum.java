package fun.xiaorang.microservice.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/01 22:11
 */
@Getter
@AllArgsConstructor
public enum PasswordEncoderTypeEnum {
    BCRYPT("{bcrypt}", "BCRYPT加密"),
    NOOP("{noop}", "无加密明文");

    private final String prefix;
    private final String desc;
}
