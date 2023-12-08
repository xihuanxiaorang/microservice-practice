package fun.xiaorang.microservice.admin.api;

import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.common.web.config.FeignDecoderConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 16:52
 */
@FeignClient(value = "microservice-admin", configuration = FeignDecoderConfig.class)
public interface UserFeignClient {
    @GetMapping("/api/v1/users/{username}/authInfo")
    UserAuthInfo getUserAuthInfo(@PathVariable String username);
}
