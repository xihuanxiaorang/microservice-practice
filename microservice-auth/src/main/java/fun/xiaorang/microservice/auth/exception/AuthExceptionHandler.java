package fun.xiaorang.microservice.auth.exception;

import fun.xiaorang.microservice.common.base.enums.ResultCode;
import fun.xiaorang.microservice.common.base.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/01 22:17
 */
@RestControllerAdvice
public class AuthExceptionHandler {
    /**
     * 用户不存在
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public <T> Result<T> handleException(UsernameNotFoundException e) {
        return Result.fail(ResultCode.USER_NOT_EXIST);
    }

    /**
     * 用户名和密码异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGrantException.class)
    public <T> Result<T> handleException(InvalidGrantException e) {
        return Result.fail(ResultCode.USERNAME_OR_PASSWORD_ERROR);
    }

    /**
     * 客户端认证失败
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidClientException.class)
    public <T> Result<T> handleException(InvalidClientException e) {
        return Result.fail(ResultCode.CLIENT_AUTHENTICATION_FAILED);
    }

    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public <T> Result<T> handleException(InternalAuthenticationServiceException e) {
        return Result.fail(e.getMessage());
    }

    /**
     * token 无效或已过期
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenException.class})
    public <T> Result<T> handleException(InvalidTokenException e) {
        return Result.fail(e.getMessage());
    }
}
