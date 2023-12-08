package fun.xiaorang.microservice.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.xiaorang.microservice.admin.convert.SysOauthClientConverter;
import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import fun.xiaorang.microservice.admin.mapper.SysOauthClientMapper;
import fun.xiaorang.microservice.admin.pojo.entity.SysOauthClient;
import fun.xiaorang.microservice.admin.service.SysOauthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 13:44:15
 */
@RequiredArgsConstructor
@Service
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements SysOauthClientService {
    private final SysOauthClientConverter sysOauthClientConverter;

    @Override
    public OauthClientDTO getOauthClientByClientId(final String clientId) {
        return sysOauthClientConverter.entity2DTO(this.getById(clientId));
    }
}




