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

/*Table structure for table `tbcase` */

DROP TABLE IF EXISTS `tbcase`;

CREATE TABLE `tbcase` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `fieldcode` varchar(8) DEFAULT NULL COMMENT '产品类型',
  `data` varchar(5000) DEFAULT NULL COMMENT '内容',
  `pic01` varchar(64) DEFAULT NULL COMMENT '图片1路径',
  `pic02` varchar(64) DEFAULT NULL COMMENT '图片2路径',
  `pic03` varchar(64) DEFAULT NULL COMMENT '图片3路径',
  `pic04` varchar(64) DEFAULT NULL COMMENT '图片4路径',
  `pic05` varchar(64) DEFAULT NULL COMMENT '图片5路径',
  `res01` varchar(512) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(512) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(512) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(512) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(512) DEFAULT NULL COMMENT '预备项目5',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `createuid` varchar(32) DEFAULT NULL COMMENT '数据创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '数据更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbcase` */

insert  into `tbcase`(`id`,`title`,`fieldcode`,`data`,`pic01`,`pic02`,`pic03`,`pic04`,`pic05`,`res01`,`res02`,`res03`,`res04`,`res05`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (8,'test1aba23111','06','afdafdafdsaddd\r\n\r\nfdafd\r\nfda\r\nfda\r\nfdsa\r\nfdsafdsafdsafdsafdsafd\r\nfd\r\nsaf\r\nds\r\naffdsafdsafdsafd','20150207164600221399294.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'admin11','2015-01-01 00:00:00','admin','2015-02-07 16:46:00');

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

/*Table structure for table `tbnews` */

DROP TABLE IF EXISTS `tbnews`;

CREATE TABLE `tbnews` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `showno` int(4) DEFAULT NULL COMMENT '显示顺序号',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `data` varchar(5000) DEFAULT NULL COMMENT '内容',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `pic01` varchar(64) DEFAULT NULL COMMENT '图片1路径',
  `pic02` varchar(64) DEFAULT NULL COMMENT '图片2路径',
  `pic03` varchar(64) DEFAULT NULL COMMENT '图片3路径',
  `pic04` varchar(64) DEFAULT NULL COMMENT '图片4路径',
  `pic05` varchar(64) DEFAULT NULL COMMENT '图片5路径',
  `res01` varchar(512) DEFAULT NULL COMMENT '预备项目1',
  `res02` varchar(512) DEFAULT NULL COMMENT '预备项目2',
  `res03` varchar(512) DEFAULT NULL COMMENT '预备项目3',
  `res04` varchar(512) DEFAULT NULL COMMENT '预备项目4',
  `res05` varchar(512) DEFAULT NULL COMMENT '预备项目5',
  `newsdate` varchar(10) DEFAULT NULL COMMENT '新闻日期',
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他为无效',
  `createuid` varchar(32) DEFAULT NULL COMMENT '数据创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '数据更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tbnews` */

