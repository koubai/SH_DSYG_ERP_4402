/*
Navicat MySQL Data Transfer

Source Server         : dsyg
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : dsyg

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2015-06-14 08:48:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `etb_assets`
-- ----------------------------
DROP TABLE IF EXISTS `etb_assets`;
CREATE TABLE `etb_assets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `assetsno` varchar(32) DEFAULT NULL COMMENT '文件编号',
  `assetsname` varchar(128) DEFAULT NULL COMMENT '文件名',
  `handler` varchar(32) DEFAULT NULL COMMENT '负责人',
  `registerdate` datetime DEFAULT NULL COMMENT '登记日期',
  `purpose` varchar(64) DEFAULT NULL COMMENT '用途',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '内容介绍',
  `rank` int(2) DEFAULT NULL COMMENT '级别(0-99)',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_assets
-- ----------------------------
INSERT INTO `etb_assets` VALUES ('1', null, '3', '办公桌', '陈', '2015-04-01 00:00:00', '办公', null, '哈哈哈', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:38:12', 'admin11', '2015-05-31 23:34:46');
INSERT INTO `etb_assets` VALUES ('2', null, 'PR1500000001', '笔记本电脑', '刘', '2015-05-01 00:00:00', '销售', null, 'note', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:44:57', 'admin11', '2015-06-05 22:30:07');
INSERT INTO `etb_assets` VALUES ('3', null, '4', '笔记本', '陈', '2015-06-01 00:00:00', '办公', null, 'test', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:58:12', 'admin11', '2015-05-31 23:07:54');
INSERT INTO `etb_assets` VALUES ('4', null, 'PR444444', '凳子', '陈', '2015-06-01 00:00:00', '办公', null, '上海', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 23:22:54', null, null);

-- ----------------------------
-- Table structure for `etb_customer`
-- ----------------------------
DROP TABLE IF EXISTS `etb_customer`;
CREATE TABLE `etb_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customername` varchar(64) DEFAULT NULL COMMENT '客户名',
  `belongto` varchar(16) DEFAULT NULL COMMENT '客户所属地（以后可能分上海和深圳）',
  `note` varchar(256) DEFAULT NULL COMMENT '客户备注',
  `customertel1` varchar(32) DEFAULT NULL COMMENT '联系电话1',
  `customermanager1` varchar(32) DEFAULT NULL COMMENT '联系人1',
  `customeraddress1` varchar(128) DEFAULT NULL COMMENT '地址1',
  `customermail1` varchar(64) DEFAULT NULL COMMENT '邮箱1',
  `customerfax1` varchar(32) DEFAULT NULL COMMENT '传真1',
  `customertel2` varchar(32) DEFAULT NULL COMMENT '联系电话2',
  `customermanager2` varchar(32) DEFAULT NULL COMMENT '联系人2',
  `customeraddress2` varchar(128) DEFAULT NULL COMMENT '地址2',
  `customermail2` varchar(64) DEFAULT NULL COMMENT '邮箱2',
  `customerfax2` varchar(32) DEFAULT NULL COMMENT '传真2',
  `customertel3` varchar(32) DEFAULT NULL COMMENT '联系电话3',
  `customermanager3` varchar(32) DEFAULT NULL COMMENT '联系人3',
  `customeraddress3` varchar(128) DEFAULT NULL COMMENT '地址3',
  `customermail3` varchar(64) DEFAULT NULL COMMENT '邮箱3',
  `customerfax3` varchar(32) DEFAULT NULL COMMENT '传真3',
  `customertel4` varchar(32) DEFAULT NULL COMMENT '联系电话4',
  `customermanager4` varchar(32) DEFAULT NULL COMMENT '联系人4',
  `customeraddress4` varchar(128) DEFAULT NULL COMMENT '地址4',
  `customermail4` varchar(64) DEFAULT NULL COMMENT '邮箱4',
  `customerfax4` varchar(32) DEFAULT NULL COMMENT '传真4',
  `customertel5` varchar(32) DEFAULT NULL COMMENT '联系电话5',
  `customermanager5` varchar(32) DEFAULT NULL COMMENT '联系人5',
  `customeraddress5` varchar(128) DEFAULT NULL COMMENT '地址5',
  `customermail5` varchar(64) DEFAULT NULL COMMENT '邮箱5',
  `customerfax5` varchar(32) DEFAULT NULL COMMENT '传真5',
  `customertype` int(2) DEFAULT NULL COMMENT '供应商类型0：公司开拓，1：个人开拓',
  `handlerid` varchar(32) DEFAULT NULL COMMENT '供应商担当（个人开拓时担当ID）',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_customer
-- ----------------------------
INSERT INTO `etb_customer` VALUES ('1', 'test11', null, 'test-note111', '1123', 'test', '1123', '1123@163.com', '1123', '12', 'test2', '12', '12@163.com', '1256', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-17 20:48:10', 'admin11', '2015-05-17 20:50:21');
INSERT INTO `etb_customer` VALUES ('2', 'test2', '', 'memo', '1234', 'test2', '1234', '111@163.com', '1234', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '', '1', '', '', '', '', '', '', '', '', '', '', 'admin11', '2015-05-17 21:08:22', 'admin11', '2015-05-17 21:08:38');
INSERT INTO `etb_customer` VALUES ('3', 'test', null, '', '11', 'test', '11', '11@163.com', '11', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-10 18:14:47', null, null);
INSERT INTO `etb_customer` VALUES ('4', 'teset', null, '', 'wet', 'we', 'wet', 'wt@163.com', 'wet', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:46:36', 'admin11', '2015-05-16 09:48:59');
INSERT INTO `etb_customer` VALUES ('5', '通力hhhhhhh', null, '测试用', '13912345678', '王小姐', '上海', 'wang@163.com', '13912345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:59:21', 'admin11', '2015-05-31 22:53:52');
INSERT INTO `etb_customer` VALUES ('6', 'test', null, '', '11', 'test', '11', '11@163.com', '11', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-10 18:14:47', null, null);
INSERT INTO `etb_customer` VALUES ('7', 'teset', null, '', 'wet', 'we', 'wet', 'wt@163.com', 'wet', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:46:36', 'admin11', '2015-05-16 09:48:59');
INSERT INTO `etb_customer` VALUES ('8', '通力', null, '测试用', '13912345678', '王小姐', '上海', 'wang@163.com', '13912345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:59:21', 'admin11', '2015-05-16 09:59:43');
INSERT INTO `etb_customer` VALUES ('9', 'test', null, '', '11', 'test', '11', '11@163.com', '11', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-10 18:14:47', null, null);
INSERT INTO `etb_customer` VALUES ('10', 'teset', null, '', 'wet', 'we', 'wet', 'wt@163.com', 'wet', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:46:36', 'admin11', '2015-05-16 09:48:59');
INSERT INTO `etb_customer` VALUES ('11', '通力', null, '测试用', '13912345678', '王小姐', '上海', 'wang@163.com', '13912345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:59:21', 'admin11', '2015-05-16 09:59:43');
INSERT INTO `etb_customer` VALUES ('12', 'test', null, '', '11', 'test', '11', '11@163.com', '11', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-10 18:14:47', null, null);
INSERT INTO `etb_customer` VALUES ('13', 'teset', null, '', 'wet', 'we', 'wet', 'wt@163.com', 'wet', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:46:36', 'admin11', '2015-05-17 21:14:46');
INSERT INTO `etb_customer` VALUES ('14', '通力', null, '测试用', '13912345678', '王小姐', '上海', 'wang@163.com', '13912345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:59:21', 'admin11', '2015-05-16 09:59:43');

-- ----------------------------
-- Table structure for `etb_delivery`
-- ----------------------------
DROP TABLE IF EXISTS `etb_delivery`;
CREATE TABLE `etb_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `deliveryname` varchar(64) DEFAULT NULL COMMENT '快递名',
  `belongto` varchar(16) DEFAULT NULL COMMENT '快递所属地（以后可能分上海和深圳）',
  `note` varchar(256) DEFAULT NULL COMMENT '快递备注',
  `deliverytel1` varchar(32) DEFAULT NULL COMMENT '联系电话1',
  `deliverymanager1` varchar(32) DEFAULT NULL COMMENT '联系人1',
  `deliveryaddress1` varchar(128) DEFAULT NULL COMMENT '地址1',
  `deliverymail1` varchar(64) DEFAULT NULL COMMENT '邮箱1',
  `deliveryfax1` varchar(32) DEFAULT NULL COMMENT '传真1',
  `deliverytel2` varchar(32) DEFAULT NULL COMMENT '联系电话2',
  `deliverymanager2` varchar(32) DEFAULT NULL COMMENT '联系人2',
  `deliveryaddress2` varchar(128) DEFAULT NULL COMMENT '地址2',
  `deliverymail2` varchar(64) DEFAULT NULL COMMENT '邮箱2',
  `deliveryfax2` varchar(32) DEFAULT NULL COMMENT '传真2',
  `deliverytel3` varchar(32) DEFAULT NULL COMMENT '联系电话3',
  `deliverymanager3` varchar(32) DEFAULT NULL COMMENT '联系人3',
  `deliveryaddress3` varchar(128) DEFAULT NULL COMMENT '地址3',
  `deliverymail3` varchar(64) DEFAULT NULL COMMENT '邮箱3',
  `deliveryfax3` varchar(32) DEFAULT NULL COMMENT '传真3',
  `deliverytel4` varchar(32) DEFAULT NULL COMMENT '联系电话4',
  `deliverymanager4` varchar(32) DEFAULT NULL COMMENT '联系人4',
  `deliveryaddress4` varchar(128) DEFAULT NULL COMMENT '地址4',
  `deliverymail4` varchar(64) DEFAULT NULL COMMENT '邮箱4',
  `deliveryfax4` varchar(32) DEFAULT NULL COMMENT '传真4',
  `deliverytel5` varchar(32) DEFAULT NULL COMMENT '联系电话5',
  `deliverymanager5` varchar(32) DEFAULT NULL COMMENT '联系人5',
  `deliveryaddress5` varchar(128) DEFAULT NULL COMMENT '地址5',
  `deliverymail5` varchar(64) DEFAULT NULL COMMENT '邮箱5',
  `deliveryfax5` varchar(32) DEFAULT NULL COMMENT '传真5',
  `deliverytype` int(2) DEFAULT NULL COMMENT '快递类型0：公司开拓，1：个人开拓',
  `handlerid` varchar(32) DEFAULT NULL COMMENT '快递担当（个人开拓时担当ID）',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_delivery
-- ----------------------------
INSERT INTO `etb_delivery` VALUES ('1', 'test', null, '', '111', 'test', '23232', '11@163.com', '54654', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-28 23:29:24', 'admin11', '2015-05-28 23:29:53');
INSERT INTO `etb_delivery` VALUES ('2', 'test1', null, 'test', '13712345678', 'test2', '上海浦东', '11@163.com', '13412345678', '11111', 'test', '123', '111@163.com', '456', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-28 23:31:32', 'admin11', '2015-06-05 22:29:42');
INSERT INTO `etb_delivery` VALUES ('3', 'test3', null, 'note', '111111', 'test', '111', '11@sina.cn', '111', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-28 23:32:31', 'admin11', '2015-05-28 23:32:43');

-- ----------------------------
-- Table structure for `etb_document`
-- ----------------------------
DROP TABLE IF EXISTS `etb_document`;
CREATE TABLE `etb_document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `documentno` varchar(32) DEFAULT NULL COMMENT '文件编号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `documentname` varchar(128) DEFAULT NULL COMMENT '文件名',
  `handler` varchar(32) DEFAULT NULL COMMENT '负责人',
  `registerdate` datetime DEFAULT NULL COMMENT '登记日期',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '内容介绍',
  `rank` int(2) DEFAULT NULL COMMENT '级别(0-99)',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_document
-- ----------------------------
INSERT INTO `etb_document` VALUES ('1', 'PR10000001', null, '合同正本', '小明', '2015-06-02 00:00:00', 'CP', 'note', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 20:32:30', 'admin11', '2015-06-06 20:39:02');
INSERT INTO `etb_document` VALUES ('2', 'PR2000001', null, '电子书', '陈', '2015-05-30 00:00:00', '陈', 'note', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 21:57:05', null, null);


-- ----------------------------
-- Table structure for `etb_personal`
-- ----------------------------
DROP TABLE IF EXISTS `etb_personal`;
CREATE TABLE `etb_personal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` varchar(32) DEFAULT NULL COMMENT '员工ID',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `userno` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `registdate` datetime DEFAULT NULL COMMENT '登记时间',
  `username` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `post` varchar(64) DEFAULT NULL COMMENT '岗位',
  `superior` varchar(32) DEFAULT NULL COMMENT '直接领导',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机',
  `tell` varchar(32) DEFAULT NULL COMMENT '电话',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `employeddate` datetime DEFAULT NULL COMMENT '入职时间',
  `retiredate` datetime DEFAULT NULL COMMENT '离职时间',
  `personaldesc` varchar(500) DEFAULT NULL COMMENT '档案明细',
  `note` varchar(500) DEFAULT NULL COMMENT '内容介绍',
  `rank` int(2) DEFAULT NULL COMMENT '级别(0-99)',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_personal
-- ----------------------------
INSERT INTO `etb_personal` VALUES ('1', null, null, '1', '2015-05-30 00:00:00', '1', '0', '', '', '', '', '', '2012-01-01 00:00:00', '2015-08-08 00:00:00', null, '', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 23:09:11', 'admin11', '2015-06-06 23:33:08');
INSERT INTO `etb_personal` VALUES ('2', null, null, 'YG10001', '2015-05-05 00:00:00', '丁丁一', '1', '销售员', '陈一', '13912345678', '02112345678', '浦东新区北蔡', '2013-01-01 00:00:00', '2015-06-01 00:00:00', '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 23:34:48', 'admin11', '2015-06-07 00:06:47');


-- ----------------------------
-- Table structure for `etb_supplier`
-- ----------------------------
DROP TABLE IF EXISTS `etb_supplier`;
CREATE TABLE `etb_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `suppliername` varchar(64) DEFAULT NULL COMMENT '供应商名',
  `belongto` varchar(16) DEFAULT NULL COMMENT '供应商所属地（以后可能分上海和深圳）',
  `note` varchar(256) DEFAULT NULL COMMENT '供应商备注',
  `suppliertel1` varchar(32) DEFAULT NULL COMMENT '联系电话1',
  `suppliermanager1` varchar(32) DEFAULT NULL COMMENT '联系人1',
  `supplieraddress1` varchar(128) DEFAULT NULL COMMENT '地址1',
  `suppliermail1` varchar(64) DEFAULT NULL COMMENT '邮箱1',
  `supplierfax1` varchar(32) DEFAULT NULL COMMENT '传真1',
  `suppliertel2` varchar(32) DEFAULT NULL COMMENT '联系电话2',
  `suppliermanager2` varchar(32) DEFAULT NULL COMMENT '联系人2',
  `supplieraddress2` varchar(128) DEFAULT NULL COMMENT '地址2',
  `suppliermail2` varchar(64) DEFAULT NULL COMMENT '邮箱2',
  `supplierfax2` varchar(32) DEFAULT NULL COMMENT '传真2',
  `suppliertel3` varchar(32) DEFAULT NULL COMMENT '联系电话3',
  `suppliermanager3` varchar(32) DEFAULT NULL COMMENT '联系人3',
  `supplieraddress3` varchar(128) DEFAULT NULL COMMENT '地址3',
  `suppliermail3` varchar(64) DEFAULT NULL COMMENT '邮箱3',
  `supplierfax3` varchar(32) DEFAULT NULL COMMENT '传真3',
  `suppliertel4` varchar(32) DEFAULT NULL COMMENT '联系电话4',
  `suppliermanager4` varchar(32) DEFAULT NULL COMMENT '联系人4',
  `supplieraddress4` varchar(128) DEFAULT NULL COMMENT '地址4',
  `suppliermail4` varchar(64) DEFAULT NULL COMMENT '邮箱4',
  `supplierfax4` varchar(32) DEFAULT NULL COMMENT '传真4',
  `suppliertel5` varchar(32) DEFAULT NULL COMMENT '联系电话5',
  `suppliermanager5` varchar(32) DEFAULT NULL COMMENT '联系人5',
  `supplieraddress5` varchar(128) DEFAULT NULL COMMENT '地址5',
  `suppliermail5` varchar(64) DEFAULT NULL COMMENT '邮箱5',
  `supplierfax5` varchar(32) DEFAULT NULL COMMENT '传真5',
  `suppliertype` int(2) DEFAULT NULL COMMENT '供应商类型',
  `handlerid` varchar(32) DEFAULT NULL COMMENT '供应商担当',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `res01` varchar(10) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(10) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(10) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(10) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(10) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(10) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(10) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(10) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(50) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(200) DEFAULT NULL COMMENT '预备项目10',
  `createuid` varchar(32) DEFAULT NULL COMMENT '作成者',
  `createdate` datetime DEFAULT NULL COMMENT '作成时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_supplier
-- ----------------------------
INSERT INTO `etb_supplier` VALUES ('1', '供应商1', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('2', '供应商2', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', 'admin11', '2015-05-10 21:45:52');
INSERT INTO `etb_supplier` VALUES ('3', '供应商33333', '上海', '测试用~~~', '13912345678', '小刘', '浦东', 'zhao@163.com', '12345678', '1231', 'test', '4234', 'liu@163.com', '23423', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', 'admin11', '2015-05-16 10:33:17');
INSERT INTO `etb_supplier` VALUES ('4', '供应商4', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('5', '供应商5', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('6', '供应商6', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('7', '供应商7', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('8', '供应商8', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('9', '供应商9', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('10', '供应商10', '上海', '测试用test', '13912345678', '玛莎', '浦东', 'liu@163.com', '12345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', 'admin11', '2015-05-10 21:45:15');
INSERT INTO `etb_supplier` VALUES ('11', '供应商11', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('12', '供应商12', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('13', '供应商13', '上海', '测试用', '13912345678', '小刘', '浦东', 'liu@163.com', '12345678', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin', '2015-05-09 23:03:00', null, null);
INSERT INTO `etb_supplier` VALUES ('14', 'test', null, '', '11', 'test', '11', '11@163.com', '11', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-10 18:14:47', null, null);
INSERT INTO `etb_supplier` VALUES ('16', 'teset', null, '', 'wet', 'we', 'wet', 'wt@163.com', 'wet', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:46:36', 'admin11', '2015-05-16 09:48:59');
INSERT INTO `etb_supplier` VALUES ('17', '通力', null, '测试用', '13912345678', '王小姐', '上海', 'wang@163.com', '13912345678', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', null, '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-16 09:59:21', 'admin11', '2015-05-16 09:59:43');


