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

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.service.notify.dao.RpNotifyRecordLogDao;
import pers.zb.pay.service.notify.entity.RpNotifyRecordLog;

@Repository("rpNotifyRecordLogDao")
public class RpNotifyRecordLogDaoImpl extends BaseDaoImpl<RpNotifyRecordLog> implements RpNotifyRecordLogDao {
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insertSelective(RpNotifyRecordLog record) {
        return 0;
    }

    @Override
    public RpNotifyRecordLog selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RpNotifyRecordLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RpNotifyRecordLog record) {
        return 0;
    }
}
