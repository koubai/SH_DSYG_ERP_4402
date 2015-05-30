/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.51-community : Database - dsyg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dsyg` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dsyg`;

/*Table structure for table `etb_assets` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_assets` */

/*Table structure for table `etb_customer` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_customer` */

/*Table structure for table `etb_document` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_document` */

/*Table structure for table `etb_finance` */

DROP TABLE IF EXISTS `etb_finance`;

CREATE TABLE `etb_finance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `financetype` int(4) DEFAULT NULL COMMENT '财务数据来源：1采购单，2销售单，3快递，4手动录入',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `invoiceid` varchar(32) DEFAULT NULL COMMENT '单据号',
  `receiptid` varchar(32) DEFAULT NULL COMMENT '发票号',
  `receiptdate` datetime DEFAULT NULL COMMENT '开票日期',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `handler` varchar(32) DEFAULT NULL COMMENT '负责人',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `customerid` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `customername` varchar(64) DEFAULT NULL COMMENT '客户名',
  `customertel` varchar(32) DEFAULT NULL COMMENT '客户联系电话',
  `customermanager` varchar(32) DEFAULT NULL COMMENT '客户联系人',
  `customeraddress` varchar(128) DEFAULT NULL COMMENT '客户地址',
  `customermail` varchar(64) DEFAULT NULL COMMENT '客户邮箱',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_finance` */

/*Table structure for table `etb_personal` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_personal` */

/*Table structure for table `etb_price` */

DROP TABLE IF EXISTS `etb_price`;

CREATE TABLE `etb_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `productid` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `marketid` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `price01` decimal(15,3) DEFAULT NULL COMMENT '产品单价（未税）',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `item1` decimal(15,3) DEFAULT NULL COMMENT 'item1',
  `item2` decimal(15,3) DEFAULT NULL COMMENT 'item2',
  `item3` decimal(15,3) DEFAULT NULL COMMENT 'item3',
  `item4` decimal(15,3) DEFAULT NULL COMMENT 'item4',
  `item5` varchar(10) DEFAULT NULL COMMENT 'item5',
  `item6` datetime DEFAULT NULL COMMENT 'item6',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_price` */

/*Table structure for table `etb_product` */

DROP TABLE IF EXISTS `etb_product`;

