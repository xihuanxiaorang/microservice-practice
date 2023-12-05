package fun.xiaorang.microservice.common.web.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.codec.DecodeException;
import fun.xiaorang.microservice.common.base.enums.ResultCode;
import fun.xiaorang.microservice.common.base.exception.BizException;
import fun.xiaorang.microservice.common.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.concurrent.CompletionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public <T> Result<T> handleException(BindException e) {
        log.error("参数校验异常信息，异常堆栈信息：{}", e.getMessage(), e);
        String msg = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return Result.fail(ResultCode.PARAM_ERROR, msg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public <T> Result<T> handleException(ConstraintViolationException e) {
        log.error("参数校验异常信息，异常堆栈信息：{}", e.getMessage(), e);
        String msg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return Result.fail(ResultCode.PARAM_ERROR, msg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> Result<T> handleException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.fail(ResultCode.PARAM_IS_NULL);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> Result<T> handleException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型不匹配异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return Result.fail(ResultCode.PARAM_ERROR, "类型错误");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> Result<T> handleException(NoHandlerFoundException e) {
        log.error("404异常信息，异常堆栈信息：{}", e.getMessage(), e);
        return Result.fail(ResultCode.RESOURCE_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletException.class)
    public <T> Result<T> handleException(ServletException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> Result<T> handleException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public <T> Result<T> handleException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}", e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> Result<T> handleException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        String errorMessage = "请求体不可为空";
        Throwable cause = e.getCause();
        if (cause != null) {
            errorMessage = convertMessage(cause);
        }
        return Result.fail(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public <T> Result<T> handleException(TypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public <T> Result<T> handleException(SQLSyntaxErrorException e) {
        String errorMsg = e.getMessage();
        log.error(errorMsg);
        if (StrUtil.isNotBlank(errorMsg) && errorMsg.contains("denied to user")) {
            return Result.fail(ResultCode.FORBIDDEN_OPERATION);
        } else {
            return Result.fail(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CompletionException.class)
    public <T> Result<T> handleException(CompletionException e) {
        if (e.getMessage().startsWith("feign.FeignException")) {
            return Result.fail("微服务调用异常");
        }
        return processException(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DecodeException.class)
    public <T> Result<T> handleException(DecodeException e) {
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BizException.class)
    public <T> Result<T> handleException(BizException e) {
        log.error("biz exception:{}", e.getMessage(), e);
        if (e.getCode() != null) {
            return Result.fail(e.getCode());
        }
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public <T> Result<T> processException(Exception e) {
        String errorMsg = e.getMessage();
        if (StrUtil.isNotBlank(errorMsg) && errorMsg.contains("denied to user")) {
            return Result.fail(ResultCode.FORBIDDEN_OPERATION);
        } else {
            log.error("unknown exception:{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 传参类型错误时，用于消息转换
     *
     * @param throwable 异常
     * @return 错误信息
     */
    private String convertMessage(Throwable throwable) {
        String error = throwable.toString();
        String regulation = "\\[\"(.*?)\"]+";
        Pattern pattern = Pattern.compile(regulation);
        Matcher matcher = pattern.matcher(error);
        String group = "";
        if (matcher.find()) {
            String matchString = matcher.group();
            matchString = matchString.replace("[", "").replace("]", "");
            matchString = matchString.replaceAll("\\\"", "") + "字段类型错误";
            group += matchString;
        }
        return group;
    }
}
