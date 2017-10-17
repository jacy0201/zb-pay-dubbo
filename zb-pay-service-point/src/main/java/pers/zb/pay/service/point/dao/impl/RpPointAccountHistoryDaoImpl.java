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
package pers.zb.pay.service.point.dao.impl;

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.service.point.dao.RpPointAccountHistoryDao;
import pers.zb.pay.service.point.entity.RpPointAccountHistory;

import java.util.HashMap;
import java.util.Map;


@Repository
public class RpPointAccountHistoryDaoImpl extends BaseDaoImpl<RpPointAccountHistory> implements RpPointAccountHistoryDao {
    /**
     * 根据请求号获取账户历史
     *
     * @param requestNo
     **/
    @Override
    public RpPointAccountHistory getByRequestNo(String requestNo) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("requestNo",requestNo);
        return super.getBy(paramMap);
    }
}
