package fun.xiaorang.microservice.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.entity.SysUser;
import fun.xiaorang.microservice.admin.mapper.SysUserMapper;
import fun.xiaorang.microservice.admin.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public UserAuthInfo getUserAuthInfo(final String username) {
        return this.baseMapper.getUserAuthInfo(username);
    }
}




