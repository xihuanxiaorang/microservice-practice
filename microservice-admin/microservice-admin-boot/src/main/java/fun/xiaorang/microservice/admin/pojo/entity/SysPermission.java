package fun.xiaorang.microservice.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.xiaorang.microservice.common.mybatisplus.entity.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">权限表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-06 16:43:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_permission")
public class SysPermission extends BaseEntity {
    /**
     * 权限id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 接口权限标识（如：PUT:/microservice-admin/api/v1/users/*）
     */
    private String urlPerm;
    /**
     * 按钮权限标识（如：sys:user:edit）
     */
    private String btnPerm;
    /**
     * 权限状态（0-正常，1-停用）
     *
     * @see fun.xiaorang.microservice.common.base.enums.StatusEnum
     */
    private Integer status;
    /**
     * 有该权限的角色编号集合
     */
    @TableField(exist = false)
    private List<String> roles;
}