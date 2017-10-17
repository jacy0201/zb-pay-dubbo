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

/**
 * 
 *  把httpclient 发送请求的 方法封装成枚举类型 
 *  这样可以避免字符串出错的情况
 *   GET 代表法get 请求 
 *   POST代表发post 请求 
 *   等等
 */
public enum MethodType {
	
		GET, POST, DELETE, PUT, TRACE, OPTION
	
}
