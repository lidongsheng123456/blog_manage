/*
 Navicat Premium Data Transfer

 Source Server         : lidong
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 06/02/2025 21:51:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_account
-- ----------------------------
DROP TABLE IF EXISTS `blog_account`;
CREATE TABLE `blog_account`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_account
-- ----------------------------
INSERT INTO `blog_account` VALUES (11, 'admin', '123456', '李东升', 'http://localhost:8088/common/files/1737020774027-logo.jpg', '管理员', '19976898057', '208550738@qq.com', '2024-11-23 23:49:55', '2025-01-16 17:46:15');
INSERT INTO `blog_account` VALUES (24, '代d', '123456', 'dhs', 'http://localhost:8088/common/files/1737020784011-微信图片a.jpg', '员工', '1111', '2222.com', '2024-11-28 15:38:13', '2025-01-16 17:46:27');
INSERT INTO `blog_account` VALUES (31, 'lidongshen', '123', 'lidongshen', 'http://localhost:8088/common/files/1737020791427-微信图片e.jpg', '管理员', '1', '1', '2024-12-03 11:16:21', '2025-01-16 17:46:38');
INSERT INTO `blog_account` VALUES (32, 'lidongshe', '123', 'lidongsheng', 'http://localhost:8088/common/files/1737020818249-微信图片_20250105205358.jpg', '管理员', '44', '55', '2024-12-03 11:18:23', '2025-01-16 17:47:03');
INSERT INTO `blog_account` VALUES (33, 'lidongshenge', '123', 'lidongshenger', 'http://localhost:8088/common/files/1737020808812-微信图片c.jpg', '管理员', '44', '455', '2024-12-03 11:20:06', '2025-01-18 15:59:09');
INSERT INTO `blog_account` VALUES (34, 'use', '123456', 'lds123', 'http://localhost:8088/common/files/1737020802773-微信图片f.jpg', '员工', '19976898057', '208550738@qq.com', '2024-12-26 16:57:16', '2025-01-16 17:46:45');

-- ----------------------------
-- Table structure for blog_docs
-- ----------------------------
DROP TABLE IF EXISTS `blog_docs`;
CREATE TABLE `blog_docs`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_id` int NOT NULL COMMENT '操作人id',
  `docs_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文档标题',
  `docs_content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文档内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_docs
-- ----------------------------
INSERT INTO `blog_docs` VALUES (4, 11, 'javaweb', 'Java 编程语言的核心类库，涵盖了从基本的数据类型、集合框架、输入输出流到多线程、网络编程', '2024-11-22 17:03:00', '2024-11-22 17:03:02');
INSERT INTO `blog_docs` VALUES (14, 11, 'java', 'java', '2024-11-23 18:17:44', '2024-11-24 18:08:52');
INSERT INTO `blog_docs` VALUES (37, 11, 'dd', 'dd', '2024-12-01 17:32:03', '2024-12-01 17:32:03');
INSERT INTO `blog_docs` VALUES (43, 11, 'dada', 'da', '2024-12-01 18:57:17', '2024-12-01 18:57:17');
INSERT INTO `blog_docs` VALUES (44, 11, 'ds', 'ds', '2024-12-02 08:21:11', '2024-12-02 08:21:11');
INSERT INTO `blog_docs` VALUES (45, 11, 'dsd', 'ds', '2024-12-02 08:22:20', '2024-12-02 08:22:20');
INSERT INTO `blog_docs` VALUES (46, 11, 'sD', 'ds', '2024-12-02 08:22:24', '2024-12-02 08:22:24');
INSERT INTO `blog_docs` VALUES (47, 11, 'ddddd', 'dddd', '2024-12-02 09:45:35', '2024-12-02 09:45:35');
INSERT INTO `blog_docs` VALUES (48, 11, 'dadada', 'dada', '2024-12-02 09:45:39', '2024-12-02 09:45:39');
INSERT INTO `blog_docs` VALUES (49, 11, 'd', 'd', '2024-12-03 08:30:33', '2024-12-03 08:30:33');
INSERT INTO `blog_docs` VALUES (50, 11, 'dwa', 'dwa', '2024-12-03 08:30:37', '2024-12-03 08:30:37');
INSERT INTO `blog_docs` VALUES (51, 11, 'ddd', 'dd', '2024-12-03 08:31:11', '2024-12-03 08:31:11');
INSERT INTO `blog_docs` VALUES (52, 11, 'da', 'daw', '2024-12-03 08:31:16', '2024-12-03 08:31:16');

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `operate_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人id',
  `operate_time` datetime NULL DEFAULT NULL COMMENT '开始操作时间',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作类名',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '形参接收的参数',
  `return_value` varchar(9999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '响应的数据',
  `cost_time` bigint NULL DEFAULT NULL COMMENT '总耗时（毫秒）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES (3, '11', '2024-11-27 15:46:30', 'com.zjjhy.controller.admin.AdminSystemUserController', 'validateUser', '[UserDto{id = null, username = , pwd = null, name = null, imgUrl = null, role = null, phone = null, email = null, createTime = null, updateTime = null}]', '{\"code\":\"4001\",\"msg\":\"参数缺失\"}', 0);
INSERT INTO `operate_log` VALUES (4, '11', '2024-11-27 15:46:36', 'com.zjjhy.controller.admin.AdminSystemUserController', 'validateUser', '[UserDto{id = null, username = , pwd = null, name = null, imgUrl = null, role = null, phone = null, email = null, createTime = null, updateTime = null}]', '{\"code\":\"4001\",\"msg\":\"参数缺失\"}', 0);
INSERT INTO `operate_log` VALUES (6, '11', '2024-11-27 15:48:25', 'com.zjjhy.controller.admin.AdminSystemUserController', 'validateUser', '[UserDto{id = null, username = d, pwd = null, name = null, imgUrl = null, role = null, phone = null, email = null, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 1);
INSERT INTO `operate_log` VALUES (7, '11', '2024-11-27 15:52:25', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'validateDocs', '[DocsDto{id = null, accountId = null, docsTitle = jjjjjjjjjjjjjjj, docsContent = null, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"data\":\"SUCCESS\",\"msg\":\"成功\"}', 3);
INSERT INTO `operate_log` VALUES (8, '11', '2024-11-27 15:52:27', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'validateDocs', '[DocsDto{id = null, accountId = null, docsTitle = jjjjjjjjjjjjjjj, docsContent = null, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"data\":\"SUCCESS\",\"msg\":\"成功\"}', 2);
INSERT INTO `operate_log` VALUES (9, '11', '2024-11-27 15:52:27', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = jjjjjjjjjjjjjjj, docsContent = kkkkkkkkkkkkk, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 9);
INSERT INTO `operate_log` VALUES (10, '11', '2024-11-27 16:54:03', 'com.zjjhy.controller.user.LoginController', 'login', '[UserDto{id = null, username = lidong, pwd = 123, name = null, imgUrl = null, role = null, phone = null, email = null, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"data\":{\"createTime\":\"2024-11-23T23:49:55\",\"email\":\"208550738@qq.com\",\"id\":11,\"imgUrl\":\"http://localhost:8088/files/1732586240670-logo.jpg\",\"name\":\"李东升\",\"phone\":\"19976898057\",\"pwd\":\"123\",\"role\":\"管理员\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMS3nrqHnkIblkZgiLCJleHAiOjE3MzI3MDQ4NDJ9.HPR96WuRKcLcon4UqsYNR9OP3w2j8syzalz840vmRs0\",\"updateTime\":\"2024-11-26T09:57:22\",\"username\":\"lidong\"},\"msg\":\"成功\"}', 18);
INSERT INTO `operate_log` VALUES (11, '11', '2024-11-27 17:03:37', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = ffff, docsContent = fff, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 21);
INSERT INTO `operate_log` VALUES (12, '21', '2024-11-27 17:13:29', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'deleteDocs', '[[22]]', '{\"code\":\"200\",\"msg\":\"200\"}', 22);
INSERT INTO `operate_log` VALUES (13, '21', '2024-11-27 17:14:16', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'deleteDocs', '[[23]]', '{\"code\":\"200\",\"msg\":\"200\"}', 6);
INSERT INTO `operate_log` VALUES (14, '21', '2024-11-27 17:15:26', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 21, docsTitle = dd, docsContent = ddd, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 15);
INSERT INTO `operate_log` VALUES (15, '21', '2024-11-27 17:32:24', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'deleteDocs', '[[21]]', '1', 291);
INSERT INTO `operate_log` VALUES (16, '21', '2024-11-27 17:33:24', 'jdk.proxy2.$Proxy77', 'deleteDocs', '[[20]]', '1', 311);
INSERT INTO `operate_log` VALUES (17, '21', '2024-11-27 17:35:05', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 21, docsTitle = ddd, docsContent = dd, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 19050);
INSERT INTO `operate_log` VALUES (18, '21', '2024-11-27 17:36:42', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 21, docsTitle = jj, docsContent = jj, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 2262);
INSERT INTO `operate_log` VALUES (19, '21', '2024-11-27 17:39:43', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'updateDocs', '[DocsDto{id = 26, accountId = null, docsTitle = jjj, docsContent = jja, createTime = 2024-11-27T17:36:44, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 11);
INSERT INTO `operate_log` VALUES (20, '11', '2024-11-27 19:35:41', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = ds, docsContent = ds, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 9);
INSERT INTO `operate_log` VALUES (21, '11', '2024-11-27 19:36:02', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = gd, docsContent = gd, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 1123466);
INSERT INTO `operate_log` VALUES (22, '11', '2024-11-28 09:43:17', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'deleteDocs', '[[26]]', '{\"code\":\"200\",\"msg\":\"200\"}', 14);
INSERT INTO `operate_log` VALUES (23, '11', '2024-11-28 09:47:11', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'deleteDocs', '[[25]]', '{\"code\":\"200\",\"msg\":\"200\"}', 10);
INSERT INTO `operate_log` VALUES (24, '11', '2024-11-28 15:34:56', 'com.zjjhy.controller.admin.AdminSystemUserController', 'deleteDocs', '[[18]]', '{\"code\":\"200\",\"msg\":\"200\"}', 18);
INSERT INTO `operate_log` VALUES (25, '11', '2024-11-28 15:36:49', 'com.zjjhy.controller.admin.AdminSystemUserController', 'personCenter', '[UserDto{id = 11, username = lidong, pwd = 123, name = 李东升, imgUrl = http://localhost:8088/files/1732779408230-logo.jpg, role = 管理员, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-11-23T23:49:55, updateTime = 2024-11-26T09:57:22}]', '{\"code\":\"200\",\"msg\":\"200\"}', 7);
INSERT INTO `operate_log` VALUES (26, '11', '2024-11-28 15:37:00', 'com.zjjhy.controller.admin.AdminSystemUserController', 'updateDocs', '[UserDto{id = 19, username = ssjj, pwd = null, name = 苏世杰, imgUrl = http://localhost:8088/files/1732779415261-微信图片c.jpg, role = 员工, phone = 111, email = 222.com, createTime = 2024-11-24T18:11:14, updateTime = 2024-11-25T17:31:40}]', '{\"code\":\"200\",\"msg\":\"200\"}', 6);
INSERT INTO `operate_log` VALUES (27, '11', '2024-11-28 15:37:09', 'com.zjjhy.controller.admin.AdminSystemUserController', 'updateDocs', '[UserDto{id = 21, username = 史晨翔x, pwd = null, name = 史晨翔, imgUrl = http://localhost:8088/files/1732779426944-微信图片e.jpg, role = 管理员, phone = 111111111, email = 44444444444.com, createTime = 2024-11-26T10:48:42, updateTime = 2024-11-26T17:42:46}]', '{\"code\":\"200\",\"msg\":\"200\"}', 17);
INSERT INTO `operate_log` VALUES (28, '11', '2024-11-28 15:37:20', 'com.zjjhy.controller.admin.AdminSystemUserController', 'deleteDocs', '[[21]]', '{\"code\":\"200\",\"msg\":\"200\"}', 13);
INSERT INTO `operate_log` VALUES (29, '11', '2024-11-28 15:37:44', 'com.zjjhy.controller.admin.AdminSystemUserController', 'addDocs', '[UserDto{id = null, username = lhj, pwd = null, name = kl, imgUrl = http://localhost:8088/files/1732779454183-微信图片b.jpg, role = 经理, phone = 23, email = 23, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 17);
INSERT INTO `operate_log` VALUES (30, '11', '2024-11-28 15:37:52', 'com.zjjhy.controller.admin.AdminSystemUserController', 'deleteDocs', '[[19, 23]]', '{\"code\":\"200\",\"msg\":\"200\"}', 9);
INSERT INTO `operate_log` VALUES (31, '11', '2024-11-28 15:38:13', 'com.zjjhy.controller.admin.AdminSystemUserController', 'addDocs', '[UserDto{id = null, username = dhs, pwd = null, name = dhs, imgUrl = http://localhost:8088/files/1732779483911-微信图片a.jpg, role = 员工, phone = 1111, email = 2222.com, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"200\"}', 6);
INSERT INTO `operate_log` VALUES (32, '11', '2024-12-01 12:45:03', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = d, docsContent = d, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"success\"}', 11);
INSERT INTO `operate_log` VALUES (33, '11', '2024-12-01 12:45:09', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'updateDocs', '[DocsDto{id = 29, accountId = null, docsTitle = ddd, docsContent = d, createTime = 2024-12-01T12:45:03, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"success\"}', 6);
INSERT INTO `operate_log` VALUES (34, '11', '2024-12-01 12:45:13', 'com.zjjhy.controller.admin.AdminSystemHomeController', 'deleteDocs', '[[29]]', '{\"code\":\"200\",\"msg\":\"success\"}', 8);
INSERT INTO `operate_log` VALUES (35, '11', '2024-12-01 13:05:23', 'com.zjjhy.controller.admin.AdminSystemUserController', 'updatePwd', '[PwdDto{id = 11, confirmPwd = 123456, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"success\"}', 17);
INSERT INTO `operate_log` VALUES (36, '11', '2024-12-01 13:05:58', 'com.zjjhy.controller.admin.AdminSystemUserController', 'addDocs', '[UserDto{id = null, username = chenhong, pwd = null, name = 陈鸿, imgUrl = http://localhost:8088/files/1733029545410-微信图片e.jpg, role = 员工, phone = 111, email = 222, createTime = null, updateTime = null}]', '{\"code\":\"200\",\"msg\":\"success\"}', 7);
INSERT INTO `operate_log` VALUES (37, '11', '2024-12-01 13:06:15', 'com.zjjhy.controller.admin.AdminSystemUserController', 'updateDocs', '[UserDto{id = 25, username = chenhongg, pwd = null, name = 陈鸿, imgUrl = http://localhost:8088/files/1733029545410-微信图片e.jpg, role = 员工, phone = 111, email = 222, createTime = 2024-12-01T13:05:58, updateTime = 2024-12-01T13:05:58}]', '{\"code\":\"200\",\"msg\":\"success\"}', 17);
INSERT INTO `operate_log` VALUES (38, '11', '2024-12-01 13:06:29', 'com.zjjhy.controller.admin.AdminSystemUserController', 'deleteDocs', '[[25]]', '{\"code\":\"200\",\"msg\":\"success\"}', 18);
INSERT INTO `operate_log` VALUES (39, '11', '2024-12-01 13:09:44', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dwa, docsContent = dwa, createTime = 2024-12-01T13:09:44.108186200, updateTime = 2024-12-01T13:09:44.108186200}]', 'null', 9);
INSERT INTO `operate_log` VALUES (40, '11', '2024-12-01 13:41:33', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'addUser', '[UserDto{id = null, username = chenhong, pwd = null, name = 陈鸿, imgUrl = http://localhost:8088/files/1733031680272-微信图片e.jpg, role = 管理员, phone = 1111, email = 2222, createTime = 2024-12-01T13:41:32.929035300, updateTime = 2024-12-01T13:41:32.929035300}]', 'null', 17);
INSERT INTO `operate_log` VALUES (41, '26', '2024-12-01 14:37:04', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 26, docsTitle = gggg, docsContent = ggggg, createTime = 2024-12-01T14:37:04.452835900, updateTime = 2024-12-01T14:37:04.452835900}]', 'null', 21);
INSERT INTO `operate_log` VALUES (42, '26', '2024-12-01 14:37:11', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 26, docsTitle = jjjjjj, docsContent = jjjj, createTime = 2024-12-01T14:37:11.484086700, updateTime = 2024-12-01T14:37:11.484086700}]', 'null', 7);
INSERT INTO `operate_log` VALUES (43, '11', '2024-12-01 14:37:39', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'deleteUser', '[[26]]', 'null', 5);
INSERT INTO `operate_log` VALUES (44, '11', '2024-12-01 14:38:14', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'deleteDocs', '[[24, 27, 28]]', 'null', 19);
INSERT INTO `operate_log` VALUES (45, '11', '2024-12-01 14:38:18', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'deleteDocs', '[[30, 31]]', 'null', 17);
INSERT INTO `operate_log` VALUES (46, '11', '2024-12-01 14:39:02', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'addUser', '[UserDto{id = null, username = ch, pwd = null, name = ch, imgUrl = http://localhost:8088/files/1733035137258-微信图片e.jpg, role = 管理员, phone = 1, email = 1, createTime = 2024-12-01T14:39:02.238709200, updateTime = 2024-12-01T14:39:02.238709200}]', 'null', 7);
INSERT INTO `operate_log` VALUES (47, '27', '2024-12-01 14:39:14', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 27, docsTitle = ddd, docsContent = ddd, createTime = 2024-12-01T14:39:14.458660600, updateTime = 2024-12-01T14:39:14.458660600}]', 'null', 21);
INSERT INTO `operate_log` VALUES (48, '27', '2024-12-01 14:39:17', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 27, docsTitle = ggg, docsContent = ggg, createTime = 2024-12-01T14:39:17.207905100, updateTime = 2024-12-01T14:39:17.207905100}]', 'null', 17);
INSERT INTO `operate_log` VALUES (49, '11', '2024-12-01 14:39:32', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'deleteUser', '[[27]]', 'null', 3);
INSERT INTO `operate_log` VALUES (50, '11', '2024-12-01 17:28:08', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = meven, docsContent = gyuiyu, createTime = 2024-12-01T17:28:08.276025200, updateTime = 2024-12-01T17:28:08.276025200}]', 'null', 11);
INSERT INTO `operate_log` VALUES (51, '11', '2024-12-01 17:32:03', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dd, docsContent = dd, createTime = 2024-12-01T17:32:03.216158400, updateTime = 2024-12-01T17:32:03.216158400}]', 'null', 16);
INSERT INTO `operate_log` VALUES (52, '11', '2024-12-01 17:32:07', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = ddd, docsContent = ddddd, createTime = 2024-12-01T17:32:06.838940200, updateTime = 2024-12-01T17:32:06.838940200}]', 'null', 16);
INSERT INTO `operate_log` VALUES (53, '11', '2024-12-01 17:32:10', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dawda, docsContent = daw, createTime = 2024-12-01T17:32:09.677343300, updateTime = 2024-12-01T17:32:09.677343300}]', 'null', 7);
INSERT INTO `operate_log` VALUES (54, '11', '2024-12-01 17:32:13', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = daw, docsContent = daw, createTime = 2024-12-01T17:32:12.848092900, updateTime = 2024-12-01T17:32:12.848092900}]', 'null', 7);
INSERT INTO `operate_log` VALUES (55, '11', '2024-12-01 18:50:41', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = d, docsContent = d, createTime = 2024-12-01T18:50:41.338185500, updateTime = 2024-12-01T18:50:41.338185500}]', 'null', 237);
INSERT INTO `operate_log` VALUES (56, '11', '2024-12-01 18:53:31', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = javaWeber, docsContent = web, createTime = 2024-12-01T18:53:30.546396, updateTime = 2024-12-01T18:53:30.546396}]', 'null', 17);
INSERT INTO `operate_log` VALUES (57, '11', '2024-12-01 18:53:45', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'deleteDocs', '[[42, 41, 39, 40]]', 'null', 18);
INSERT INTO `operate_log` VALUES (58, '11', '2024-12-01 18:54:04', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'updateDocs', '[DocsDto{id = 38, accountId = null, docsTitle = javaweber, docsContent = web, createTime = 2024-12-01T17:32:07, updateTime = 2024-12-01T18:54:04.252647300}]', 'null', 8);
INSERT INTO `operate_log` VALUES (59, '11', '2024-12-01 18:54:27', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'addUser', '[UserDto{id = null, username = lhj, pwd = null, name = 路环节, imgUrl = http://localhost:8088/files/1733050453572-微信图片b.jpg, role = 员工, phone = 1111, email = 222.com, createTime = 2024-12-01T18:54:27.170956600, updateTime = 2024-12-01T18:54:27.170956600}]', 'null', 18);
INSERT INTO `operate_log` VALUES (60, '11', '2024-12-01 18:55:44', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'deleteUser', '[[28]]', 'null', 1);
INSERT INTO `operate_log` VALUES (61, '11', '2024-12-01 18:55:55', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 24, username = 代, pwd = null, name = dhs, imgUrl = http://localhost:8088/files/1732779483911-微信图片a.jpg, role = 员工, phone = 1111, email = 2222.com, createTime = 2024-11-28T15:38:13, updateTime = 2024-12-01T18:55:54.670950900}]', 'null', 16);
INSERT INTO `operate_log` VALUES (62, '11', '2024-12-01 18:56:03', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'updatePwd', '[PwdDto{id = 11, confirmPwd = 123, updateTime = 2024-12-01T18:56:02.567825600}]', 'null', 7);
INSERT INTO `operate_log` VALUES (63, '11', '2024-12-01 18:57:17', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dada, docsContent = da, createTime = 2024-12-01T18:57:16.789884700, updateTime = 2024-12-01T18:57:16.789884700}]', 'null', 20);
INSERT INTO `operate_log` VALUES (64, '29', '2024-12-01 19:01:14', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'personCenter', '[UserDto{id = 29, username = shichnegxiang, pwd = 12345, name = shichnegxiang, imgUrl = http://localhost:8088/files/1733050719494-微信图片d.jpg, role = 管理员, phone = 222, email = 222.com, createTime = 2024-12-01T18:58:20, updateTime = 2024-12-01T19:01:13.684127400}]', 'null', 27);
INSERT INTO `operate_log` VALUES (65, '29', '2024-12-01 19:02:09', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'personCenter', '[UserDto{id = 29, username = shichnegxiang, pwd = 12345, name = shichnegxiang, imgUrl = http://localhost:8088/files/1733050719494-微信图片d.jpg, role = 管理员, phone = 222, email = 222.com, createTime = 2024-12-01T18:58:20, updateTime = 2024-12-01T19:02:09.083253700}]', 'null', 4);
INSERT INTO `operate_log` VALUES (66, '29', '2024-12-01 19:02:19', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'deleteDocs', '[[36]]', 'null', 219);
INSERT INTO `operate_log` VALUES (67, '11', '2024-12-01 22:56:26', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'deleteUser', '[[29]]', 'null', 37229);
INSERT INTO `operate_log` VALUES (68, '11', '2024-12-01 23:01:04', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'addUser', '[UserDto{id = null, username = sch, pwd = null, name = dd, imgUrl = http://localhost:8088/files/1733065254929-微信图片d.jpg, role = 管理员, phone = 2, email = 2, createTime = 2024-12-01T23:01:03.979064300, updateTime = 2024-12-01T23:01:03.979064300}]', 'null', 41);
INSERT INTO `operate_log` VALUES (69, '11', '2024-12-01 23:01:14', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'deleteUser', '[[30]]', 'null', 66904);
INSERT INTO `operate_log` VALUES (70, '11', '2024-12-02 08:22:20', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dsd, docsContent = ds, createTime = 2024-12-02T08:22:20.207278700, updateTime = 2024-12-02T08:22:20.207278700}]', 'null', 42);
INSERT INTO `operate_log` VALUES (71, '11', '2024-12-02 08:22:24', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = sD, docsContent = ds, createTime = 2024-12-02T08:22:24.115780700, updateTime = 2024-12-02T08:22:24.115780700}]', 'null', 17);
INSERT INTO `operate_log` VALUES (72, '11', '2024-12-02 09:45:35', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = ddddd, docsContent = dddd, createTime = 2024-12-02T09:45:35.311714700, updateTime = 2024-12-02T09:45:35.311714700}]', 'null', 34);
INSERT INTO `operate_log` VALUES (73, '11', '2024-12-02 09:45:39', 'com.zjjhy.service.impl.AdminSystemHomeServiceImpl', 'addDocs', '[DocsDto{id = null, accountId = 11, docsTitle = dadada, docsContent = dada, createTime = 2024-12-02T09:45:38.960194700, updateTime = 2024-12-02T09:45:38.960194700}]', 'null', 9);
INSERT INTO `operate_log` VALUES (76, '34', '2024-12-26 16:59:32', 'com.zjjhy.service.impl.AdminSystemUserServiceImpl', 'personCenter', '[UserDto{id = 34, username = lds123, pwd = lds123, name = lds123, imgUrl = http://localhost:8088/files/1735203480620-logo.jpg, role = 管理员, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-12-26T16:57:16, updateTime = 2024-12-26T16:59:32.284727200}]', 'null', 7);
INSERT INTO `operate_log` VALUES (77, '11', '2025-01-09 13:09:12', 'com.example.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 34, username = userr, pwd = null, name = lds123, imgUrl = http://localhost:8088/files/1735203480620-logo.jpg, role = 员工, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-12-26T16:57:16, updateTime = 2025-01-09T13:09:12.471405200}]', 'null', 16);
INSERT INTO `operate_log` VALUES (78, '11', '2025-01-09 13:09:15', 'com.example.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 34, username = user, pwd = null, name = lds123, imgUrl = http://localhost:8088/files/1735203480620-logo.jpg, role = 员工, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-12-26T16:57:16, updateTime = 2025-01-09T13:09:15.265431900}]', 'null', 19);
INSERT INTO `operate_log` VALUES (79, '11', '2025-01-09 17:09:46', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 31, username = lidongshengf, pwd = null, name = lidongshen, imgUrl = http://localhost:8088/files/1736413781651-微信图片c.jpg, role = 管理员, phone = 1, email = 1, createTime = 2024-12-03T11:16:21, updateTime = 2025-01-09T17:09:46.195578800}]', 'null', 17);
INSERT INTO `operate_log` VALUES (80, 'null', '2025-01-16 17:44:12', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'personCenter', '[UserDto{id = 11, username = admin, pwd = 123456, name = 李东升, imgUrl = http://localhost:8088/files/1732779408230-logo.jpg, role = 管理员, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-11-23T23:49:55, updateTime = 2025-01-16T17:44:11.690523300, code = null}]', 'null', 16);
INSERT INTO `operate_log` VALUES (81, 'null', '2025-01-16 17:46:15', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'personCenter', '[UserDto{id = 11, username = admin, pwd = 123456, name = 李东升, imgUrl = http://localhost:8088/common/files/1737020774027-logo.jpg, role = 管理员, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-11-23T23:49:55, updateTime = 2025-01-16T17:46:15.451662, code = null}]', 'null', 170);
INSERT INTO `operate_log` VALUES (82, 'null', '2025-01-16 17:46:27', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 24, username = 代d, pwd = null, name = dhs, imgUrl = http://localhost:8088/common/files/1737020784011-微信图片a.jpg, role = 员工, phone = 1111, email = 2222.com, createTime = 2024-11-28T15:38:13, updateTime = 2025-01-16T17:46:27.422295900, code = null}]', 'null', 18);
INSERT INTO `operate_log` VALUES (83, 'null', '2025-01-16 17:46:38', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 31, username = lidongshen, pwd = null, name = lidongshen, imgUrl = http://localhost:8088/common/files/1737020791427-微信图片e.jpg, role = 管理员, phone = 1, email = 1, createTime = 2024-12-03T11:16:21, updateTime = 2025-01-16T17:46:37.567595600, code = null}]', 'null', 17);
INSERT INTO `operate_log` VALUES (84, 'null', '2025-01-16 17:46:45', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 34, username = use, pwd = null, name = lds123, imgUrl = http://localhost:8088/common/files/1737020802773-微信图片f.jpg, role = 员工, phone = 19976898057, email = 208550738@qq.com, createTime = 2024-12-26T16:57:16, updateTime = 2025-01-16T17:46:44.531208300, code = null}]', 'null', 7);
INSERT INTO `operate_log` VALUES (85, 'null', '2025-01-16 17:46:54', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 33, username = lidongshenge, pwd = null, name = lidongshenger, imgUrl = http://localhost:8088/common/files/1737020808812-微信图片c.jpg, role = 管理员, phone = 44, email = 455, createTime = 2024-12-03T11:20:06, updateTime = 2025-01-16T17:46:53.849576800, code = null}]', 'null', 9);
INSERT INTO `operate_log` VALUES (86, 'null', '2025-01-16 17:47:03', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 32, username = lidongshe, pwd = null, name = lidongsheng, imgUrl = http://localhost:8088/common/files/1737020818249-微信图片_20250105205358.jpg, role = 管理员, phone = 44, email = 55, createTime = 2024-12-03T11:18:23, updateTime = 2025-01-16T17:47:03.124256700, code = null}]', 'null', 11);
INSERT INTO `operate_log` VALUES (87, '11', '2025-01-18 15:56:40', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 33, username = lidongshenge, pwd = null, name = lidongshenger, imgUrl = http://localhost:8088/common/files/1737020808812-微信图片c.jpg, role = 管理员, phone = 44, email = 455, createTime = 2024-12-03T11:20:06, updateTime = 2025-01-18T15:56:39.555807300, code = null}]', 'null', 29);
INSERT INTO `operate_log` VALUES (88, '11', '2025-01-18 15:56:44', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 33, username = lidongshenge, pwd = null, name = lidongshenger, imgUrl = http://localhost:8088/common/files/1737020808812-微信图片c.jpg, role = 管理员, phone = 44, email = 455, createTime = 2024-12-03T11:20:06, updateTime = 2025-01-18T15:56:44.255963, code = null}]', 'null', 18);
INSERT INTO `operate_log` VALUES (89, '11', '2025-01-18 15:59:09', 'com.example.system.service.impl.AdminSystemUserServiceImpl', 'updateUser', '[UserDto{id = 33, username = lidongshenge, pwd = null, name = lidongshenger, imgUrl = http://localhost:8088/common/files/1737020808812-微信图片c.jpg, role = 管理员, phone = 44, email = 455, createTime = 2024-12-03T11:20:06, updateTime = 2025-01-18T15:59:08.708851800, code = null}]', 'null', 8);
INSERT INTO `operate_log` VALUES (90, 'null', '2025-02-06 21:49:27', 'com.example.system.service.impl.LoginServiceImpl', 'login', '[UserDto{id = null, username = admin, pwd = 123456, name = null, imgUrl = null, role = null, phone = null, email = null, createTime = null, updateTime = null, code = aj1ue}, org.apache.catalina.session.StandardSessionFacade@6a7df764]', '{\"createTime\":\"2024-11-23T23:49:55\",\"email\":\"208550738@qq.com\",\"id\":11,\"imgUrl\":\"http://localhost:8088/common/files/1737020774027-logo.jpg\",\"name\":\"李东升\",\"phone\":\"19976898057\",\"pwd\":\"123456\",\"role\":\"管理员\",\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJlbXBJZCI6MTEsImV4cCI6MTczODg1Njk2N30.KJdHF8wfcPATk9b1YqZvQZDQd2NjnaHYwMEeWdHjs5M\",\"updateTime\":\"2025-01-16T17:46:15\",\"username\":\"admin\"}', 277);

-- ----------------------------
-- Table structure for untitled
-- ----------------------------
DROP TABLE IF EXISTS `untitled`;
CREATE TABLE `untitled`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `operate_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人id',
  `operate_time` datetime NULL DEFAULT NULL COMMENT '开始操作时间',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作类名',
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '形参接收的参数',
  `return_value` varchar(9999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '响应的数据',
  `cost_time` bigint NULL DEFAULT NULL COMMENT '总耗时（毫秒）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of untitled
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
