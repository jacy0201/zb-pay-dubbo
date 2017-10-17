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
package pers.zb.pay.service.point.entity;

import pers.zb.pay.common.core.entity.BaseEntity;

import java.io.Serializable;


/**
 * 账户信息
 *
 * @author zhoubang
 * @date 2017年10月17日 21:51:58
 *
 */
public class RpPointAccount extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996976092574646493L;
	
    /** 用户编号 **/
    private String userNo;
    
    /** 账户余额 **/
    private Integer balance;
    
    
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }



}