package fun.xiaorang.microservice.admin.controller;

import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import fun.xiaorang.microservice.admin.service.SysOauthClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 13:58
 */
@Api(tags = "客户端管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/oauth-clients")
public class SysOauthClientController {
    private final SysOauthClientService sysOauthClientService;

    /**
     * 根据客户端ID获取客户端信息
     *
     * @param clientId 客户端ID
     * @return 客户端信息
     */
    @ApiOperation(value = "根据客户端ID获取客户端信息", hidden = true)
    @GetMapping("/{clientId}")
    public OauthClientDTO getOauthClient(@PathVariable String clientId) {
        return sysOauthClientService.getOauthClientByClientId(clientId);
    }
}
