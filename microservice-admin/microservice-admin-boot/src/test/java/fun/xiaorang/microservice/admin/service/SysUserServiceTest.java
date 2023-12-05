package fun.xiaorang.microservice.admin.service;

import fun.xiaorang.microservice.admin.entity.SysUser;
import fun.xiaorang.microservice.common.base.enums.GenderEnum;
import fun.xiaorang.microservice.common.base.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/04 20:53
 */
@SpringBootTest
class SysUserServiceTest {
    private final SysUserService sysUserService;

    @Autowired
    SysUserServiceTest(final SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Test
    public void save() {
        final SysUser sysUser = new SysUser()
                .setUsername("admin")
                .setNickname("系统管理员")
                .setPassword("$2a$10$GRVVJkmT23ljJUVrDXMKIu64t7C22m2KPbFJSqB613/LK6cdhykt.")
                .setPhone("15019474951")
                .setEmail("15019474951@163.com")
                .setStatus(StatusEnum.ENABLE.getValue())
                .setGender(GenderEnum.MALE.getValue());
        this.sysUserService.save(sysUser);
    }
}