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
package pers.zb.pay.app.queue;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONObject;
import pers.zb.pay.app.queue.bankmessage.BankMessageFixedThreadPool;
import pers.zb.pay.app.queue.bankmessage.BankMessageTask;
import pers.zb.pay.app.queue.bankmessage.biz.BankMessageBiz;
import pers.zb.pay.common.core.exception.BizException;


public class BankMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log LOG = LogFactory.getLog(BankMessageListener.class);

	@Autowired
	private JmsTemplate notifyJmsTemplate;

	@Autowired
	private BankMessageBiz bankMessageBiz;

//	@Autowired
//	private RpTradePaymentManagerService rpTradePaymentManagerService;
//
//	@Autowired
//	private RpTransactionMessageService rpTransactionMessageService;


	public synchronized void onMessage(Message message, Session session) {

		Map<String,String> param = null;
		String strMessage = null;

		try {
			ActiveMQTextMessage objectMessage = (ActiveMQTextMessage) message;
			strMessage = objectMessage.getText();
			LOG.info("strMessage1 bank:" + strMessage);
			param = JSONObject.parseObject(strMessage, Map.class); // 这里转换成相应的对象还有问题

			BankMessageTask bankMessageTask = new BankMessageTask(param);
			bankMessageTask.setBankMessageBiz(bankMessageBiz);

			BankMessageFixedThreadPool.addTask(bankMessageTask);

		} catch (RpcException e) {
			LOG.error("==>RpcException", e);
		} catch (BizException e) {
			// 业务异常，不再写会队列
			LOG.error("==>BizException", e);
		} catch (Exception e) {
			// 不明异常不再写会队列
			LOG.error("==>Exception", e);
		}
	}

	public JmsTemplate getNotifyJmsTemplate() {
		return notifyJmsTemplate;
	}


	public void setNotifyJmsTemplate(JmsTemplate notifyJmsTemplate) {
		this.notifyJmsTemplate = notifyJmsTemplate;
	}

	public BankMessageBiz getBankMessageBiz() {
		return bankMessageBiz;
	}

	public void setBankMessageBiz(BankMessageBiz bankMessageBiz) {
		this.bankMessageBiz = bankMessageBiz;
	}
}
