package fun.xiaorang.microservice.admin.pojo.request;

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
@Data
public class UserCreateRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "{phone.valid}")
    private String phone;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "用户角色不能为空")
    private List<Long> roleIds;

}
