/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.35-log : Database - rc_pay_dubbo_order
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rc_pay_dubbo_order` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rc_pay_dubbo_order`;

/*Table structure for table `rp_trade_payment_order` */

DROP TABLE IF EXISTS `rp_trade_payment_order`;

CREATE TABLE `rp_trade_payment_order` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态(参考枚举:OrderStatusEnum)',
  `product_name` varchar(300) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(30) NOT NULL COMMENT '商户订单号',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `merchant_name` varchar(100) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(100) NOT NULL COMMENT '商户编号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_date` date DEFAULT NULL COMMENT '下单日期',
  `order_ip` varchar(50) DEFAULT NULL COMMENT '下单IP(客户端IP,在网关页面获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `return_url` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notify_url` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `cancel_reason` varchar(600) DEFAULT NULL COMMENT '订单撤销原因',
  `order_period` smallint(6) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '支付备注',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `field1` varchar(500) DEFAULT NULL,
  `field2` varchar(500) DEFAULT NULL,
  `field3` varchar(500) DEFAULT NULL,
  `field4` varchar(500) DEFAULT NULL,
  `field5` varchar(500) DEFAULT NULL,
  `trx_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_KEY_2` (`merchant_order_no`,`merchant_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='roncoo pay 微支付 支付订单表';

/*Data for the table `rp_trade_payment_order` */

/*Table structure for table `rp_trade_payment_record` */

DROP TABLE IF EXISTS `rp_trade_payment_record`;

CREATE TABLE `rp_trade_payment_record` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(30) DEFAULT NULL COMMENT '状态(参考枚举:PaymentRecordStatusEnum)',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `product_name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `merchant_order_no` varchar(50) NOT NULL COMMENT '商户订单号',
  `trx_no` varchar(50) NOT NULL COMMENT '支付流水号',
  `bank_order_no` varchar(50) DEFAULT NULL COMMENT '银行订单号',
  `bank_trx_no` varchar(50) DEFAULT NULL COMMENT '银行流水号',
  `merchant_name` varchar(300) DEFAULT NULL COMMENT '商家名称',
  `merchant_no` varchar(50) NOT NULL COMMENT '商家编号',
  `payer_user_no` varchar(50) DEFAULT NULL COMMENT '付款人编号',
  `payer_name` varchar(60) DEFAULT NULL COMMENT '付款人名称',
  `payer_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方支付金额',
  `payer_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '付款方手续费',
  `payer_account_type` varchar(30) DEFAULT NULL COMMENT '付款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `receiver_user_no` varchar(15) DEFAULT NULL COMMENT '收款人编号',
  `receiver_name` varchar(60) DEFAULT NULL COMMENT '收款人名称',
  `receiver_pay_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方支付金额',
  `receiver_fee` decimal(20,6) DEFAULT '0.000000' COMMENT '收款方手续费',
  `receiver_account_type` varchar(30) DEFAULT NULL COMMENT '收款方账户类型(参考账户类型枚举:AccountTypeEnum)',
  `order_ip` varchar(30) DEFAULT NULL COMMENT '下单IP(客户端IP,从网关中获取)',
  `order_referer_url` varchar(100) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `order_amount` decimal(20,6) DEFAULT '0.000000' COMMENT '订单金额',
  `plat_income` decimal(20,6) DEFAULT NULL COMMENT '平台收入',
  `fee_rate` decimal(20,6) DEFAULT NULL COMMENT '费率',
  `plat_cost` decimal(20,6) DEFAULT NULL COMMENT '平台成本',
  `plat_profit` decimal(20,6) DEFAULT NULL COMMENT '平台利润',
  `return_url` varchar(600) DEFAULT NULL COMMENT '页面回调通知URL',
  `notify_url` varchar(600) DEFAULT NULL COMMENT '后台异步通知URL',
  `pay_way_code` varchar(50) DEFAULT NULL COMMENT '支付通道编号',
  `pay_way_name` varchar(100) DEFAULT NULL COMMENT '支付通道名称',
  `pay_success_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `is_refund` varchar(30) DEFAULT '101' COMMENT '是否退款(100:是,101:否,默认值为:101)',
  `refund_times` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  `success_refund_amount` decimal(20,6) DEFAULT NULL COMMENT '成功退款总金额',
  `trx_type` varchar(30) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `order_from` varchar(30) DEFAULT NULL COMMENT '订单来源',
  `pay_type_code` varchar(50) DEFAULT NULL COMMENT '支付方式类型编号',
  `pay_type_name` varchar(100) DEFAULT NULL COMMENT '支付方式类型名称',
  `fund_into_type` varchar(30) DEFAULT NULL COMMENT '资金流入类型',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `field1` varchar(500) DEFAULT NULL,
  `field2` varchar(500) DEFAULT NULL,
  `field3` varchar(500) DEFAULT NULL,
  `field4` varchar(500) DEFAULT NULL,
  `field5` varchar(500) DEFAULT NULL,
  `bank_return_msg` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

/*Data for the table `rp_trade_payment_record` */

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

insert  into `seq_table`(`SEQ_NAME`,`CURRENT_VALUE`,`INCREMENT`,`REMARK`) values ('BANK_ORDER_NO_SEQ',1000259689,1,'交易--银行订单号'),('TRX_NO_SEQ',1000259689,1,'交易--支付流水号');

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
