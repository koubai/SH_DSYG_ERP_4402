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

/*Table structure for table `calendar` */

DROP TABLE IF EXISTS `calendar`;

CREATE TABLE `calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `start` varchar(50) NOT NULL,
  `end` varchar(50) DEFAULT NULL,
  `allday` tinyint(1) NOT NULL DEFAULT '0',
  `color` varchar(20) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `calendar` */

insert  into `calendar`(`id`,`title`,`start`,`end`,`allday`,`color`,`userId`) values (1,'aa','2015-07-09','',1,'#000080','admin'),(2,'test111','2015-10-02','',1,'#008000','admin'),(3,'abcccccccccccccccccccccccccccccccccccccccccccccccddddddddddddddddddddeeeeeeeeeee</tr>eeeeeeeeeeeee','2015-10-03','',1,'#00ff00','admin'),(4,'<tr>','2015-10-04','',1,'#000080','admin'),(5,'dddd4444','2015-10-18','',1,'#99cc00','admin'),(6,'17','2015-10-17','',1,'#000080','admin'),(7,'出差','2015-10-16','',1,'#000080','admin');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `etb_assets` */

insert  into `etb_assets`(`id`,`belongto`,`assetsno`,`assetsname`,`handler`,`registerdate`,`purpose`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'0','ZCN02015072323031956424ffc','111','333','2015-07-08 00:00:00','22222',NULL,'',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-07-23 23:03:19',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `etb_customer` */

insert  into `etb_customer`(`id`,`customername`,`belongto`,`note`,`customertel1`,`customermanager1`,`customeraddress1`,`customermail1`,`customerfax1`,`customertel2`,`customermanager2`,`customeraddress2`,`customermail2`,`customerfax2`,`customertel3`,`customermanager3`,`customeraddress3`,`customermail3`,`customerfax3`,`customertel4`,`customermanager4`,`customeraddress4`,`customermail4`,`customerfax4`,`customertel5`,`customermanager5`,`customeraddress5`,`customermail5`,`customerfax5`,`customertype`,`handlerid`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'微软',NULL,'test-note111','1123','test','1123','1123@163.com','1123','12','test2','12','12@163.com','1256','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-17 20:48:10','admin11','2015-05-17 20:50:21'),(2,'IBM','','memo','1234','test2','1234','111@163.com','1234','','','','','','','','','','','','','','','','','','','','',0,'',1,'','','','','','','','','','','admin11','2015-05-17 21:08:22','admin11','2015-05-17 21:08:38'),(3,'oracle',NULL,'','11','test','11','11@163.com','11','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-10 18:14:47',NULL,NULL),(4,'GE',NULL,'','wet','we','wet','wt@163.com','wet','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:46:36','admin11','2015-05-16 09:48:59'),(5,'通力',NULL,'测试用','13912345678','王小姐','上海','wang@163.com','13912345678','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:59:21','admin11','2015-05-16 09:59:43'),(6,'BMW',NULL,'','11','test','11','11@163.com','11','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-10 18:14:47',NULL,NULL),(7,'AUTO',NULL,'','wet','we','wet','wt@163.com','wet','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:46:36','admin11','2015-05-16 09:48:59'),(8,'ALIBABA',NULL,'测试用','13912345678','王小姐','上海','wang@163.com','13912345678','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:59:21','admin11','2015-05-16 09:59:43'),(9,'TENCENT',NULL,'','11','test','11','11@163.com','11','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-10 18:14:47',NULL,NULL),(10,'BAIDU',NULL,'','wet','we','wet','wt@163.com','wet','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:46:36','admin11','2015-05-16 09:48:59'),(11,'SOHU',NULL,'测试用','13912345678','王小姐','上海','wang@163.com','13912345678','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-05-16 09:59:21','admin11','2015-05-16 09:59:43');

/*Table structure for table `etb_delivery` */

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `etb_delivery` */

insert  into `etb_delivery`(`id`,`deliveryname`,`belongto`,`note`,`deliverytel1`,`deliverymanager1`,`deliveryaddress1`,`deliverymail1`,`deliveryfax1`,`deliverytel2`,`deliverymanager2`,`deliveryaddress2`,`deliverymail2`,`deliveryfax2`,`deliverytel3`,`deliverymanager3`,`deliveryaddress3`,`deliverymail3`,`deliveryfax3`,`deliverytel4`,`deliverymanager4`,`deliveryaddress4`,`deliverymail4`,`deliveryfax4`,`deliverytel5`,`deliverymanager5`,`deliveryaddress5`,`deliverymail5`,`deliveryfax5`,`deliverytype`,`handlerid`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'申通','0','','ccc','bbb','fad','ddd@eee.cc','fda','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-09-20 11:08:49',NULL,NULL),(3,'中通','0','','bbb','aa','bb','cc@dd.aa','cc','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-09-20 11:09:17',NULL,NULL),(4,'韵达','0','','bb','aa','af','cc@cc.da','fda','','','','','','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-09-20 11:09:34',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `etb_document` */

insert  into `etb_document`(`id`,`documentno`,`belongto`,`documentname`,`handler`,`registerdate`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'PR10000001',NULL,'合同正本','小明','2015-06-02 00:00:00','CP','note',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-06-06 20:32:30','admin11','2015-06-06 20:39:02'),(2,'PR2000001',NULL,'电子书','陈','2015-05-30 00:00:00','陈','note',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-06-06 21:57:05',NULL,NULL),(3,'WDN020150723230003a553693b','0','test111','aaa','2015-07-01 00:00:00','da','aa',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-07-23 23:00:03',NULL,NULL);

/*Table structure for table `etb_finance` */

DROP TABLE IF EXISTS `etb_finance`;

CREATE TABLE `etb_finance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `financetype` int(4) DEFAULT NULL COMMENT '财务数据来源：1采购单，2销售单，3快递，4手动录入',
  `theme` varchar(32) DEFAULT NULL COMMENT '主题（仅当financetype=4时有值）',
  `mode` varchar(16) DEFAULT NULL COMMENT '方式：1为收款，2为付款',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `invoiceid` varchar(32) DEFAULT NULL COMMENT '单据号',
  `receiptid` varchar(32) DEFAULT NULL COMMENT '发票号',
  `receiptdate` datetime DEFAULT NULL COMMENT '开票日期',
  `accountdate` datetime DEFAULT NULL COMMENT '结算日期',
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `etb_finance` */

