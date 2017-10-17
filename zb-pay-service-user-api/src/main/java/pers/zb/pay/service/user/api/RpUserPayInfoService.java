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
package pers.zb.pay.service.user.api;


import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.user.entity.RpUserPayInfo;
import pers.zb.pay.service.user.exceptions.UserBizException;

/**
 * 用户第三方支付信息service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:21:58
 *
 */
public interface RpUserPayInfoService{
	
	/**
	 * 保存
	 */
	void saveData(RpUserPayInfo rpUserPayInfo) throws UserBizException;

	/**
	 * 更新
	 */
	void updateData(RpUserPayInfo rpUserPayInfo) throws UserBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpUserPayInfo getDataById(String id) throws UserBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpUserPayInfo rpUserPayInfo) throws UserBizException;

	/**
	 * 通过商户编号获取商户支付配置信息
	 * @param userNo
	 * @param payWayCode
	 * @return
	 */
	public RpUserPayInfo getByUserNo(String userNo, String payWayCode) throws UserBizException;
	
}