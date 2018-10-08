-- 单表的方式， 不推荐
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

/*Table structure for table `sys_attrs` */

DROP TABLE IF EXISTS `sys_attrs`;

CREATE TABLE `sys_attrs` (
  `USERNAME` varchar(30) NOT NULL,
  `ATTR_KEY` varchar(50) NOT NULL,
  `ATTR_VAL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_attrs` */

insert  into `sys_attrs`(`USERNAME`,`ATTR_KEY`,`ATTR_VAL`) values ('yellowcong','role','ADMIN_ROLE'),('yellowcong','role','MANAGEMENT_ROLE'),('yellowcong','role','DEV_ROLE'),('yellowcong','acl','doubi1'),('yellowcong','acl','doubi2');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  `username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`email`,`password`,`create_time`,`last_login_time`,`status`,`username`) values (2,'717350389@qq.com','yellowcong',NULL,NULL,1,'yellowcong');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
