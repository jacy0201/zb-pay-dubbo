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
package pers.zb.pay.service.trade.api;

import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.trade.entity.RpTradePaymentOrder;
import pers.zb.pay.service.trade.entity.RpTradePaymentRecord;
import pers.zb.pay.service.trade.vo.OrderPayResultVo;
import pers.zb.pay.service.trade.vo.PaymentOrderQueryVo;

import java.util.List;
import java.util.Map;


/**
 * 交易模块查询接口类.
 *
 * @author zhoubang
 * @date 2017年10月17日 22:23:01
 *
 */
public interface RpTradePaymentQueryService {



	/**
	 * 根据参数查询交易记录List
	 * 
	 * @param paremMap
	 * @return
	 */
	public List<RpTradePaymentRecord> listPaymentRecord(Map<String, Object> paremMap);

	/**
	 * 根据商户支付KEY 及商户订单号 查询支付结果
	 * 
	 * @param payKey
	 *            商户支付KEY
	 * @param orderNo
	 *            商户订单号
	 * @return
	 */
	public OrderPayResultVo getPayResult(String payKey, String orderNo);

	/**
	 * 根据银行订单号查询支付记录
	 * 
	 * @param bankOrderNo
	 * @return
	 */
	public RpTradePaymentRecord getRecordByBankOrderNo(String bankOrderNo);

	/**
	 * 根据支付流水号查询支付记录
	 * 
	 * @param trxNo
	 * @return
	 */
	public RpTradePaymentRecord getRecordByTrxNo(String trxNo);


	/**
	 * 分页查询支付订单
	 * @param pageParam
	 * @param paymentOrderQueryVo
	 * @return
	 */
	public PageBean<RpTradePaymentOrder> listPaymentOrderPage(PageParam pageParam , PaymentOrderQueryVo paymentOrderQueryVo);

	/**
	 * 分页查询支付记录
	 * @param pageParam
	 * @param paymentOrderQueryVo
	 * @return
	 */
	public PageBean<RpTradePaymentRecord> listPaymentRecordPage(PageParam pageParam ,PaymentOrderQueryVo paymentOrderQueryVo);

}
