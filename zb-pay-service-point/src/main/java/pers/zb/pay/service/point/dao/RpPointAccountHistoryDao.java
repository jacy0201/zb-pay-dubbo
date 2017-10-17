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
package pers.zb.pay.service.point.dao;


import pers.zb.pay.common.core.dao.BaseDao;
import pers.zb.pay.service.point.entity.RpPointAccountHistory;

public interface RpPointAccountHistoryDao extends BaseDao<RpPointAccountHistory> {

    /** 根据请求号获取账户历史 **/
    public RpPointAccountHistory getByRequestNo(String requestNo);
}