package fun.xiaorang.microservice.gateway.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">参考自 <a href="https://doc.xiaominfo.com/docs/action/springcloud-gateway#2133-%E6%96%87%E6%A1%A3%E8%81%9A%E5%90%88%E4%B8%9A%E5%8A%A1%E7%BC%96%E7%A0%81">Spring Cloud Gateway集成Knife4j - 文档聚合业务编码</a><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/08 18:35
 */
@RestController
public class SwaggerHandler {
    private final SwaggerResourcesProvider swaggerResources;
    private final SecurityConfiguration securityConfiguration;
    private final UiConfiguration uiConfiguration;

    @Autowired
    public SwaggerHandler(
            SwaggerResourcesProvider swaggerResources,
            @Autowired(required = false) final SecurityConfiguration securityConfiguration,
            @Autowired(required = false) final UiConfiguration uiConfiguration) {
        this.swaggerResources = swaggerResources;
        this.securityConfiguration = securityConfiguration;
        this.uiConfiguration = uiConfiguration;
    }

    /**
     * 聚合各个服务的 swagger 接口
     */
    public Mono<ServerResponse> handleResource(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(swaggerResources.get()));
    }

    /**
     * 权限处理器
     */
    public Mono<ServerResponse> handleSecurity(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(Optional.ofNullable(securityConfiguration)
                                .orElse(SecurityConfigurationBuilder.builder().build())));
    }

    /**
     * UI处理器
     */
    public Mono<ServerResponse> handleUi(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(Optional.ofNullable(uiConfiguration)
                                .orElse(UiConfigurationBuilder.builder().build())));
    }
}