insert  into `tbnews`(`id`,`showno`,`title`,`data`,`author`,`pic01`,`pic02`,`pic03`,`pic04`,`pic05`,`res01`,`res02`,`res03`,`res04`,`res05`,`newsdate`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (4,NULL,'11111','333333333333','2222222222','20141228023856221aa15be.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-02',1,'admin','2014-12-27 21:35:45','admin','2015-01-03 01:49:34'),(5,NULL,'ABABABEEE','        fasfsa\r\n       测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行测试换行\r\nbbbadafdasfdsafdsafdsafdsafdsa\r\n\r\ndafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafddafd\r\nd\r\nfdasaaaaaaaaaaaaaaaaaaaaaaaaaaaa\r\nfdsa','3wwe','20150103015024559095b95.JPG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-04',1,'admin','2015-01-03 01:50:24','admin','2015-01-06 01:07:01'),(6,NULL,'1111','dsafdsafdsa\r\nfdasfdsa\r\nfdsafdafdaf1111111111111\r\n\r\nfd\r\nsafdsafdsfdsafdsafdsa','2222','20150107024839079019417.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-08',1,'admin','2015-01-07 02:42:23','admin','2015-01-07 02:48:39'),(7,NULL,'tes1111111','fdafdsa11\r\n\r\nfdsafdsafdsa22','avcc','20150108004812173387982.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-01-08',1,'admin','2015-01-08 00:33:02','admin','2015-01-08 00:48:12');

/*Table structure for table `tbproduct01` */

DROP TABLE IF EXISTS `tbproduct01`;

CREATE TABLE `tbproduct01` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fieldcode` varchar(8) DEFAULT NULL COMMENT '品种01：电线，02：套管，03：扁平线，04：线束，05：连接器，06：FPC',
  `cata1` varchar(8) DEFAULT NULL COMMENT '大分类',
  `cata2` varchar(8) DEFAULT NULL COMMENT '中分类',
  `cata3` varchar(8) DEFAULT NULL COMMENT '小分类',
  `nameno` varchar(64) DEFAULT NULL COMMENT '名称代码',
  `typeno` varchar(64) DEFAULT NULL COMMENT '型号',
  `typenosub` varchar(64) DEFAULT NULL COMMENT '型号辅助',
  `color1` varchar(64) DEFAULT NULL COMMENT '颜色1',
  `color2` varchar(64) DEFAULT NULL COMMENT '颜色2（备用）',
  `size01` varchar(64) DEFAULT NULL COMMENT '规格1',
  `size02` varchar(64) DEFAULT NULL COMMENT '规格2',
  `makearea` varchar(64) DEFAULT NULL COMMENT '产地',
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `tbproduct01` */

insert  into `tbproduct01`(`id`,`fieldcode`,`cata1`,`cata2`,`cata3`,`nameno`,`typeno`,`typenosub`,`color1`,`color2`,`size01`,`size02`,`makearea`,`item01`,`item02`,`item03`,`item04`,`item05`,`item06`,`item07`,`item08`,`item09`,`item10`,`item11`,`item12`,`item13`,`item14`,`item15`,`item16`,`item17`,`item18`,`item19`,`item20`,`item21`,`item22`,`item23`,`item24`,`item25`,`item26`,`item27`,`item28`,`item29`,`item30`,`pic01`,`pic02`,`pic03`,`pic04`,`pic05`,`pdfpath`,`rank`,`status`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`keyword`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (38,'01',NULL,NULL,NULL,'test1111','abaa',NULL,'04',NULL,NULL,NULL,NULL,'01','002','02','02','02','',NULL,'newbbb','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','',NULL,NULL,'2015021422181551223bc21.pdf',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'熱収缩套管,热收缩套管,test1111,newbbb,,80℃～105℃,100V～600V,单层绝缘,3:1,其他,abaa,绿,','admin','2015-02-14 22:18:15','admin','2015-02-26 22:47:32'),(39,'01',NULL,NULL,NULL,'test1111','abaa1',NULL,'02',NULL,NULL,NULL,NULL,'02','002','02','','','',NULL,'immb','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','',NULL,NULL,'20150214222403180cfefb3.pdf',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,test2222,immb,,105℃～150℃,100V～600V,PE,bbaeee,橙,','admin','2015-02-14 22:24:03','admin','2015-02-26 22:22:00'),(40,'01',NULL,NULL,NULL,'abccc','d对对对',NULL,'03',NULL,NULL,NULL,NULL,'02','002','02','01','','',NULL,'fdafdsa','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','',NULL,NULL,'201502262239137963bea33.pdf',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'電子線,电子线,abccc,fdafdsa,,105℃～150℃,100V～600V,PE,LEAD Free,d对对对,黄,','admin','2015-02-26 22:39:13','admin','2015-03-01 11:19:22'),(41,'02',NULL,NULL,NULL,'hbtest1','111',NULL,'02',NULL,NULL,NULL,NULL,'01','002','01','02','01','02',NULL,'222','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','','',NULL,NULL,'20150226224916708a0be7a.pdf',50,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'熱収缩套管,热收缩套管,hbtest1,222,,80℃～105℃,100V～600V,双层绝缘,3:1,PE,Halogen Free,111,橙,','admin','2015-02-26 22:49:16','admin','2015-03-01 12:52:49');

/*Table structure for table `tbqa` */

DROP TABLE IF EXISTS `tbqa`;

CREATE TABLE `tbqa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(64) DEFAULT NULL,
  `Data` varchar(5000) DEFAULT NULL,
  `fullname` varchar(32) DEFAULT NULL,
  `company` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `tell` varchar(32) DEFAULT NULL,
  `fax` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `Res01` varchar(128) DEFAULT NULL,
  `Res02` varchar(128) DEFAULT NULL,
  `Res03` varchar(128) DEFAULT NULL,
  `Res04` varchar(128) DEFAULT NULL,
  `Res05` varchar(128) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `CreateUid` varchar(32) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdateUid` varchar(32) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tbqa` */

insert  into `tbqa`(`id`,`Title`,`Data`,`fullname`,`company`,`address`,`tell`,`fax`,`email`,`ip`,`Res01`,`Res02`,`Res03`,`Res04`,`Res05`,`status`,`CreateUid`,`CreateDate`,`UpdateUid`,`UpdateDate`) values (5,'aaa','bbb','ccc','ddd','eee','fff','gg','hhh','127.0.0.1',NULL,NULL,NULL,NULL,NULL,0,NULL,'2015-02-07 00:26:42','admin','2015-02-07 00:30:07');

/*Table structure for table `tbrecruit` */

DROP TABLE IF EXISTS `tbrecruit`;

CREATE TABLE `tbrecruit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) DEFAULT NULL,
  `recruittype` char(2) DEFAULT NULL,
  `Data` varchar(5000) DEFAULT NULL,
  `Persons` int(10) DEFAULT NULL,
  `Res01` varchar(1000) DEFAULT NULL,
  `Res02` varchar(1000) DEFAULT NULL,
  `Res03` varchar(1000) DEFAULT NULL,
  `Res04` varchar(1000) DEFAULT NULL,
  `Res05` varchar(1000) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `CreateUid` varchar(32) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdateUid` varchar(32) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbrecruit` */

insert  into `tbrecruit`(`id`,`Title`,`recruittype`,`Data`,`Persons`,`Res01`,`Res02`,`Res03`,`Res04`,`Res05`,`status`,`CreateUid`,`CreateDate`,`UpdateUid`,`UpdateDate`) values (2,'testt111',NULL,'fdsafdafa\r\nfdafdsa',1,NULL,NULL,NULL,NULL,NULL,1,'admin','2015-02-07 15:48:30','admin','2015-02-07 15:48:30');

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
  `restype` int(4) DEFAULT NULL COMMENT '资源类型：1为主节点，其他为子节',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tbresource` */

insert  into `tbresource`(`id`,`url`,`note`,`status`,`createtime`,`sort`,`parentid`,`restype`) values (1,'主节点1','主节点1',1,'2015-04-28 00:00:00',1,-1,1),(2,'主节点2','主节点2',1,'2015-04-28 00:00:00',2,-1,1),(3,'url1','子节点1',1,'2015-04-28 00:00:00',1,1,2),(4,'url2','子节点2',1,'2015-04-28 00:00:00',1,2,2);

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

insert  into `tbroleres`(`roleid`,`resourceid`) values (1,1),(1,2),(2,1),(2,2),(1,3),(1,4);

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

/*Table structure for table `tbwarehouse` */

DROP TABLE IF EXISTS `tbwarehouse`;

CREATE TABLE `tbwarehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `productid` bigint(20) DEFAULT NULL COMMENT '产品表ID',
  `item01` decimal(10,2) DEFAULT NULL COMMENT '入库',
  `item02` decimal(10,2) DEFAULT NULL COMMENT '出库',
  `item03` varchar(32) DEFAULT NULL COMMENT '定单单位',
  `item04` varchar(32) DEFAULT NULL COMMENT '送货期',
  `item05` decimal(10,2) DEFAULT NULL COMMENT '项目5',
  `item06` decimal(10,2) DEFAULT NULL COMMENT '项目6',
  `item07` decimal(10,2) DEFAULT NULL COMMENT '项目7',
  `item08` decimal(10,2) DEFAULT NULL COMMENT '项目8',
  `item09` decimal(10,2) DEFAULT NULL COMMENT '项目9',
  `item10` decimal(10,2) DEFAULT NULL COMMENT '项目10',
  `rank` int(4) DEFAULT NULL COMMENT '级别（0-99）',
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
  `status` int(4) DEFAULT NULL COMMENT '状态，1为有效，其他无效',
  `createuid` varchar(32) DEFAULT NULL COMMENT '数据创建者',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateuid` varchar(32) DEFAULT NULL COMMENT '数据更新者',
  `updatedate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbwarehouse` */

insert  into `tbwarehouse`(`id`,`productid`,`item01`,`item02`,`item03`,`item04`,`item05`,`item06`,`item07`,`item08`,`item09`,`item10`,`rank`,`res01`,`res02`,`res03`,`res04`,`res05`,`res06`,`res07`,`res08`,`res09`,`res10`,`status`,`createuid`,`createdate`,`updateuid`,`updatedate`) values (5,38,'300.00',NULL,'12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,'008','002','1','0',NULL,NULL,NULL,NULL,NULL,NULL,0,'admin','2015-02-14 22:18:47','admin','2015-02-26 21:02:14'),(6,38,'400.00',NULL,'45',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,'003','001','0','1',NULL,NULL,NULL,NULL,NULL,NULL,1,'admin','2015-02-14 22:19:01','admin','2015-02-14 22:19:01'),(7,39,'1000.00',NULL,'23',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,'005','003','0','1',NULL,NULL,NULL,NULL,NULL,NULL,0,'admin','2015-02-14 22:24:25','admin','2015-02-14 22:33:57'),(8,39,'5400.00',NULL,'60',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,'004','001','0','1',NULL,NULL,NULL,NULL,NULL,NULL,0,'admin','2015-02-14 22:26:22','admin','2015-02-14 22:33:57');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
