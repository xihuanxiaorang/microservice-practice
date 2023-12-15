package fun.xiaorang.microservice.auth.config;

import fun.xiaorang.microservice.admin.api.OauthClientFeignClient;
import fun.xiaorang.microservice.admin.dto.OauthClientDTO;
import fun.xiaorang.microservice.auth.enums.PasswordEncoderTypeEnum;
import fun.xiaorang.microservice.auth.extension.captcha.CaptchaTokenGranter;
import fun.xiaorang.microservice.auth.pojo.SysUserDetails;
import fun.xiaorang.microservice.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.*;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/01 16:48
 */
@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final ClientDetailsService clientDetailsService;
    private final AuthenticationManager authenticationManager;
    private final OauthClientFeignClient oauthClientFeignClient;
    private final RedisService redisService;

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientId -> {
            final OauthClientDTO oauthClient = oauthClientFeignClient.getOauthClient(clientId);
            if (oauthClient == null) {
                throw new NoSuchClientException("No client with requested id: " + clientId);
            }
            final BaseClientDetails clientDetails = new BaseClientDetails(
                    oauthClient.getClientId(),
                    oauthClient.getResourceIds(),
                    oauthClient.getScope(),
                    oauthClient.getAuthorizedGrantTypes(),
                    oauthClient.getAuthorities(),
                    oauthClient.getWebServerRedirectUri());
            clientDetails.setClientSecret(PasswordEncoderTypeEnum.NOOP.getPrefix() + oauthClient.getClientSecret());
            clientDetails.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValidity());
            clientDetails.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValidity());
            clientDetails.setAutoApproveScopes(Arrays.asList(oauthClient.getAutoapprove().split(",")));
            return clientDetails;
        });
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        // 获取原有默认授权模式(授权码模式、密码模式、客户端模式、简化模式)的授权者
        List<TokenGranter> granterList = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // 添加验证码授权模式授权者
        granterList.add(new CaptchaTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), redisService));
        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices())
                .tokenGranter(compositeTokenGranter);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenEnhancer(tokenEnhancerChain());
        tokenServices.setSupportRefreshToken(true);
        /*
         * refresh_token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
         *  1 重复使用：access_token过期刷新时， refresh_token过期时间未改变，仍以初次生成的时间为准
         *  2 非重复使用：access_token过期刷新时， refresh_token过期时间延续，在refresh_token有效期内刷新便永不失效达到无需再次登录的目的
         */
        tokenServices.setReuseRefreshToken(true);
        return tokenServices;
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        // Token增强
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        final List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(addtionalInformationTokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        return tokenEnhancerChain;
    }

    @Bean
    public TokenEnhancer addtionalInformationTokenEnhancer() {
        return (accessToken, authentication) -> {
            final Authentication userAuthentication = authentication.getUserAuthentication();
            if (Objects.nonNull(userAuthentication)) {
                Object principal = userAuthentication.getPrincipal();
                Map<String, Object> additionalInfo = new LinkedHashMap<>();
                if (principal instanceof SysUserDetails) {
                    SysUserDetails sysUserDetails = (SysUserDetails) principal;
                    additionalInfo.put("userId", sysUserDetails.getUserId());
                    additionalInfo.put("username", sysUserDetails.getUsername());
                }
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
            return accessToken;
        };
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 使用非对称加密算法对token签名
     *
     * @return {@link JwtAccessTokenConverter}
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 从类路径下的证书中获取秘钥对
     *
     * @return {@link KeyPair}
     */
    @Bean
    public KeyPair keyPair() {
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }
}
