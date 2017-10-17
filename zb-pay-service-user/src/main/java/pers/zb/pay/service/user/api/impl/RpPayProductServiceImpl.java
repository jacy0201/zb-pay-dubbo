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
package pers.zb.pay.service.user.api.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zb.pay.common.core.enums.PublicEnum;
import pers.zb.pay.common.core.enums.PublicStatusEnum;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.common.core.utils.StringUtil;
import pers.zb.pay.service.user.api.RpPayProductService;
import pers.zb.pay.service.user.api.RpPayWayService;
import pers.zb.pay.service.user.api.RpUserPayConfigService;
import pers.zb.pay.service.user.dao.RpPayProductDao;
import pers.zb.pay.service.user.entity.RpPayProduct;
import pers.zb.pay.service.user.entity.RpPayWay;
import pers.zb.pay.service.user.entity.RpUserPayConfig;
import pers.zb.pay.service.user.exceptions.PayBizException;


/**
 * 支付产品service实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 20:53:38
 *
 */
@Service("rpPayProductService")
public class RpPayProductServiceImpl implements RpPayProductService {

	@Autowired
	private RpPayProductDao rpPayProductDao;
	
	@Autowired
	private RpPayWayService rpPayWayService;
	
	@Autowired
	private RpUserPayConfigService rpUserPayConfigService;
	
	@Override
	public void saveData(RpPayProduct rpPayProduct) {
		rpPayProductDao.insert(rpPayProduct);
	}

	@Override
	public void updateData(RpPayProduct rpPayProduct) {
		rpPayProductDao.update(rpPayProduct);
	}

	@Override
	public RpPayProduct getDataById(String id) {
		return rpPayProductDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpPayProduct rpPayProduct) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", rpPayProduct.getAuditStatus());
		paramMap.put("productName", rpPayProduct.getProductName());
		return rpPayProductDao.listPage(pageParam, paramMap);
	}
	
	/**
	 * 根据产品编号获取支付产品
	 */
	@Override
	public RpPayProduct getByProductCode(String productCode, String auditStatus){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		paramMap.put("auditStatus", auditStatus);
		return rpPayProductDao.getBy(paramMap);
	}
	
	/**
	 * 创建支付产品
	 * @param productCode
	 * @param productName
	 */
	@Override
	public void createPayProduct(String productCode, String productName) throws PayBizException {
		RpPayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct != null){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EXIST,"支付产品已存在");
		}
		rpPayProduct = new RpPayProduct();
		rpPayProduct.setStatus(PublicStatusEnum.ACTIVE.name());
		rpPayProduct.setCreateTime(new Date());
		rpPayProduct.setId(StringUtil.get32UUID());
		rpPayProduct.setProductCode(productCode);
		rpPayProduct.setProductName(productName);
		rpPayProduct.setAuditStatus(PublicEnum.NO.name());
		saveData(rpPayProduct);
	}
	
	/**
	 * 删除支付产品
	 * @param productCode
	 */
	@Override
	public void deletePayProduct(String productCode) throws PayBizException{
		
		List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
		if(!payWayList.isEmpty()){
			throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联支付方式，无法删除！");
		}
		
		List<RpUserPayConfig> payConfigList = rpUserPayConfigService.listByProductCode(productCode);
		if(!payConfigList.isEmpty()){
			throw new PayBizException(PayBizException.PAY_PRODUCT_HAS_DATA,"支付产品已关联用户，无法删除！");
		}
		
		RpPayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct.getAuditStatus().equals(PublicEnum.YES.name())){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_EFFECTIVE,"支付产品已生效，无法删除！");
		}
		
		rpPayProduct.setStatus(PublicStatusEnum.UNACTIVE.name());
		updateData(rpPayProduct);
	}
	
	/**
	 * 获取所有支付产品
	 */
	@Override
	public List<RpPayProduct> listAll(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", PublicStatusEnum.ACTIVE.name());
		return rpPayProductDao.listBy(paramMap);
	}
	
	/**
	 * 审核
	 * @param productCode
	 * @param auditStatus
	 */
	@Override
	public void audit(String productCode, String auditStatus) throws PayBizException{
		RpPayProduct rpPayProduct = getByProductCode(productCode, null);
		if(rpPayProduct == null){
			throw new PayBizException(PayBizException.PAY_PRODUCT_IS_NOT_EXIST,"支付产品不存在！");
		}
		
		if(auditStatus.equals(PublicEnum.YES.name())){
			//检查是否已设置支付方式
			List<RpPayWay> payWayList = rpPayWayService.listByProductCode(productCode);
			if(payWayList.isEmpty()){
				throw new PayBizException(PayBizException.PAY_TYPE_IS_NOT_EXIST,"支付方式未设置，无法操作！");
			}
			
		}else if(auditStatus.equals(PublicEnum.NO.name())){
			//检查是否已有支付配置
			List<RpUserPayConfig> payConfigList = rpUserPayConfigService.listByProductCode(productCode);
			if(!payConfigList.isEmpty()){
				throw new PayBizException(PayBizException.USER_PAY_CONFIG_IS_EXIST,"支付产品已关联用户支付配置，无法操作！");
			}
		}
		rpPayProduct.setAuditStatus(auditStatus);
		rpPayProduct.setEditTime(new Date());
		updateData(rpPayProduct);
	}
}