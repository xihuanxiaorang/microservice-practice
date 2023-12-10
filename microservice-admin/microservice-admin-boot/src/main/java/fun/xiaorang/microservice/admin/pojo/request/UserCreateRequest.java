package fun.xiaorang.microservice.admin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/08 01:17
 */
@ApiModel(value = "用户创建请求对象")
@Data
public class UserCreateRequest {
    @ApiModelProperty(value = "用户名", example = "xiaorang")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户昵称", example = "小让")
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "手机号", example = "18888888888")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 性别(0-未知, 1-男, 2-女)
     *
     * @see fun.xiaorang.microservice.common.base.enums.GenderEnum
     */
    @ApiModelProperty(value = "性别(0-未知, 1-男, 2-女)", example = "1", allowableValues = "0,1,2")
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "邮箱", example = "18888888888@163.com")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "用户角色ID集合", example = "[1]")
    @NotEmpty(message = "用户角色不能为空")
    private List<Long> roleIds;
}
