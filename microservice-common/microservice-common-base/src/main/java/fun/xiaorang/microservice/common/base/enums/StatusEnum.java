package fun.xiaorang.microservice.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">状态枚举<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 21:29
 */
@Getter
@AllArgsConstructor
public enum StatusEnum implements IBaseEnum<Integer> {
    ENABLE(0, "正常"),
    DISABLE(1, "禁用");

    private final Integer value;
    private final String label;
}
