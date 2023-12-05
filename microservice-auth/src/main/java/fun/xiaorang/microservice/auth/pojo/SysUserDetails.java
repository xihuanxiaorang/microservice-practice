package fun.xiaorang.microservice.auth.pojo;

import cn.hutool.core.collection.CollectionUtil;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.auth.enums.PasswordEncoderTypeEnum;
import fun.xiaorang.microservice.common.base.enums.StatusEnum;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/01 21:46
 */
@Data
public class SysUserDetails implements UserDetails {
    /**
     * 扩展字段：用户ID
     */
    private Long userId;

    /**
     * 默认字段
     */
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private Collection<SimpleGrantedAuthority> authorities;

    public SysUserDetails(UserAuthInfo userAuthInfo) {
        this.userId = userAuthInfo.getUserId();
        this.username = userAuthInfo.getUsername();
        this.password = PasswordEncoderTypeEnum.BCRYPT.getPrefix() + userAuthInfo.getPassword();
        this.enabled = StatusEnum.ENABLE.getValue().equals(userAuthInfo.getStatus());
        this.locked = userAuthInfo.getLocked() == 1;
        if (CollectionUtil.isNotEmpty(userAuthInfo.getRoles())) {
            this.authorities = userAuthInfo.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
