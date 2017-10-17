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
package pers.zb.pay.service.message.exceptions;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pers.zb.pay.common.core.exception.BizException;

/**
 * 消息模块业务异常类
 *
 * @author zhoubang
 * @date 2017年10月17日 22:10:42
 *
 */
public class MessageBizException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3536909333010163563L;

	/** 保存的消息为空 **/
	public static final int SAVA_MESSAGE_IS_NULL = 8001;
	
	/** 消息的消费队列为空 **/
	public static final int MESSAGE_CONSUMER_QUEUE_IS_NULL = 8002;

	private static final Log LOG = LogFactory.getLog(MessageBizException.class);

	public MessageBizException() {
	}

	public MessageBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public MessageBizException(int code, String msg) {
		super(code, msg);
	}

	public MessageBizException print() {
		LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
