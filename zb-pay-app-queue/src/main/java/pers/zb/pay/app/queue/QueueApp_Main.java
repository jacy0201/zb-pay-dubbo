package pers.zb.pay.app.queue;/*
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.zb.pay.app.queue.utils.SpringContextUtil;


/**
 * 通知 app应用
 *
 * @author zhoubang
 * @date 2017年10月17日 23:49:44
 *
 */
public class QueueApp_Main {

	private static final Log LOG = LogFactory.getLog(QueueApp_Main.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-context.xml" });
			// 初始化SpringContextUtil
			SpringContextUtil ctxUtil = new SpringContextUtil();
			ctxUtil.setApplicationContext(context);
			
			context.start();
			LOG.info("[zb-pay-app-queue] == context start");
			
		} catch (Exception e) {
			LOG.error("[zb-pay-app-queue] == application start error:", e);
			return;
		}
		
		synchronized (QueueApp_Main.class) {
			while (true) {
				try {
					QueueApp_Main.class.wait();
				} catch (InterruptedException e) {
					LOG.error("[zb-pay-app-queue] == synchronized error:", e);
				}
			}
		}
	}
}
