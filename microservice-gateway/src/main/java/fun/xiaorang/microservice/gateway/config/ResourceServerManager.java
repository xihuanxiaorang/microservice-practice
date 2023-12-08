package fun.xiaorang.microservice.gateway.config;

import cn.hutool.core.convert.Convert;
import fun.xiaorang.microservice.common.base.constants.GlobalConstant;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/07 16:38
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private final RedisService redisService;

    @Override
    public Mono<AuthorizationDecision> check(final Mono<Authentication> mono, final AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 预检请求放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 如果 token 为空或者 token 不合法 则进行拦截
        String token = request.getHeaders().getFirst(SecurityConstant.JWT_TOKEN_HEADER);
        if (token == null || !token.startsWith(SecurityConstant.JWT_TOKEN_PREFIX)) {
            return Mono.just(new AuthorizationDecision(false));
        }
        // RESTFul接口权限设计 https://www.cnblogs.com/haoxianrui/p/14961707.html
        String restfulPath = request.getMethodValue() + ":" + request.getURI().getPath();
        // 从缓存中获取权限角色关系列表
        final Map<Object, Object> permissionRolesMap = redisService.hGet(GlobalConstant.URL_PERM_ROLES_KEY);
        PathMatcher pathMatcher = new AntPathMatcher();
        // 获取当前资源所需要的角色
        final List<String> authorizedRoles = permissionRolesMap
                .entrySet()
                .stream()
                .filter(permRoles -> pathMatcher.match((String) permRoles.getKey(), restfulPath))
                .map(permRoles -> Convert.toList(String.class, permRoles.getValue()))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        // 如果当前资源没有设置角色限制，则默认无需鉴权，直接放行
        if (authorizedRoles.isEmpty()) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    String role = authority.substring(SecurityConstant.AUTHORITY_PREFIX.length());
                    log.info("访问路径：{}", restfulPath);
                    log.info("用户角色信息：{}", role);
                    log.info("访问资源所需角色：{}", authorizedRoles);
                    return authorizedRoles.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