CREATE TABLE `etb_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `belongto` varchar(16) DEFAULT NULL,
  `fieldno` varchar(8) DEFAULT NULL COMMENT '类型01：电线，02：套管，03：扁平线，04：线束，05：连接器，06：FPC',
  `brand` varchar(32) DEFAULT NULL COMMENT '品牌',
  `item1` varchar(8) DEFAULT NULL COMMENT '类型1',
  `item2` varchar(8) DEFAULT NULL COMMENT '类型2',
  `item3` varchar(8) DEFAULT NULL COMMENT '类型3',
  `tradename` varchar(64) DEFAULT NULL COMMENT '品名',
  `typeno` varchar(64) DEFAULT NULL COMMENT '规格',
  `color` varchar(64) DEFAULT NULL COMMENT '颜色',
  `packaging` varchar(16) DEFAULT NULL COMMENT '包装',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `sampleflag` int(4) DEFAULT NULL COMMENT '是否样品',
  `productname` varchar(600) DEFAULT NULL COMMENT '品牌-类型1-品名-规格-颜色-包装-是否样品',
  `makearea` varchar(64) DEFAULT NULL COMMENT '产地',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '供应商ID（备用）',
  `salesprice` decimal(11,2) DEFAULT NULL COMMENT '销售价',
  `purchaseprice` decimal(11,2) DEFAULT NULL COMMENT '采购价',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  `item01` varchar(64) DEFAULT NULL COMMENT '项目1',
  `item02` varchar(64) DEFAULT NULL COMMENT '项目2',
  `item03` varchar(64) DEFAULT NULL COMMENT '项目3',
  `item04` varchar(64) DEFAULT NULL COMMENT '项目4',
  `item05` varchar(64) DEFAULT NULL COMMENT '项目5',
  `item06` varchar(64) DEFAULT NULL COMMENT '项目6',
  `item07` varchar(64) DEFAULT NULL COMMENT '项目7',
  `item08` varchar(64) DEFAULT NULL COMMENT '项目8',
  `item09` varchar(64) DEFAULT NULL COMMENT '项目9',
  `item10` varchar(64) DEFAULT NULL COMMENT '项目10',
  `item11` varchar(64) DEFAULT NULL COMMENT '项目11',
  `item12` varchar(64) DEFAULT NULL COMMENT '项目12',
  `item13` varchar(64) DEFAULT NULL COMMENT '项目13',
  `item14` varchar(64) DEFAULT NULL COMMENT '项目14',
  `item15` varchar(64) DEFAULT NULL COMMENT '项目15',
  `item16` varchar(64) DEFAULT NULL COMMENT '项目16',
  `item17` varchar(64) DEFAULT NULL COMMENT '项目17',
  `item18` varchar(64) DEFAULT NULL COMMENT '项目18',
  `item19` varchar(64) DEFAULT NULL COMMENT '项目19',
  `item20` varchar(64) DEFAULT NULL COMMENT '项目20',
  `item21` varchar(64) DEFAULT NULL COMMENT '项目21',
  `item22` varchar(64) DEFAULT NULL COMMENT '项目22',
  `item23` varchar(64) DEFAULT NULL COMMENT '项目23',
  `item24` varchar(64) DEFAULT NULL COMMENT '项目24',
  `item25` varchar(64) DEFAULT NULL COMMENT '项目25',
  `item26` varchar(64) DEFAULT NULL COMMENT '项目26',
  `item27` varchar(64) DEFAULT NULL COMMENT '项目27',
  `item28` varchar(64) DEFAULT NULL COMMENT '项目28',
  `item29` varchar(64) DEFAULT NULL COMMENT '项目29',
  `item30` varchar(64) DEFAULT NULL COMMENT '项目30',
  `pic01` varchar(32) DEFAULT NULL COMMENT '图片1路径',
  `pic02` varchar(32) DEFAULT NULL COMMENT '图片2路径',
  `pic03` varchar(32) DEFAULT NULL COMMENT '图片3路径',
  `pic04` varchar(32) DEFAULT NULL COMMENT '图片4路径',
  `pic05` varchar(32) DEFAULT NULL COMMENT '图片5路径',
  `pdfpath` varchar(32) DEFAULT NULL COMMENT 'PDF文件路径',
  `rank` int(4) DEFAULT NULL COMMENT '级别（0-99）',
  `status` int(4) DEFAULT NULL COMMENT '状态：1为有效，其他为无效',
  `res01` varchar(16) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(16) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(16) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(16) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(16) DEFAULT NULL COMMENT '预备项目5',
  `res06` varchar(16) DEFAULT NULL COMMENT '预备项目6',
  `res07` varchar(16) DEFAULT NULL COMMENT '预备项目7',
  `res08` varchar(16) DEFAULT NULL COMMENT '预备项目8',
  `res09` varchar(16) DEFAULT NULL COMMENT '预备项目9',
  `res10` varchar(16) DEFAULT NULL COMMENT '预备项目10',
  `keyword` varchar(512) DEFAULT NULL COMMENT '关键字，模糊查询用',
  `createuid` varchar(32) DEFAULT NULL COMMENT '数据创建者',
  `createdate` datetime DEFAULT NULL COMMENT '数据创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '数据更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `etb_product` */

insert  into `etb_product`(`id`,`belongto`,`fieldno`,`brand`,`item1`,`item2`,`item3`,`tradename`,`typeno`,`color`,`packaging`,`unit`,`sampleflag`,`productname`,`makearea`,`supplierid`,`salesprice`,`purchaseprice`,`note`,`item01`,`item02`,`item03`,`item04`,`item05`,`item06`,`item07`,`item08`,`item09`,`item10`,`item11`,`item12`,`item13`,`item14`,`item15`,`item16`,`item17`,`item18`,`item19`,`item20`,`item21`,`item22`,`item23`,`item24`,`item25`,`item26`,`item27`,`item28`,`item29`,`item30`,`pic01`,`pic02`,`pic03`,`pic04`,`pic05`,`pdfpath`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`keyword`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(2,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(3,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(4,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(5,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(6,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(7,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(8,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(9,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(10,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(11,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(12,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(13,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(14,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(15,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(16,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(17,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(18,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(19,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(20,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(21,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(22,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(23,'0','01','testppp',NULL,NULL,NULL,'aa111','bb111','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',NULL,'122.00','111.00','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,testppp,null,aa111,bb111,红,整箱,苏州,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48');

/*Table structure for table `etb_purchase` */

DROP TABLE IF EXISTS `etb_purchase`;

CREATE TABLE `etb_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `purchaseno` varchar(32) DEFAULT NULL COMMENT '采购单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '采购单所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '采购主题',
  `theme2` varchar(32) DEFAULT NULL COMMENT '采购主题2',
  `warehouse` varchar(128) DEFAULT NULL COMMENT '仓库',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '采购单供应商',
  `suppliername` varchar(64) DEFAULT NULL COMMENT '采购单供应商名',
  `supplieraddr` varchar(128) DEFAULT NULL COMMENT '供应商地址',
  `suppliertel` varchar(32) DEFAULT NULL COMMENT '供应商联系人电话',
  `suppliermanager` varchar(32) DEFAULT NULL COMMENT '供应商联系人',
  `suppliermanageraddr` varchar(128) DEFAULT NULL COMMENT '供应商联系人地址',
  `suppliertax` varchar(32) DEFAULT NULL COMMENT '供应商联系人传真',
  `suppliermail` varchar(64) DEFAULT NULL COMMENT '供应商联系人邮箱',
  `handler` varchar(32) DEFAULT NULL COMMENT '采购单经手人',
  `purchasedate` date DEFAULT NULL COMMENT '采购日期',
  `totalamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（不含税）',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（含税）',
  `paidamount` decimal(11,2) DEFAULT NULL COMMENT '已付金额（含税）',
  `unpaidamount` decimal(11,2) DEFAULT NULL COMMENT '未付金额（含税）',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `productlist` varchar(512) DEFAULT NULL COMMENT '采购单对应的产品ID（逗号分割）',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `etb_purchase` */

insert  into `etb_purchase`(`id`,`purchaseno`,`belongto`,`theme1`,`theme2`,`warehouse`,`supplierid`,`suppliername`,`supplieraddr`,`suppliertel`,`suppliermanager`,`suppliermanageraddr`,`suppliertax`,`suppliermail`,`handler`,`purchasedate`,`totalamount`,`taxamount`,`paidamount`,`unpaidamount`,`approverid`,`productlist`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (35,'PURCHASE0150530182822','0','01',NULL,'11aa',NULL,'22aa',NULL,'55aa','33aa','44aa','66aa','77aa','test1aa','2015-05-30','73593.00','75000.00','0.00',NULL,NULL,'3,5,10,',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-30 18:28:22','admin','2015-05-30 21:04:11');

/*Table structure for table `etb_purchaseitem` */

DROP TABLE IF EXISTS `etb_purchaseitem`;

CREATE TABLE `etb_purchaseitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `purchaseno` varchar(32) DEFAULT NULL COMMENT '采购单单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '采购单所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '采购主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '采购主题2',
  `productid` varchar(20) DEFAULT NULL COMMENT '产品ID号',
  `quantity` int(11) DEFAULT NULL COMMENT '采购数量',
  `inquantity` int(11) DEFAULT NULL COMMENT '已入库数量',
  `remainquantity` int(11) DEFAULT NULL COMMENT '未入库数量',
  `unitprice` decimal(11,2) DEFAULT NULL COMMENT '采购单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额(未税)',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '金额（含税）',
  `handler` varchar(32) DEFAULT NULL COMMENT '收货人',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '供应商',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `etb_purchaseitem` */

