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
package pers.zb.pay.service.notify.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.service.notify.dao.RpNotifyRecordDao;
import pers.zb.pay.service.notify.entity.RpNotifyRecord;


@Repository("rpNotifyRecordDao")
public class RpNotifyRecordDaoImpl extends BaseDaoImpl<RpNotifyRecord> implements RpNotifyRecordDao {


    @Override
    public RpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyType) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("merchantNo",merchantNo);
        paramMap.put("merchantOrderNo",merchantOrderNo);
        paramMap.put("notifyType",notifyType);

        return super.getBy(paramMap);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(RpNotifyRecord record) {
        return 0;
    }

    @Override
    public RpNotifyRecord selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RpNotifyRecord record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RpNotifyRecord record) {
        return 0;
    }

}
