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
package pers.zb.pay.app.queue.bankmessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BankMessageFixedThreadPool {

  private  static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

   public static void addTask(BankMessageTask bankMessageTask){
       fixedThreadPool.execute(bankMessageTask);
   }
}
