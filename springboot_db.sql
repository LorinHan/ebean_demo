/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : springboot_db

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 04/01/2020 19:58:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_author
-- ----------------------------
DROP TABLE IF EXISTS `t_author`;
CREATE TABLE `t_author` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `real_name` varchar(32) NOT NULL COMMENT '用户名称',
  `nick_name` varchar(32) NOT NULL COMMENT '用户匿名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_author
-- ----------------------------
BEGIN;
INSERT INTO `t_author` VALUES (1, 'Lorin', 'Lorin123');
INSERT INTO `t_author` VALUES (2, 'Lorin2', 'Lorin2');
INSERT INTO `t_author` VALUES (3, 'test', 'test');
INSERT INTO `t_author` VALUES (4, 'Lorin', 'Lorin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
