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
package pers.zb.pay.service.user.dao;


import pers.zb.pay.common.core.dao.BaseDao;
import pers.zb.pay.service.user.entity.RpUserInfo;

/**
 * 用户dao
 *
 * @author zhoubang
 * @date 2017年10月17日 20:55:31
 *
 */
public interface RpUserInfoDao  extends BaseDao<RpUserInfo> {
	
	/** 获取用户编号 **/
	String buildUserNo();
	
	/** 获取账户编号 **/
	String buildAccountNo();
}