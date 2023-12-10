package fun.xiaorang.microservice.admin.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/12/09 11:32
 */
@ApiModel("用户信息")
@Data
public class SysUserVO {
    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户角色ID集合")
    private List<Long> roleIds;

    @ApiModelProperty("用户菜单ID集合")
    private List<Long> menuIds;

    @ApiModelProperty("用户权限标识集合")
    private List<String> permissions;
}
