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
package pers.zb.pay.service.point.api;


import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.point.entity.RpPointAccount;
import pers.zb.pay.service.point.exceptions.PointBizException;

import java.util.List;
import java.util.Map;

/**
 * 账户查询service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 21:51:27
 *
 */
public interface RpPointAccountQueryService {

	/**
	 * 根据用户编号编号获取账户信息
	 * 
	 * @param userNo
	 *            用户编号
	 * @return
	 */
	RpPointAccount getAccountByUserNo(String userNo) throws PointBizException;

	/**
	 * 根据参数分页查询账户.
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param params
	 *            查询参数，可以为null.
	 * @return AccountList.
	 * @throws PointBizException
	 */
	PageBean queryAccountListPage(PageParam pageParam, Map<String, Object> params) throws PointBizException;
	
	/**
	 * 获取所有账户
	 * @return
	 */
	List<RpPointAccount> listAll() throws PointBizException;
}