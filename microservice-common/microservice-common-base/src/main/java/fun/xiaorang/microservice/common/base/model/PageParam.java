package fun.xiaorang.microservice.common.base.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:40
 */
@Data
public class PageParam implements Serializable {
    private static final Integer DEFAULT_PAGE_NO = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 {value}")
    private Integer pageNo = DEFAULT_PAGE_NO;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 {value}")
    @Max(value = 100, message = "每页条数最大值为 {value}")
    private Integer pageSize = DEFAULT_PAGE_SIZE;
}
