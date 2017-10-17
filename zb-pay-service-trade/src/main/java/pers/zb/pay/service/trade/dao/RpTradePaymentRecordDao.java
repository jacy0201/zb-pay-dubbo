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
package pers.zb.pay.service.trade.dao;


import pers.zb.pay.common.core.dao.BaseDao;
import pers.zb.pay.service.trade.entity.RpTradePaymentRecord;

public interface RpTradePaymentRecordDao extends BaseDao<RpTradePaymentRecord> {
	
	/** 获取支付流水号 **/
	String buildTrxNo();
	
	/** 获取银行订单号 **/
	String buildBankOrderNo();
	
	
    int deleteByPrimaryKey(String id);

    int insertSelective(RpTradePaymentRecord record);

    RpTradePaymentRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RpTradePaymentRecord record);

    /**
     * 根据银行订单号获取支付信息
     * @param bankOrderNo
     * @return
     */
    RpTradePaymentRecord getByBankOrderNo(String bankOrderNo);

    /**
     * 根据商户编号及商户订单号获取支付结果
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    RpTradePaymentRecord getByMerchantNoAndMerchantOrderNo(String merchantNo , String merchantOrderNo);

    /**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	RpTradePaymentRecord getByTrxNo(String trxNo);

}
