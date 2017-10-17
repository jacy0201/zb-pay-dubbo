/*
 * ====================================================================
 * 【个人网站】：http://www.2b2b92b.com
 * 【网站源码】：http://git.oschina.net/zhoubang85/zb
 * 【技术论坛】：http://www.2b2b92b.cn
 * 【开源中国】：https://gitee.com/zhoubang85
 *
 * 【支付-微信_支付宝_银联】技术QQ群：470414533
 * 【联系QQ】：842324724
 * 【联系Email】：842324724@qq.com
 * ====================================================================
 */
package pers.zb.pay.service.account.api;


import pers.zb.pay.service.account.entity.RpAccount;
import pers.zb.pay.service.account.exceptions.AccountBizException;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.math.BigDecimal;


/**
 * 账户操作service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:33:09
 *
 */
public interface RpAccountTransactionService {

	/** 加款:有银行流水 **/
	RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String bankTrxNo, String trxType, String remark) throws AccountBizException;

	/** 加款:有银行流水 **/
	void creditToAccountTcc(TransactionContext transactionContext, String userNo, BigDecimal amount, String requestNo,String bankTrxNo, String trxType, String remark) throws AccountBizException;

	/** 减款 :有银行流水**/
	RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo,String bankTrxNo, String trxType, String remark) throws AccountBizException;
	
	/** 加款 **/
	RpAccount creditToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws AccountBizException;

	/** 减款 **/
	RpAccount debitToAccount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws AccountBizException;


	/** 冻结 **/
	RpAccount freezeAmount(String userNo, BigDecimal freezeAmount) throws AccountBizException;

	/** 结算成功：解冻+减款 **/
	RpAccount unFreezeAmount(String userNo, BigDecimal amount, String requestNo, String trxType, String remark) throws AccountBizException;
	
	/** 结算失败：解冻 **/
	RpAccount unFreezeSettAmount(String userNo, BigDecimal amount) throws AccountBizException;

	/** 更新账户历史中的结算状态，并且累加可结算金额 **/
	void settCollectSuccess(String accountNo, String collectDate, int riskDay, BigDecimal totalAmount) throws AccountBizException;

}