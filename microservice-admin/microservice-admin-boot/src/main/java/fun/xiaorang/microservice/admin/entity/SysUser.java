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
 * @description <p style = " font-weight:bold ; ">用户表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户性别（0-未知，1-男，2-女）
     *
     * @see fun.xiaorang.microservice.common.base.enums.GenderEnum
     */
    private Integer gender;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 账号状态((0-正常, 1-禁用))
     *
     * @see fun.xiaorang.microservice.common.base.enums.StatusEnum
     */
    private Integer status;
    /**
     * 是否锁定（0-未锁定，1-已锁定）
     */
    private Integer locked;
    /**
     * 备注
     */
    private String remark;
}