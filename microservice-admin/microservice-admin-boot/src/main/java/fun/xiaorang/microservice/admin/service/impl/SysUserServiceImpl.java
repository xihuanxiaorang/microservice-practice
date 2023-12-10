package fun.xiaorang.microservice.admin.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.xiaorang.microservice.admin.convert.SysUserConverter;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.admin.mapper.SysUserMapper;
import fun.xiaorang.microservice.admin.pojo.entity.SysUser;
import fun.xiaorang.microservice.admin.pojo.entity.SysUserRole;
import fun.xiaorang.microservice.admin.pojo.request.SysUserCreateRequest;
import fun.xiaorang.microservice.admin.pojo.vo.SysUserVO;
import fun.xiaorang.microservice.admin.service.SysUserRoleService;
import fun.xiaorang.microservice.admin.service.SysUserService;
import fun.xiaorang.microservice.common.base.constants.GlobalConstant;
import fun.xiaorang.microservice.common.base.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    private final SysUserConverter sysUserConverter;
    private final PasswordEncoder passwordEncoder;
    private final SysUserRoleService sysUserRoleService;

    @Override
    public UserAuthInfo getUserAuthInfo(final String username) {
        return this.baseMapper.getUserAuthInfo(username);
    }

    @Transactional
    @Override
    public boolean save(final SysUserCreateRequest sysUserCreateRequest) {
        final String username = sysUserCreateRequest.getUsername();
        final long count = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        Assert.isTrue(count == 0, "用户名已存在");
        final SysUser sysUser = sysUserConverter.requestToEntity(sysUserCreateRequest);
        sysUser.setPassword(passwordEncoder.encode(GlobalConstant.USER_DEFAULT_PASSWORD));
        final boolean result = this.save(sysUser);
        if (result) {
            final List<SysUserRole> sysUserRoles = sysUserCreateRequest.getRoleIds()
                    .stream()
                    .map(roleId -> new SysUserRole(sysUser.getId(), roleId))
                    .collect(Collectors.toList());
            sysUserRoleService.saveBatch(sysUserRoles);
        }
        return result;
    }

    @Override
    public SysUserVO getSysUserDetails(final Long userId) {
        final SysUser sysUser = lambdaQuery().eq(SysUser::getId, userId).one();
        if (sysUser == null) {
            throw new BizException("用户不存在！");
        }
        SysUserVO sysUserVO = sysUserConverter.entityToVO(sysUser);
        final List<Long> roleIds = sysUserRoleService.selectRoleIds(userId);
        sysUserVO.setRoleIds(roleIds);
        return sysUserVO;
    }
}




