package fun.xiaorang.microservice.auth.utils;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import fun.xiaorang.microservice.common.base.constants.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/02 17:36
 */
@Slf4j
public class RequestUtil {
    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取OAuth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return clientId 客户端ID
     */
    public static String getClientId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 从请求路径中获取
        String clientId = request.getParameter("client_id");
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }
        // 从请求头获取
        String basic = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(basic) && basic.startsWith("Basic ")) {
            basic = basic.replace("Basic ", "");
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            clientId = basicPlainText.split(":")[0]; //client:secret
        }
        return clientId;
    }

    /**
     * 获取JWT负载信息
     *
     * @return payload 负载信息
     * @throws ParseException 解析异常
     */
    public static String getJwtPayload() throws ParseException {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String authorization = request.getHeader(SecurityConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(authorization) || !StrUtil.startWithIgnoreCase(authorization, SecurityConstant.JWT_TOKEN_PREFIX)) {
            return null;
        }
        authorization = StrUtil.replaceIgnoreCase(authorization, SecurityConstant.JWT_TOKEN_PREFIX, "");
        return JWSObject.parse(authorization).getPayload().toString();
    }
}
