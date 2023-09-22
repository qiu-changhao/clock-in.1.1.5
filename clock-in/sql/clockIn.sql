/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.19 : Database - clock-in
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clock-in` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `clock-in`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`username`,`password`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `t_clock_in_record` */

DROP TABLE IF EXISTS `t_clock_in_record`;

CREATE TABLE `t_clock_in_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `s_id` bigint NOT NULL COMMENT '学生ID',
  `c_id` bigint NOT NULL COMMENT '打卡设置ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='打卡记录表';

/*Data for the table `t_clock_in_record` */

insert  into `t_clock_in_record`(`id`,`s_id`,`c_id`,`create_time`) values (8,1,5,'2023-02-23 11:45:26'),(9,1,6,'2023-02-23 11:45:29'),(10,1,7,'2023-02-23 11:45:33');

/*Table structure for table `t_clock_in_set` */

DROP TABLE IF EXISTS `t_clock_in_set`;

CREATE TABLE `t_clock_in_set` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `t_id` bigint NOT NULL COMMENT '老师ID',
  `team_id` bigint NOT NULL COMMENT '班级ID',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `latitude` decimal(10,6) NOT NULL COMMENT '经度',
  `longitude` decimal(10,6) NOT NULL COMMENT '维度',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `address_range` decimal(10,2) DEFAULT '1000.00' COMMENT '范围（米）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='打卡设置表';

/*Data for the table `t_clock_in_set` */

insert  into `t_clock_in_set`(`id`,`t_id`,`team_id`,`title`,`start_time`,`end_time`,`latitude`,`longitude`,`address`,`address_range`,`create_time`) values (5,1,4,'xx课程','2023-02-23 08:00:00','2023-02-23 18:00:00','22.548360','114.131660','广东省深圳市罗湖区文锦中路1008号','1000.00','2023-02-23 11:22:56'),(6,1,4,'语文课','2023-02-23 08:00:00','2023-02-23 18:00:00','22.548360','114.131660','广东省深圳市罗湖区文锦中路1008号','1000.00','2023-02-23 11:41:12'),(7,1,4,'数学课','2023-02-23 08:00:00','2023-02-23 18:00:00','22.548360','114.131660','广东省深圳市罗湖区文锦中路1008号','1000.00','2023-02-23 11:41:31');

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';

/*Data for the table `t_student` */

insert  into `t_student`(`id`,`username`,`password`,`real_name`) values (1,'xiaoming','e10adc3949ba59abbe56e057f20f883e','啊'),(2,'xiaohong','e10adc3949ba59abbe56e057f20f883e','小红');

/*Table structure for table `t_student_team_relation` */

DROP TABLE IF EXISTS `t_student_team_relation`;

CREATE TABLE `t_student_team_relation` (
  `s_id` bigint NOT NULL COMMENT '学生ID',
  `team_id` bigint NOT NULL COMMENT '班级ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生班级表';

/*Data for the table `t_student_team_relation` */

insert  into `t_student_team_relation`(`s_id`,`team_id`) values (1,4),(2,4);

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='老师表';

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`id`,`username`,`password`,`real_name`) values (1,'xiaohonglaoshi','e10adc3949ba59abbe56e057f20f883e',NULL);

/*Table structure for table `t_team` */

DROP TABLE IF EXISTS `t_team`;

CREATE TABLE `t_team` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(64) NOT NULL COMMENT '班级名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_team` */

insert  into `t_team`(`id`,`name`,`sort`) values (4,'一年级一班',0),(5,'一年级二班',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
