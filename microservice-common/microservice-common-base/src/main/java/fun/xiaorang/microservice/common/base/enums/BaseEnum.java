package fun.xiaorang.microservice.common.base.enums;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 20:58
 */
public interface BaseEnum {
    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    Integer getCode();

    /**
     * 获取枚举名称
     *
     * @return 枚举名称
     */
    String getName();
}
