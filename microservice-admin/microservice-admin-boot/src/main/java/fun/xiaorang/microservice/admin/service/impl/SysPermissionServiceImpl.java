package fun.xiaorang.microservice.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.xiaorang.microservice.admin.mapper.SysPermissionMapper;
import fun.xiaorang.microservice.admin.pojo.entity.SysPermission;
import fun.xiaorang.microservice.admin.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 16:43:29
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> listForPermissionRoles() {
        return this.baseMapper.listForPermissionRoles();
    }
}




