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
package pers.zb.pay.service.point.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zb.pay.common.core.enums.PublicStatusEnum;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.common.core.utils.DateUtils;
import pers.zb.pay.service.point.api.RpPointAccountQueryService;
import pers.zb.pay.service.point.dao.RpPointAccountDao;
import pers.zb.pay.service.point.entity.RpPointAccount;
import pers.zb.pay.service.point.exceptions.PointBizException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户查询service实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 22:01:22
 *
 */
@Service("rpPointAccountQueryService")
public class RpPointAccountQueryServiceImpl implements RpPointAccountQueryService {
	@Autowired
	private RpPointAccountDao rpPointAccountDao;

	private static final Logger LOG = LoggerFactory.getLogger(RpPointAccountQueryServiceImpl.class);


	/**
	 * 根据用户编号编号获取账户信息
	 * 
	 * @param userNo
	 *            用户编号
	 * @return
	 */
	public RpPointAccount getAccountByUserNo(String userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		LOG.info("根据用户编号查询账户信息");
		RpPointAccount account = this.rpPointAccountDao.getBy(map);
		if (account == null) {
			throw PointBizException.ACCOUNT_NOT_EXIT;
		}
		// 不是同一天直接清0
		if (!DateUtils.isSameDayWithToday(account.getEditTime())) {
			account.setEditTime(new Date());
			rpPointAccountDao.update(account);
		}
		return account;
	}


	/**
	 * 根据参数分页查询账户.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountList.
	 * @throws
	 */
	public PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) {

		return rpPointAccountDao.listPage(pageParam, params);
	}
	
	
    /**
	 * 获取所有账户
	 * @return
	 */
    @Override
    public List<RpPointAccount> listAll(){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpPointAccountDao.listBy(paramMap);
	}

}