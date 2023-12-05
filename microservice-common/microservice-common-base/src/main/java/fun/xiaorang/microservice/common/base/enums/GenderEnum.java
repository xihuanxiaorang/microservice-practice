package fun.xiaorang.microservice.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 21:32
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements IBaseEnum<Integer> {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer value;
    private final String label;
}
