package fun.xiaorang.microservice.common.web.validation;

import javax.validation.groups.Default;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 22:01
 */
public class ValidationGroups {
    public interface Insert extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }
}
