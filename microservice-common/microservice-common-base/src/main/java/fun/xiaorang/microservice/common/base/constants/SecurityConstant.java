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
}
