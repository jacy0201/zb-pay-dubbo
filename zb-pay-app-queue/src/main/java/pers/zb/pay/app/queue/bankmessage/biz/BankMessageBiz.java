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
package pers.zb.pay.app.queue.bankmessage.biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.zb.pay.service.message.api.RpTransactionMessageService;
import pers.zb.pay.service.trade.api.RpTradePaymentManagerService;

import java.util.Map;


public class BankMessageBiz {

    private static final Log LOG = LogFactory.getLog(BankMessageBiz.class);

    @Autowired
    private RpTradePaymentManagerService rpTradePaymentManagerService;

    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;

    public void completePay(Map<String , String > notifyMessageMap){

        String messageId = notifyMessageMap.get("messageId");
        String payWayCode = notifyMessageMap.get("payWayCode");
        //调用业务方法,完成交易
        try{

            rpTradePaymentManagerService.completeScanPay(payWayCode, notifyMessageMap);

            //删除消息
            rpTransactionMessageService.deleteMessageByMessageId(messageId);
        }catch (Exception e){
            LOG.error("完成支付结果异常:",e);
        }

    }

    public RpTradePaymentManagerService getRpTradePaymentManagerService() {
        return rpTradePaymentManagerService;
    }

    public void setRpTradePaymentManagerService(RpTradePaymentManagerService rpTradePaymentManagerService) {
        this.rpTradePaymentManagerService = rpTradePaymentManagerService;
    }

    public RpTransactionMessageService getRpTransactionMessageService() {
        return rpTransactionMessageService;
    }

    public void setRpTransactionMessageService(RpTransactionMessageService rpTransactionMessageService) {
        this.rpTransactionMessageService = rpTransactionMessageService;
    }
}
