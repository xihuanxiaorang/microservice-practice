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
 * @description <p style = " font-weight:bold ; ">角色表<p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023-12-04 19:55:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色权限字符串（如：admin）
     */
    private String code;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 角色状态（0-正常，1-停用）
     *
     * @see fun.xiaorang.microservice.common.base.enums.StatusEnum
     */
    private Integer status;
}