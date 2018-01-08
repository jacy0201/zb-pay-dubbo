package pers.zb.pay.service.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 *  为了方便大家的本地学习与调试，可直接运行此main方法启动服务，进行服务注册
 *
 * 	==================== ======================
 * 					用户服务
 * 	==================== ======================
 */
public class UserService_Main {

	private static final Log LOG = LogFactory.getLog(UserService_Main.class);


	/**
	 * main方法启动
	 */
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/spring-context.xml" });
			context.start();
			LOG.info("UserService Dubbo Service == context start");
			
		} catch (Exception e) {
			LOG.error("[zb-pay-service-user] == application start error:", e);
			return;
		}
		
		synchronized (UserService_Main.class) {
			while (true) {
				try {
					UserService_Main.class.wait();
				} catch (InterruptedException e) {
					LOG.error("== synchronized error:", e);
				}
			}
		}
	}
}
