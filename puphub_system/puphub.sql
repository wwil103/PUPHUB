/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : localhost:3306
 Source Schema         : puphub

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 06/12/2023 15:25:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `o_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `o_uid` int(11) NOT NULL COMMENT '用户编号',
  `o_service` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务编号',
  `o_date` date NULL DEFAULT NULL COMMENT '服务日期',
  `o_orderTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `o_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务时间段',
  `o_age` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宠物年龄',
  `o_size` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宠物大小',
  `o_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务地址',
  `o_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '联系方式',
  `o_status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 1 COMMENT '订单状态',
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (87, 0, '6', '2023-11-01', '2023-12-06 12:18:24', '1', '2', '2', '河北省-唐山市-路北区-阿松大', '1806040804912', 2);
INSERT INTO `orders` VALUES (88, 1, '1', '2023-11-29', '2023-12-06 12:18:32', '2', '3', '2', '山西省-太原市-尖草坪区-阿松大', '1806040804912', 2);
INSERT INTO `orders` VALUES (89, 1, '6', '2023-11-01', '2023-12-06 12:18:39', '1', '2', '2', '山东省-济南市-历下区-啊实打实的', '1806040804912', 1);
INSERT INTO `orders` VALUES (90, 78, '5', '2023-11-01', '2023-12-03 18:19:29', '1', '3', '2', '北京市-市辖区-朝阳区-测试', 'demo@qq.com', 1);
INSERT INTO `orders` VALUES (91, 1, '2', '2023-11-22', '2023-12-06 01:21:56', '1', '3', '2', '北京市-市辖区-东城区-奥帆基地', 'admin@qq.com', 0);
INSERT INTO `orders` VALUES (92, 1, '2', '2023-11-23', '2023-12-05 01:22:38', '2', '3', '2', '北京市-市辖区-东城区-奥帆基地', '1806040804912', 1);
INSERT INTO `orders` VALUES (93, 1, '6', '2023-12-28', '2023-12-04 12:10:23', '1', '2', '2', '山西省-太原市-小店区-奥帆基地', 'admin@qq.com', 0);
INSERT INTO `orders` VALUES (94, 1, '2', '2023-12-08', '2023-12-02 12:11:03', '1', '2', '2', '山西省-太原市-小店区-奥帆基地', '1806040804912', 0);
INSERT INTO `orders` VALUES (95, 1, '5', '2023-12-12', '2023-12-03 12:11:19', '1', '2', '2', '山西省-太原市-小店区-奥帆基地', 'admin@qq.com', 0);

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service`  (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务编号',
  `s_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `s_Intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务简介',
  `s_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务价格',
  `s_poster` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务海报',
  `s_serviceTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES (1, '上门遛狗', '工作繁忙、离家外出... PUPHUB认证宠物保姆让您的爱宠开心快乐每一天。高效便捷的办法让您的狗狗活跃开心...', '50', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/57301dcd30264631812f84b52b95e70e.png', '2023-11-30 01:31:21');
INSERT INTO `service` VALUES (2, '上门铲屎', '添食加水，陪玩互动等，家里迎来新的宠物或没有时间照看？预定一个快速上门服务的宠物保姆，确保万事大吉。', '80', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/6c172dcf106141fab4617b2214f2cfc5.png', '2023-12-02 01:31:21');
INSERT INTO `service` VALUES (3, '狗狗寄养', '狗狗生活在保姆家无笼、舒适的家庭环境中。您可以自由为爱宠选择专属寄养或群体寄养。动物呆在无笼式且舒适的宠物保姆家。', '150', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/760c2c9450cd48d989126b2d6b3fdda5.png', '2023-11-30 01:31:21');
INSERT INTO `service` VALUES (4, '住家照料', 'PUPHUB宠物保姆上门住家陪伴您的爱宠。免除爱宠紧张不适，保持自家日常生活习惯和规律。宠物得以休息、安适、放松。', '200', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/51713820889e4719afc084ecd8149ac7.png', '2023-12-05 01:31:21');
INSERT INTO `service` VALUES (5, '陪诊就医', 'PUPHUB宠物保姆带您的爱宠就医诊疗或您可以联系我们合作的上门宠物医生家庭诊疗服务。不管需要一天或一个星期时间，我们帮你安排。', '200', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/822c23372e094fb2ae7ed4139314199b.png', '2023-12-04 01:31:21');
INSERT INTO `service` VALUES (6, '狗狗训练', '国际认证宠物训犬师以及经验丰富的宠物保姆家庭式训犬、纠正爱宠的不良习惯。学习永远不会太迟。', '150', 'https://puphub.oss-cn-fuzhou.aliyuncs.com/39b16516b04549b8896468ef396caba0.jpeg', '2023-12-06 01:31:21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `u_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `u_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `u_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `u_telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `u_birth` date NULL DEFAULT NULL COMMENT '出生日期',
  `u_gender` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '性别',
  `u_class` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '权限',
  `u_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `u_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `u_userTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1234567', 'admin', '1806040804912', '2003-11-18', 2, 0, NULL, '山西省-太原市-小店区-奥帆基地', '2023-12-06 13:39:57');
