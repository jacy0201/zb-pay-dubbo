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
package pers.zb.pay.app.message.scheduled;

/**
 * 消息定时器接口
 *
 * @author zhoubang
 * @date 2017年10月17日 23:34:22
 *
 */
public interface MessageScheduled {

	/**
	 * 处理状态为“待确认”但已超时的消息.
	 */
	public void handleWaitingConfirmTimeOutMessages();

	/**
	 * 处理状态为“发送中”但超时没有被成功消费确认的消息
	 */
	public void handleSendingTimeOutMessage();

}
