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

/*Table structure for table `yellowcong_users` */

DROP TABLE IF EXISTS `yellowcong_users`;

CREATE TABLE `yellowcong_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `yellowcong_users` */

insert  into `yellowcong_users`(`id`,`age`,`nick_name`,`password`,`user_name`) values (1,18,'test1','test3','test1'),(2,22,'yellowcong','yellowcong','yellowcong'),(3,24,'test','test','test'),(4,24,'test4','test4','test4'),(5,23,'yellowlu','yellowlu','yellowlu'),(6,25,'yellow','yellow3','yellow3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
