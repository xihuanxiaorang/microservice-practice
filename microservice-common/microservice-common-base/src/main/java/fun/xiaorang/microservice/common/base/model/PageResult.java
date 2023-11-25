package fun.xiaorang.microservice.common.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResult<T> {
    /**
     * 当前页码
     */
    private Long page;
    /**
     * 每页记录数
     */
    private Long pageSize;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> items;

    public static <T> PageResult<T> of(Long page, Long pageSize, Long total, List<T> items) {
        return new PageResult<>(page, pageSize, total, items);
    }
}
