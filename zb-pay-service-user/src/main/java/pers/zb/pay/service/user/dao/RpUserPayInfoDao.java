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
import pers.zb.pay.service.user.entity.RpUserPayInfo;

/**
 * 用户第三方支付信息dao
 *
 * @author zhoubang
 * @date 2017年10月17日 20:55:50
 *
 */
public interface RpUserPayInfoDao  extends BaseDao<RpUserPayInfo> {

    /**
     * 通过商户编号获取商户第三方支付信息
     * @param userNo
     * @param payWayCode
     * @return
     */
    public  RpUserPayInfo getByUserNo(String userNo, String payWayCode);

}