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
package pers.zb.pay.common.core.dwz;

/**
 * 封装DWZ框架ajax请求及响应的参数. .
 *
 * @author zhoubang
 * @date 2017年10月18日 13:42:34
 *
 */
public final class DWZ {

	/**
	 * Ajax请求成功,statusCode 为200.
	 */
	public static final String SUCCESS = "200";
	/**
	 * Ajax请求失败,statusCode 为300.
	 */
	public static final String ERROR = "300";
	/**
	 * Ajax请求超时,statusCode 为301.
	 */
	public static final String TIMEOUT = "301";

	// //////////////////////////////////////////

	/**
	 * callbackType ajax请求回调类型. <br/>
	 * callbackType如果是closeCurrent就会关闭当前tab选项,
	 */
	public static final String CLOSE = "closeCurrent";

	/**
	 * 只有callbackType="forward"时需要forwardUrl值,以重定向到另一个URL.
	 */
	public static final String FORWARD = "forward";
	
	public static final String AJAX_DONE = "common/ajaxDone";
	
	public static final String SUCCESS_MSG = "操作成功";

	/**
	 * 私有构造方法,单例模式.
	 */
	private DWZ() {

	}
}
