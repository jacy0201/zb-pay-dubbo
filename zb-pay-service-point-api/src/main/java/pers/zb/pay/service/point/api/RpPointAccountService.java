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

import org.mengyun.tcctransaction.api.TransactionContext;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.point.entity.RpPointAccount;
import pers.zb.pay.service.point.exceptions.PointBizException;

/**
 * 账户service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 21:51:40
 *
 */
public interface RpPointAccountService{
	
	/**
	 * 保存
	 */
	void saveData(RpPointAccount rpPointAccount) throws PointBizException;

	/**
	 * 更新
	 */
	void updateData(RpPointAccount rpPointAccount) throws PointBizException;

	void creditToPointAccountTcc(TransactionContext transactionContext, String userNo, Integer pointAmount, String requestNo,String bankTrxNo, String trxType, String remark) throws PointBizException;

	void creditToPointAccount(String userNo, Integer pointAmount, String requestNo,String bankTrxNo, String trxType, String remark) throws PointBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpPointAccount getDataById(String id) throws PointBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpPointAccount rpPointAccount) throws PointBizException;
	
}