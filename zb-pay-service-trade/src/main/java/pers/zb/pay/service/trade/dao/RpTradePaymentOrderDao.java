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
import pers.zb.pay.service.trade.entity.RpTradePaymentOrder;

public interface RpTradePaymentOrderDao  extends BaseDao<RpTradePaymentOrder> {

    /**
     * 根据商户编号及商户订单号获取支付订单信息
     * @param merchantNo
     * @param merchantOrderNo
     * @return
     */
    RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo);

    int deleteByPrimaryKey(String id);

    int insertSelective(RpTradePaymentOrder record);

    RpTradePaymentOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RpTradePaymentOrder record);

    int updateByPrimaryKey(RpTradePaymentOrder record);
    
   

}