insert  into `etb_finance`(`id`,`financetype`,`theme`,`mode`,`belongto`,`invoiceid`,`receiptid`,`receiptdate`,`accountdate`,`amount`,`handler`,`approverid`,`customerid`,`customername`,`customertel`,`customermanager`,`customeraddress`,`customermail`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (54,1,NULL,'2','0','RKD0151019224811','CWN020151019224811','2015-10-19 00:00:00','2015-10-19 00:00:00','1298.70','admin','admin',3,'迪亚天天','13912345678','小刘','浦东','zhao@163.com',NULL,50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:48:11','admin','2015-10-19 22:50:44'),(55,3,NULL,'2','0','RKD0151019224811','CWN020151019224910','2015-10-19 00:00:00','2015-10-19 00:00:00','10.00','admin','admin',1,'申通','ccc','bbb','fad','ddd@eee.cc',NULL,50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'12221',NULL,'admin','2015-10-19 22:49:10','admin','2015-10-19 22:49:56'),(56,1,NULL,'2','0','RKD0151019225239','CWN020151019225239','2015-10-19 00:00:00','2015-10-19 00:00:00','14285.70','admin','admin',1,'沃尔玛','13912345678','小刘','浦东','liu@163.com',NULL,50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:39','admin','2015-10-19 22:56:21'),(57,3,NULL,'2','0','RKD0151019225239','CWN020151019225441','2015-10-19 00:00:00','2015-10-19 00:00:00','25.00','admin','admin',4,'韵达','bb','aa','af','cc@cc.da',NULL,50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'424424',NULL,'admin','2015-10-19 22:54:41','admin','2015-10-19 22:56:29'),(58,2,NULL,'1','0','CKD0151019230338','CWN020151019230338',NULL,'2015-10-19 00:00:00','14274.00','admin',NULL,1,'微软','1123','test','1123','1123@163.com',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:03:38','admin','2015-10-19 23:03:38'),(59,2,NULL,'1','0','CKD0151019230556','CWN020151019230556',NULL,'2015-10-19 00:00:00','1427.40','admin',NULL,4,'GE','wet','we','wet','wt@163.com',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:05:56','admin','2015-10-19 23:05:56'),(60,2,NULL,'1','0','CKD0151019230855','CWN020151019230855',NULL,'2015-10-19 00:00:00','4282.20','user1',NULL,3,'oracle','11','test','11','11@163.com',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user1','2015-10-19 23:08:55','user1','2015-10-19 23:08:55'),(61,3,NULL,'1','0','CKD0151019230855','CWN020151019231142','2015-10-19 00:00:00','2015-10-19 00:00:00','100.00','user1','user1',3,'中通','bbb','aa','bb','cc@dd.aa',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'212133',NULL,'user1','2015-10-19 23:11:42','user1','2015-10-19 23:11:42'),(62,3,NULL,'1','0','CKD0151019230556','CWN020151019231156','2015-10-19 00:00:00','2015-10-19 00:00:00','1000.00','user1','user1',3,'中通','bbb','aa','bb','cc@dd.aa',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'42424',NULL,'user1','2015-10-19 23:11:56','user1','2015-10-19 23:11:56'),(63,3,NULL,'1','0','CKD0151019230338','CWN020151019231211','2015-10-19 00:00:00','2015-10-19 00:00:00','10000.00','user1','user1',3,'中通','bbb','aa','bb','cc@dd.aa',NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'32424',NULL,'user1','2015-10-19 23:12:11','user1','2015-10-19 23:12:11');

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
  `birthday` datetime DEFAULT NULL COMMENT '生日',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `etb_personal` */

insert  into `etb_personal`(`id`,`userid`,`belongto`,`userno`,`registdate`,`username`,`sex`,`birthday`,`post`,`superior`,`phone`,`tell`,`address`,`employeddate`,`retiredate`,`personaldesc`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,NULL,NULL,'1','2015-05-30 00:00:00','1',0,NULL,'','','','','','2012-01-01 00:00:00','2015-08-08 00:00:00',NULL,'',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-06-06 23:09:11','admin11','2015-06-06 23:33:08'),(2,NULL,NULL,'YG10001','2015-05-05 00:00:00','丁丁一',1,NULL,'销售员','陈一','13912345678','02112345678','浦东新区北蔡','2013-01-01 00:00:00','2015-06-01 00:00:00','11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111','11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-06-06 23:34:48','admin11','2015-06-07 00:06:47'),(3,NULL,'0','YGN020150723230251de7a7baa','2015-07-23 00:00:00','abda',0,'2015-07-10 00:00:00','22','11','333333','','','2015-07-17 00:00:00','2015-07-24 00:00:00','','',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin11','2015-07-23 23:02:51',NULL,NULL);

/*Table structure for table `etb_position` */

DROP TABLE IF EXISTS `etb_position`;

CREATE TABLE `etb_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `belongto` varchar(16) DEFAULT NULL,
  `productid` varchar(20) DEFAULT NULL COMMENT '产品ID',
  `amount` int(11) DEFAULT NULL COMMENT '盘点后数量',
  `beforeamount` int(11) DEFAULT NULL COMMENT '盘点前数量',
  `warehousename` varchar(64) DEFAULT NULL COMMENT '仓库名',
  `productposition` varchar(32) DEFAULT NULL COMMENT '位置',
  `checkday` varchar(20) DEFAULT NULL COMMENT '盘点时间，格式yyyy-MM-dd',
  `handler` varchar(32) DEFAULT NULL COMMENT '盘点人',
  `note` varchar(500) DEFAULT NULL,
  `rank` int(2) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `res01` varchar(32) DEFAULT NULL,
  `res02` varchar(32) DEFAULT NULL,
  `res03` varchar(32) DEFAULT NULL,
  `res04` varchar(32) DEFAULT NULL,
  `res05` varchar(32) DEFAULT NULL,
  `createuid` varchar(32) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updateuid` varchar(32) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `etb_position` */

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
  `salesprice` decimal(11,6) DEFAULT NULL COMMENT '销售价',
  `purchaseprice` decimal(11,6) DEFAULT NULL COMMENT '采购价',
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `etb_product` */

insert  into `etb_product`(`id`,`belongto`,`fieldno`,`brand`,`item1`,`item2`,`item3`,`tradename`,`typeno`,`color`,`packaging`,`unit`,`sampleflag`,`productname`,`makearea`,`supplierid`,`salesprice`,`purchaseprice`,`note`,`item01`,`item02`,`item03`,`item04`,`item05`,`item06`,`item07`,`item08`,`item09`,`item10`,`item11`,`item12`,`item13`,`item14`,`item15`,`item16`,`item17`,`item18`,`item19`,`item20`,`item21`,`item22`,`item23`,`item24`,`item25`,`item26`,`item27`,`item28`,`item29`,`item30`,`pic01`,`pic02`,`pic03`,`pic04`,`pic05`,`pdfpath`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`keyword`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'0','01','testppp',NULL,NULL,NULL,'tradename1','typeno1','03','0','003',NULL,'testppp-電子線-aa111-bb111-黄-整箱','003',1,'122.000000','111.000000','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,tradename1,typeno1,黄,整箱,,苏州,,','admin','2015-05-21 23:03:30','admin','2015-06-02 22:35:12'),(2,'0','01','testppp',NULL,NULL,NULL,'tradename2','typeno2','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',1,'122.000000','111.000000','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,tradename2,typeno2,红,整箱,,苏州,,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(3,'0','01','testppp',NULL,NULL,NULL,'tradename3','typeno3','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',1,'122.000000','111.000000','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,tradename3,typeno3,红,整箱,,苏州,,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(4,'0','01','testppp',NULL,NULL,NULL,'tradename4','typeno4','01','0','002',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',1,'122.000000','111.000000','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,tradename4,typeno4,红,整箱,,苏州,,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(5,'0','01','testppp',NULL,NULL,NULL,'tradename5','typeno5','01','0','003',NULL,'testppp-電子線-aa111-bb111-红-整箱','003',1,'122.000000','111.000000','fdas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,tradename5,typeno5,红,整箱,,苏州,,','admin','2015-05-21 23:03:30','admin','2015-05-23 23:11:48'),(33,'0','02','aa',NULL,NULL,NULL,'pdftest11','bb','03','0','003',0,'aa-熱収缩套管-pdftest11-bb-黄-整箱','001',NULL,NULL,NULL,'aaa','01','002','03','02','01','01',NULL,NULL,'','cc','323',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','',NULL,NULL,NULL,50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'熱収缩套管,热收缩套管,aa,pdftest11,bb,黄,整箱,cc,日本,','admin','2015-10-24 21:14:59','admin','2015-10-24 21:31:42');

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
  `supplierfax` varchar(32) DEFAULT NULL COMMENT '供应商联系人传真',
  `suppliermail` varchar(64) DEFAULT NULL COMMENT '供应商联系人邮箱',
  `handler` varchar(32) DEFAULT NULL COMMENT '采购单经手人',
  `purchasedate` datetime DEFAULT NULL COMMENT '采购日期',
  `plandate` varchar(32) DEFAULT NULL COMMENT '预入库时间',
  `totalamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（不含税）',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（含税）',
  `paidamount` decimal(11,2) DEFAULT NULL COMMENT '已付金额（含税）',
  `unpaidamount` decimal(11,2) DEFAULT NULL COMMENT '未付金额（含税）',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `productlist` varchar(1024) DEFAULT NULL COMMENT '采购单对应的产品ID（逗号分割）',
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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Data for the table `etb_purchase` */

insert  into `etb_purchase`(`id`,`purchaseno`,`belongto`,`theme1`,`theme2`,`warehouse`,`supplierid`,`suppliername`,`supplieraddr`,`suppliertel`,`suppliermanager`,`suppliermanageraddr`,`supplierfax`,`suppliermail`,`handler`,`purchasedate`,`plandate`,`totalamount`,`taxamount`,`paidamount`,`unpaidamount`,`approverid`,`productlist`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (77,'CGD02015101922391855631c92','0',NULL,'DS2015000027','SH01',3,'迪亚天天',NULL,'13912345678','小刘','浦东','12345678','zhao@163.com','admin','2015-10-19 00:00:00','2015-10-19','1110.00','1298.70','0.00',NULL,NULL,'2,','aabb',50,20,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:39:18','admin','2015-10-19 22:48:11'),(78,'CGD020151019225231b1148cde','0',NULL,'DS2015000028','SH01',1,'沃尔玛',NULL,'13912345678','小刘','浦东','12345678','liu@163.com','admin','2015-10-19 00:00:00','2015-10-19','12210.00','14285.70','0.00',NULL,NULL,'4,5,','',50,20,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:31','admin','2015-10-19 22:52:39'),(79,'CGD02015102510124696423af3','0',NULL,'DS2015000029','SH01',3,'迪亚天天',NULL,'13912345678','小刘','浦东','12345678','zhao@163.com','admin','2015-10-25 00:00:00','2015-10-25','3729.00','4362.93','0.00',NULL,NULL,'4,33,','',50,10,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:12:47','admin','2015-10-25 10:12:47'),(80,'CGD02015102516590180d98050','0',NULL,'DS2015000030','SH01',4,'罗森',NULL,'13912345678','小刘','浦东','12345678','liu@163.com','admin','2015-10-25 00:00:00','2015-10-25','1221.00','1428.57','0.00',NULL,NULL,'5,','',50,10,'03',NULL,'1111','2222',NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:59:02','admin','2015-10-25 16:59:44'),(81,'CGD020151025174022bb2c4213','0',NULL,'DS2015000031','SH01',4,'罗森',NULL,'13912345678','小刘','浦东','12345678','liu@163.com','admin','2015-10-25 00:00:00','2015-10-25','4906.00','5740.02','0.00',NULL,NULL,'5,33,3,','',50,10,'01',NULL,'331','441',NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 17:40:22','admin','2015-10-25 17:54:15');

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
  `inquantity` int(11) DEFAULT NULL COMMENT '确认入库数量',
  `beforequantity` int(11) DEFAULT NULL COMMENT '预入库数量',
  `remainquantity` int(11) DEFAULT NULL COMMENT '未入库数量',
  `unitprice` decimal(11,6) DEFAULT NULL COMMENT '采购单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额(未税)',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '金额（含税）',
  `plandate` varchar(32) DEFAULT NULL COMMENT '预入库时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Data for the table `etb_purchaseitem` */

insert  into `etb_purchaseitem`(`id`,`purchaseno`,`belongto`,`theme1`,`theme2`,`productid`,`quantity`,`inquantity`,`beforequantity`,`remainquantity`,`unitprice`,`amount`,`taxamount`,`plandate`,`handler`,`supplierid`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (104,'CGD02015101922391855631c92','0','01','DS2015000027','2',10,10,0,0,'111.000000','1110.00','1298.70','2015-10-19','admin',3,NULL,'aabb',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:39:18','admin','2015-10-19 22:44:32'),(105,'CGD020151019225231b1148cde','0','01','DS2015000028','4',50,50,0,0,'111.000000','5550.00','6493.50','2015-10-19','admin',1,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:31','admin','2015-10-19 22:52:31'),(106,'CGD020151019225231b1148cde','0','01','DS2015000028','5',60,60,0,0,'111.000000','6660.00','7792.20','2015-10-19','admin',1,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:31','admin','2015-10-19 22:52:31'),(107,'CGD02015102510124696423af3','0','01','DS2015000029','4',33,0,0,33,'111.000000','3663.00','4285.71','2015-10-25','admin',3,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:12:47','admin','2015-10-25 10:12:47'),(108,'CGD02015102510124696423af3','0','02','DS2015000029','33',22,0,0,22,'3.000000','66.00','77.22','2015-10-25','admin',3,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:12:47','admin','2015-10-25 10:12:47'),(109,'CGD02015102516590180d98050','0','01','DS2015000030','5',11,2,0,9,'111.000000','1221.00','1428.57','2015-10-25','admin',4,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:59:02','admin','2015-10-25 16:59:44'),(110,'CGD020151025174022bb2c4213','0','01','DS2015000031','5',11,1,0,10,'111.000000','1221.00','1428.57','2015-10-25','admin',4,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'bz11100a',NULL,'admin','2015-10-25 17:40:22','admin','2015-10-25 17:54:15'),(111,'CGD020151025174022bb2c4213','0','02','DS2015000031','33',22,2,0,20,'1.000000','22.00','25.74','2015-10-25','admin',4,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'bz22200b',NULL,'admin','2015-10-25 17:40:22','admin','2015-10-25 17:54:15'),(112,'CGD020151025174022bb2c4213','0','01','DS2015000031','3',33,2,0,31,'111.000000','3663.00','4285.71',NULL,NULL,4,NULL,'',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'bz333cc',NULL,'admin','2015-10-25 17:48:30','admin','2015-10-25 17:54:15');

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
  `customerfax` varchar(32) DEFAULT NULL COMMENT '客户传真',
  `customermail` varchar(64) DEFAULT NULL COMMENT '客户邮箱',
  `handler` varchar(32) DEFAULT NULL COMMENT '销售',
  `bookdate` datetime DEFAULT NULL COMMENT '订单日期',
  `plandate` varchar(32) DEFAULT NULL COMMENT '预入库时间',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（不含税）',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '采购金额（含税）',
  `paidamount` decimal(11,2) DEFAULT NULL COMMENT '已付金额（含税）',
  `unpaidamount` decimal(11,2) DEFAULT NULL COMMENT '未付金额（含税）',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `productlist` varchar(1024) DEFAULT NULL COMMENT '销售单对应的产品ID（逗号分割）',
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `etb_sales` */

insert  into `etb_sales`(`id`,`salesno`,`belongto`,`theme1`,`theme2`,`warehouse`,`customerid`,`customername`,`customertel`,`customermanager`,`customeraddress`,`customerfax`,`customermail`,`handler`,`bookdate`,`plandate`,`amount`,`taxamount`,`paidamount`,`unpaidamount`,`approverid`,`productlist`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (30,'XSD02015101923015788e62bd3','0',NULL,'10000011','SH01',1,'微软','1123','test','1123','1123','1123@163.com','admin','2015-10-19 00:00:00','2015-10-19','12200.00','14274.00','0.00',NULL,NULL,'3,','',50,20,'01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:01:57','admin','2015-10-19 23:03:38'),(31,'XSD020151019230550b7c97f5b','0',NULL,'1100004','SH01',4,'GE','wet','we','wet','wet','wt@163.com','admin','2015-10-19 00:00:00','2015-10-19','1220.00','1427.40','0.00',NULL,NULL,'2,','',50,20,'03','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:05:50','admin','2015-10-19 23:05:56'),(32,'XSD020151019230850d4ce68de','0',NULL,'2000013','SH01',3,'oracle','11','test','11','11','11@163.com','user1','2015-10-19 00:00:00','2015-10-19','3660.00','4282.20','0.00',NULL,NULL,'3,','aa',50,20,'02','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user1','2015-10-19 23:08:50','user1','2015-10-19 23:08:55'),(33,'XSD020151024200336e8a68079','0',NULL,'a330000001','SH01',11,'SOHU','13912345678','王小姐','上海','13912345678','wang@163.com','admin','2015-10-24 00:00:00','2015-10-24','1830.00','2141.10','0.00',NULL,NULL,'3,','',50,10,'02','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-24 20:03:36','admin','2015-10-24 20:03:36'),(34,'XSD0201510251011564e96a324','0',NULL,'test22222','SH01',11,'SOHU','13912345678','王小姐','上海','13912345678','wang@163.com','admin','2015-10-25 00:00:00','2015-10-25','124.00','145.08','0.00',NULL,NULL,'5,33,','332',50,10,'03','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:11:56','admin','2015-10-25 10:11:56'),(35,'XSD02015102516452399423f0e','0',NULL,'jj111111111','SH01',5,'通力','13912345678','王小姐','上海','13912345678','wang@163.com','admin','2015-10-25 00:00:00','2015-10-25','1364.00','1595.88','0.00',NULL,NULL,'5,33,','aa',50,10,'01','0','111','121',NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:45:23','admin','2015-10-25 16:53:56'),(36,'XSD02015102516553905a7b465','0',NULL,'jjj3333333333333','SH01',10,'BAIDU','wet','we','wet','wet','wt@163.com','admin','2015-10-25 00:00:00','2015-10-25','1342.00','1570.14','0.00',NULL,NULL,'5,','',50,10,'01','0','44','55',NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:55:39','admin','2015-10-25 16:55:39'),(37,'XSD020151025181731c372925e','0',NULL,'j444444444444','SH01',11,'SOHU','13912345678','王小姐','上海','13912345678','wang@163.com','admin','2015-10-25 00:00:00','2015-10-25','5412.00','6332.04','0.00',NULL,NULL,'5,33,3,','',50,10,'02','0','881','991',NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 18:17:31','admin','2015-10-25 18:18:34');

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
  `beforequantity` int(11) DEFAULT NULL COMMENT '预出库数量',
  `remainquantity` int(11) DEFAULT NULL COMMENT '未出库数量',
  `unitprice` decimal(11,6) DEFAULT NULL COMMENT '销售单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '金额（含税）',
  `plandate` varchar(32) DEFAULT NULL COMMENT '预出库时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `etb_salesitem` */

insert  into `etb_salesitem`(`id`,`salesno`,`belongto`,`theme1`,`theme2`,`productid`,`quantity`,`outquantity`,`beforequantity`,`remainquantity`,`unitprice`,`amount`,`taxamount`,`plandate`,`handler`,`customerid`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (52,'XSD02015101923015788e62bd3','0','01','10000011','3',100,100,0,0,'122.000000','12200.00','14274.00','2015-10-19','admin',1,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:01:57','admin','2015-10-19 23:03:27'),(53,'XSD020151019230550b7c97f5b','0','01','1100004','2',10,10,0,0,'122.000000','1220.00','1427.40','2015-10-19','admin',4,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:05:50','admin','2015-10-19 23:05:50'),(54,'XSD020151019230850d4ce68de','0','01','2000013','3',30,30,0,0,'122.000000','3660.00','4282.20','2015-10-19','user1',3,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user1','2015-10-19 23:08:50','user1','2015-10-19 23:08:50'),(55,'XSD020151024200336e8a68079','0','01','a330000001','3',15,15,0,0,'122.000000','1830.00','2141.10','2015-10-24','admin',11,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-24 20:03:36','admin','2015-10-24 20:03:36'),(56,'XSD0201510251011564e96a324','0','01','test22222','5',1,1,0,0,'122.000000','122.00','142.74','2015-10-25','admin',11,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:11:56','admin','2015-10-25 10:11:56'),(57,'XSD0201510251011564e96a324','0','02','test22222','33',2,2,0,0,'1.000000','2.00','2.34','2015-10-25','admin',11,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:11:56','admin','2015-10-25 10:11:56'),(58,'XSD02015102516452399423f0e','0','01','jj111111111','5',11,1,0,10,'122.000000','1342.00','1570.14','2015-10-25','admin',5,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:45:23','admin','2015-10-25 16:53:56'),(59,'XSD02015102516452399423f0e','0','02','jj111111111','33',22,2,0,20,'1.000000','22.00','25.74','2015-10-25','admin',5,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:45:23','admin','2015-10-25 16:53:56'),(60,'XSD02015102516553905a7b465','0','01','jjj3333333333333','5',11,0,0,11,'122.000000','1342.00','1570.14','2015-10-25','admin',10,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:55:39','admin','2015-10-25 16:55:39'),(61,'XSD020151025181731c372925e','0','01','j444444444444','5',11,1,0,10,'122.000000','1342.00','1570.14','2015-10-25','admin',11,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'bz111a4',NULL,'admin','2015-10-25 18:17:31','admin','2015-10-25 18:18:34'),(62,'XSD020151025181731c372925e','0','02','j444444444444','33',22,2,0,20,'2.000000','44.00','51.48','2015-10-25','admin',11,NULL,NULL,50,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'bz222b5',NULL,'admin','2015-10-25 18:17:31','admin','2015-10-25 18:18:34'),(63,'XSD020151025181731c372925e','0','01','j444444444444','3',33,1,0,32,'122.000000','4026.00','4710.42',NULL,NULL,11,NULL,NULL,NULL,1,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'bz333cc6',NULL,'admin','2015-10-25 18:18:03','admin','2015-10-25 18:18:34');

/*Table structure for table `etb_salesreport` */

DROP TABLE IF EXISTS `etb_salesreport`;

CREATE TABLE `etb_salesreport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `belongto` varchar(16) DEFAULT NULL COMMENT '0：上海，1：深圳',
  `salesreportno` varchar(32) DEFAULT NULL COMMENT '报告编号',
  `salesreportname` varchar(128) DEFAULT NULL COMMENT '报告名',
  `handler` varchar(32) DEFAULT NULL COMMENT '负责人',
  `registerdate` datetime DEFAULT NULL COMMENT '登记日期',
  `purpose` varchar(64) DEFAULT NULL COMMENT '用途',
  `approverid` varchar(32) DEFAULT NULL COMMENT '确认者',
  `reportpath01` varchar(100) DEFAULT NULL COMMENT '销售报告1文件路径',
  `reportpath02` varchar(100) DEFAULT NULL COMMENT '销售报告2文件路径',
  `reportpath03` varchar(100) DEFAULT NULL COMMENT '销售报告3文件路径',
  `reportpath04` varchar(100) DEFAULT NULL COMMENT '销售报告4文件路径',
  `reportpath05` varchar(100) DEFAULT NULL COMMENT '销售报告5文件路径',
  `customerid` varchar(20) DEFAULT NULL COMMENT '客户ID',
  `customername` varchar(64) DEFAULT NULL COMMENT '客户名',
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `etb_salesreport` */

/*Table structure for table `etb_sample` */

DROP TABLE IF EXISTS `etb_sample`;

CREATE TABLE `etb_sample` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warehousename` varchar(64) DEFAULT NULL,
  `belongto` varchar(16) DEFAULT NULL,
  `productid` varchar(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `customertype` int(4) DEFAULT NULL COMMENT '客户类型，1为供应商，2为客户',
  `customerid` varchar(20) DEFAULT NULL,
  `customername` varchar(64) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `rank` int(2) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `res01` varchar(32) DEFAULT NULL,
  `res02` varchar(32) DEFAULT NULL,
  `res03` varchar(32) DEFAULT NULL,
  `res04` varchar(32) DEFAULT NULL,
  `res05` varchar(32) DEFAULT NULL,
  `createuid` varchar(32) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updateuid` varchar(32) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `etb_sample` */

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

insert  into `etb_supplier`(`id`,`suppliername`,`belongto`,`note`,`suppliertel1`,`suppliermanager1`,`supplieraddress1`,`suppliermail1`,`supplierfax1`,`suppliertel2`,`suppliermanager2`,`supplieraddress2`,`suppliermail2`,`supplierfax2`,`suppliertel3`,`suppliermanager3`,`supplieraddress3`,`suppliermail3`,`supplierfax3`,`suppliertel4`,`suppliermanager4`,`supplieraddress4`,`suppliermail4`,`supplierfax4`,`suppliertel5`,`suppliermanager5`,`supplieraddress5`,`suppliermail5`,`supplierfax5`,`suppliertype`,`handlerid`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'沃尔玛','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL),(2,'家乐福','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00','admin11','2015-05-10 21:45:52'),(3,'迪亚天天','上海','测试用~~~','13912345678','小刘','浦东','zhao@163.com','12345678','1231','test','4234','liu@163.com','23423','','','','','','','','','','','','','','','',0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00','admin11','2015-05-16 10:33:17'),(4,'罗森','上海','测试用','13912345678','小刘','浦东','liu@163.com','12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-05-09 23:03:00',NULL,NULL);

/*Table structure for table `etb_warehouse` */

DROP TABLE IF EXISTS `etb_warehouse`;

CREATE TABLE `etb_warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parentid` varchar(32) DEFAULT NULL COMMENT '数据来源单号',
  `warehousetype` int(4) DEFAULT NULL COMMENT '类型',
  `warehouseno` varchar(32) DEFAULT NULL COMMENT '入出库单号',
  `warehousename` varchar(64) DEFAULT NULL COMMENT '库存名',
  `belongto` varchar(16) DEFAULT NULL COMMENT '所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '主题2',
  `productid` varchar(20) DEFAULT NULL COMMENT '产品ID号',
  `quantity` int(11) DEFAULT NULL COMMENT '入出库数量',
  `actualquantity` int(11) DEFAULT NULL COMMENT '实际入出库数量',
  `unitprice` decimal(11,6) DEFAULT NULL COMMENT '单价',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '入出库金额',
  `taxamount` decimal(11,2) DEFAULT NULL COMMENT '入出库金额（含税）',
  `warehousedate` datetime DEFAULT NULL COMMENT '入库日期',
  `plandate` varchar(32) DEFAULT NULL COMMENT '预计入库时间（yyyy-MM-dd）',
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
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8;

/*Data for the table `etb_warehouse` */

insert  into `etb_warehouse`(`id`,`parentid`,`warehousetype`,`warehouseno`,`warehousename`,`belongto`,`theme1`,`theme2`,`productid`,`quantity`,`actualquantity`,`unitprice`,`amount`,`taxamount`,`warehousedate`,`plandate`,`handler`,`supplierid`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (211,'CGD02015101922391855631c92',1,'KCN020151019223918bc9c3e66','SH01','0',NULL,'DS2015000027','2',5,NULL,'111.000000','555.00','649.35','2015-10-19 00:00:00','2015-10-19','admin',3,NULL,NULL,50,99,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:39:18','admin','2015-10-19 22:50:44'),(212,'CGD02015101922391855631c92',1,'KCN0201510192239392d6fcf12','SH01','0',NULL,'DS2015000027','2',4,NULL,'111.000000','444.00','519.48','2015-10-19 00:00:00','2015-10-19','admin',3,NULL,NULL,50,99,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:39:39','admin','2015-10-19 22:50:44'),(213,'CGD02015101922391855631c92',1,'KCN020151019223959baed979d','SH01','0',NULL,'DS2015000027','2',-1,NULL,'111.000000','-111.00','-129.87','2015-10-19 00:00:00','2015-10-19','admin',3,NULL,NULL,50,99,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:39:59','admin','2015-10-19 22:50:44'),(214,'CGD02015101922391855631c92',1,'KCN020151019224341bda2bc35','SH01','0',NULL,'DS2015000027','2',1,NULL,'111.000000','111.00','129.87','2015-10-19 00:00:00','2015-10-19','admin',3,NULL,NULL,50,99,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:43:41','admin','2015-10-19 22:50:44'),(215,'CGD02015101922391855631c92',1,'KCN02015101922435280c34ffa','SH01','0',NULL,'DS2015000027','2',1,NULL,'111.000000','111.00','129.87','2015-10-19 00:00:00','2015-10-19','admin',3,NULL,NULL,50,99,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:43:52','admin','2015-10-19 22:50:44'),(216,'CGD020151019225231b1148cde',1,'KCN020151019225231a9472fb2','SH01','0',NULL,'DS2015000028','4',50,NULL,'111.000000','5550.00','6493.50','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,99,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:31','admin','2015-10-19 22:56:21'),(217,'CGD020151019225231b1148cde',1,'KCN02015101922523160a6ea25','SH01','0',NULL,'DS2015000028','5',60,NULL,'111.000000','6660.00','7792.20','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,99,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:31','admin','2015-10-19 22:56:21'),(218,'XSD02015101923015788e62bd3',2,'KCN020151019230157742761f4','SH01','0',NULL,'10000011','3',-80,NULL,'122.000000','9760.00','11419.20','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,20,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:01:57','admin','2015-10-19 23:03:38'),(219,'XSD02015101923015788e62bd3',2,'KCN0201510192303104d20c084','SH01','0',NULL,'10000011','3',-10,NULL,'122.000000','1220.00','1427.40','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,20,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:03:10','admin','2015-10-19 23:03:38'),(220,'XSD02015101923015788e62bd3',2,'KCN020151019230321202a9627','SH01','0',NULL,'10000011','3',-1,NULL,'122.000000','122.00','142.74','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,20,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:03:21','admin','2015-10-19 23:03:38'),(221,'XSD02015101923015788e62bd3',2,'KCN020151019230327b8bd7c5c','SH01','0',NULL,'10000011','3',-9,NULL,'122.000000','1098.00','1284.66','2015-10-19 00:00:00','2015-10-19','admin',1,NULL,NULL,50,20,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:03:27','admin','2015-10-19 23:03:38'),(222,'XSD020151019230550b7c97f5b',2,'KCN0201510192305501412932a','SH01','0',NULL,'1100004','2',-10,NULL,'122.000000','1220.00','1427.40','2015-10-19 00:00:00','2015-10-19','admin',4,NULL,NULL,50,20,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:05:50','admin','2015-10-19 23:05:56'),(223,'XSD020151019230850d4ce68de',2,'KCN02015101923085029b855d5','SH01','0',NULL,'2000013','3',-30,NULL,'122.000000','3660.00','4282.20','2015-10-19 00:00:00','2015-10-19','user1',3,NULL,NULL,50,20,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user1','2015-10-19 23:08:50','user1','2015-10-19 23:08:55'),(224,'XSD020151024200336e8a68079',2,'KCN020151024200336fb389447','SH01','0',NULL,'a330000001','3',-15,NULL,'122.000000','1830.00','2141.10','2015-10-24 00:00:00','2015-10-24','admin',11,NULL,NULL,50,10,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-24 20:03:36','admin','2015-10-24 20:03:36'),(225,'XSD0201510251011564e96a324',2,'KCN0201510251011564e01e77c','SH01','0',NULL,'test22222','5',-1,NULL,'122.000000','122.00','142.74','2015-10-25 00:00:00','2015-10-25','admin',11,NULL,NULL,50,10,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:11:56','admin','2015-10-25 10:11:56'),(226,'XSD0201510251011564e96a324',2,'KCN020151025101156c03624cd','SH01','0',NULL,'test22222','33',-2,NULL,'1.000000','2.00','2.34','2015-10-25 00:00:00','2015-10-25','admin',11,NULL,NULL,50,10,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 10:11:56','admin','2015-10-25 10:11:56'),(227,'XSD02015102516452399423f0e',2,'KCN020151025164523667fdaae','SH01','0',NULL,'jj111111111','5',-1,NULL,'122.000000','122.00','142.74','2015-10-25 00:00:00','2015-10-25','admin',5,NULL,NULL,50,10,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:45:23','admin','2015-10-25 16:45:23'),(228,'XSD02015102516452399423f0e',2,'KCN020151025164523e8e356a5','SH01','0',NULL,'jj111111111','33',-2,NULL,'1.000000','2.00','2.34','2015-10-25 00:00:00','2015-10-25','admin',5,NULL,NULL,50,10,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:45:23','admin','2015-10-25 16:45:23'),(229,'CGD02015102516590180d98050',1,'KCN02015102516590212a4de88','SH01','0',NULL,'DS2015000030','5',2,NULL,'111.000000','222.00','259.74','2015-10-25 00:00:00','2015-10-25','admin',4,NULL,NULL,50,10,'03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 16:59:02','admin','2015-10-25 16:59:02'),(230,'CGD020151025174022bb2c4213',1,'KCN0201510251740225e915a5c','SH01','0',NULL,'DS2015000031','5',1,NULL,'111.000000','111.00','129.87','2015-10-25 00:00:00','2015-10-25','admin',4,NULL,NULL,50,10,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 17:40:22','admin','2015-10-25 17:40:22'),(231,'CGD020151025174022bb2c4213',1,'KCN020151025174022cac443ec','SH01','0',NULL,'DS2015000031','33',2,NULL,'1.000000','2.00','2.34','2015-10-25 00:00:00','2015-10-25','admin',4,NULL,NULL,50,10,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 17:40:22','admin','2015-10-25 17:40:22'),(232,'CGD020151025174022bb2c4213',1,'KCN02015102517483046b9eb3d','SH01','0',NULL,'DS2015000031','3',2,NULL,'111.000000','222.00','259.74','2015-10-25 00:00:00','2015-10-25','admin',4,NULL,NULL,50,10,'01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 17:48:30','admin','2015-10-25 17:48:30'),(233,'XSD020151025181731c372925e',2,'KCN0201510251817315057a407','SH01','0',NULL,'j444444444444','5',-1,NULL,'122.000000','122.00','142.74','2015-10-25 00:00:00','2015-10-25','admin',11,NULL,NULL,50,10,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 18:17:31','admin','2015-10-25 18:17:31'),(234,'XSD020151025181731c372925e',2,'KCN020151025181731aa94dc90','SH01','0',NULL,'j444444444444','33',-2,NULL,'2.000000','4.00','4.68','2015-10-25 00:00:00','2015-10-25','admin',11,NULL,NULL,50,10,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 18:17:31','admin','2015-10-25 18:17:31'),(235,'XSD020151025181731c372925e',2,'KCN0201510251818035b17f900','SH01','0',NULL,'j444444444444','3',-1,NULL,'122.000000','122.00','142.74','2015-10-25 00:00:00','2015-10-25','admin',11,NULL,NULL,50,10,'02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-25 18:18:03','admin','2015-10-25 18:18:03');

/*Table structure for table `etb_warehouserpt` */

DROP TABLE IF EXISTS `etb_warehouserpt`;

CREATE TABLE `etb_warehouserpt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `warehousetype` int(4) DEFAULT NULL COMMENT '类型：1为入库单，2为出库单',
  `warehouseno` varchar(32) DEFAULT NULL COMMENT '入出库单号',
  `warehousename` varchar(64) DEFAULT NULL COMMENT '仓库名',
  `productinfo` varchar(5000) DEFAULT NULL COMMENT '产品ID,产品数量,产品金额#产品ID,产品数量,产品金额',
  `belongto` varchar(16) DEFAULT NULL COMMENT '所属地（以后可能分上海和深圳）',
  `theme1` varchar(32) DEFAULT NULL COMMENT '主题1',
  `theme2` varchar(32) DEFAULT NULL COMMENT '主题2',
  `parentid` varchar(5000) DEFAULT NULL COMMENT '数据来源单号',
  `totaltaxamount` decimal(11,2) DEFAULT NULL COMMENT '入出库单总金额（含税）',
  `totalnum` int(11) DEFAULT NULL COMMENT '入库数量',
  `warehousedate` datetime DEFAULT NULL COMMENT '入出库单日期',
  `handler` varchar(32) DEFAULT NULL COMMENT '收货人',
  `supplierid` bigint(20) DEFAULT NULL COMMENT '供应商/客户',
  `suppliername` varchar(64) DEFAULT NULL COMMENT '供应商名/客户',
  `suppliertel` varchar(32) DEFAULT NULL COMMENT '供应商联系电话/客户',
  `suppliermanager` varchar(32) DEFAULT NULL COMMENT '供应商联系人/客户',
  `supplieraddress` varchar(128) DEFAULT NULL COMMENT '供应商地址/客户',
  `supplierfax` varchar(64) DEFAULT NULL COMMENT '供应商传真/客户',
  `suppliermail` varchar(64) DEFAULT NULL COMMENT '供应商邮箱/客户',
  `expressid` varchar(64) DEFAULT NULL COMMENT '快递公司ID',
  `expressname` varchar(64) DEFAULT NULL COMMENT '快递公司名',
  `expresstel` varchar(32) DEFAULT NULL COMMENT '快递公司联系电话',
  `expressfax` varchar(64) DEFAULT NULL COMMENT '快递公司传真',
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
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

/*Data for the table `etb_warehouserpt` */

insert  into `etb_warehouserpt`(`id`,`warehousetype`,`warehouseno`,`warehousename`,`productinfo`,`belongto`,`theme1`,`theme2`,`parentid`,`totaltaxamount`,`totalnum`,`warehousedate`,`handler`,`supplierid`,`suppliername`,`suppliertel`,`suppliermanager`,`supplieraddress`,`supplierfax`,`suppliermail`,`expressid`,`expressname`,`expresstel`,`expressfax`,`expressmanager`,`expressaddress`,`expressmail`,`expressno`,`expressamount`,`expresstaxamount`,`expressnote`,`approverid`,`note`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (70,1,'RKD0151019224811','SH01','2,5,649.35#2,4,519.48#2,-1,-129.87#2,1,129.87#2,1,129.87#','0',NULL,NULL,'KCN020151019223918bc9c3e66,KCN0201510192239392d6fcf12,KCN020151019223959baed979d,KCN020151019224341bda2bc35,KCN02015101922435280c34ffa,','1298.70',10,'2015-10-19 00:00:00','',3,'迪亚天天','13912345678','小刘','浦东','12345678','zhao@163.com','1','申通','ccc','fda','bbb','fad','ddd@eee.cc','12221',NULL,'10.00',NULL,NULL,'',50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:48:11','admin','2015-10-19 22:50:44'),(71,1,'RKD0151019225239','SH01','4,50,6493.50#5,60,7792.20#','0',NULL,NULL,'KCN020151019225231a9472fb2,KCN02015101922523160a6ea25,','14285.70',110,'2015-10-19 00:00:00','',1,'沃尔玛','13912345678','小刘','浦东','12345678','liu@163.com','4','韵达','bb','fda','aa','af','cc@cc.da','424424',NULL,'25.00',NULL,NULL,'',50,99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 22:52:39','admin','2015-10-19 22:56:21'),(72,2,'CKD0151019230338','SH01','3,-10,1427.40#3,-1,142.74#3,-9,1284.66#3,-80,11419.20#','0',NULL,NULL,'KCN0201510192303104d20c084,KCN020151019230321202a9627,KCN020151019230327b8bd7c5c,KCN020151019230157742761f4,','14274.00',100,'2015-10-19 00:00:00','',1,'微软','1123','test','1123','1123','1123@163.com','3','中通','bbb','cc','aa','bb','cc@dd.aa','32424',NULL,'10000.00',NULL,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:03:38','user1','2015-10-19 23:12:11'),(73,2,'CKD0151019230556','SH01','2,-10,1427.40#','0',NULL,NULL,'KCN0201510192305501412932a,','1427.40',10,'2015-10-19 00:00:00','',4,'GE','wet','we','wet','wet','wt@163.com','3','中通','bbb','cc','aa','bb','cc@dd.aa','42424',NULL,'1000.00',NULL,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2015-10-19 23:05:56','user1','2015-10-19 23:11:56'),(74,2,'CKD0151019230855','SH01','3,-30,4282.20#','0',NULL,NULL,'KCN02015101923085029b855d5,','4282.20',30,'2015-10-19 00:00:00','',3,'oracle','11','test','11','11','11@163.com','3','中通','bbb','cc','aa','bb','cc@dd.aa','212133',NULL,'100.00',NULL,NULL,'',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user1','2015-10-19 23:08:55','user1','2015-10-19 23:11:42');

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
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `tbdict01` */

insert  into `tbdict01`(`id`,`fieldcode`,`fieldname`,`code`,`lang`,`mean`,`note`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'goods','電子線','01','c','Electronic Wire','电子线',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(2,'goods','熱収缩套管','02','c','Heat-Shrink-Tube','热收缩套管',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(3,'goods','柔軟扁平電纜','03','c','Flexible Flat Cable','柔软扁平电缆',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(4,'goods','漆包線','04','c','Magnesium Alloy','漆包线',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(5,'goods','線束加工','05','c','Wiring Harness','线束加工',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(6,'goods','柔軟印刷電路板','06','c','Flexible Printed Circuit Board','柔软印刷电路板',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(7,'goods','其他','07','c','其他','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(8,'01_item01','80℃～105℃','01','c','耐温','电线耐温80℃～105℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(9,'01_item01','105℃～150℃','02','c','耐温','电线耐温105℃～150℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(10,'01_item01','150℃～200℃及以上','03','c','耐温','电线耐温150℃～200℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(11,'01_item02','30V～100V','001','c','耐压','耐压30V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(12,'01_item02','100V～600V','002','c','耐压','耐压60V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(13,'01_item02','1KV及以上','003','c','耐压','耐压150V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(14,'01_item03','PVC','01','c','材质','材质PVC',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(15,'01_item03','PE','02','c','材质','材质PE',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(16,'01_item03','其他','03','c','材质','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(17,'01_item04','LEAD Free','01','c','环保','LEAD Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(18,'01_item04','Halogen Free','02','c','环保','Halogen Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(19,'02_item01','80℃～105℃','01','c','耐温','套管耐温80℃～105℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(20,'02_item01','105℃～150℃','02','c','耐温','套管耐温105℃～150℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(21,'02_item01','150℃～200℃及以上','03','c','耐温','套管耐温150℃～200℃',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(22,'02_item02','30V～100V','001','c','耐压','耐压30V～100V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(23,'02_item02','100V～600V','002','c','耐压','耐压100V～600V',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(24,'02_item02','1KV及以上','003','c','耐压','耐压1KV及以上',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(25,'02_item03','双层绝缘','01','c','绝缘','绝缘双层绝缘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(26,'02_item03','单层绝缘','02','c','绝缘','绝缘单层绝缘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(27,'02_item04','2:1','02','c','收缩比','收缩比2:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(28,'02_item04','3:1','04','c','收缩比','收缩比3:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(29,'02_item04','4:1','05','c','收缩比','收缩比4:1',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(30,'02_item05','PE','01','c','材质','PE',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(31,'02_item05','其他','02','c','材质','其他',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(32,'02_item06','LEAD Free','01','c','环保','LEAD Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(33,'02_item06','Halogen Free','02','c','环保','Halogen Free',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(34,'makearea','日本','001','c','产地','产地日本',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(35,'makearea','马来西亚','002','c','产地','产地马来西亚',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(36,'makearea','苏州','003','c','产地','产地苏州',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(37,'unit','米','002','c','单位','米',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(38,'unit','卷','003','c','单位','卷',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(39,'unit','箱','004','c','单位','箱',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(40,'unit','盘','005','c','单位','盘',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(41,'unit','套','006','c','单位','套',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(42,'unit','PSC','007','c','单位','PSC',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(43,'unit','根','008','c','单位','根',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(44,'unit','其它','009','c','单位','其它',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(45,'color','红','01','c','颜色','红',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(46,'color','橙','02','c','颜色','橙',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(47,'color','黄','03','c','颜色','黄',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(48,'color','绿','04','c','颜色','绿',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(49,'color','蓝','05','c','颜色','蓝',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(50,'color','靛','06','c','颜色','靛',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(51,'color','紫','07','c','颜色','紫',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(52,'color','灰','08','c','颜色','灰',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(53,'color','白','09','c','颜色','白',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(54,'color','黑','10','c','颜色','黑',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(55,'color','金','11','c','颜色','金',0,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(56,'color','银','12','c','颜色','银',0,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(57,'color','粉红','13','c','颜色','粉红',1,'admin','2015-03-08 00:00:00','admin','2015-03-08 00:00:00'),(58,'color','棕','14','c','颜色','棕',1,'admin','2015-03-08 00:00:00','admin','2015-03-08 00:00:00'),(59,'02_item04','1.5:1','01','c','收缩比','收缩比1.5:1',1,'admin','2015-03-09 00:00:00','admin','2015-03-09 00:00:00'),(60,'02_item04','2.4:1','03','c','收缩比','收缩比2.4:1',1,'admin','2015-03-09 00:00:00','admin','2015-03-09 00:00:00'),(61,'color','透明','15','c','颜色','透明',1,'admin','2015-03-11 00:00:00','admin','2015-03-11 00:00:00'),(62,'02_item01','其他','04','c','耐温','耐温其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(63,'02_item02','其他','04','c','耐压','耐压其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(64,'02_item03','其他','03','c','绝缘','绝缘其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(65,'02_item04','其他','06','c','收缩比','收缩比其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(66,'01_item01','其他','04','c','耐温','耐温其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(67,'01_item02','其他','04','c','耐压','耐压其他',1,'admin','2015-03-16 00:00:00','admin','2015-03-16 00:00:00'),(70,'rate','税率','0.17','c','税率','税率',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(71,'financetheme','工资','01','c','财务主题','工资',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(72,'financetheme','办公用品','02','c','财务主题','办公用品',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(73,'financetheme','出差费','03','c','财务主题','出差费',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(81,'pay','款到发货','01','c','支付方式','款到发货',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(82,'pay','货到付款','02','c','支付方式','货到付款',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(83,'pay','月结','03','c','支付方式','月结',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(84,'pay','电汇','04','c','支付方式','电汇',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(91,'purchaseorder2015','采购单番号2015','32','c','采购单番号2015','采购单番号2015',1,'admin','2014-12-16 00:00:00','admin','2015-10-25 17:40:22'),(100,'finance','type','1','c','采购单','采购单',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(101,'finance','type','2','c','订单','订单',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(102,'finance','type','3','c','快递单','快递单',1,'admin','2014-12-16 00:00:00','admin','2014-12-16 00:00:00'),(104,'expresserorder','快递商番号','3','c','快递商番号','快递商番号',1,'admin','2015-09-20 11:08:49','admin','2015-09-20 11:09:34');

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `tbresource` */

insert  into `tbresource`(`id`,`url`,`note`,`status`,`createtime`,`sort`,`parentid`,`restype`) values (1,'采购','采购',1,'2015-04-28 00:00:00',1,-1,1),(2,'销售','销售',1,'2015-05-10 00:00:00',2,-1,1),(3,'库存','库存',1,'2015-05-10 00:00:00',3,-1,1),(4,'产品','产品',1,'2015-04-28 00:00:00',4,-1,1),(5,'财务','财务',1,'2015-05-10 00:00:00',5,-1,1),(6,'分析','分析',1,'2015-05-10 00:00:00',6,-1,1),(7,'企业管理','企业管理',1,'2015-05-10 00:00:00',7,-1,1),(8,'系统管理','系统管理',1,'2015-05-10 00:00:00',8,-1,1),(9,'个人信息','个人信息',1,'2015-05-10 00:00:00',9,-1,1),(10,'/purchase/showPurchaseAction.action','采购单管理',1,'2015-04-28 00:00:00',1,1,2),(11,'/supplier/showSupplierAction.action','供应商管理',1,'2015-05-10 00:00:00',2,1,2),(12,'/sales/showSalesAction.action','销售单管理',1,'2015-05-10 00:00:00',1,2,2),(13,'/customer/showEtbCustomerAction.action','客户管理',1,'2015-04-28 00:00:00',2,2,2),(14,'/warehouse/showWarehouseInOkAction.action','预入库确认',1,'2015-05-10 00:00:00',1,3,2),(15,'/warehouse/showWarehouseOutOkAction.action','预出库确认',1,'2015-05-10 00:00:00',2,3,2),(16,'/warehouserpt/showWarehouserptInAction.action','入库单一览',1,'2015-05-10 00:00:00',3,3,2),(17,'/warehouserpt/showWarehouserptOutAction.action','发货单一览',1,'2015-05-10 00:00:00',4,3,2),(18,'/warehouse/showProductOkAction.action','仓位确认',1,'2015-05-10 00:00:00',5,3,2),(19,'/warehouse/showPositionCollectAction.action','库存盘点',1,'2015-05-10 00:00:00',6,3,2),(20,'/product/showProductAction.action','产品管理',1,'2015-04-28 00:00:00',1,4,2),(21,'/finance/showFinanceInAction.action','入库单审核',1,'2015-05-10 00:00:00',1,5,2),(22,'/finance/showFinanceOutAction.action','出库单审核',1,'2015-05-10 00:00:00',2,5,2),(23,'/finance/showFinanceAction.action','账目管理',1,'2015-05-10 00:00:00',3,5,2),(24,'/chart/showSaleInfoMainChartAction.action','销售信息分析',1,'2015-05-10 00:00:00',2,6,2),(25,'/chart/showSaleDetailInfoMainChartAction.action','销售详细信息分析',1,'2015-05-10 00:00:00',3,6,2),(26,'/chart/showPurchaseInfoMainChartAction.action','采购信息分析',1,'2015-05-10 00:00:00',4,6,2),(27,'/chart/showDeliveryInfoMainChartAction.action','物流信息分析',1,'2015-05-10 00:00:00',5,6,2),(28,'/chart/showAccountInfoMainChartAction.action','财务信息分析',1,'2015-05-10 00:00:00',6,6,2),(29,'/chart/showSupplierInfoMainChartAction.action','供应商信息分析',1,'2015-05-10 00:00:00',7,6,2),(30,'/chart/showCustomerInfoMainChartAction.action','客户信息分析',1,'2015-05-10 00:00:00',8,6,2),(31,'/intermana/showCalendarAction.action','考勤管理',1,'2015-05-10 00:00:00',1,8,2),(32,'/document/showEtbDocumentAction.action','文件/物品管理',1,'2015-05-10 00:00:00',2,8,2),(33,'/personal/showEtbPersonalAction.action','员工档案',1,'2015-05-10 00:00:00',2,7,2),(34,'/assets/showEtbAssetsAction.action','固定资产档案',1,'2015-05-10 00:00:00',4,5,2),(35,'/customer/showEtbCustomerManageAction.action','客户指定',1,'2015-05-10 00:00:00',1,7,2),(36,'/chart/showSaleTotalInfoMainChartAction.action','销售综合信息分析',1,'2015-05-10 00:00:00',1,6,2),(37,'/warehouse/showWarehouseDetailAction.action','产品对照表',1,'2015-05-10 00:00:00',3,2,2),(38,'/product/showProductCostCheckAction.action','产品成本信息',1,'2015-05-10 00:00:00',9,6,2),(39,'/salesreport/showSalesReportAction.action','销售报告上传',1,'2015-05-10 00:00:00',5,2,2),(40,'/warehouse/showWarehouseRefundAction.action','库存修正',1,'2015-05-10 00:00:00',7,3,2),(41,'/user/showUpdUserPsdAction.action','修改密码',1,'2015-05-10 00:00:00',1,9,2),(42,'/finance/showFinanceExpressAction.action','快递单管理',1,'2015-05-10 00:00:00',8,3,2),(43,'/delivery/showEtbDeliveryAction.action','快递公司管理',1,'2015-05-10 00:00:00',9,3,2),(44,'/sample/showSampleAction.action','样品管理',1,'2015-05-10 00:00:00',4,2,2),(45,'/qa/showQaAction.action','咨询Q/A管理',1,'2015-05-10 00:00:00',6,2,2),(46,'/user/showUserManageAction.action','用户管理',1,'2015-05-10 00:00:00',3,7,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tbrole` */

insert  into `tbrole`(`id`,`rolecode`,`rolename`,`rank`,`note`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (1,'admin','系统管理员',99,'系统管理员',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(2,'normal','一般用户',70,'只能输入和更新产品、库存和新闻，不能删除产品、库存和新闻',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(3,'operator','操作员',50,'只能输入和更新产品和库存，不能删除产品和库存，可以看新闻',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(4,'saler','销售员',50,'销售员',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(5,'buyer','采购员',50,'采购员',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(6,'warehouser','仓库管理员',50,'仓库管理员\r\n',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(7,'fanical','财务',50,'财务,出纳',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(8,'manager','经理',80,'经理',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(9,'boss','总经理',90,'总经理',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00'),(10,'buysaler','采购销售员',50,'采购销售员',1,'admin','2014-12-08 00:00:00','admin','2014-12-08 00:00:00');

/*Table structure for table `tbroleres` */

DROP TABLE IF EXISTS `tbroleres`;

CREATE TABLE `tbroleres` (
  `roleid` int(10) DEFAULT NULL,
  `resourceid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbroleres` */

insert  into `tbroleres`(`roleid`,`resourceid`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(9,1),(9,2),(9,3),(9,4),(9,5),(9,6),(9,7),(9,8),(9,9),(9,10),(9,11),(9,12),(9,13),(9,14),(9,15),(9,16),(9,17),(9,18),(9,19),(9,20),(9,21),(9,22),(9,23),(9,24),(9,25),(9,26),(9,27),(9,28),(9,29),(9,30),(9,31),(9,32),(9,33),(9,34),(9,35),(9,36),(9,37),(9,38),(9,39),(9,40),(9,41),(9,42),(9,43),(9,44),(9,45),(9,46),(8,1),(8,2),(8,3),(8,4),(8,5),(8,7),(8,8),(8,9),(8,10),(8,11),(8,12),(8,13),(8,14),(8,15),(8,16),(8,17),(8,18),(8,19),(8,20),(8,21),(8,22),(8,23),(8,31),(8,32),(8,33),(8,34),(8,37),(8,38),(8,39),(8,40),(8,41),(8,42),(8,44),(8,45),(8,46),(7,5),(7,8),(7,9),(7,21),(7,22),(7,23),(7,31),(7,32),(7,34),(7,41),(10,1),(10,2),(10,8),(10,9),(10,10),(10,11),(10,12),(10,13),(10,31),(10,32),(10,37),(10,39),(10,41),(10,44),(10,45),(5,1),(5,2),(5,8),(5,9),(5,10),(5,11),(5,31),(5,32),(5,37),(5,39),(5,41),(5,44),(5,45),(4,2),(4,8),(4,9),(4,12),(4,13),(4,31),(4,32),(4,37),(4,39),(4,41),(4,44),(4,45),(6,3),(6,8),(6,9),(6,14),(6,15),(6,16),(6,17),(6,18),(6,19),(6,31),(6,32),(6,40),(6,42),(6,43),(6,41),(4,4),(4,20),(5,4),(5,20),(10,4),(10,20);

/*Table structure for table `tbuser` */

DROP TABLE IF EXISTS `tbuser`;

CREATE TABLE `tbuser` (
  `userid` varchar(32) NOT NULL COMMENT '用户登录ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `belongto` varchar(4) DEFAULT NULL COMMENT '0上海，1深圳，99全部',
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

insert  into `tbuser`(`userid`,`username`,`belongto`,`password`,`rolecode`,`status`,`note`,`createuid`,`createdate`,`updateuid`,`updatedate`) values ('admin','admin11','0','b59c67bf196a4758191e42f76670ceba','admin',1,'admin addaaa','admin','2014-12-08 00:00:00','admin','2015-07-27 22:54:20'),('normal','normalname','0','b59c67bf196a4758191e42f76670ceba','normal',1,'fdas','admin11','2015-01-11 21:40:47','admin11','2015-01-11 21:40:47'),('test22','aa11','0','b59c67bf196a4758191e42f76670ceba','operator',2,'aa22','admin','2015-07-25 21:15:54','admin','2015-07-25 21:38:39'),('test222','11','0','b59c67bf196a4758191e42f76670ceba','boss',1,'','admin','2015-09-20 11:37:12','admin','2015-09-20 11:37:12'),('user1','user1','0','b59c67bf196a4758191e42f76670ceba','admin',1,'user1','admin11','2015-01-11 21:30:13','admin11','2015-01-11 21:30:13'),('wh11','whname','0','b59c67bf196a4758191e42f76670ceba','warehouser',1,'fdsafdsa111','admin11','2015-01-11 21:30:13','admin11','2015-01-11 21:35:16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
