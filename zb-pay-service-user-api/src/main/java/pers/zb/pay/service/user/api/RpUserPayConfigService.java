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
import pers.zb.pay.service.user.entity.RpUserPayConfig;
import pers.zb.pay.service.user.exceptions.PayBizException;

import java.util.List;

/**
 * 用户支付配置service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:21:45
 *
 */
public interface RpUserPayConfigService{
	
	/**
	 * 保存
	 */
	void saveData(RpUserPayConfig rpUserPayConfig) throws PayBizException;

	/**
	 * 更新
	 */
	void updateData(RpUserPayConfig rpUserPayConfig) throws PayBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpUserPayConfig getDataById(String id) throws PayBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpUserPayConfig rpUserPayConfig) throws PayBizException;

	/**
	 * 根据商户编号获取已生效的支付配置
	 * @param userNo
	 * @return
	 */
	RpUserPayConfig getByUserNo(String userNo) throws PayBizException;
	
	/**
	 * 根据商户编号获取支付配置
	 * @param userNo
	 * @param auditStatus
	 * @return
	 */
	RpUserPayConfig getByUserNo(String userNo, String auditStatus) throws PayBizException;
	
	/**
	 * 根据支付产品获取已生效数据
	 */
	List<RpUserPayConfig> listByProductCode(String productCode) throws PayBizException;
	
	/**
	 * 根据支付产品获取数据
	 */
	List<RpUserPayConfig> listByProductCode(String productCode, String auditStatus) throws PayBizException;
	
	/**
	 * 创建用户支付配置
	 */
	void createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
			String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key)  throws PayBizException;
	
	/**
	 * 删除支付产品
	 * @param userNo
	 */
	void deleteUserPayConfig(String userNo) throws PayBizException;
	
	/**
	 * 修改用户支付配置
	 */
	void updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
			String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key)  throws PayBizException;

	/**
	 * 审核
	 * @param userNo
	 * @param auditStatus
	 */
	void audit(String userNo, String auditStatus) throws PayBizException;
	
	/**
	 * 根据商户key获取已生效的支付配置
	 * @param payKey
	 * @return
	 */
	RpUserPayConfig getByPayKey(String payKey) throws PayBizException;
	
}