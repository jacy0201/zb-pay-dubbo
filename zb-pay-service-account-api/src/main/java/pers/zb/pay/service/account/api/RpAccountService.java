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
import pers.zb.pay.service.account.exceptions.AccountBizException;

/**
 * 账户service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:32:52
 *
 */
public interface RpAccountService{
	
	/**
	 * 保存
	 */
	void saveData(RpAccount rpAccount) throws AccountBizException;

	/**
	 * 更新
	 */
	void updateData(RpAccount rpAccount) throws AccountBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpAccount getDataById(String id) throws AccountBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpAccount rpAccount) throws AccountBizException;
	
}