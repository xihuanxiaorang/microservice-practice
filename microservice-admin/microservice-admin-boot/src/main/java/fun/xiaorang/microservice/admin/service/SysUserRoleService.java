package fun.xiaorang.microservice.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.xiaorang.microservice.admin.pojo.entity.SysUserRole;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-08 02:26:10
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    /**
     * 根据用户ID获取绑定的角色ID集合
     *
     * @param userId 用户ID
     * @return 角色ID集合
     */
    List<Long> selectRoleIds(Long userId);
}
