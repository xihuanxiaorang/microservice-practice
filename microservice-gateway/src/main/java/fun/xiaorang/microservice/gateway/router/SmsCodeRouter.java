package fun.xiaorang.microservice.gateway.router;

import fun.xiaorang.microservice.gateway.handler.SmsCodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/14 16:54
 */
@Configuration
public class SmsCodeRouter {
    @Bean
    public RouterFunction<ServerResponse> smsCodeRouterFunction(SmsCodeHandler smsCodeHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/sms-code")
                        .and(RequestPredicates.accept(MediaType.ALL)), smsCodeHandler);
    }
}
