/*
 Navicat Premium Data Transfer

 Source Server         : db2021
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 19/10/2022 14:17:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_pers
-- ----------------------------
DROP TABLE IF EXISTS `t_pers`;
CREATE TABLE `t_pers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pers
-- ----------------------------
INSERT INTO `t_pers` VALUES (1, 'user:*:*', NULL);
INSERT INTO `t_pers` VALUES (2, 'guest:*:*', NULL);
INSERT INTO `t_pers` VALUES (3, 'user:add:*', NULL);
INSERT INTO `t_pers` VALUES (4, 'order:add:*', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin');
INSERT INTO `t_role` VALUES (2, 'user_manager');
INSERT INTO `t_role` VALUES (3, 'order_manager');
INSERT INTO `t_role` VALUES (4, 'user');
INSERT INTO `t_role` VALUES (5, 'addinfo_manager');

-- ----------------------------
-- Table structure for t_role_perms
-- ----------------------------
DROP TABLE IF EXISTS `t_role_perms`;
CREATE TABLE `t_role_perms`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL,
  `permis_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_perms
-- ----------------------------
INSERT INTO `t_role_perms` VALUES (1, 1, 1);
INSERT INTO `t_role_perms` VALUES (2, 1, 2);
INSERT INTO `t_role_perms` VALUES (3, 1, 3);
INSERT INTO `t_role_perms` VALUES (4, 1, 4);
INSERT INTO `t_role_perms` VALUES (5, 2, 1);
INSERT INTO `t_role_perms` VALUES (6, 3, 4);
INSERT INTO `t_role_perms` VALUES (7, 4, 3);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `passWord` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'cef60ad5db0832aa28619e610c00905e', 'V3NfMi0P');
INSERT INTO `t_user` VALUES (2, 'two', 'e1443c6500b31bc7e08e9d013e095e5a', 'pY6iz99r');
INSERT INTO `t_user` VALUES (3, 'um', 'e1443c6500b31bc7e08e9d013e095e5a', 'pY6iz99r');
INSERT INTO `t_user` VALUES (4, 'om', 'e1443c6500b31bc7e08e9d013e095e5a', 'pY6iz99r');
INSERT INTO `t_user` VALUES (5, 'pm', 'e1443c6500b31bc7e08e9d013e095e5a', 'pY6iz99r');
INSERT INTO `t_user` VALUES (6, 'am', 'e1443c6500b31bc7e08e9d013e095e5a', 'pY6iz99r');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 1, 2);
INSERT INTO `t_user_role` VALUES (3, 1, 3);
INSERT INTO `t_user_role` VALUES (4, 2, 4);
INSERT INTO `t_user_role` VALUES (5, 3, 2);
INSERT INTO `t_user_role` VALUES (6, 4, 3);

SET FOREIGN_KEY_CHECKS = 1;
