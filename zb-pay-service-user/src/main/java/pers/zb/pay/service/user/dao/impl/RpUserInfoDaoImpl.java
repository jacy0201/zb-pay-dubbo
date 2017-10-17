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
package pers.zb.pay.service.user.dao.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;
import com.alibaba.druid.util.StringUtils;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.common.core.exception.BizException;
import pers.zb.pay.common.core.utils.DateUtils;
import pers.zb.pay.service.user.dao.RpUserInfoDao;
import pers.zb.pay.service.user.entity.RpUserInfo;


/**
 * 用户信息dao实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 20:57:33
 *
 */
@Repository
public class RpUserInfoDaoImpl  extends BaseDaoImpl<RpUserInfo> implements RpUserInfoDao {
	
	/** 用户编号前缀 **/
	private static final String USER_NO_PREFIX = "8888";
	
	/** 账户编号前缀 **/
	private static final String ACCOUNT_NO_PREFIX = "9999";

	@Override
	public String buildUserNo() {

		// 获取用户编号序列
		String userNoSeq = null;
		String userNo = null;
		
		try {
			// 获取用户编号序列
			userNoSeq = super.getSqlSession().selectOne(getStatement("buildUserNoSeq"));
			// 20位的用户编号规范：'8888' + yyyyMMdd(时间) + 序列的后8位
			String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
			userNo = USER_NO_PREFIX + dateString + userNoSeq.substring(userNoSeq.length() - 8, userNoSeq.length());
		} catch (Exception e) {
			LOG.error("生成用户编号异常：", e);
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		if (StringUtils.isEmpty(userNo)) {
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		
		return userNo;

	}

	@Override
	public String buildAccountNo() {
		
		// 获取账户编号序列值，用于生成20位的账户编号
		String accountNoSeq = null;
		// 20位的账户编号规范：'9999' + yyyyMMdd(时间) + 序列的后8位
		String accountNo = null;

		try {
			// 获取账户编号序列值，用于生成20位的账户编号
			accountNoSeq = super.getSqlSession().selectOne(getStatement("buildAccountNoSeq"));
			// 20位的账户编号规范：'9999' + yyyyMMdd(时间) + 序列的后8位
			String dateString = DateUtils.toString(new Date(), "yyyyMMdd");
			accountNo = ACCOUNT_NO_PREFIX + dateString + accountNoSeq.substring(accountNoSeq.length() - 8, accountNoSeq.length());

		} catch (Exception e) {
			LOG.error("生成账户编号异常：", e);
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		if (StringUtils.isEmpty(accountNo)) {
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
		}
		
		return accountNo;
	}
	
}