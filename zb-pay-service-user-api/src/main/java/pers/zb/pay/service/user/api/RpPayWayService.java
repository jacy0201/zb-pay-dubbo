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
import pers.zb.pay.service.user.entity.RpPayWay;
import pers.zb.pay.service.user.exceptions.PayBizException;

import java.util.List;


/**
 * 支付方式service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:21:11
 *
 */
public interface RpPayWayService{
	
	/**
	 * 保存
	 */
	void saveData(RpPayWay rpPayWay) throws PayBizException;

	/**
	 * 更新
	 */
	void updateData(RpPayWay rpPayWay) throws PayBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpPayWay getDataById(String id) throws PayBizException;
	
	/**
	 * 根据支付方式、渠道编码获取数据
	 * @param rpTypeCode
	 * @return
	 */
	RpPayWay getByPayWayTypeCode(String payProductCode, String payWayCode, String rpTypeCode) throws PayBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpPayWay rpPayWay) throws PayBizException;
	
	/**
	 * 绑定支付费率
	 * @param payWayCode
	 * @param payTypeCode
	 * @param payRate
	 */
	void createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate)  throws PayBizException;
	
	/**
	 * 根据支付产品获取支付方式
	 * @param payProductCode
	 */
	List<RpPayWay> listByProductCode(String payProductCode) throws PayBizException;
	
	/**
	 * 获取所有支付方式
	 */
	List<RpPayWay> listAll() throws PayBizException;
	
}