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
package pers.zb.pay.app.notify.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zb.pay.service.notify.api.RpNotifyService;
import pers.zb.pay.service.notify.entity.RpNotifyRecord;
import pers.zb.pay.service.notify.entity.RpNotifyRecordLog;


@Service("notifyPersist")
public class NotifyPersist {

    @Autowired
    private RpNotifyService rpNotifyService;

    /**
     * 创建商户通知记录.<br/>
     *
     * @param notifyRecord
     * @return
     */
    public long saveNotifyRecord(RpNotifyRecord notifyRecord) {
        return rpNotifyService.createNotifyRecord(notifyRecord);
    }

    /**
     * 更新商户通知记录.<br/>
     *
     * @param id
     * @param notifyTimes
     *            通知次数.<br/>
     * @param status
     *            通知状态.<br/>
     * @return 更新结果
     */
    public  void updateNotifyRord(String id, int notifyTimes, String status) {
        RpNotifyRecord notifyRecord = rpNotifyService.getNotifyRecordById(id);
        notifyRecord.setNotifyTimes(notifyTimes);
        notifyRecord.setStatus(status);
        notifyRecord.setLastNotifyTime(new Date());
        rpNotifyService.updateNotifyRecord(notifyRecord);
    }

    /**
     * 创建商户通知日志记录.<br/>
     *
     * @param notifyId
     *            通知记录ID.<br/>
     * @param merchantNo
     *            商户编号.<br/>
     * @param merchantOrderNo
     *            商户订单号.<br/>
     * @param request
     *            请求信息.<br/>
     * @param response
     *            返回信息.<br/>
     * @param httpStatus
     *            通知状态(HTTP状态).<br/>
     * @return 创建结果
     */
    public long saveNotifyRecordLogs(String notifyId, String merchantNo, String merchantOrderNo, String request, String response,
                                            int httpStatus) {
        RpNotifyRecordLog notifyRecordLog = new RpNotifyRecordLog();
        notifyRecordLog.setHttpStatus(httpStatus);
        notifyRecordLog.setMerchantNo(merchantNo);
        notifyRecordLog.setMerchantOrderNo(merchantOrderNo);
        notifyRecordLog.setNotifyId(notifyId);
        notifyRecordLog.setRequest(request);
        notifyRecordLog.setResponse(response);
        notifyRecordLog.setCreateTime(new Date());
        notifyRecordLog.setEditTime(new Date());
        return rpNotifyService.createNotifyRecordLog(notifyRecordLog);
    }

}
