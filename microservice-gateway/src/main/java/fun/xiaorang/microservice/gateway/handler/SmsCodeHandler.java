package fun.xiaorang.microservice.gateway.handler;

import cn.hutool.core.util.RandomUtil;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.base.model.Result;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/19 01:02
 */
@Component
@RequiredArgsConstructor
public class SmsCodeHandler implements HandlerFunction<ServerResponse> {
    private final RedisService redisService;

    @Override
    public Mono<ServerResponse> handle(final ServerRequest request) {
        // 获取手机号
        final String mobile = request.queryParam("mobile")
                .orElseThrow(() -> new IllegalArgumentException("手机号不能为空"));
        // 随机生成6位的验证码
        String code = RandomUtil.randomNumbers(6);
        // 获取短信发送实例
        final SmsBlend smsBlend = SmsFactory.getSmsBlend("alibaba");
        // 发送短信验证码
        smsBlend.sendMessageAsync(mobile, code);
        // 5分钟过期
        redisService.set(SecurityConstant.SMS_CODE_PREFIX + mobile, code, 300);
        return ServerResponse.ok().body(BodyInserters.fromValue(Result.success()));
    }
}
