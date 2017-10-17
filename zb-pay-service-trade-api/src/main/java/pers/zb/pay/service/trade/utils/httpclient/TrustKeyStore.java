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
package pers.zb.pay.service.trade.utils.httpclient;


import javax.net.ssl.TrustManagerFactory;

public class TrustKeyStore {
	private TrustManagerFactory trustManagerFactory;
	
	TrustKeyStore(TrustManagerFactory trustManagerFactory){
		this.trustManagerFactory = trustManagerFactory;
	}
	
	TrustManagerFactory getTrustManagerFactory(){
		return trustManagerFactory;
	}
}
