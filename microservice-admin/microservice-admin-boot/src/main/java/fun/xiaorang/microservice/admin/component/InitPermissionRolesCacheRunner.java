package fun.xiaorang.microservice.admin.component;

import fun.xiaorang.microservice.admin.pojo.entity.SysPermission;
import fun.xiaorang.microservice.admin.service.SysPermissionService;
import fun.xiaorang.microservice.common.base.constants.GlobalConstant;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 17:08
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class InitPermissionRolesCacheRunner implements CommandLineRunner {
    private final SysPermissionService sysPermissionService;
    private final RedisService redisService;

    @Override
    public void run(final String... args) {
        log.info("InitPermissionRolesCacheRunner run...");
        redisService.delete(GlobalConstant.URL_PERM_ROLES_KEY);
        final Map<String, Object> permissionRolesMap = sysPermissionService.listForPermissionRoles()
                .stream()
                .filter(sysPermission -> StringUtils.hasText(sysPermission.getUrlPerm()))
                .collect(Collectors.toMap(SysPermission::getUrlPerm, SysPermission::getRoles, (k1, k2) -> k1));
        redisService.hSet(GlobalConstant.URL_PERM_ROLES_KEY, permissionRolesMap);
    }
}
