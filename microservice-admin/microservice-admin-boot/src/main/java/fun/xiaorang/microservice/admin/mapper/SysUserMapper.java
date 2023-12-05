package fun.xiaorang.microservice.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.entity.SysUser;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return 认证信息
     */
    UserAuthInfo getUserAuthInfo(String username);
}




