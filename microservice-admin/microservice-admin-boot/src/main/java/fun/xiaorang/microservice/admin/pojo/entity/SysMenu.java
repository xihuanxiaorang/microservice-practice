package fun.xiaorang.microservice.admin.pojo.entity;

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
 * @description <p style = " font-weight:bold ; ">菜单权限表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
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
     * 父菜单id
     */
    private Long parentId;
    /**
     * 菜单类型（1-菜单，2-目录，3-外链，4-按钮）
     *
     * @see fun.xiaorang.microservice.admin.enums.MenuTypeEnum
     */
    private Integer type;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 路由地址（浏览器地址栏路径）
     */
    private String path;
    /**
     * 组件路径（vue页面完整路径，省略.vue后缀）
     */
    private String component;
    /**
     * 组件名
     */
    private String componentName;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 菜单状态（0-正常，1-停用）
     *
     * @see fun.xiaorang.microservice.common.base.enums.StatusEnum
     */
    private Integer status;
    /**
     * 是否可见（0-否，1-是）
     */
    private Integer visible;
    /**
     * 跳转路径
     */
    private String redirect;
    /**
     * 【目录】只有一个子路由是否始终显示（0-否，1-是）
     */
    private Integer alwaysShow;

    /**
     * 【菜单】是否开启页面缓存（0-否，1-是）
     */
    private Integer keepAlive;
}