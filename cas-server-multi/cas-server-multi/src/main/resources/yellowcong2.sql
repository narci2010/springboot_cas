-- 多表的方式（ 推荐使用）
/*
SQLyog v10.2 
MySQL - 5.1.68-community : Database - yellowcong2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yellowcong2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yellowcong2`;

/*Table structure for table `sys_acl` */

DROP TABLE IF EXISTS `sys_acl`;

CREATE TABLE `sys_acl` (
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `acl` varchar(32) DEFAULT NULL COMMENT '权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_acl` */

insert  into `sys_acl`(`username`,`acl`) values ('yellowcong','打酱油'),('yellowcong','java'),('yellowcong','都比权限');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `role` varchar(16) DEFAULT '' COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`username`,`role`) values ('yellowcong','管理员'),('yellowcong','老师');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`email`,`password`,`create_time`,`last_login_time`,`status`,`username`) values (1,'717350389@qq.com','yellowcong',NULL,NULL,1,'yellowcong');

/*Table structure for table `sys_attrs` */

DROP TABLE IF EXISTS `sys_attrs`;

/*!50001 DROP VIEW IF EXISTS `sys_attrs` */;
/*!50001 DROP TABLE IF EXISTS `sys_attrs` */;

/*!50001 CREATE TABLE  `sys_attrs`(
 `USERNAME` varchar(32) ,
 `ATTR_KEY` varchar(4) ,
 `ATTR_VAL` varchar(32) 
)*/;

/*View structure for view sys_attrs */

/*!50001 DROP TABLE IF EXISTS `sys_attrs` */;
/*!50001 DROP VIEW IF EXISTS `sys_attrs` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_attrs` AS select `a`.`username` AS `USERNAME`,'acl' AS `ATTR_KEY`,`b`.`acl` AS `ATTR_VAL` from (`sys_user` `a` join `sys_acl` `b`) where (`a`.`username` = `b`.`username`) union select `a`.`username` AS `USERNAME`,'role' AS `ATTR_KEY`,`b`.`role` AS `ATTR_VAL` from (`sys_user` `a` join `sys_role` `b`) where (`a`.`username` = `b`.`username`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- 视图
CREATE VIEW sys_attrs AS 
-- 查询出访问权限的信息
SELECT 
  a.`username` AS 'USERNAME',
  'acl' AS 'ATTR_KEY' ,
  b.`acl` AS 'ATTR_VAL'
FROM
  sys_user a,
  sys_acl b
WHERE a.`username` = b.`username`
UNION
-- 查询 角色的信息
SELECT 
  a.`username`  AS 'USERNAME',
  -- 角色
  'role' AS 'ATTR_KEY' ,
  b.role AS 'ATTR_VAL'
FROM
  sys_user a,
  sys_role b
WHERE a.`username` = b.`username`
