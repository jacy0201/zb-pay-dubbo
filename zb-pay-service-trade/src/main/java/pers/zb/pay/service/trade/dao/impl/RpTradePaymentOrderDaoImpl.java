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
package pers.zb.pay.service.trade.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.service.trade.dao.RpTradePaymentOrderDao;
import pers.zb.pay.service.trade.entity.RpTradePaymentOrder;



@Repository("rpTradePaymentOrderDao")
public class RpTradePaymentOrderDaoImpl extends BaseDaoImpl<RpTradePaymentOrder> implements RpTradePaymentOrderDao {
	

    /**
     * 根据商户编号及商户订单号获取支付订单信息
     * @param merchantNo    商户编号
     * @param merchantOrderNo   商户订单号
     * @return
     */
    @Override
    public RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("merchantNo",merchantNo);
        paramMap.put("merchantOrderNo",merchantOrderNo);
        return super.getBy(paramMap);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(RpTradePaymentOrder record) {
        return 0;
    }

    @Override
    public RpTradePaymentOrder selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RpTradePaymentOrder record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RpTradePaymentOrder record) {
        return 0;
    }
}
