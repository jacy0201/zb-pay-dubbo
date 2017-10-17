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
package pers.zb.pay.service.user.exceptions;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pers.zb.pay.common.core.exception.BizException;

/**
 * 支付业务异常类
 *
 * @author zhoubang
 * @date 2017年10月17日 20:25:16
 *
 */
public class PayBizException extends BizException {

    /** 支付方式已存在 **/
    public static final int PAY_TYPE_IS_EXIST = 101;
    
    /** 支付产品已存在 **/
    public static final int PAY_PRODUCT_IS_EXIST = 102;
    
    /** 支付产品已关联数据 **/
    public static final int PAY_PRODUCT_HAS_DATA = 103;
    
    /** 用户支付配置已存在 **/
    public static final int USER_PAY_CONFIG_IS_EXIST = 104;
    
    /** 用户支付配置不存在 **/
    public static final int USER_PAY_CONFIG_IS_NOT_EXIST = 105;
    
    /** 用户支付配置已生效 **/
    public static final int USER_PAY_CONFIG_IS_EFFECTIVE = 106;
    
    /** 支付产品已生效 **/
    public static final int PAY_PRODUCT_IS_EFFECTIVE = 107;
    
    /** 支付产品不存在 **/
    public static final int PAY_PRODUCT_IS_NOT_EXIST = 108;
    
    /** 支付方式不存在 **/
    public static final int PAY_TYPE_IS_NOT_EXIST = 108;

    private static final Log LOG = LogFactory.getLog(PayBizException.class);

    public PayBizException() {
    }

    public PayBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public PayBizException(int code, String msg) {
        super(code, msg);
    }

    public PayBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
