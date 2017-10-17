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
package pers.zb.pay.service.account.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.account.api.RpAccountService;
import pers.zb.pay.service.account.dao.RpAccountDao;
import pers.zb.pay.service.account.entity.RpAccount;


/**
 * 账户service实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 21:20:03
 *
 */
@Service("rpAccountService")
public class RpAccountServiceImpl implements RpAccountService {

	@Autowired
	private RpAccountDao rpAccountDao;
	
	@Override
	public void saveData(RpAccount rpAccount) {
		rpAccountDao.insert(rpAccount);
	}

	@Override
	public void updateData(RpAccount rpAccount) {
		rpAccountDao.update(rpAccount);
	}

	@Override
	public RpAccount getDataById(String id) {
		return rpAccountDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpAccount rpAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountNo", rpAccount.getAccountNo());
		return rpAccountDao.listPage(pageParam, paramMap);
	}
}