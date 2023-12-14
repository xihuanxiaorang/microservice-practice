package fun.xiaorang.microservice.gateway.handler;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.core.util.IdUtil;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.base.model.Result;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/14 16:46
 */
@Component
@RequiredArgsConstructor
public class CaptchaHandler implements HandlerFunction<ServerResponse> {
    private final RedisService redisService;

    @Override
    public Mono<ServerResponse> handle(final ServerRequest request) {
        // 宽，高，位数
        final GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(120, 40, 4);
        // 验证码
        final String captchaCode = gifCaptcha.getCode();
        // 验证码图片Base64
        final String captchaImgBase64 = gifCaptcha.getImageBase64Data();
        // 验证码文本缓存至Redis，用于登录校验
        final String verifyCodeKey = IdUtil.fastSimpleUUID();
        redisService.set(SecurityConstant.VERIFY_CODE_KEY_PREFIX + verifyCodeKey, captchaCode, 120);

        final Map<String, String> resultMap = new HashMap<>(2);
        resultMap.put("verifyCodeKey", verifyCodeKey);
        resultMap.put("captchaImgBase64", captchaImgBase64);
        return ServerResponse.ok().body(BodyInserters.fromValue(Result.success(resultMap)));
    }
}
