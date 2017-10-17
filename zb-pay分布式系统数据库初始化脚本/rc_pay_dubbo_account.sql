/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.35-log : Database - rc_pay_dubbo_account
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rc_pay_dubbo_account` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rc_pay_dubbo_account`;

/*Table structure for table `rp_account` */

DROP TABLE IF EXISTS `rp_account`;

CREATE TABLE `rp_account` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `account_no` varchar(50) NOT NULL,
  `balance` decimal(20,6) NOT NULL,
  `unbalance` decimal(20,6) NOT NULL,
  `security_money` decimal(20,6) NOT NULL,
  `status` varchar(36) NOT NULL,
  `total_income` decimal(20,6) NOT NULL,
  `total_expend` decimal(20,6) NOT NULL,
  `today_income` decimal(20,6) NOT NULL,
  `today_expend` decimal(20,6) NOT NULL,
  `account_type` varchar(50) NOT NULL,
  `sett_amount` decimal(20,6) NOT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金账户表';

/*Data for the table `rp_account` */

insert  into `rp_account`(`id`,`create_time`,`edit_time`,`version`,`remark`,`account_no`,`balance`,`unbalance`,`security_money`,`status`,`total_income`,`total_expend`,`today_income`,`today_expend`,`account_type`,`sett_amount`,`user_no`) values ('1715f68276d54027b405bd99d4641cc6','2016-07-21 01:06:51','2017-09-24 19:53:35',0,NULL,'99992016072100000004','0.000000','0.000000','0.000000','ACTIVE','0.000000','0.000000','0.000000','0.000000','0','0.000000','88882016072100000004'),('20b7c1ce75644da6b044437db217ee59','2016-07-19 16:55:09','2017-09-24 19:56:18',0,NULL,'99992016071900000003','0.000000','0.000000','0.000000','ACTIVE','0.000000','0.000000','0.000000','0.000000','0','0.000000','88882016071900000003'),('77842f563a6b4b918b6955b5f07b583c','2016-07-24 20:21:45','2017-09-24 19:53:36',0,NULL,'99992016072400000007','0.000000','0.000000','0.000000','ACTIVE','0.000000','0.000000','0.000000','0.000000','0','0.000000','88882016072400000007'),('a34b5b788c884dadbd640c52b4d7be61','2016-07-24 20:20:26','2017-09-24 19:53:36',0,NULL,'99992016072400000006','0.000000','0.000000','0.000000','ACTIVE','0.000000','0.000000','0.000000','0.000000','0','0.000000','88882016072400000006'),('f79ae95111f6405887b41d000e793552','2016-07-22 15:56:26','2017-09-24 19:53:35',0,NULL,'99992016072200000005','0.000000','0.000000','0.000000','ACTIVE','0.000000','0.000000','0.000000','0.000000','0','0.000000','88882016072200000005');

/*Table structure for table `rp_account_history` */

DROP TABLE IF EXISTS `rp_account_history`;

CREATE TABLE `rp_account_history` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount` decimal(20,6) NOT NULL,
  `balance` decimal(20,6) NOT NULL,
  `fund_direction` varchar(36) NOT NULL,
  `is_allow_sett` varchar(36) NOT NULL,
  `is_complete_sett` varchar(36) NOT NULL,
  `request_no` varchar(36) NOT NULL,
  `bank_trx_no` varchar(50) DEFAULT NULL,
  `trx_type` varchar(36) NOT NULL,
  `risk_day` int(11) DEFAULT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  `status` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金账户流水表';

/*Data for the table `rp_account_history` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
