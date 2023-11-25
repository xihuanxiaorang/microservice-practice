package fun.xiaorang.microservice.common.base.utils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:20
 */
public class TreeUtil {
    public static <T, R> List<T> toTree(
            List<T> list, Function<T, R> idGetter, Function<T, R> parentIdGetter, R pidValue,
            BiConsumer<T, List<T>> childrenSetter) {
        return toTree(list, idGetter, parentIdGetter, pidValue, childrenSetter, null);
    }

    public static <T, R> List<T> toTree(
            List<T> list, Function<T, R> idGetter, Function<T, R> parentIdGetter, R pidValue,
            BiConsumer<T, List<T>> childrenSetter, Comparator<T> comparator) {
        Map<R, List<T>> pIdValuesMap = list.stream().collect(Collectors.groupingBy(parentIdGetter));
        List<T> parents = pIdValuesMap.getOrDefault(pidValue, new ArrayList<>());
        return getTreeSet(pIdValuesMap, parents, idGetter, childrenSetter, comparator);
    }

    private static <T, R> List<T> getTreeSet(
            Map<R, List<T>> pIdValuesMap, List<T> parents, Function<T, R> idGetter,
            BiConsumer<T, List<T>> childrenSetter,
            Comparator<T> comparator) {
        if (Optional.ofNullable(comparator).isPresent()) {
            parents.sort(comparator);
        }
        for (T parent : parents) {
            getChildrenFromMapByPidAndSet(pIdValuesMap, parent, idGetter, childrenSetter, comparator);
        }
        return parents;
    }

    private static <T, R> void getChildrenFromMapByPidAndSet(
            Map<R, List<T>> pIdValuesMap, T parent, Function<T, R> idGetter,
            BiConsumer<T, List<T>> childrenSetter, Comparator<T> comparator) {
        List<T> children = pIdValuesMap.get(idGetter.apply(parent));
        if (Optional.ofNullable(children).isEmpty()) {
            return;
        }
        if (Optional.ofNullable(comparator).isPresent()) {
            children.sort(comparator);
        }
        childrenSetter.accept(parent, children);
        for (T child : children) {
            getChildrenFromMapByPidAndSet(pIdValuesMap, child, idGetter, childrenSetter, comparator);
        }
    }
}