insert  into `etb_purchaseitem`(`id`,`purchaseno`,`belongto`,`theme1`,`theme2`,`productid`,`quantity`,`inquantity`,`remainquantity`,`unitprice`,`amount`,`taxamount`,`handler`,`supplierid`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (10,'PURCHASE0150530182822','0','01',NULL,'3',111,1,110,'111.00','12321.00','13000.00',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-30 18:28:22','admin','2015-05-30 21:04:11'),(11,'PURCHASE0150530182822','0','01',NULL,'5',221,2,219,'111.00','24531.00','25000.00',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-30 18:28:22','admin','2015-05-30 21:04:11'),(12,'PURCHASE0150530182822','0','01',NULL,'10',331,3,328,'111.00','36741.00','37000.00',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-30 20:33:52','admin','2015-05-30 21:04:11');

/*Table structure for table `etb_quantity` */

DROP TABLE IF EXISTS `etb_quantity`;

CREATE TABLE `etb_quantity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `productid` bigint(20) DEFAULT NULL COMMENT '产品ID',
  `marketid` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `quantity01` int(10) DEFAULT NULL COMMENT '实际库存数',
  `quantity02` int(10) DEFAULT NULL COMMENT 'Quantity02',
  `quantity03` int(10) DEFAULT NULL COMMENT 'Quantity03',
  `quantity04` int(10) DEFAULT NULL COMMENT 'Quantity04',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `item1` varchar(10) DEFAULT NULL COMMENT 'Item1',
  `item2` varchar(10) DEFAULT NULL COMMENT 'Item2',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_quantity` */

/*Table structure for table `etb_sales` */

DROP TABLE IF EXISTS `etb_sales`;

CREATE TABLE `etb_sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `salesno` varchar(32) DEFAULT NULL COMMENT '销售单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '销售单所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '销售主题',
  `theme2` varchar(32) DEFAULT NULL COMMENT '销售主题2',
  `warehouse` varchar(128) DEFAULT NULL COMMENT '仓库',
  `customerid` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `customername` varchar(64) DEFAULT NULL COMMENT '客户名',
  `customertel` varchar(32) DEFAULT NULL COMMENT '客户联系电话',
  `customermanager` varchar(32) DEFAULT NULL COMMENT '客户联系人',
  `customeraddress` varchar(128) DEFAULT NULL COMMENT '客户地址',
  `customermail` varchar(64) DEFAULT NULL COMMENT '客户邮箱',
  `handler` varchar(32) DEFAULT NULL COMMENT '销售',
  `bookdate` datetime DEFAULT NULL COMMENT '订单日期',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（不含税）',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（含税）',
  `paidamount` decimal(11,2) DEFAULT NULL COMMENT '已付金额（含税）',
  `unpaidamount` decimal(11,2) DEFAULT NULL COMMENT '未付金额（含税）',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_sales` */

/*Table structure for table `etb_salesitem` */

DROP TABLE IF EXISTS `etb_salesitem`;

CREATE TABLE `etb_salesitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `salesno` varchar(32) DEFAULT NULL COMMENT '销售单单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '销售单所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '销售主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '销售主题2',
  `productid` varchar(20) DEFAULT NULL COMMENT '产品ID号',
  `quantity` int(11) DEFAULT NULL COMMENT '销售数量',
  `outquantity` int(11) DEFAULT NULL COMMENT '已出库数量',
  `remainquantity` int(11) DEFAULT NULL COMMENT '未出库数量',
  `unitprice` decimal(11,2) DEFAULT NULL COMMENT '销售单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '金额（含税）',
  `handler` varchar(32) DEFAULT NULL COMMENT '销售',
  `customerid` bigint(20) DEFAULT NULL COMMENT '客户ID',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_salesitem` */

/*Table structure for table `etb_supplier` */

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

/*Data for the table `etb_supplier` */

insert  into `etb_supplier`(`id`,`suppliername`,`belongto`,`note`,`suppliertel1`,`suppliermanager1`,`supplieraddress1`,`suppliermail1`,`supplierfax1`,`suppliertel2`,`suppliermanager2`,`supplieraddress2`,`suppliermail2`,`supplierfax2`,`suppliertel3`,`suppliermanager3`,`supplieraddress3`,`suppliermail3`,`supplierfax3`,`suppliertel4`,`suppliermanager4`,`supplieraddress4`,`suppliermail4`,`supplierfax4`,`suppliertel5`,`suppliermanager5`,`supplieraddress5`,`suppliermail5`,`supplierfax5`,`suppliertype`,`handlerid`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'供应商1','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(2,'供应商2','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00','admin11','2015-05-10 21:45:52'),(3,'供应商33333','上海','测试用~~~','13912345678','小刘','浦东','zhao@163.com','12345678','1231','test','4234','liu@163.com','23423','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00','admin11','2015-05-16 10:33:17'),(4,'供应商4','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(5,'供应商5','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(6,'供应商6','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(7,'供应商7','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(8,'供应商8','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(9,'供应商9','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(10,'供应商10','上海','测试用test','13912345678','玛莎','浦东','liu@163.com','12345678','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00','admin11','2015-05-10 21:45:15'),(11,'供应商11','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(12,'供应商12','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(13,'供应商13','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(14,'test',NULL,'','11','test','11','11@163.com','11','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-10 18:14:47',NULL,NULL),(16,'teset',NULL,'','wet','we','wet','wt@163.com','wet','','','','','','','','','','','','','','','','','','','','',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:46:36','admin11','2015-05-16 09:48:59'),(17,'通力',NULL,'测试用','13912345678','王小姐','上海','wang@163.com','13912345678','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:59:21','admin11','2015-05-16 09:59:43');

/*Table structure for table `etb_warehouse` */

DROP TABLE IF EXISTS `etb_warehouse`;

CREATE TABLE `etb_warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parentid` varchar(32) DEFAULT NULL COMMENT '数据来源单号',
  `warehousetype` int(4) DEFAULT NULL COMMENT '类型',
  `warehouseno` varchar(32) DEFAULT NULL COMMENT '入出库单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '主题2',
  `productid` varchar(20) DEFAULT NULL COMMENT '产品ID号',
  `quantity` int(11) DEFAULT NULL COMMENT '入出库数量',
  `actualquantity` int(11) DEFAULT NULL COMMENT '实际入出库数量',
  `unitprice` decimal(11,2) DEFAULT NULL COMMENT '单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '入出库金额',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '入出库金额（含税）',
  `warehousedate` datetime DEFAULT NULL COMMENT '入库日期',
  `handler` varchar(32) DEFAULT NULL COMMENT '收货人',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '供应商ID/客户',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_warehouse` */

/*Table structure for table `etb_warehouserpt` */

DROP TABLE IF EXISTS `etb_warehouserpt`;

CREATE TABLE `etb_warehouserpt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `warehousetype` int(4) DEFAULT NULL COMMENT '类型：1为入库单，2为出库单',
  `warehouseno` varchar(32) DEFAULT NULL COMMENT '入出库单号',
  `belongto` varchar(16) DEFAULT NULL COMMENT '所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '主题2',
  `parentid` varchar(5000) DEFAULT NULL COMMENT '数据来源单号',
  `totaltaxamount` decimal(11,2) DEFAULT NULL COMMENT '入出库单总金额（含税）',
  `warehousedate` datetime DEFAULT NULL COMMENT '入出库单日期',
  `handler` varchar(32) DEFAULT NULL COMMENT '收货人',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '供应商/客户',
  `suppliername` varchar(64) DEFAULT NULL COMMENT '供应商名/客户',
  `suppliertel` varchar(32) DEFAULT NULL COMMENT '供应商联系电话/客户',
  `suppliermanager` varchar(32) DEFAULT NULL COMMENT '供应商联系人/客户',
  `supplieraddress` varchar(128) DEFAULT NULL COMMENT '供应商地址/客户',
  `suppliermail` varchar(64) DEFAULT NULL COMMENT '供应商邮箱/客户',
  `expressid` varchar(64) DEFAULT NULL COMMENT '快递公司ID',
  `expressname` varchar(64) DEFAULT NULL COMMENT '快递公司名',
  `expresstel` varchar(32) DEFAULT NULL COMMENT '快递公司联系电话',
  `expressmanager` varchar(32) DEFAULT NULL COMMENT '快递联系人',
  `expressaddress` varchar(128) DEFAULT NULL COMMENT '快递地址',
  `expressmail` varchar(64) DEFAULT NULL COMMENT '快递邮箱',
  `expressno` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `expressamount` decimal(11,2) DEFAULT NULL COMMENT '快递金额(未含税)',
  `expresstaxamount` decimal(11,2) DEFAULT NULL COMMENT '快递金额(含税)',
  `expressnote` varchar(500) DEFAULT NULL COMMENT '快递备注',
  `approverid` varchar(5) DEFAULT NULL COMMENT '确认者',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etb_warehouserpt` */

/*Table structure for table `tbdict01` */

DROP TABLE IF EXISTS `tbdict01`;

CREATE TABLE `tbdict01` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fieldcode` varchar(32) DEFAULT NULL COMMENT '大分类',
  `fieldname` varchar(64) DEFAULT NULL COMMENT '名称',
  `code` varchar(16) DEFAULT NULL COMMENT '代码',
  `lang` varchar(8) DEFAULT NULL COMMENT '语言，默认为C=Chinese',
  `mean` varchar(128) DEFAULT NULL COMMENT '含义（单位）',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `createuid` varchar(32) DEFAULT NULL COMMENT '数据创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '数据更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `tbdict01` */

insert  into `tbdict01`(`id`,`fieldcode`,`fieldname`,`code`,`lang`,`mean`,`note`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'goods','電子線','01','c','Electronic Wire','电子线',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(2,'goods','熱収缩套管','02','c','Heat-Shrink-Tube','热收缩套管',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(3,'goods','柔軟扁平電纜','03','c','Flexible Flat Cable','柔软扁平电缆',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(4,'goods','漆包線','04','c','Magnesium Alloy','漆包线',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(5,'goods','線束加工','05','c','Wiring Harness','线束加工',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(6,'goods','柔軟印刷電路板','06','c','Flexible Printed Circuit Board','柔软印刷电路板',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(7,'goods','其他','07','c','其他','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(8,'01_item01','80℃～105℃','01','c','耐温','电线耐温80℃～105℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(9,'01_item01','105℃～150℃','02','c','耐温','电线耐温105℃～150℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(10,'01_item01','150℃～200℃及以上','03','c','耐温','电线耐温150℃～200℃及以上',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(11,'01_item02','30V～100V','001','c','耐压','耐压30V～100V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(12,'01_item02','100V～600V','002','c','耐压','耐压100V～600V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(13,'01_item02','1KV及以上','003','c','耐压','耐压1KV及以上',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(14,'01_item03','PVC','01','c','材质','材质PVC',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(15,'01_item03','PE','02','c','材质','材质PE',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(16,'01_item03','其他','03','c','材质','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(17,'01_item04','LEAD Free','01','c','环保','LEAD Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(18,'01_item04','Halogen Free','02','c','环保','Halogen Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(19,'02_item01','80℃～105℃','01','c','耐温','套管耐温80℃～105℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(20,'02_item01','105℃～150℃','02','c','耐温','套管耐温105℃～150℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(21,'02_item01','150℃～200℃及以上','03','c','耐温','套管耐温150℃～200℃及以上',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(22,'02_item02','30V～100V','001','c','耐压','耐压30V～100V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(23,'02_item02','100V～600V','002','c','耐压','耐压100V～600V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(24,'02_item02','1KV及以上','003','c','耐压','耐压1KV及以上',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(25,'02_item03','双层绝缘','01','c','绝缘','绝缘双层绝缘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(26,'02_item03','单层绝缘','02','c','绝缘','绝缘单层绝缘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(27,'02_item04','1.5:1','01','c','收缩比','收缩比1.5:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(28,'02_item04','2:1','02','c','收缩比','收缩比2:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(29,'02_item04','2.4:1','03','c','收缩比','收缩比2.4:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(30,'02_item04','3:1','04','c','收缩比','收缩比3:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(31,'02_item04','4:1','05','c','收缩比','收缩比4:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(32,'02_item05','PE','01','c','材质','PE',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(33,'02_item05','其他','02','c','材质','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(34,'02_item06','LEAD Free','01','c','环保','LEAD Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(35,'02_item06','Halogen Free','02','c','环保','Halogen Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(36,'makearea','日本','001','c','产地','产地日本',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(37,'makearea','马来西亚','002','c','产地','产地马来西亚',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(38,'makearea','苏州','003','c','产地','产地苏州',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(39,'unit','米','002','c','单位','米',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(40,'unit','卷','003','c','单位','卷',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(41,'unit','箱','004','c','单位','箱',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(42,'unit','盘','005','c','单位','盘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(43,'unit','套','006','c','单位','套',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(44,'unit','PSC','007','c','单位','PSC',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(45,'unit','根','008','c','单位','根',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(46,'unit','其它','009','c','单位','其它',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(47,'color','红','01','c','颜色','红',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(48,'color','橙','02','c','颜色','橙',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(49,'color','黄','03','c','颜色','黄',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(50,'color','绿','04','c','颜色','绿',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(51,'color','蓝','05','c','颜色','蓝',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(52,'color','靛','06','c','颜色','靛',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(53,'color','紫','07','c','颜色','紫',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(54,'color','灰','08','c','颜色','灰',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(55,'color','白','09','c','颜色','白',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(56,'color','黑','10','c','颜色','黑',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(57,'color','透明','11','c','颜色','透明',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(58,'color','粉红','12','c','颜色','粉红',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(59,'color','棕','13','c','颜色','棕',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00');

/*Table structure for table `tbresource` */

DROP TABLE IF EXISTS `tbresource`;

CREATE TABLE `tbresource` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  `parentid` int(10) DEFAULT NULL COMMENT '父节点ID',
  `restype` int(4) DEFAULT NULL COMMENT '资源类型：1为主节点，2为子节，3为其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tbresource` */

insert  into `tbresource`(`id`,`url`,`note`,`status`,`createtime`,`sort`,`parentid`,`restype`) values (1,'采购','采购',1,'2015-04-28 00:00:00',1,-1,1),(2,'产品','产品',1,'2015-04-28 00:00:00',2,-1,1),(3,'/purchase/showEtbPurchaseAction.action','采购单管理',1,'2015-04-28 00:00:00',1,1,2),(4,'/product/showEtbProductAction.action','产品管理',1,'2015-04-28 00:00:00',1,2,2),(5,'/supplier/showSupplierAction.action','供应商管理',1,'2015-05-10 00:00:00',2,1,3);

/*Table structure for table `tbrole` */

DROP TABLE IF EXISTS `tbrole`;

CREATE TABLE `tbrole` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rolecode` varchar(32) DEFAULT NULL COMMENT '角色CODE',
  `rolename` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `rank` int(4) DEFAULT NULL COMMENT '角色级别',
  `note` varchar(128) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `createuid` varchar(32) DEFAULT NULL COMMENT '创建者ID',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者ID',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tbrole` */

insert  into `tbrole`(`id`,`rolecode`,`rolename`,`rank`,`note`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'admin','系统管理员',99,'系统管理员',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(2,'normal','一般用户',70,'只能输入和更新产品、库存和新闻，不能删除产品、库存和新闻',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(3,'operator','操作员',50,'只能输入和更新产品和库存，不能删除产品和库存，可以看新闻',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00');

/*Table structure for table `tbroleres` */

DROP TABLE IF EXISTS `tbroleres`;

CREATE TABLE `tbroleres` (
  `roleid` int(10) DEFAULT NULL,
  `resourceid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbroleres` */

insert  into `tbroleres`(`roleid`,`resourceid`) values (1,1),(1,2),(2,1),(2,2),(1,3),(1,4),(1,5);

/*Table structure for table `tbuser` */

DROP TABLE IF EXISTS `tbuser`;

CREATE TABLE `tbuser` (
  `userid` varchar(32) NOT NULL COMMENT '用户登录ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(32) DEFAULT NULL COMMENT '登录密码，MD5加密',
  `rolecode` varchar(32) DEFAULT NULL COMMENT '角色CODE',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `createuid` varchar(32) DEFAULT NULL COMMENT '创建者ID',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '更新者ID',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbuser` */

insert  into `tbuser`(`userid`,`username`,`password`,`rolecode`,`status`,`note`,`createuid`,`createdate`,`updateuid`,`updatedate`) values ('admin','admin11','b59c67bf196a4758191e42f76670ceba','admin',1,'admin addaaa','admin','2014-12-08 00:00:00','admin','2015-01-07 01:36:46'),('normal','nnnn','b59c67bf196a4758191e42f76670ceba','normal',1,'fdas','admin11','2015-01-11 21:40:47','admin11','2015-01-11 21:40:47'),('test11','tesababa','b59c67bf196a4758191e42f76670ceba','operator',1,'fdsafdsa111','admin11','2015-01-11 21:30:13','admin11','2015-01-11 21:35:16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
