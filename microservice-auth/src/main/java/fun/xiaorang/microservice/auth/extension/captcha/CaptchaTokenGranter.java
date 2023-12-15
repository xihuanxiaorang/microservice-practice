package fun.xiaorang.microservice.auth.extension.captcha;

import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">验证码授权模式授权者<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/15 10:18
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {
    /**
     * 声明授权者 CaptchaTokenGranter 支持授权模式 captcha
     * 根据接口传值 grant_type = captcha 的值匹配到此授权者
     * 匹配逻辑详见下面的两个方法
     *
     * @see org.springframework.security.oauth2.provider.CompositeTokenGranter#grant(String, TokenRequest)
     * @see org.springframework.security.oauth2.provider.token.AbstractTokenGranter#grant(String, TokenRequest)
     */
    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    public CaptchaTokenGranter(
            final AuthenticationManager authenticationManager,
            final AuthorizationServerTokenServices tokenServices,
            final ClientDetailsService clientDetailsService,
            final OAuth2RequestFactory requestFactory,
            final RedisService redisService) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.redisService = redisService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(final ClientDetails client, final TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        // 验证码校验逻辑
        String validateCode = parameters.get("verifyCode");
        String verifyCodeKey = parameters.get("verifyCodeKey");
        // Protect from downstream leaks of verifyCode & verifyCodeKey
        parameters.remove("validateCode");
        parameters.remove("verifyCodeKey");
        Assert.isTrue(StringUtils.hasText(validateCode), "验证码不能为空");
        // 从缓存取出正确的验证码和用户输入的验证码比对
        String correctValidateCode = (String) redisService.get(SecurityConstant.VERIFY_CODE_KEY_PREFIX + verifyCodeKey);
        Assert.isTrue(StringUtils.hasText(correctValidateCode), "验证码已过期");
        Assert.isTrue(correctValidateCode.equals(validateCode), "验证码不正确");
        // 验证码校验通过后，移除验证码缓存
        redisService.delete(SecurityConstant.VERIFY_CODE_KEY_PREFIX + verifyCodeKey);

        // 用户名密码校验逻辑，与密码模式完全一致，参考自 ResourceOwnerPasswordTokenGranter
        String username = parameters.get("username");
        String password = parameters.get("password");
        // Protect from downstream leaks of password
        parameters.remove("password");
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        } catch (UsernameNotFoundException e) {
            // If the user is not found, report a generic error message
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
