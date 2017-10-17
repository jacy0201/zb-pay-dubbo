/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.35-log : Database - rc_pay_dubbo_point
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rc_pay_dubbo_point` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rc_pay_dubbo_point`;

/*Table structure for table `rp_point_account` */

DROP TABLE IF EXISTS `rp_point_account`;

CREATE TABLE `rp_point_account` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version` bigint(20) NOT NULL COMMENT '版本号',
  `user_no` varchar(50) NOT NULL COMMENT '用户编号',
  `balance` int(11) NOT NULL COMMENT '积分余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_KEY_2` (`user_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分账户表';

/*Data for the table `rp_point_account` */

insert  into `rp_point_account`(`id`,`create_time`,`edit_time`,`version`,`user_no`,`balance`) values ('8fda4f630bed4b5dabb3d26ff1ef1f13','2016-07-24 20:26:47','2017-09-24 19:53:37',0,'88882016072400000007',0),('acd7c91325c84331a3356dac5e8a05b7','2016-07-24 20:26:47','2017-09-24 19:53:37',0,'88882016072400000006',0),('bfc28dd5bfe04648846b09131e8984a4','2016-07-22 15:59:31','2017-09-24 19:53:36',0,'88882016072100000004',0),('c10db471e70f4a089ccba95aa8515ab1','2016-07-19 18:09:54','2017-09-24 19:56:19',0,'88882016071900000003',0),('fe27d17443384d4ea7c037a772a8a931','2016-07-22 16:00:16','2017-09-24 19:53:36',0,'88882016072200000005',0);

/*Table structure for table `rp_point_account_history` */

DROP TABLE IF EXISTS `rp_point_account_history`;

CREATE TABLE `rp_point_account_history` (
  `id` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `edit_time` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `amount` int(20) NOT NULL,
  `balance` int(20) NOT NULL,
  `fund_direction` varchar(36) NOT NULL,
  `request_no` varchar(36) NOT NULL,
  `bank_trx_no` varchar(50) DEFAULT NULL,
  `trx_type` varchar(36) NOT NULL,
  `user_no` varchar(50) DEFAULT NULL,
  `status` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金账户流水表';

/*Data for the table `rp_point_account_history` */

/*Table structure for table `seq_table` */

DROP TABLE IF EXISTS `seq_table`;

CREATE TABLE `seq_table` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `CURRENT_VALUE` bigint(20) NOT NULL DEFAULT '1000000002',
  `INCREMENT` smallint(6) NOT NULL DEFAULT '1',
  `REMARK` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seq_table` */

/* Function  structure for function  `FUN_DATE_ADD` */

/*!50003 DROP FUNCTION IF EXISTS `FUN_DATE_ADD` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_DATE_ADD`(STR_DATE VARCHAR(10), STR_INTERVAL INTEGER) RETURNS date
BEGIN
	RETURN DATE_ADD(STR_DATE, INTERVAL STR_INTERVAL DAY);
    END */$$
DELIMITER ;

/* Function  structure for function  `FUN_NOW` */

/*!50003 DROP FUNCTION IF EXISTS `FUN_NOW` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_NOW`() RETURNS datetime
BEGIN
	RETURN NOW();
    END */$$
DELIMITER ;

/* Function  structure for function  `FUN_SEQ` */

/*!50003 DROP FUNCTION IF EXISTS `FUN_SEQ` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_SEQ`(SEQ VARCHAR(50)) RETURNS bigint(20)
BEGIN
	UPDATE seq_table SET CURRENT_VALUE = CURRENT_VALUE + INCREMENT WHERE  SEQ_NAME=SEQ;
	RETURN FUN_SEQ_CURRENT_VALUE(SEQ);
    END */$$
DELIMITER ;

/* Function  structure for function  `FUN_SEQ_CURRENT_VALUE` */

/*!50003 DROP FUNCTION IF EXISTS `FUN_SEQ_CURRENT_VALUE` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_SEQ_CURRENT_VALUE`(SEQ VARCHAR(50)) RETURNS bigint(20)
BEGIN
	DECLARE VALUE INTEGER;
	SET VALUE=0;
	SELECT CURRENT_VALUE INTO VALUE FROM seq_table  WHERE SEQ_NAME=SEQ;
	RETURN VALUE;
    END */$$
DELIMITER ;

/* Function  structure for function  `FUN_SEQ_SET_VALUE` */

/*!50003 DROP FUNCTION IF EXISTS `FUN_SEQ_SET_VALUE` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_SEQ_SET_VALUE`(SEQ VARCHAR(50), val INTEGER) RETURNS bigint(20)
BEGIN
	UPDATE seq_table  SET CURRENT_VALUE=val WHERE SEQ_NAME=SEQ;
	RETURN FUN_SEQ_CURRENT_VALUE(SEQ);
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
