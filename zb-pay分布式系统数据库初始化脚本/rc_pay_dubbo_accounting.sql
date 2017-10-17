/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.35-log : Database - rc_pay_dubbo_accounting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rc_pay_dubbo_accounting` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rc_pay_dubbo_accounting`;

/*Table structure for table `rp_accounting_voucher` */

DROP TABLE IF EXISTS `rp_accounting_voucher`;

CREATE TABLE `rp_accounting_voucher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `entry_type` smallint(6) NOT NULL COMMENT '会计分录类型',
  `request_no` varchar(32) NOT NULL COMMENT '请求号',
  `from_system` smallint(6) NOT NULL COMMENT '来源系统',
  `voucher_no` varchar(32) DEFAULT NULL COMMENT '原始凭证号',
  `accounting_date` date DEFAULT NULL COMMENT '会计日期',
  `bank_change_amount` decimal(24,10) DEFAULT NULL COMMENT '平台银行帐户变动金额',
  `payer_account_no` varchar(20) DEFAULT NULL COMMENT '付款方账号',
  `receiver_account_no` varchar(20) DEFAULT NULL COMMENT '收款方账号',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账户',
  `bank_channel_code` varchar(32) DEFAULT NULL COMMENT '银行渠道编码',
  `payer_change_amount` decimal(24,10) DEFAULT NULL COMMENT '付款方账户变动金额',
  `receiver_change_amount` decimal(24,10) DEFAULT NULL COMMENT '收款方账户变动金额',
  `profit` decimal(24,10) DEFAULT NULL COMMENT '利润',
  `income` decimal(24,10) DEFAULT NULL COMMENT '收入',
  `cost` decimal(24,10) DEFAULT NULL COMMENT '成本',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `bank_order_no` varchar(32) DEFAULT NULL COMMENT '银行订单号',
  `payer_account_type` smallint(6) DEFAULT NULL COMMENT '付款方帐户类型',
  `pay_amount` decimal(20,6) DEFAULT NULL COMMENT '支付金额',
  `receiver_account_type` smallint(6) DEFAULT NULL COMMENT '收款方帐户类型',
  `receiver_fee` decimal(20,6) DEFAULT NULL COMMENT '收款方手续费',
  `payer_fee` decimal(20,6) DEFAULT NULL COMMENT '付款方手续费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `REQUEST_NOTE_UKEY` (`entry_type`,`voucher_no`,`from_system`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='会计原始凭证表';

/*Data for the table `rp_accounting_voucher` */

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

insert  into `seq_table`(`SEQ_NAME`,`CURRENT_VALUE`,`INCREMENT`,`REMARK`) values ('ACCOUNTING_REQUEST_NO_SEQ',1000000081,1,'会计--请求号自增序列');

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

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FUN_SEQ_SET_VALUE`(SEQ VARCHAR(50), VALUE INTEGER) RETURNS bigint(20)
BEGIN
	UPDATE seq_table  SET CURRENT_VALUE=VALUE WHERE SEQ_NAME=SEQ;
	RETURN FUN_SEQ_CURRENT_VALUE(SEQ);
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
