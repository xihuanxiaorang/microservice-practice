package fun.xiaorang.microservice.common.base.constants;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/07 17:28
 */
public interface SecurityConstant {
    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";
    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";
    /**
     * 认证请求头key
     */
    String JWT_TOKEN_HEADER = "Authorization";
    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";
    /**
     * 接口文档 Knife4j 测试客户端ID
     */
    String TEST_CLIENT_ID = "client";
    /**
     * 黑名单TOKEN Key前缀
     */
    String BLACKLIST_TOKEN_PREFIX = "AUTH:BLACKLIST_TOKEN:";
    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";
    /**
     * JWT 过期时间戳
     */
    String JWT_EXP = "exp";
}
