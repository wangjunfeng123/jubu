/*
Navicat MySQL Data Transfer

Source Server         : nxin
Source Server Version : 50722
Source Host           : 47.95.8.251:3306
Source Database       : farm

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-27 13:56:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auth`
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `auth_type` int(11) DEFAULT NULL COMMENT '权限类型',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth
-- ----------------------------

-- ----------------------------
-- Table structure for `org`
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of org
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `describle` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_auth`
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for `role_org`
-- ----------------------------
DROP TABLE IF EXISTS `role_org`;
CREATE TABLE `role_org` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_org
-- ----------------------------

-- ----------------------------
-- Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL,
  `role_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色ids',
  `url_pattern` varchar(200) DEFAULT NULL,
  `url_description` varchar(200) DEFAULT NULL,
  `is_menu` int(11) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` char(36) NOT NULL COMMENT '用户id',
  `user_num` int(11) NOT NULL COMMENT '用户编号',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone_num` varchar(255) NOT NULL COMMENT '电话号码',
  `department` smallint(4) DEFAULT NULL COMMENT '部门',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `join_date` datetime DEFAULT NULL,
  `leave_date` datetime DEFAULT NULL,
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'admin', '123456', '15650735910', '1', '1', '1', '2018-07-12 16:44:02', null, '1', null, null, null, '11596223@qq.com');
INSERT INTO `user` VALUES ('9005ff97-4967-4a44-a3c6-64b4c03b6801', '0', 'string', '123456', '15650733456', '1', '0', '1', '2018-07-12 18:56:24', null, '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-12 19:00:12', '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-12 19:00:12', '1159@qq.com');
INSERT INTO `user` VALUES ('c3f0e66e-952d-4c32-8b2e-519b37ffd505', '0', 'string', 'string', '18323456789', '0', '0', '1', '2018-07-13 17:07:33', '2018-07-13 17:07:33', '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-13 17:08:36', '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-13 17:08:36', 'wang@163.com');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
