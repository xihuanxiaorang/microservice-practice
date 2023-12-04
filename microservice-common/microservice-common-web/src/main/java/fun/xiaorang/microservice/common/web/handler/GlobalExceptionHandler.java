package fun.xiaorang.microservice.common.web.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import fun.xiaorang.microservice.common.base.enums.ResultCode;
import fun.xiaorang.microservice.common.base.exception.BizException;
import fun.xiaorang.microservice.common.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 22:21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result<Void>> handleException(BindException e) {
        log.error("参数校验异常信息，异常堆栈信息：{}", e.getMessage(), e);
        String msg = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.PARAM_ERROR.getCode(), msg));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Result<Void>> handleException(ConstraintViolationException e) {
        log.error("参数校验异常信息，异常堆栈信息：{}", e.getMessage(), e);
        String msg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.PARAM_ERROR.getCode(), msg));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result<Void>> processException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.PARAM_IS_NULL));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Result<Void>> handleException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型不匹配异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(ResultCode.PARAM_ERROR.getCode(), "类型错误"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Result<Void>> handleException(NoHandlerFoundException e) {
        log.error("404异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Result.fail(ResultCode.RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Result<Void>> handleException(ServletException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result<Void>> handleException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Result<Void>> handleException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Result<Void>> handleException(TypeMismatchException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(Result.fail(e.getMessage()));
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Result<Void>> handleException(BizException e) {
        log.error("业务异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.fail(e.getCode(), e.getMsg()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleException(Exception e) {
        log.error("全局异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.fail(e.getMessage()));
    }
}
