/*
SQLyog v10.2 
MySQL - 5.1.68-community : Database - yellowcong
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yellowcong` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yellowcong`;

/*Table structure for table `cas_user` */

DROP TABLE IF EXISTS `cas_user`;

CREATE TABLE `cas_user` (
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `age` tinyint(4) DEFAULT '0' COMMENT '年龄',
  `addr` varchar(64) DEFAULT '' COMMENT '住址',
  `phone` varchar(11) DEFAULT '' COMMENT '电话'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cas_user` */

insert  into `cas_user`(`username`,`password`,`email`,`age`,`addr`,`phone`) values ('yellowcong','yellowcong','yellowcong@aliyun.com',18,'haerbin','110'),('doubi','doubi','717350389@qq.com',12,'beijin','110'),('zhangsan','zhangsan','717350389@qq.com',12,'wuhan','110'),('','','',0,'','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
