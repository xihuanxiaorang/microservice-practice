package fun.xiaorang.microservice.admin.convert;

import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import fun.xiaorang.microservice.admin.pojo.entity.SysOauthClient;
import org.mapstruct.Mapper;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 14:13
 */
@Mapper
public interface SysOauthClientConverter {
    OauthClientDTO entity2DTO(SysOauthClient sysOauthClient);
}
