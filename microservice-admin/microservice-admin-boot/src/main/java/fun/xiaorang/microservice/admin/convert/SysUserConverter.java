package fun.xiaorang.microservice.admin.convert;

import fun.xiaorang.microservice.admin.pojo.entity.SysUser;
import fun.xiaorang.microservice.admin.pojo.request.UserCreateRequest;
import org.mapstruct.Mapper;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/08 01:36
 */
@Mapper
public interface SysUserConverter {
    SysUser requestToEntity(UserCreateRequest userCreateRequest);
}