INSERT INTO `user` VALUES (82, 'Ross', '123456', 'kpbpomlb@163.com', '17105446281', '2023-12-11', 1, 0, NULL, '天津市-市辖区-河西区-阿迪斯', '2023-12-01 11:44:28');
INSERT INTO `user` VALUES (83, 'Suzette', '123456', 'f2en9x@163.com', '14758887276', '1976-02-05', 1, 0, NULL, '山西省-大同市-平城区-打撒撒旦', '2023-12-01 11:46:24');
INSERT INTO `user` VALUES (84, 'Mavraganis', '123456', 'j6scxyamm@163.com', '18600937670', '2023-12-07', 1, 0, NULL, '新疆维吾尔自治区-乌鲁木齐市-沙依巴克区-擦撒撒旦', '2023-12-02 11:47:43');
INSERT INTO `user` VALUES (85, 'Rensen', '123456', 'ws094@163.com', '18289200281', '2020-06-04', 2, 0, NULL, '江西省-景德镇市-昌江区-阿达', '2023-12-02 11:48:28');
INSERT INTO `user` VALUES (86, 'Linamen', '123456', 'b5pg42bsb@163.com', '17218182232', '2023-12-29', 2, 0, NULL, '河北省-唐山市-路北区-法按时', '2023-12-02 11:49:07');
INSERT INTO `user` VALUES (87, 'Siniard', '123456', 'gkux@163.com', '18751601662', '2023-12-04', 2, 0, NULL, '重庆市-县-丰都县-阿迪斯', '2023-12-03 11:50:53');
INSERT INTO `user` VALUES (88, 'Edison', '123456', 'ssypvmiuu@163.com', '13049462281', '2021-06-11', 1, 0, NULL, '西藏自治区-日喀则市-南木林县-阿松大', '2023-12-04 11:51:35');
INSERT INTO `user` VALUES (89, 'Ogasawara', '123456', 'alk@163.com', '15621016752', '2025-07-01', 1, 0, NULL, '陕西省-铜川市-印台区-阿达', '2023-12-05 11:53:05');
INSERT INTO `user` VALUES (90, 'Yonek', '123456', 'gvvid2@163.com', '17199739587', '2020-06-12', 0, 0, NULL, '湖南省-株洲市-荷塘区-啊方法', '2023-12-06 11:53:58');
INSERT INTO `user` VALUES (91, 'Finchman', '123456', 'vk9no2pp@163.com', '15670076956', '2023-11-07', 0, 0, NULL, '西藏自治区-日喀则市-桑珠孜区-飒飒的', '2023-12-06 12:07:20');

SET FOREIGN_KEY_CHECKS = 1;
