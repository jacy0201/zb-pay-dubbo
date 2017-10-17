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
package pers.zb.pay.service.notify.entity;

import pers.zb.pay.common.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;


public class RpNotifyRecordLog extends BaseEntity implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 459406550725396000L;

    /** 通知记录ID **/
    private String notifyId;

    /** 请求信息 **/
    private String request;

    /** 返回信息 **/
    private String response;

    /** 商户编号 **/
    private String merchantNo;

    /** 商户订单号 **/
    private String merchantOrderNo;

    /** HTTP状态 **/
    private Integer httpStatus;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public RpNotifyRecordLog() {
        super();
    }

    public RpNotifyRecordLog(Date createTime, String notifyId, String request, String response, String merchantNo, String merchantOrderNo, Integer httpStatus) {
        super();
        this.createTime = createTime;
        this.notifyId = notifyId;
        this.request = request;
        this.response = response;
        this.merchantNo = merchantNo;
        this.merchantOrderNo = merchantOrderNo;
        this.httpStatus = httpStatus;
    }

    /** 通知记录ID **/
    public String getNotifyId() {
        return notifyId;
    }

    /** 通知记录ID **/
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /** 请求信息 **/
    public String getRequest() {
        return request;
    }

    /** 请求信息 **/
    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    /** 返回信息 **/
    public String getResponse() {
        return response;
    }

    /** 返回信息 **/
    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    /** 商户编号 **/
    public String getMerchantNo() {
        return merchantNo;
    }

    /** 商户编号 **/
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /** 商户订单号 **/
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    /** 商户订单号 **/
    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo == null ? null : merchantOrderNo.trim();
    }

    /** HTTP状态 **/
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /** HTTP状态 **/
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

}
