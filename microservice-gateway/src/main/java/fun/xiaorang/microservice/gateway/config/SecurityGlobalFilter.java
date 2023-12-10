package fun.xiaorang.microservice.gateway.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.base.enums.ResultCode;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import fun.xiaorang.microservice.gateway.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">安全拦截全局过滤器
 * <p>在ResourceServerManager#check鉴权善后一些无关紧要的事宜(线上请求拦截、黑名单拦截)</p>
 * <p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/09 16:26
 */
@Order(0)
@RequiredArgsConstructor
@Component
public class SecurityGlobalFilter implements GlobalFilter {
    private final RedisService redisService;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, final GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 拦截黑名单的JWT
        String authorization = request.getHeaders().getFirst(SecurityConstant.JWT_TOKEN_HEADER);
        String payload = this.getPayload(authorization);
        if (StrUtil.isBlank(payload)) {
            return chain.filter(exchange);
        }
        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在则拦截访问
        String jti = JSONUtil.parseObj(payload).getStr(SecurityConstant.JWT_JTI);
        boolean isBlackJwt = redisService.hasKey(SecurityConstant.BLACKLIST_TOKEN_PREFIX + jti);
        if (isBlackJwt) {
            return ResponseUtil.writeErrorInfo(exchange.getResponse(), ResultCode.TOKEN_ACCESS_FORBIDDEN);
        }
        // 传递 payload 给其他微服务
        request = exchange.getRequest()
                .mutate()
                .header(SecurityConstant.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, StandardCharsets.UTF_8))
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    /**
     * 获取JWT负载信息
     *
     * @param authorization 请求头中的Authorization
     * @return payload 负载信息
     * @throws ParseException 解析异常
     */
    private String getPayload(String authorization) throws ParseException {
        if (StrUtil.isBlank(authorization) || !StrUtil.startWithIgnoreCase(authorization, SecurityConstant.JWT_TOKEN_PREFIX)) {
            return null;
        }
        authorization = StrUtil.replaceIgnoreCase(authorization, SecurityConstant.JWT_TOKEN_PREFIX, StrUtil.EMPTY);
        return JWSObject.parse(authorization).getPayload().toString();
    }
}
