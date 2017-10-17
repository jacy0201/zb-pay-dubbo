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
package pers.zb.pay.service.message.api;

import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.message.entity.RpTransactionMessage;
import pers.zb.pay.service.message.exceptions.MessageBizException;

import java.util.Map;



public interface RpTransactionMessageService {

	/**
	 * 预存储消息. 
	 */
	public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException;
	
	
	/**
	 * 确认并发送消息.
	 */
	public void confirmAndSendMessage(String messageId) throws MessageBizException;

	
	/**
	 * 存储并发送消息.
	 */
	public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;

	
	/**
	 * 直接发送消息.
	 */
	public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;
	
	
	/**
	 * 重发消息.
	 */
	public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;
	
	
	/**
	 * 根据messageId重发某条消息.
	 */
	public void reSendMessageByMessageId(String messageId) throws MessageBizException;
	
	
	/**
	 * 将消息标记为死亡消息.
	 */
	public void setMessageToAreadlyDead(String messageId) throws MessageBizException;


	/**
	 * 根据消息ID获取消息
	 */
	public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

	/**
	 * 根据消息ID删除消息
	 */
	public void deleteMessageByMessageId(String messageId) throws MessageBizException;
	
	
	/**
	 * 重发某个消息队列中的全部已死亡的消息.
	 */
	public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException;
	
	/**
	 * 获取分页数据
	 */
	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException;


}
