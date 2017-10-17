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
package pers.zb.pay.service.account.dao;

import pers.zb.pay.common.core.dao.BaseDao;
import pers.zb.pay.service.account.entity.RpAccountHistory;
import pers.zb.pay.service.account.vo.DailyCollectAccountHistoryVo;

import java.util.List;
import java.util.Map;


/**
 * 账户历史dao
 *
 * @author zhoubang
 * @date 2017年10月17日 21:20:58
 *
 */
public interface RpAccountHistoryDao  extends BaseDao<RpAccountHistory> {
	List<RpAccountHistory> listPageByParams(Map<String, Object> params);
	
	List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params);

	RpAccountHistory getByRequestNo(String requestNo);

	/** 更新账户风险预存期外的账户历史记录记为结算完成 **/
	void updateCompleteSettTo100(Map<String, Object> params);
}