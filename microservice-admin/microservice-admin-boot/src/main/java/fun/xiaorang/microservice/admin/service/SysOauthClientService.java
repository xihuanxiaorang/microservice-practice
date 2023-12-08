package fun.xiaorang.microservice.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import fun.xiaorang.microservice.admin.pojo.entity.SysOauthClient;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 13:44:15
 */
public interface SysOauthClientService extends IService<SysOauthClient> {
    /**
     * 根据客户端ID获取客户端信息
     *
     * @param clientId 客户端ID
     * @return 客户端信息
     */
    OauthClientDTO getOauthClientByClientId(String clientId);
}
