package fun.xiaorang.microservice.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/06 14:00
 */
@ApiModel(value = "客户端信息传输层对象")
@Data
public class OauthClientDTO {
    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;
    /**
     * 客户端密钥
     */
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;
    /**
     * 资源id列表
     */
    @ApiModelProperty(value = "资源id列表")
    private String resourceIds;
    /**
     * 权限范围
     */
    @ApiModelProperty(value = "权限范围")
    private String scope;
    /**
     * 支持的授权类型
     */
    @ApiModelProperty(value = "支持的授权类型")
    private String authorizedGrantTypes;
    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;
    /**
     * 权限列表
     */
    @ApiModelProperty(value = "权限列表")
    private String authorities;
    /**
     * 访问令牌时效
     */
    @ApiModelProperty(value = "访问令牌时效")
    private Integer accessTokenValidity;
    /**
     * 刷新令牌时效
     */
    @ApiModelProperty(value = "刷新令牌时效")
    private Integer refreshTokenValidity;
    /**
     * 扩展信息
     */
    @ApiModelProperty(value = "扩展信息")
    private String additionalInformation;
    /**
     * 是否自动放行
     */
    @ApiModelProperty(value = "是否自动放行")
    private String autoapprove;
}
