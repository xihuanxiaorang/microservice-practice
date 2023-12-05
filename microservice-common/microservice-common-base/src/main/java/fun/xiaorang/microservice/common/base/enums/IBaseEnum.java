package fun.xiaorang.microservice.common.base.enums;

import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 20:58
 */
public interface IBaseEnum<T> {
    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    T getValue();

    /**
     * 获取枚举名称
     *
     * @return 枚举名称
     */
    String getLabel();

    /**
     * 根据枚举值获取枚举对象
     *
     * @param value 枚举值
     * @param clazz 枚举类
     * @param <E>   枚举
     * @return 枚举对象
     */
    static <E extends Enum<E> & IBaseEnum> E getEnumByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        return EnumSet.allOf(clazz).stream().filter(e -> ObjectUtil.equal(e.getValue(), value)).findFirst().orElse(null);
    }

    /**
     * 根据文本标签获取枚举对象
     *
     * @param label 枚举值
     * @param clazz 枚举类
     * @param <E>   枚举
     * @return 枚举对象
     */
    static <E extends Enum<E> & IBaseEnum> E getEnumByLabel(String label, Class<E> clazz) {
        Objects.requireNonNull(label);
        return EnumSet.allOf(clazz).stream().filter(e -> ObjectUtil.equal(e.getLabel(), label)).findFirst().orElse(null);
    }

    /**
     * 根据枚举值获取文本标签
     *
     * @param value 枚举值
     * @param clazz 枚举类
     * @param <E>   枚举
     * @return 文本标签
     */
    static <E extends Enum<E> & IBaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
        if (value == null) {
            return null;
        }
        EnumSet<E> allEnums = EnumSet.allOf(clazz); // 获取类型下的所有枚举
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);
        String label = null;
        if (matchEnum != null) {
            label = matchEnum.getLabel();
        }
        return label;
    }

    /**
     * 根据文本标签获取枚举值
     *
     * @param label 文本标签
     * @param clazz 枚举类
     * @param <E>   枚举
     * @return 枚举值
     */
    static <E extends Enum<E> & IBaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
        Objects.requireNonNull(label);
        EnumSet<E> allEnums = EnumSet.allOf(clazz); // 获取类型下的所有枚举
        E matchEnum = allEnums.stream()
                .filter(e -> ObjectUtil.equal(e.getLabel(), label))
                .findFirst()
                .orElse(null);
        Object value = null;
        if (matchEnum != null) {
            value = matchEnum.getValue();
        }
        return value;
    }
}
