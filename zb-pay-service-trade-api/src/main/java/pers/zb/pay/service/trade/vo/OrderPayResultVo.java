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
package pers.zb.pay.service.trade.vo;

import pers.zb.pay.common.core.enums.PublicEnum;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderPayResultVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2023891587770894541L;

	/** 状态 **/
    private String status = PublicEnum.NO.name();

    /** 金额 **/
    private BigDecimal orderPrice;

    /** 商户页面通知结果地址 **/
    private String returnUrl;

    /** 产品名称 **/
    private String productName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", status=").append(status);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", returnUrl=").append(returnUrl);
        sb.append(", productName=").append(productName);
        sb.append("]");
        return sb.toString();
    }
}
