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
package pers.zb.pay.service.notify.api;

import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.notify.entity.RpNotifyRecord;
import pers.zb.pay.service.notify.entity.RpNotifyRecordLog;
import pers.zb.pay.service.notify.exceptions.NotifyBizException;

import java.util.Map;


public interface RpNotifyService {

    /**
     * 发送消息通知
     * @param notifyUrl 通知地址
     * @param merchantOrderNo   商户订单号
     * @param merchantNo    商户编号
     */
    public void notifySend(String notifyUrl,String merchantOrderNo,String merchantNo) throws NotifyBizException;


    /**
     * 通过ID获取通知记录
     * @param id
     * @return
     */
    public RpNotifyRecord getNotifyRecordById(String id) throws NotifyBizException;

    /**
     * 根据商户编号,商户订单号,通知类型获取通知记录
     * @param merchantNo    商户编号
     * @param merchantOrderNo   商户订单号
     * @param notifyType    消息类型
     * @return
     */
    public RpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo , String merchantOrderNo , String notifyType) throws NotifyBizException;


    public PageBean<RpNotifyRecord> queryNotifyRecordListPage(PageParam pageParam , Map<String, Object> paramMap) throws NotifyBizException;
    /**
     * 创建消息通知
     * @param rpNotifyRecord
     */
    public long createNotifyRecord(RpNotifyRecord rpNotifyRecord) throws NotifyBizException;

    /**
     * 修改消息通知
     * @param rpNotifyRecord
     */
    public void updateNotifyRecord(RpNotifyRecord rpNotifyRecord) throws NotifyBizException;

    /**
     * 创建消息通知记录
     * @param rpNotifyRecordLog
     * @return
     */
    public long createNotifyRecordLog(RpNotifyRecordLog rpNotifyRecordLog) throws NotifyBizException;

}
