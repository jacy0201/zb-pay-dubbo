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
package pers.zb.pay.service.account.api;

import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.account.entity.RpAccount;
import pers.zb.pay.service.account.entity.RpAccountHistory;
import pers.zb.pay.service.account.exceptions.AccountBizException;
import pers.zb.pay.service.account.vo.DailyCollectAccountHistoryVo;

import java.util.List;
import java.util.Map;

/**
 * 账户查询service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:32:22
 *
 */
public interface RpAccountQueryService {

	/**
	 * 根据账户编号获取账户信息
	 * 
	 * @param accountNo
	 *            账户编号
	 * @return
	 */
	RpAccount getAccountByAccountNo(String accountNo) throws AccountBizException;

	/**
	 * 根据用户编号编号获取账户信息
	 * 
	 * @param userNo
	 *            用户编号
	 * @return
	 */
	RpAccount getAccountByUserNo(String userNo) throws AccountBizException;

	// /////////////////////账户历史/////////////////////////////

	/**
	 * 根据账户编号分页查询账户历史单商户.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param accountNo
	 *            账户编号.
	 * @return AccountHistoryList.
	 * @throws AccountBizException
	 */
	PageBean queryAccountHistoryListPage(PageParam pageParam, String accountNo) throws AccountBizException;

	/**
	 * 根据账户编号分页查询账户历史单角色.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            参数
	 * @return AccountHistoryList.
	 * @throws AccountBizException
	 */
	PageBean queryAccountHistoryListPageByRole(PageParam pageParam, Map<String, Object> params) throws AccountBizException;
	
	
	/**
	 * 获取账户历史（参数没有可以传null）
	 * 
	 * @param accountNo
	 *            账户编号
	 * @param requestNo
	 *            请求号
	 * @param trxType
	 *            业务类型
	 * @return AccountHistory
	 */
	RpAccountHistory getAccountHistoryByAccountNo_requestNo_trxType(String accountNo, String requestNo, Integer trxType) throws AccountBizException;

	/**
	 * 日汇总账户待结算金额 .
	 * 
	 * @param accountNo
	 *            账户编号
	 * @param statDate
	 *            统计日期
	 * @param riskDay
	 *            风险预测期
	 * @param fundDirection
	 *            资金流向
	 * @return
	 * @throws AccountBizException
	 */
	List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(String accountNo, String statDate, Integer riskDay, Integer fundDirection) throws AccountBizException;

	/**
	 * 根据参数分页查询账户.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountList.
	 * @throws AccountBizException
	 */
	PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) throws AccountBizException;
	
	
	/**
	 * 根据参数分页查询账户历史.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountHistoryList.
	 * @throws AccountBizException
	 */
	PageBean queryAccountHistoryListPage(PageParam pageParam, Map<String, Object> params) throws AccountBizException;
	
	/**
	 * 获取所有账户
	 * @return
	 */
	List<RpAccount> listAll() throws AccountBizException;
}