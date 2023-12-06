package fun.xiaorang.microservice.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import fun.xiaorang.microservice.common.base.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 12:07
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements IBaseEnum<Integer> {
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    LINK(3, "外链"),
    BUTTON(4, "按钮");

    @EnumValue
    private final Integer value;
    private final String label;
}
