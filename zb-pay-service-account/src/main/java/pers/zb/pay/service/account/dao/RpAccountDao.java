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
import pers.zb.pay.service.account.entity.RpAccount;

import java.util.Map;


/**
 * 账户dao
 *
 * @author zhoubang
 * @date 2017年10月17日 21:20:43
 *
 */
public interface RpAccountDao  extends BaseDao<RpAccount> {
	RpAccount getByAccountNo(String accountNo);

	RpAccount getByUserNo(Map<String, Object> map);
}