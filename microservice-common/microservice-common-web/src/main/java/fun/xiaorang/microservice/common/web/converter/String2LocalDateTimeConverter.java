package fun.xiaorang.microservice.common.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static fun.xiaorang.microservice.common.base.constants.DateTimeConstant.DEFAULT_DATE_TIME_PATTERN;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 22:16
 */
@Component
public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(@NonNull String s) {
        if (ObjectUtils.isEmpty(s)) {
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN));
    }
}
