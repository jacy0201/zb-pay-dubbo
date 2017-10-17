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
package pers.zb.pay.service.user.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import pers.zb.pay.common.core.dao.impl.BaseDaoImpl;
import pers.zb.pay.common.core.enums.PublicStatusEnum;
import pers.zb.pay.service.user.dao.RpUserPayConfigDao;
import pers.zb.pay.service.user.entity.RpUserPayConfig;


/**
 * 用户支付配置dao实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 20:57:55
 *
 */
@Repository
public class RpUserPayConfigDaoImpl  extends BaseDaoImpl<RpUserPayConfig> implements RpUserPayConfigDao {
    @Override
    public RpUserPayConfig getByUserNo(String userNo, String auditStatus) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("userNo",userNo);
        paramMap.put("status", PublicStatusEnum.ACTIVE.name());
        paramMap.put("auditStatus", auditStatus);
        return super.getBy(paramMap);
    }
}