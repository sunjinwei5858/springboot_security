SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`  (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
                                     `gmt_create` datetime(0) DEFAULT now() COMMENT '创建时间',
                                     `gmt_modified` datetime(0) DEFAULT now() COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-权限资源表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES (1, '/admin',  now(), now());
INSERT INTO `t_sys_permission` VALUES (2, '/test', now(), now());
INSERT INTO `t_sys_permission` VALUES (3, '/', now(), now());

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
                               `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
                               `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
                               `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                               `gmt_modified` datetime(0) DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-角色表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', '系统管理员', '系统管理员', now(), now());
INSERT INTO `t_sys_role` VALUES (2, 'visitor', '访客', '访客', now(), now());

-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission`  (
                                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
                                          `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
                                          `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                                          `gmt_modified` datetime(0) DEFAULT NULL COMMENT '更新时间',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 角色-权限资源关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------
INSERT INTO `t_sys_role_permission` VALUES (1, 1, 11, now(), now());
INSERT INTO `t_sys_role_permission` VALUES (2, 1, 2, now(), now());
INSERT INTO `t_sys_role_permission` VALUES (3, 1, 3, now(), now());
INSERT INTO `t_sys_role_permission` VALUES (4, 2, 1, now(), now());
INSERT INTO `t_sys_role_permission` VALUES (5, 3, 1, now(), now());

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
                               `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录密码',
                               `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
                               `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
                               `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'token',
                               `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                               `gmt_modified` datetime(0) DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', '97ba1ef7f148b2aec1c61303a7d88d0967825495', 'tom', 'tom', '0d717eed80140b7840ed5b4a7c35bbda5a03fcd5', now(), now());
INSERT INTO `t_sys_user` VALUES (2, 'test', '97ba1ef7f148b2aec1c61303a7d88d0967825495', 'jack', 'jack', 'a4bf084f250aebc8f0bc806bdf9bca205c7706c9', now(), now());

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
                                    `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
                                    `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                                    `gmt_modified` datetime(0) DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 用户角色关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (1, 1, 1, now(), now());
INSERT INTO `t_sys_user_role` VALUES (2, 1, 2, now(), now());
INSERT INTO `t_sys_user_role` VALUES (3, 2, 2, now(), now());

SET FOREIGN_KEY_CHECKS = 1;
