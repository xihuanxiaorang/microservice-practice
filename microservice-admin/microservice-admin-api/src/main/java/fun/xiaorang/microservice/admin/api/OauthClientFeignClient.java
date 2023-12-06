package fun.xiaorang.microservice.admin.api;

import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 14:19
 */
@FeignClient(value = "microservice-admin", contextId = "oauth-client")
public interface OauthClientFeignClient {
    @GetMapping("/api/v1/oauth-clients/{clientId}")
    OauthClientDTO getOauthClient(@PathVariable String clientId);
}
