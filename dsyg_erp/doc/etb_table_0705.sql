
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
  `registerdate` date DEFAULT NULL COMMENT '登记日期',
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_assets
-- ----------------------------
INSERT INTO `etb_assets` VALUES ('1', null, '3', '办公桌', '陈', '2015-04-01', '办公', null, '哈哈哈', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:38:12', 'admin11', '2015-05-31 23:34:46');
INSERT INTO `etb_assets` VALUES ('2', null, 'PR1500000001', '笔记本电脑', '刘', '2015-05-01', '销售', null, 'memo', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:44:57', 'admin11', '2015-07-05 17:15:35');
INSERT INTO `etb_assets` VALUES ('3', null, '4', '笔记本', '陈yi', '2015-06-01', '办公', null, 'test', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 22:58:12', 'admin11', '2015-07-05 18:00:53');
INSERT INTO `etb_assets` VALUES ('4', null, 'PR444444', '凳子', '陈', '2015-07-05', '办公', null, '深圳', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-05-31 23:22:54', 'admin11', '2015-07-05 18:04:51');
INSERT INTO `etb_assets` VALUES ('5', null, 'PR100002', 'test', 'kk', '2015-07-05', 'test', null, 'note', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 18:00:15', 'admin11', '2015-07-05 18:00:31');


-- ----------------------------
-- Table structure for `etb_personal`
-- ----------------------------
DROP TABLE IF EXISTS `etb_personal`;
CREATE TABLE `etb_personal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` varchar(32) DEFAULT NULL COMMENT '员工ID',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `userno` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `registdate` date DEFAULT NULL COMMENT '登记时间',
  `username` varchar(32) DEFAULT NULL COMMENT '员工姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL,
  `post` varchar(64) DEFAULT NULL COMMENT '岗位',
  `superior` varchar(32) DEFAULT NULL COMMENT '直接领导',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机',
  `tell` varchar(32) DEFAULT NULL COMMENT '电话',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `employeddate` date DEFAULT NULL COMMENT '入职时间',
  `retiredate` date DEFAULT NULL COMMENT '离职时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of etb_personal
-- ----------------------------
INSERT INTO `etb_personal` VALUES ('1', null, null, '1', '2015-05-30', '1', '0', null, '', '', '', '', '', '2012-01-01', '2015-08-08', null, '', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 23:09:11', 'admin11', '2015-06-06 23:33:08');
INSERT INTO `etb_personal` VALUES ('2', null, null, 'YG10001', '2015-07-05', '丁丁一', '1', '2000-11-01', '销售员', '陈一', '13912345678', '02112345678', '浦东新区北蔡', '2013-01-02', '2015-06-30', '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 23:34:48', 'admin11', '2015-07-05 13:46:44');
INSERT INTO `etb_personal` VALUES ('3', null, null, 'YG10002', '2015-07-05', 'CWT', '1', null, 'CP', '', '13912345678', '', '', '2010-07-05', null, '', '', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 10:46:09', 'admin11', '2015-07-05 11:14:34');
INSERT INTO `etb_personal` VALUES ('4', null, null, 'YG10003', '2015-07-05', 'TEST', '0', null, '', '', '', '', '', '2014-07-05', null, '', '', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 11:17:24', null, null);
INSERT INTO `etb_personal` VALUES ('5', null, null, 'E', '2015-07-07', 'E', '0', null, '', '', '', '', '', '2013-07-07', null, '', '', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 11:18:41', 'admin11', '2015-07-05 15:04:07');
INSERT INTO `etb_personal` VALUES ('6', null, null, 'rrr', '2015-07-04', 'YG10002', '0', '1985-11-21', '', '', '', '', '', '2012-07-04', null, '', 'memottt', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 11:19:41', 'admin11', '2015-07-05 15:03:53');
INSERT INTO `etb_personal` VALUES ('7', null, null, 'YG10006', '2015-07-06', 'rr', '0', null, '', '', '', '', '', '2015-07-07', null, '', '', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 14:49:17', 'admin11', '2015-07-05 15:06:09');
INSERT INTO `etb_personal` VALUES ('8', null, null, 'YG10007', '2015-07-05', 'test', '0', null, '', '', '', '', '', '1997-07-02', null, '', '', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 15:05:23', 'admin11', '2015-07-05 15:05:42');


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
  `registerdate` date DEFAULT NULL COMMENT '登记日期',
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
-- Records of etb_document
-- ----------------------------
INSERT INTO `etb_document` VALUES ('1', 'PR10000001', null, '合同正本', '小明', '2015-07-01', 'CP', 'memo', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 20:32:30', 'admin11', '2015-07-05 18:01:52');
INSERT INTO `etb_document` VALUES ('2', 'PR2000001', null, '电子书', '陈', '2015-05-30', '陈', 'note', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-06-06 21:57:05', null, null);
INSERT INTO `etb_document` VALUES ('3', 'PR100002', null, 'test', 'KK', '2015-07-05', 'kkkk', 'memo', '0', '0', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 17:41:01', 'admin11', '2015-07-05 17:54:58');
INSERT INTO `etb_document` VALUES ('4', 'PR100003', null, 'test3', 'KK', '2015-07-04', 'ttttt', 'hello world', '0', '1', null, null, null, null, null, null, null, null, null, null, 'admin11', '2015-07-05 17:54:25', 'admin11', '2015-07-05 17:54:38');
