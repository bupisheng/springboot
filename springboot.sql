/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springboot`;

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `eid` varchar(64) NOT NULL,
  `ename` varchar(60) DEFAULT NULL COMMENT '员工名称',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别0女 1男',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态0禁用 1启用',
  `hireDate` datetime DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`eid`,`ename`,`sex`,`status`,`hireDate`,`hire_date`) values ('UV1344MY3UPWJYLZ39LVPPD488U85SGB','Augus',1,1,'2018-01-10 13:53:27',NULL);

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` varchar(50) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `status` int(10) DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`username`,`password`,`status`,`create_time`,`email`) values ('1','admin','3ef7164d1f6167cb9f2658c07d3c2f0a',1,'2018-01-19 16:45:11','798294336@qq.com'),('2','user','user',0,'2018-01-19 17:43:38',NULL),('3fb3ef60896c48b1a2b9d4b52b55f6a7','bpsssss','s',1,'2018-01-23 15:41:37','7945@qq.com'),('4318273691f244a3935c4dfff7671f21','bps3','32',1,'2018-01-23 10:58:36','232'),('61e96d0f4a514a84b4d863eb1928ec1a','bps','123',0,'2018-01-22 17:57:38','augus@qq.com'),('635c125b0257467c9ed6360f4674cb79','bpsss','12',1,'2018-01-23 14:55:00','1567333@qq.com'),('7a2ec46360e446b2bdac45ce05628dab','bps5','4',0,'2018-01-23 11:00:50','4'),('a4fc65edf65548a59e639467939fab8e','bps2','re',0,'2018-01-23 10:57:34','re'),('d5fc5f4459ae42719d33f1a221d325f6','111','2',1,'2018-01-23 15:42:33','fhsdkfs@qq.com'),('e01188a9969b482f96e7579ba6b0de3f','bpsp','3',0,'2018-01-23 13:54:17','3'),('fa10f6fb6c014d53beb3f8efc2c7b63a','bps8','12',0,'2018-01-23 14:53:57','798294336@qq.com');

/*Table structure for table `t_admin_role` */

DROP TABLE IF EXISTS `t_admin_role`;

CREATE TABLE `t_admin_role` (
  `admin_id` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_admin_role` */

insert  into `t_admin_role`(`admin_id`,`role_id`) values ('23',2),('1',3),('2',2),('61e96d0f4a514a84b4d863eb1928ec1a',3),('a4fc65edf65548a59e639467939fab8e',3),('4318273691f244a3935c4dfff7671f21',2),('7a2ec46360e446b2bdac45ce05628dab',2),('e01188a9969b482f96e7579ba6b0de3f',3),('fa10f6fb6c014d53beb3f8efc2c7b63a',3),('635c125b0257467c9ed6360f4674cb79',1),('3fb3ef60896c48b1a2b9d4b52b55f6a7',1),('d5fc5f4459ae42719d33f1a221d325f6',2);

/*Table structure for table `t_resources` */

DROP TABLE IF EXISTS `t_resources`;

CREATE TABLE `t_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_resources` */

insert  into `t_resources`(`id`,`name`,`res_url`,`type`,`parent_id`,`sort`) values (1,'系统设置','/system',1,0,1),(2,'管理员管理','/back/admin/list',1,1,2),(3,'角色管理','/rolesPage',1,1,3),(4,'资源管理','/back/resource/list',1,1,4),(5,'添加用户','/users/add',2,2,5),(6,'删除用户','/users/delete',2,2,6),(7,'添加角色','/roles/add',2,3,7),(8,'删除角色','/roles/delete',2,3,8),(9,'添加资源','/resources/add',2,4,9),(10,'删除资源','/resources/delete',2,4,10),(11,'分配角色','/users/saveUserRoles',2,2,11),(13,'分配权限','/roles/saveRoleResources',2,3,12);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`) values (1,'管理员'),(2,'普通用户'),(3,'超级管理员');

/*Table structure for table `t_role_resources` */

DROP TABLE IF EXISTS `t_role_resources`;

CREATE TABLE `t_role_resources` (
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_resources` */

insert  into `t_role_resources`(`role_id`,`resources_id`) values (1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,13),(2,2),(2,3),(2,4),(2,9),(3,1),(3,2),(3,3),(3,4),(3,5),(3,7),(3,8),(3,9),(3,10),(9,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
