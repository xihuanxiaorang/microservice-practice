package fun.xiaorang.microservice.common.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.xiaorang.microservice.common.base.model.Result;
import fun.xiaorang.microservice.common.web.annotations.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 22:24
 */
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        final Method method = returnType.getMethod();
        final Class<?> clazz = returnType.getDeclaringClass();
        // 如果方法上有 ResponseResult 注解并且 ignore = true，则不进行统一包装
        if (method != null && method.isAnnotationPresent(ResponseResult.class) && method.getAnnotation(ResponseResult.class).ignore()) {
            return false;
        }
        // 如果类上有 ResponseResult 注解并且 ignore = true，则不进行统一包装
        return !clazz.isAnnotationPresent(ResponseResult.class) || !clazz.getAnnotation(ResponseResult.class).ignore();
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body == null) {
            return Result.success();
        }
        if (body instanceof Result) {
            return body;
        }
        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }
}
