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
package pers.zb.pay.service.notify.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pers.zb.pay.common.core.exception.BizException;


public class NotifyBizException extends BizException {

    /**
     *
     */
    private static final long serialVersionUID = 3536909333010163563L;

    /** 保存的消息为空 **/
    public static final int NOTIFY_SYSTEM_EXCEPTION = 9001;

    private static final Log LOG = LogFactory.getLog(NotifyBizException.class);

    public NotifyBizException() {
    }

    public NotifyBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public NotifyBizException(int code, String msg) {
        super(code, msg);
    }

    public NotifyBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
