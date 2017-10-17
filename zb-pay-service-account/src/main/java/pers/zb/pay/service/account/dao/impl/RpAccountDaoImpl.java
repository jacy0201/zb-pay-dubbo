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
import java.util.Map;

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.common.core.enums.PublicStatusEnum;
import pers.zb.pay.service.account.dao.RpAccountDao;
import pers.zb.pay.service.account.entity.RpAccount;


/**
 * 账户dao实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 21:21:17
 *
 */
@Repository
public class RpAccountDaoImpl  extends BaseDaoImpl<RpAccount> implements RpAccountDao {
	public RpAccount getByAccountNo(String accountNo){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", accountNo);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return this.getBy(paramMap);
	}

	public RpAccount getByUserNo(Map<String, Object> map){
		return this.getSessionTemplate().selectOne(getStatement("getByUserNo"), map);
	}
}