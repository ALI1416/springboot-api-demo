/*
 Navicat Premium Data Transfer

 Source Server         : 404z.cn_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 404z.cn:3306
 Source Schema         : springboot_api_demo

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 24/01/2021 19:50:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `role_api_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'api角色id',
  `role_web_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'web角色id',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE,
  INDEX `update_id`(`update_id`) USING BTREE,
  INDEX `role_api_id`(`role_api_id`) USING BTREE,
  CONSTRAINT `admin_ibfk_create_id` FOREIGN KEY (`create_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `admin_ibfk_role_api_id` FOREIGN KEY (`role_api_id`) REFERENCES `role_api` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `admin_ibfk_update_id` FOREIGN KEY (`update_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (0, '', '', '', '', 0, 0, 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);
INSERT INTO `admin` VALUES (1, 'admin', '$2a$10$Jna0eHkLJ6X8XFDoOZq4QufchuF442kUzHVFTPS3gWvwWNTdSPrUu', 'admin', 'admin', 0, 0, 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);

-- ----------------------------
-- Table structure for admin_bak
-- ----------------------------
DROP TABLE IF EXISTS `admin_bak`;
CREATE TABLE `admin_bak`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被备份的id',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `role_api_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'api角色id',
  `role_web_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'web角色id',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_bak
-- ----------------------------

-- ----------------------------
-- Table structure for admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被登录的id',
  `login_success` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '登录成功',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0.0.0.0' COMMENT 'IP地址',
  `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识User-Agent',
  `ip_country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-国家',
  `ip_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-省份',
  `ip_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-城市',
  `ua_os_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-操作系统名',
  `ua_browser_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-浏览器名',
  `ua_device_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-设备类型名',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for role_api
-- ----------------------------
DROP TABLE IF EXISTS `role_api`;
CREATE TABLE `role_api`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE,
  INDEX `update_id`(`update_id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `role_api_ibfk_create_id` FOREIGN KEY (`create_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_ibfk_update_id` FOREIGN KEY (`update_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api
-- ----------------------------
INSERT INTO `role_api` VALUES (0, '', 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);

-- ----------------------------
-- Table structure for role_api_bak
-- ----------------------------
DROP TABLE IF EXISTS `role_api_bak`;
CREATE TABLE `role_api_bak`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被备份的id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api_bak
-- ----------------------------

-- ----------------------------
-- Table structure for role_api_ref
-- ----------------------------
DROP TABLE IF EXISTS `role_api_ref`;
CREATE TABLE `role_api_ref`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `role_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
  `tree_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色树id',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE,
  INDEX `update_id`(`update_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `tree_id`(`tree_id`) USING BTREE,
  CONSTRAINT `role_api_ref_ibfk_create_id` FOREIGN KEY (`create_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_ref_ibfk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_api` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_ref_ibfk_tree_id` FOREIGN KEY (`tree_id`) REFERENCES `role_api_tree` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_ref_ibfk_update_id` FOREIGN KEY (`update_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色引用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api_ref
-- ----------------------------
INSERT INTO `role_api_ref` VALUES (0, 0, 0, 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);

-- ----------------------------
-- Table structure for role_api_ref_bak
-- ----------------------------
DROP TABLE IF EXISTS `role_api_ref_bak`;
CREATE TABLE `role_api_ref_bak`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被备份的id',
  `role_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
  `tree_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色树id',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色引用备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api_ref_bak
-- ----------------------------

-- ----------------------------
-- Table structure for role_api_tree
-- ----------------------------
DROP TABLE IF EXISTS `role_api_tree`;
CREATE TABLE `role_api_tree`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `parent_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路径名',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE,
  INDEX `update_id`(`update_id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  UNIQUE INDEX `parent_id_path`(`parent_id`, `path`) USING BTREE,
  CONSTRAINT `role_api_tree_ibfk_create_id` FOREIGN KEY (`create_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_tree_ibfk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `role_api_tree` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_api_tree_ibfk_update_id` FOREIGN KEY (`update_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色树表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api_tree
-- ----------------------------
INSERT INTO `role_api_tree` VALUES (0, 0, '', '', 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);
INSERT INTO `role_api_tree` VALUES (1, 0, 'a', '', 0, 0, '2021-01-21 20:58:03', 0, '2021-01-21 20:58:03', 0);
INSERT INTO `role_api_tree` VALUES (2, 0, 'b', '', 0, 0, '2021-01-21 20:58:14', 0, '2021-01-21 20:58:14', 0);
INSERT INTO `role_api_tree` VALUES (3, 0, 'c', '', 0, 0, '2021-01-21 20:58:20', 0, '2021-01-21 20:58:20', 0);
INSERT INTO `role_api_tree` VALUES (11, 1, 'aa', '', 0, 0, '2021-01-21 20:58:31', 0, '2021-01-21 20:58:31', 0);
INSERT INTO `role_api_tree` VALUES (12, 1, 'ab', '', 0, 0, '2021-01-21 20:58:39', 0, '2021-01-21 20:58:39', 0);
INSERT INTO `role_api_tree` VALUES (21, 2, 'ba', '', 0, 0, '2021-01-21 20:58:48', 0, '2021-01-21 20:58:48', 0);
INSERT INTO `role_api_tree` VALUES (22, 2, 'bb', '', 0, 0, '2021-01-21 20:59:01', 0, '2021-01-21 20:59:01', 0);
INSERT INTO `role_api_tree` VALUES (23, 2, 'bc', '', 0, 0, '2021-01-21 20:59:10', 0, '2021-01-21 20:59:10', 0);
INSERT INTO `role_api_tree` VALUES (111, 11, 'aaa', '', 0, 0, '2021-01-21 20:59:30', 0, '2021-01-21 20:59:30', 0);
INSERT INTO `role_api_tree` VALUES (231, 23, 'bca', '', 0, 0, '2021-01-21 20:59:54', 0, '2021-01-21 20:59:54', 0);
INSERT INTO `role_api_tree` VALUES (232, 23, 'bcb', '', 0, 0, '2021-01-21 21:00:03', 0, '2021-01-21 21:00:03', 0);
INSERT INTO `role_api_tree` VALUES (2321, 232, 'bcba', '', 0, 0, '2021-01-21 21:00:25', 0, '2021-01-21 21:00:25', 0);

-- ----------------------------
-- Table structure for role_api_tree_bak
-- ----------------------------
DROP TABLE IF EXISTS `role_api_tree_bak`;
CREATE TABLE `role_api_tree_bak`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被备份的id',
  `parent_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路径名',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api角色树备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_api_tree_bak
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `gender` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别',
  `year` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '出生年',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '个人简介',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `qq_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'qqOpenid',
  `qq_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'qq昵称',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `qq_openid`(`qq_openid`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  INDEX `create_id`(`create_id`) USING BTREE,
  INDEX `update_id`(`update_id`) USING BTREE,
  CONSTRAINT `user_ibfk_create_id` FOREIGN KEY (`create_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_ibfk_update_id` FOREIGN KEY (`update_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, '', '', '', 0, 0, '', '', '', '', '', 0, 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0);

-- ----------------------------
-- Table structure for user_bak
-- ----------------------------
DROP TABLE IF EXISTS `user_bak`;
CREATE TABLE `user_bak`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被备份的id',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `gender` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别',
  `year` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '出生年',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '个人简介',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电子邮箱',
  `qq_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'qqOpenid',
  `qq_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'qq昵称',
  `is_delete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已删除',
  `create_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户备份表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_bak
-- ----------------------------

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log`  (
  `id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'id',
  `ref_id` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被登录的id',
  `login_type` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '登录类型：0、账号，1、邮箱，2、QQ',
  `login_success` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '登录成功',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0.0.0.0' COMMENT 'IP地址',
  `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识User-Agent',
  `ip_country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-国家',
  `ip_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-省份',
  `ip_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'IP地址-城市',
  `ua_os_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-操作系统名',
  `ua_browser_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-浏览器名',
  `ua_device_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '浏览器标识-设备类型名',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ref_id`(`ref_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
