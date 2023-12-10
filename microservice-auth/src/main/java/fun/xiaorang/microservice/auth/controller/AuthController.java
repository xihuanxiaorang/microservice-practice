package fun.xiaorang.microservice.auth.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import fun.xiaorang.microservice.auth.utils.RequestUtil;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.base.model.Result;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/02 17:33
 */
@Api(tags = "认证中心")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class AuthController {
    private final TokenEndpoint tokenEndpoint;
    private final RedisService redisService;

    @ApiOperation(value = "OAuth2认证", notes = "登录入口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "client", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "用户密码")
    })
    @PostMapping("/token")
    public Object postAccessToken(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        String clientId = RequestUtil.getClientId();
        log.info("OAuth认证授权 客户端ID:{}，请求参数：{}", clientId, JSONUtil.toJsonStr(parameters));
        final OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        /*
         * knife4j接口文档测试使用
         * 请求头自动填充，token必须原生返回，不能有任何包装，否则显示 undefined undefined
         */
        if (SecurityConstant.TEST_CLIENT_ID.equals(clientId)) {
            return accessToken;
        }
        return Result.success(accessToken);
    }

    @SneakyThrows
    @ApiOperation(value = "注销")
    @DeleteMapping("/logout")
    public Result<String> logout() {
        final String payload = RequestUtil.getJwtPayload();
        if (StrUtil.isNotBlank(payload)) {
            final JSONObject entries = JSONUtil.parseObj(payload);
            final String jti = entries.getStr(SecurityConstant.JWT_JTI);
            final Long expireTime = entries.getLong(SecurityConstant.JWT_EXP);
            if (expireTime != null) {
                final long currentTime = System.currentTimeMillis() / 1000; // 当前时间（单位：秒）
                // token未过期，添加至redis作为黑名单限制访问，缓存时间为token剩余有效时间
                if (expireTime > currentTime) {
                    redisService.set(SecurityConstant.BLACKLIST_TOKEN_PREFIX + jti, StrUtil.EMPTY, (expireTime - currentTime));
                }
            } else {
                // token 永不过期则永久加入黑名单
                redisService.set(SecurityConstant.BLACKLIST_TOKEN_PREFIX + jti, StrUtil.EMPTY);
            }
        }
        return Result.success();
    }
}
