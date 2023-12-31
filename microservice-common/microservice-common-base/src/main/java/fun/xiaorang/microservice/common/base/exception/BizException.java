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
public class BizException extends RuntimeException {
    private final String code;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(String msg) {
        super(msg);
        this.code = ResultCode.SYSTEM_EXECUTION_ERROR.getCode();
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}
