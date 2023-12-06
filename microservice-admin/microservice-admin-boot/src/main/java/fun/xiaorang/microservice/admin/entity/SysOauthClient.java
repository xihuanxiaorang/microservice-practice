package fun.xiaorang.microservice.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.xiaorang.microservice.common.mybatisplus.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">客户端信息表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 13:44:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_oauth_client")
public class SysOauthClient extends BaseEntity {
    /**
     * 客户端ID
     */
    @TableId(type = IdType.INPUT)
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 资源id列表
     */
    private String resourceIds;
    /**
     * 权限范围
     */
    private String scope;
    /**
     * 支持的授权类型
     */
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    private String webServerRedirectUri;
    /**
     * 权限列表
     */
    private String authorities;
    /**
     * 访问令牌时效
     */
    private Integer accessTokenValidity;
    /**
     * 刷新令牌时效
     */
    private Integer refreshTokenValidity;
    /**
     * 扩展信息
     */
    private String additionalInformation;
    /**
     * 是否自动放行
     */
    private String autoapprove;
}