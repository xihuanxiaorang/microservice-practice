package fun.xiaorang.microservice.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.xiaorang.microservice.admin.pojo.entity.SysPermission;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 16:43:29
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取所有的权限以及有该权限的角色集合
     *
     * @return 权限集合
     */
    List<SysPermission> listForPermissionRoles();
}
