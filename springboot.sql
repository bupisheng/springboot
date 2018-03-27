/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-27 16:16:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` varchar(50) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `status` int(10) DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', '1', '2018-01-19 16:45:11', '798294336@qq.com');
INSERT INTO `t_admin` VALUES ('2', 'user', 'user', '0', '2018-01-19 17:43:38', null);
INSERT INTO `t_admin` VALUES ('3fb3ef60896c48b1a2b9d4b52b55f6a7', 'bpsssss', 's', '1', '2018-01-23 15:41:37', '7945@qq.com');
INSERT INTO `t_admin` VALUES ('4318273691f244a3935c4dfff7671f21', 'bps3', '32', '0', '2018-01-23 10:58:36', '232');
INSERT INTO `t_admin` VALUES ('61e96d0f4a514a84b4d863eb1928ec1a', 'bps', '123', '0', '2018-01-22 17:57:38', 'augus@qq.com');
INSERT INTO `t_admin` VALUES ('635c125b0257467c9ed6360f4674cb79', 'bpsss', '12', '1', '2018-01-23 14:55:00', '1567333@qq.com');
INSERT INTO `t_admin` VALUES ('7a2ec46360e446b2bdac45ce05628dab', 'bps5', '4', '0', '2018-01-23 11:00:50', '4');
INSERT INTO `t_admin` VALUES ('a4fc65edf65548a59e639467939fab8e', 'bps2', 're', '0', '2018-01-23 10:57:34', 're');
INSERT INTO `t_admin` VALUES ('d5fc5f4459ae42719d33f1a221d325f6', '111', '2', '1', '2018-01-23 15:42:33', 'fhsdkfs@qq.com');
INSERT INTO `t_admin` VALUES ('e01188a9969b482f96e7579ba6b0de3f', 'bpsp', '3', '0', '2018-01-23 13:54:17', '3');
INSERT INTO `t_admin` VALUES ('fa10f6fb6c014d53beb3f8efc2c7b63a', 'bps8', '12', '0', '2018-01-23 14:53:57', '798294336@qq.com');

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `admin_id` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('23', '2');
INSERT INTO `t_admin_role` VALUES ('1', '3');
INSERT INTO `t_admin_role` VALUES ('2', '2');
INSERT INTO `t_admin_role` VALUES ('61e96d0f4a514a84b4d863eb1928ec1a', '3');
INSERT INTO `t_admin_role` VALUES ('a4fc65edf65548a59e639467939fab8e', '3');
INSERT INTO `t_admin_role` VALUES ('4318273691f244a3935c4dfff7671f21', '2');
INSERT INTO `t_admin_role` VALUES ('7a2ec46360e446b2bdac45ce05628dab', '2');
INSERT INTO `t_admin_role` VALUES ('e01188a9969b482f96e7579ba6b0de3f', '3');
INSERT INTO `t_admin_role` VALUES ('fa10f6fb6c014d53beb3f8efc2c7b63a', '3');
INSERT INTO `t_admin_role` VALUES ('635c125b0257467c9ed6360f4674cb79', '1');
INSERT INTO `t_admin_role` VALUES ('3fb3ef60896c48b1a2b9d4b52b55f6a7', '1');
INSERT INTO `t_admin_role` VALUES ('d5fc5f4459ae42719d33f1a221d325f6', '2');

-- ----------------------------
-- Table structure for t_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(255) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `action_url` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `action_time` bigint(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_operation_log
-- ----------------------------
INSERT INTO `t_operation_log` VALUES ('1', 'admin', '0:0:0:0:0:0:0:1', '/back/admin/list', '管理员模块', '管理员列表', '18', 'admin,管理员模块：管理员列表,参数：[Ljava.lang.String;@6bbeab15,执行成功', '2018-03-27 16:14:50', '1');

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
CREATE TABLE `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES ('1', '系统设置', '/system', '1', '0', '1');
INSERT INTO `t_resources` VALUES ('2', '管理员管理', '/back/admin/list', '1', '1', '2');
INSERT INTO `t_resources` VALUES ('3', '角色管理', '/rolesPage', '1', '1', '3');
INSERT INTO `t_resources` VALUES ('4', '资源管理', '/back/resource/list', '1', '1', '4');
INSERT INTO `t_resources` VALUES ('5', '添加用户', '/users/add', '2', '2', '5');
INSERT INTO `t_resources` VALUES ('6', '删除用户', '/users/delete', '2', '2', '6');
INSERT INTO `t_resources` VALUES ('7', '添加角色', '/roles/add', '2', '3', '7');
INSERT INTO `t_resources` VALUES ('8', '删除角色', '/roles/delete', '2', '3', '8');
INSERT INTO `t_resources` VALUES ('9', '添加资源', '/resources/add', '2', '4', '9');
INSERT INTO `t_resources` VALUES ('10', '删除资源', '/resources/delete', '2', '4', '10');
INSERT INTO `t_resources` VALUES ('11', '分配角色', '/users/saveUserRoles', '2', '2', '11');
INSERT INTO `t_resources` VALUES ('13', '分配权限', '/roles/saveRoleResources', '2', '3', '12');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员');
INSERT INTO `t_role` VALUES ('2', '普通用户');
INSERT INTO `t_role` VALUES ('3', '超级管理员');

-- ----------------------------
-- Table structure for t_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resources`;
CREATE TABLE `t_role_resources` (
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_resources
-- ----------------------------
INSERT INTO `t_role_resources` VALUES ('1', '2');
INSERT INTO `t_role_resources` VALUES ('1', '3');
INSERT INTO `t_role_resources` VALUES ('1', '4');
INSERT INTO `t_role_resources` VALUES ('1', '5');
INSERT INTO `t_role_resources` VALUES ('1', '6');
INSERT INTO `t_role_resources` VALUES ('1', '7');
INSERT INTO `t_role_resources` VALUES ('1', '8');
INSERT INTO `t_role_resources` VALUES ('1', '9');
INSERT INTO `t_role_resources` VALUES ('1', '10');
INSERT INTO `t_role_resources` VALUES ('1', '11');
INSERT INTO `t_role_resources` VALUES ('1', '13');
INSERT INTO `t_role_resources` VALUES ('2', '2');
INSERT INTO `t_role_resources` VALUES ('2', '3');
INSERT INTO `t_role_resources` VALUES ('2', '4');
INSERT INTO `t_role_resources` VALUES ('2', '9');
INSERT INTO `t_role_resources` VALUES ('3', '1');
INSERT INTO `t_role_resources` VALUES ('3', '2');
INSERT INTO `t_role_resources` VALUES ('3', '3');
INSERT INTO `t_role_resources` VALUES ('3', '4');
INSERT INTO `t_role_resources` VALUES ('3', '5');
INSERT INTO `t_role_resources` VALUES ('3', '7');
INSERT INTO `t_role_resources` VALUES ('3', '8');
INSERT INTO `t_role_resources` VALUES ('3', '9');
INSERT INTO `t_role_resources` VALUES ('3', '10');
INSERT INTO `t_role_resources` VALUES ('9', '9');
