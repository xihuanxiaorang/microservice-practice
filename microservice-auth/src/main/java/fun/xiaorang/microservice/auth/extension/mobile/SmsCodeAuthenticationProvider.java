package fun.xiaorang.microservice.auth.extension.mobile;

import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">手机短信验证码认证授权提供者<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/19 09:52
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private final RedisService redisService;
    private final UserDetailsService userDetailsService;

    public SmsCodeAuthenticationProvider(RedisService redisService, UserDetailsService userDetailsService) {
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        final String mobile = (String) authenticationToken.getPrincipal();
        final String code = (String) authenticationToken.getCredentials();
        final String correctCode = (String) redisService.get(SecurityConstant.SMS_CODE_PREFIX + mobile);
        Assert.notNull(correctCode, "短信验证码已过期！");
        Assert.isTrue(correctCode.equals(code), "短信验证码不正确！");
        // 比对成功删除缓存的短信验证码
        redisService.delete(SecurityConstant.SMS_CODE_PREFIX + mobile);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        SmsCodeAuthenticationToken result = new SmsCodeAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return (SmsCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
