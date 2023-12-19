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

 Date: 08/12/2023 12:28:33
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
    `parent_id`      bigint                                                        NULL     DEFAULT NULL COMMENT '父菜单id',
    `type`           tinyint                                                       NOT NULL COMMENT '菜单类型（1-菜单，2-目录，3-外链，4-按钮）',
    `name`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单名称',
    `path`           varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '路由地址（浏览器地址栏路径）',
    `component`      varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件路径（vue页面完整路径，省略.vue后缀）',
    `component_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件名',
    `icon`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '菜单图标',
    `sort`           int                                                           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status`         tinyint                                                       NOT NULL DEFAULT 0 COMMENT '菜单状态（0-正常，1-停用）',
    `visible`        tinyint                                                       NOT NULL DEFAULT 1 COMMENT '是否可见（0-否，1-是）',
    `redirect`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '跳转路径',
    `always_show`    tinyint                                                       NOT NULL DEFAULT 1 COMMENT '【目录】只有一个子路由是否始终显示（0-否，1-是）',
    `keep_alive`     tinyint                                                       NOT NULL DEFAULT 1 COMMENT '【菜单】是否开启页面缓存（0-否，1-是）',
    `create_by`      bigint                                                        NULL     DEFAULT NULL COMMENT '创建人ID',
    `create_time`    datetime                                                      NOT NULL COMMENT '创建时间',
    `update_by`      bigint                                                        NULL     DEFAULT NULL COMMENT '更新人ID',
    `update_time`    datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`        tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, 2, '系统管理', '/system', 'Layout', NULL, 'el-icon-setting', 1, 0, 1, '/system/user', 0, 0, 1,
        '2023-12-08 00:54:12', 1, '2023-12-08 00:54:17', 0);
INSERT INTO `sys_menu`
VALUES (2, 1, 1, '用户管理', 'user', 'system/user/index', NULL, 'el-icon-user', 1, 0, 1, NULL, 0, 1, 1,
        '2023-12-08 00:57:48', 1, '2023-12-08 00:57:52', 0);
INSERT INTO `sys_menu`
VALUES (3, 1, 1, '角色管理', 'role', 'system/role/index', NULL, 'el-icon-menu', 2, 0, 1, NULL, 0, 1, 1,
        '2023-12-08 01:00:00', 1, '2023-12-08 01:00:05', 0);
INSERT INTO `sys_menu`
VALUES (4, 1, 1, '菜单管理', 'cmenu', 'system/menu/index', NULL, 'el-icon-menu', 3, 0, 1, NULL, 0, 1, 1,
        '2023-12-08 01:01:33', 1, '2023-12-08 01:01:36', 0);

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client`
(
    `client_id`               varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '客户端ID',
    `client_secret`           varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '客户端密钥',
    `resource_ids`            varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '资源id列表',
    `scope`                   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '权限范围',
    `authorized_grant_types`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '支持的授权类型',
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '回调地址',
    `authorities`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '权限列表',
    `access_token_validity`   int                                                            NULL     DEFAULT NULL COMMENT '访问令牌时效',
    `refresh_token_validity`  int                                                            NULL     DEFAULT NULL COMMENT '刷新令牌时效',
    `additional_information`  varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '扩展信息',
    `autoapprove`             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '是否自动放行',
    `create_by`               bigint                                                         NULL     DEFAULT NULL COMMENT '创建人ID',
    `create_time`             datetime                                                       NOT NULL COMMENT '创建时间',
    `update_by`               bigint                                                         NULL     DEFAULT NULL COMMENT '更新人ID',
    `update_time`             datetime                                                       NOT NULL COMMENT '更新时间',
    `deleted`                 tinyint                                                        NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '客户端信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
INSERT INTO `sys_oauth_client`
VALUES ('client', '123456', '', 'all',
        'authorization_code,password,client_credentials,implicit,refresh_token,captcha,sms_code',
        'https://www.baidu.com', NULL, 3600, 7200, NULL, 'true', 1, '2023-12-06 13:41:50', 1, '2023-12-06 13:41:54', 0);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '权限名称',
    `menu_id`     bigint                                                        NULL     DEFAULT NULL COMMENT '菜单id',
    `url_perm`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '接口权限标识（如：PUT:/microservice-admin/api/v1/users/*）',
    `btn_perm`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '按钮权限标识（如：sys:user:edit）',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '权限状态（0-正常，1-停用）',
    `create_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '更新人ID',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '权限表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission`
VALUES (1, '新增', 2, 'POST:/microservice-admin/api/v1/users', 'sys:user:add', 0, 1, '2023-12-08 01:04:48', 1,
        '2023-12-08 01:04:51', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `code`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串（如：admin）',
    `sort`        int                                                           NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '角色状态（0-正常，1-停用）',
    `create_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '更新人ID',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_name` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '管理员', 'admin', 1, 0, 1, '2023-12-08 01:07:38', 1, '2023-12-08 01:07:42', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NOT NULL COMMENT '角色id',
    `menu_id` bigint NOT NULL COMMENT '菜单id',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1, 2);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id`       bigint NOT NULL COMMENT '角色id',
    `permission_id` bigint NOT NULL COMMENT '权限id',
    PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色和权限关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission`
VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户账号',
    `nickname`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户昵称',
    `gender`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '用户性别（0-男，1-女，2-未知）',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '用户头像',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '手机号码',
    `email`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '用户邮箱',
    `status`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '账号状态((0-正常, 1-禁用))',
    `locked`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '是否锁定（0-未锁定，1-已锁定）',
    `create_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                                        NULL     DEFAULT NULL COMMENT '更新人ID',
    `update_time` datetime                                                      NOT NULL COMMENT '更新时间',
    `deleted`     tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除标识（0-未删除，1-已删除）',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_username` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '系统管理员', 1, '$2a$10$GRVVJkmT23ljJUVrDXMKIu64t7C22m2KPbFJSqB613/LK6cdhykt.', NULL,
        '15019474951', '15019474951@163.com', 0, 0, 1, '2023-12-05 22:16:05', 1, '2023-12-05 22:16:09', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
