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
package pers.zb.pay.service.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动Dubbo服务用的MainClass.
 *
 * @author zhoubang
 * @date 2017年10月17日 22:15:23
 *
 */
public class DubboProvider {
	private static final Log log = LogFactory.getLog(DubboProvider.class);

	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
//			RpTransactionMessageService rpTransactionMessageService = (RpTransactionMessageService) context.getBean("rpTransactionMessageService");
//
//			// step1.查询出所有符合时间条件内的，状态为发送理状态的记录
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			// 获取配置的开始处理的时间
//			String dateStr = "2016-07-18 15:27:25";
//			paramMap.put("createTimeBefore", dateStr);// 取存放了多久的消息
//			paramMap.put("status", MessageStatusEnum.SENDING.name());// 取状态为发送中的消息
//			paramMap.put("areadlyDead", PublicEnum.NO.name());// 取存活的发送中消息
//			// 每次抓去5000条数据处理
//			paramMap.put("pageFirst", 0);
//			paramMap.put("pageSize", 1000);
//			List<RpTransactionMessage> messages = rpTransactionMessageService.listTimeOutMessage(paramMap);
//			System.out.println(messages.size());
		} catch (Exception e) {
			log.error("[zb-pay-service-message] == DubboProvider context start error:", e);
		}

		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:", e);
				}
			}
		}
	}

}