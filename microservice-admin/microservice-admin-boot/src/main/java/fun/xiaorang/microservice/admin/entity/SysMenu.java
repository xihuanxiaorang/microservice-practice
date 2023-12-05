package fun.xiaorang.microservice.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.xiaorang.microservice.common.mybatisplus.entity.BaseEntity;
import lombok.*;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; ">菜单权限表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_menu")
public class SysMenu extends BaseEntity {
    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 菜单类型（0-目录，1-菜单，2-按钮）
     */
    private Integer type;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 组件名
     */
    private String componentName;
    /**
     * 菜单状态（0-正常，1-停用）
     */
    private Integer status;
    /**
     * 是否可见（0-否，1-是）
     */
    private Integer visible;
    /**
     * 是否缓存（0-否，1-是）
     */
    private Integer keepAlive;
    /**
     * 是否总是显示（0-否，1-是）
     */
    private Integer alwaysShow;
}