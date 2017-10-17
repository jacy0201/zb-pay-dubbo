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
package pers.zb.pay.app.notify.message;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.zb.pay.app.notify.core.NotifyPersist;
import pers.zb.pay.app.notify.core.NotifyQueue;
import pers.zb.pay.common.core.exception.BizException;
import pers.zb.pay.common.core.utils.StringUtil;
import pers.zb.pay.service.notify.api.RpNotifyService;
import pers.zb.pay.service.notify.entity.RpNotifyRecord;
import pers.zb.pay.service.notify.enums.NotifyStatusEnum;

public class ConsumerSessionAwareMessageListener  implements MessageListener {

    private static final Log log = LogFactory.getLog(ConsumerSessionAwareMessageListener.class);

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private RpNotifyService rpNotifyService;

    @Autowired
    private NotifyPersist notifyPersist;

    @SuppressWarnings("static-access")
    public void onMessage(Message message) {
        try {
            ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
            final String ms = msg.getText();
            log.info("== receive message:" + ms);

            JSON json = (JSON) JSONObject.parse(ms);
            RpNotifyRecord notifyRecord = JSONObject.toJavaObject(json, RpNotifyRecord.class);
            if (notifyRecord == null) {
                return;
            }
            // log.info("notifyParam:" + notifyParam);
            notifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
            notifyRecord.setCreateTime(new Date());
            notifyRecord.setLastNotifyTime(new Date());

            if ( !StringUtil.isEmpty(notifyRecord.getId())){
                RpNotifyRecord notifyRecordById = rpNotifyService.getNotifyRecordById(notifyRecord.getId());
                if (notifyRecordById != null){
                    return;
                }
            }

            while (rpNotifyService == null) {
                Thread.currentThread().sleep(1000); // 主动休眠，防止类Spring 未加载完成，监听服务就开启监听出现空指针异常
            }

            try {
                // 将获取到的通知先保存到数据库中
                notifyPersist.saveNotifyRecord(notifyRecord);
                notifyRecord = rpNotifyService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(), notifyRecord.getNotifyType());

                // 添加到通知队列
                notifyQueue.addElementToList(notifyRecord);
            }  catch (BizException e) {
                log.error("BizException :", e);
            } catch (Exception e) {
                log.error(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }


}
