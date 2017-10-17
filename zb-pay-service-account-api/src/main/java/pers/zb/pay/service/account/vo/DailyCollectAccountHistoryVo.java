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
package pers.zb.pay.service.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算日汇总vo.
 *
 * @author zhoubang
 * @date 2017年10月17日 20:35:30
 *
 */
public class DailyCollectAccountHistoryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2451289258390618916L;

	/**
	 * 账户编号
	 */
	private String accountNo;

	/**
	 * 汇总日期
	 */
	private Date collectDate;

	/**
	 * 总金额
	 */
	private BigDecimal totalAmount = BigDecimal.ZERO;

	/**
	 * 总笔数
	 */
	private Integer totalNum = 0;

	/**
	 * 最后ID
	 */
	private Long lastId = 0L;

	/**
	 * 风险预存期
	 */
	private Integer riskDay;

	/**
	 * 账户编号
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * 账户编号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 汇总日期
	 */
	public Date getCollectDate() {
		return collectDate;
	}

	/**
	 * 汇总日期
	 */
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	/**
	 * 总金额
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 总金额
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 总笔数
	 */
	public Integer getTotalNum() {
		return totalNum;
	}

	/**
	 * 总笔数
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * 最后ID
	 * 
	 * @return
	 */
	public Long getLastId() {
		return lastId;
	}

	/**
	 * 最后ID
	 * 
	 * @return
	 */
	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

	/**
	 * 风险预存期
	 */
	public Integer getRiskDay() {
		return riskDay;
	}

	/**
	 * 风险预存期
	 */
	public void setRiskDay(Integer riskDay) {
		this.riskDay = riskDay;
	}

}
