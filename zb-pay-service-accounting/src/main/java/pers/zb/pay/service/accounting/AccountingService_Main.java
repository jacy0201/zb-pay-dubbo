package pers.zb.pay.service.accounting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 *  为了方便大家的本地学习与调试，可直接运行此main方法启动服务，进行服务注册
 *
 * 	==================== ======================
 * 					会计原始凭证记账服务
 * 	==================== ======================
 */
public class AccountingService_Main {

	private static final Log LOG = LogFactory.getLog(AccountingService_Main.class);


	/**
	 * main方法启动
	 */
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-context.xml" });
			context.start();
			LOG.info("AccountingService Dubbo Service == context start");
			
		} catch (Exception e) {
			LOG.error("[zb-pay-service-accounting] == application start error:", e);
			return;
		}
		
		synchronized (AccountingService_Main.class) {
			while (true) {
				try {
					AccountingService_Main.class.wait();
				} catch (InterruptedException e) {
					LOG.error("== synchronized error:", e);
				}
			}
		}
	}
}
