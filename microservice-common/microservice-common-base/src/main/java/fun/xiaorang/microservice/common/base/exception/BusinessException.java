package fun.xiaorang.microservice.common.base.exception;

import fun.xiaorang.microservice.common.base.enums.ResultCode;
import lombok.Getter;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 21:32
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;
    private final String msg;

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getName();
    }
}
