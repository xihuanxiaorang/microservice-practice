package fun.xiaorang.microservice.auth.config;

import cn.hutool.json.JSONUtil;
import fun.xiaorang.microservice.admin.api.UserFeignClient;
import fun.xiaorang.microservice.admin.dto.UserAuthInfo;
import fun.xiaorang.microservice.auth.pojo.SysUserDetails;
import fun.xiaorang.microservice.common.base.enums.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/01 17:13
 */
@Slf4j
@ConfigurationProperties(prefix = "security")
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserFeignClient userFeignClient;

    @Setter
    private String[] ignoreUrls;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("whitelist path:{}", JSONUtil.toJsonStr(ignoreUrls));
        http
                .authorizeRequests()
                .antMatchers(ignoreUrls)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 用户名密码认证授权提供者
     *
     * @return {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(sysUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        // 是否隐藏用户不存在异常，默认:true-隐藏；false-抛出异常；
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public UserDetailsService sysUserDetailsService() {
        return username -> {
            final UserAuthInfo userAuthInfo = userFeignClient.getUserAuthInfo(username);
            if (userAuthInfo == null) {
                throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
            }
            SysUserDetails sysUserDetails = new SysUserDetails(userAuthInfo);
            if (!sysUserDetails.isEnabled()) {
                throw new DisabledException("该账户已被禁用，请联系管理员");
            } else if (!sysUserDetails.isAccountNonLocked()) {
                throw new LockedException("该账号已被锁定，请联系管理员");
            }
            return sysUserDetails;
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码器
     * <p>
     * 委托方式，根据密码的前缀选择对应的encoder，例如：{bcypt}前缀->标识BCYPT算法加密；{noop}->标识不使用任何加密即明文的方式
     * <p>
     *
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
