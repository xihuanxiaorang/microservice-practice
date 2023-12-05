/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : microservice_admin

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 05/12/2023 15:36:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '菜单id',
    `name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单名称',
    `permission`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限标识',
    `type`           tinyint                                                       NOT NULL COMMENT '菜单类型（0-目录，1-菜单，2-按钮）',
    `sort`           int                                                           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `parent_id`      bigint                                                        NOT NULL DEFAULT 0 COMMENT '父菜单id',
    `path`           varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '路由地址',
    `icon`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '菜单图标',
    `component`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件路径',
    `component_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件名',
    `status`         tinyint                                                       NOT NULL DEFAULT 0 COMMENT '菜单状态（0-正常，1-停用）',
    `visible`        tinyint                                                       NOT NULL DEFAULT 1 COMMENT '是否可见（0-否，1-是）',
    `keep_alive`     tinyint                                                       NOT NULL DEFAULT 1 COMMENT '是否缓存（0-否，1-是）',
    `always_show`    tinyint                                                       NOT NULL DEFAULT 1 COMMENT '是否总是显示（0-否，1-是）',
    `creator`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime                                                      NOT NULL COMMENT '创建时间',
    `updater`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`        tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `name`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `code`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串（如：admin）',
    `sort`        int                                                           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '角色状态（0-正常，1-停用）',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `role_id`     bigint                                                       NOT NULL COMMENT '角色id',
    `menu_id`     bigint                                                       NOT NULL COMMENT '菜单id',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime                                                     NOT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                      NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户账号',
    `nickname`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户昵称',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
    `gender`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '用户性别（0-男，1-女，2-未知）',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '用户头像',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '手机号码',
    `email`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '用户邮箱',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '账号状态((0-正常, 1-禁用))',
    `locked`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '是否锁定（0-未锁定，1-已锁定）',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_username` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (100, 'admin', '系统管理员', '$2a$10$GRVVJkmT23ljJUVrDXMKIu64t7C22m2KPbFJSqB613/LK6cdhykt.', 1, NULL,
        '15019474951', '15019474951@163.com', 0, 0, NULL, '2023-12-05 01:45:53', NULL, '2023-12-05 01:45:53', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `role_id`     bigint                                                       NOT NULL COMMENT '角色id',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '更新者',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                      NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
