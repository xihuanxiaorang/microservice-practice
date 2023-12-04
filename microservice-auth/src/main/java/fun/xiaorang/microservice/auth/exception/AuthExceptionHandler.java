package fun.xiaorang.microservice.auth.exception;

import fun.xiaorang.microservice.common.base.enums.ResultCode;
import fun.xiaorang.microservice.common.base.model.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Result<Void>> handleException(UsernameNotFoundException e) {
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.USER_NOT_EXIST));
    }

    /**
     * 用户名和密码异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidGrantException.class)
    public ResponseEntity<Result<Void>> handleException(InvalidGrantException e) {
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.USERNAME_OR_PASSWORD_ERROR));
    }

    /**
     * 客户端认证失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidClientException.class)
    public ResponseEntity<Result<Void>> handleException(InvalidClientException e) {
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.CLIENT_AUTHENTICATION_FAILED));
    }

    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<Result<Void>> handleException(InternalAuthenticationServiceException e) {
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }

    /**
     * token 无效或已过期
     *
     * @param e
     * @return
     */
    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<Result<Void>> handleException(InvalidTokenException e) {
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }
}
