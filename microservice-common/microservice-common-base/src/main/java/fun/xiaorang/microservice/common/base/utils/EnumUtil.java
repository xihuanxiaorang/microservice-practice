package fun.xiaorang.microservice.common.base.utils;


import fun.xiaorang.microservice.common.base.enums.BaseEnum;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:14
 */
public class EnumUtil {
    /**
     * 判断枚举值是否存在
     *
     * @param enums 枚举数组
     * @param code  枚举值
     * @return 是否存在，true：存在，false：不存在
     */
    public static boolean isExist(BaseEnum[] enums, Integer code) {
        if (code == null) {
            return false;
        }
        for (final BaseEnum e : enums) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举值获取枚举名称
     *
     * @param enums 枚举数组
     * @param code  枚举值
     * @return 枚举名称
     */
    public static String getNameByCode(BaseEnum[] enums, Integer code) {
        if (code == null) {
            return null;
        }
        for (final BaseEnum e : enums) {
            if (e.getCode().equals(code)) {
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取枚举值
     *
     * @param enums 枚举数组
     * @param name  枚举名称
     * @return 枚举值
     */
    public static Integer getCodeByName(BaseEnum[] enums, String name) {
        if (name == null) {
            return null;
        }
        for (final BaseEnum e : enums) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    /**
     * 根据枚举值获取枚举对象
     *
     * @param enums 枚举数组
     * @param code  枚举值
     * @return 枚举对象
     */
    public static BaseEnum getEnumByCode(BaseEnum[] enums, Integer code) {
        if (code == null) {
            return null;
        }
        for (final BaseEnum e : enums) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取枚举对象
     *
     * @param enums 枚举数组
     * @param name  枚举名称
     * @return 枚举对象
     */
    public static BaseEnum getEnumByName(BaseEnum[] enums, String name) {
        if (name == null) {
            return null;
        }
        for (final BaseEnum e : enums) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
