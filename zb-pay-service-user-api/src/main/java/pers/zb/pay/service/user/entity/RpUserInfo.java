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
package pers.zb.pay.service.user.entity;

import pers.zb.pay.common.core.entity.BaseEntity;
import pers.zb.pay.common.core.enums.PublicStatusEnum;

import java.io.Serializable;


/**
 * 支付产品实体类
 *
 * @author zhoubang
 * @date 2017年10月17日 20:23:34
 *
 */
public class RpUserInfo extends BaseEntity implements Serializable {

    private String userNo;

    private String userName;

    private String accountNo;

    private static final long serialVersionUID = 1L;


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }
    
    public String getStatusDesc() {
        return PublicStatusEnum.getEnum(this.getStatus()).getDesc();
    }

}