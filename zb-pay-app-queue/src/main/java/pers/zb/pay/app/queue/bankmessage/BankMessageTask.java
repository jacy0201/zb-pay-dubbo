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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.zb.pay.app.queue.bankmessage.biz.BankMessageBiz;

import java.util.Map;


public class BankMessageTask implements Runnable {

    private static final Log LOG = LogFactory.getLog(BankMessageTask.class);

    @Autowired
    private BankMessageBiz bankMessageBiz;

    private Map<String , String> notifyMessageMap;

    public BankMessageTask(Map<String , String> notifyMessageMap){
        this.notifyMessageMap = notifyMessageMap;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        bankMessageBiz.completePay(notifyMessageMap);

    }

    public Map<String, String> getNotifyMessageMap() {
        return notifyMessageMap;
    }

    public void setNotifyMessageMap(Map<String, String> notifyMessageMap) {
        this.notifyMessageMap = notifyMessageMap;
    }

    public BankMessageBiz getBankMessageBiz() {
        return bankMessageBiz;
    }

    public void setBankMessageBiz(BankMessageBiz bankMessageBiz) {
        this.bankMessageBiz = bankMessageBiz;
    }
}
