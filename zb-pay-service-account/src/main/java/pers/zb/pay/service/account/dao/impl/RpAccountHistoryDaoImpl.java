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
package pers.zb.pay.service.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.service.account.dao.RpAccountHistoryDao;
import pers.zb.pay.service.account.entity.RpAccountHistory;
import pers.zb.pay.service.account.vo.DailyCollectAccountHistoryVo;


/**
 * 账户历史dao实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 21:21:29
 *
 */
@Repository
public class RpAccountHistoryDaoImpl  extends BaseDaoImpl<RpAccountHistory> implements RpAccountHistoryDao {
	
	public List<RpAccountHistory> listPageByParams(Map<String, Object> params){
		return this.listBy(params);
	}
	
	public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String, Object> params){
		return this.getSessionTemplate().selectList(getStatement("listDailyCollectAccountHistoryVo"), params);
	}

	@Override
	public RpAccountHistory getByRequestNo(String requestNo) {

		Map<String , Object> paramMap = new HashMap<String , Object>();
		paramMap.put("requestNo",requestNo);
		return super.getBy(paramMap);
	}

	/** 更新账户风险预存期外的账户历史记录记为结算完成 **/
	public void updateCompleteSettTo100(Map<String, Object> params){
		this.getSessionTemplate().update(getStatement("updateCompleteSettTo100"), params);
	}
}