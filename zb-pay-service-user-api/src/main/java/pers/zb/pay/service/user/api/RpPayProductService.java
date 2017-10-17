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
import pers.zb.pay.service.user.entity.RpPayProduct;
import pers.zb.pay.service.user.exceptions.PayBizException;

import java.util.List;


/**
 * 支付产品service接口
 *
 * @author zhoubang
 * @date 2017年10月17日 20:19:39
 *
 */
public interface RpPayProductService{
	
	/**
	 * 保存
	 */
	void saveData(RpPayProduct rpPayProduct) throws PayBizException;

	/**
	 * 更新
	 */
	void updateData(RpPayProduct rpPayProduct) throws PayBizException;

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	RpPayProduct getDataById(String id) throws PayBizException;
	

	/**
	 * 获取分页数据
	 * 
	 * @param pageParam
	 * @return
	 */
	PageBean listPage(PageParam pageParam, RpPayProduct rpPayProduct) throws PayBizException;
	
	/**
	 * 根据产品编号获取支付产品
	 * @param productCode
	 * @return
	 */
	RpPayProduct getByProductCode(String productCode, String auditStatus) throws PayBizException;
	
	/**
	 * 创建支付产品
	 * @param productCode
	 * @param productName
	 */
	void createPayProduct(String productCode, String productName) throws PayBizException;
	
	/**
	 * 删除支付产品
	 * @param productCode
	 */
	void deletePayProduct(String productCode) throws PayBizException;
	
	/**
	 * 获取所有支付产品
	 */
	List<RpPayProduct> listAll() throws PayBizException;

	/**
	 * 审核
	 * @param productCode
	 * @param auditStatus
	 */
	void audit(String productCode, String auditStatus) throws PayBizException;
	
}