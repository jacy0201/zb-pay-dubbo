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
package pers.zb.pay.service.trade.biz.impl;


import org.mengyun.tcctransaction.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zb.pay.common.core.utils.DateUtils;
import pers.zb.pay.service.account.api.RpAccountTransactionService;
import pers.zb.pay.service.point.api.RpPointAccountService;
import pers.zb.pay.service.trade.dao.RpTradePaymentOrderDao;
import pers.zb.pay.service.trade.dao.RpTradePaymentRecordDao;
import pers.zb.pay.service.trade.entity.RpTradePaymentOrder;
import pers.zb.pay.service.trade.entity.RpTradePaymentRecord;
import pers.zb.pay.service.trade.enums.TradeStatusEnum;
import pers.zb.pay.service.user.enums.FundInfoTypeEnum;

import java.util.Date;


@Service
public class RpTradePaymentManagerBizImpl {

    private static final Logger LOG = LoggerFactory.getLogger(RpTradePaymentManagerBizImpl.class);

    @Autowired
    private RpTradePaymentOrderDao rpTradePaymentOrderDao;

    @Autowired
    private RpTradePaymentRecordDao rpTradePaymentRecordDao;

    @Autowired
    private RpAccountTransactionService rpAccountTransactionService;

    @Autowired
    private RpPointAccountService rpPointAccountService;

    /**
     * 支付成功方法
     * @param rpTradePaymentRecord
     */
    @Compensable(confirmMethod = "confirmCompleteSuccessOrder",cancelMethod = "cancelCompleteSuccessOrder")
    public void completeSuccessOrder(RpTradePaymentRecord rpTradePaymentRecord , String bankTrxNo , Date timeEnd , String bankReturnMsg) throws Exception{

        LOG.info("------completeSuccessOrder[订单{}完成支付TRYING阶段开始时间{}]------",rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

        // 修改支付记录状态
        rpTradePaymentRecord.setPaySuccessTime(timeEnd);
        rpTradePaymentRecord.setBankTrxNo(bankTrxNo);
        rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        rpTradePaymentRecord.setStatus(TradeStatusEnum.WAITING_PAYMENT_RESULT.name());
        rpTradePaymentRecordDao.update(rpTradePaymentRecord);

        // 修改支付订单状态
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());
        rpTradePaymentOrder.setStatus(TradeStatusEnum.WAITING_PAYMENT_RESULT.name());
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        // 给商户资金帐户加款（平台收款）
        if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(rpTradePaymentRecord.getFundIntoType())){
            LOG.info("==>修改订单账户金额");
            rpAccountTransactionService.creditToAccountTcc(null, rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getOrderAmount().subtract(rpTradePaymentRecord.getPlatIncome()), rpTradePaymentRecord.getBankOrderNo(),rpTradePaymentRecord.getBankTrxNo(), rpTradePaymentRecord.getTrxType(), rpTradePaymentRecord.getRemark());
        }

        // 增加消费积分（模拟TCC事务使用，非真实场景）
        rpPointAccountService.creditToPointAccountTcc(null,rpTradePaymentRecord.getMerchantNo(), 10, rpTradePaymentRecord.getBankOrderNo(),rpTradePaymentRecord.getBankTrxNo(), rpTradePaymentRecord.getTrxType(), rpTradePaymentRecord.getRemark());
        LOG.info("==>修改账户金额");

        LOG.info("------completeSuccessOrder[订单{}完成支付TRYING阶段结束时间{}]------",rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }

    @Transactional
    public void confirmCompleteSuccessOrder(RpTradePaymentRecord rpTradePaymentRecord , String bankTrxNo ,Date timeEnd ,  String bankReturnMsg){

        LOG.info("------confirmCompleteSuccessOrder[订单{}完成支付CONFIRMING阶段开始时间{}]------",rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        // 修改支付记录状态
        rpTradePaymentRecord.setPaySuccessTime(timeEnd);
        rpTradePaymentRecord.setBankTrxNo(bankTrxNo);
        rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        rpTradePaymentRecord.setStatus(TradeStatusEnum.SUCCESS.name());
        rpTradePaymentRecordDao.update(rpTradePaymentRecord);

        // 修改支付订单状态
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());
        rpTradePaymentOrder.setStatus(TradeStatusEnum.SUCCESS.name());
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        LOG.info("------confirmCompleteSuccessOrder[订单{}完成支付CONFIRMING阶段结束时间{}]------", rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

    }

    @Transactional
    public void cancelCompleteSuccessOrder(RpTradePaymentRecord rpTradePaymentRecord , String bankTrxNo ,Date timeEnd ,  String bankReturnMsg){

        //根据银行订单号获取支付信息
        RpTradePaymentRecord dataRpTradePaymentRecord = rpTradePaymentRecordDao.getByBankOrderNo(rpTradePaymentRecord.getBankOrderNo());//数据库数据
     // 幂等判断
        if(TradeStatusEnum.SUCCESS.name().equals(dataRpTradePaymentRecord.getStatus()) ||TradeStatusEnum.WAITING_PAYMENT.name().equals(dataRpTradePaymentRecord.getStatus()) ){
        	LOG.info("订单状态：{}，不能执行取消动作",dataRpTradePaymentRecord.getStatus());
        	return;
        }
    	
        LOG.info("------cancelCompleteSuccessOrder[订单{}完成支付CANCELING阶段开始时间{}]------",rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        // 修改支付记录状态
        rpTradePaymentRecord.setPaySuccessTime(timeEnd);
        rpTradePaymentRecord.setBankTrxNo(bankTrxNo);
        rpTradePaymentRecord.setBankReturnMsg(bankReturnMsg);
        rpTradePaymentRecord.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());
        rpTradePaymentRecordDao.update(rpTradePaymentRecord);

        // 修改支付订单状态
        RpTradePaymentOrder rpTradePaymentOrder = rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(), rpTradePaymentRecord.getMerchantOrderNo());
        rpTradePaymentOrder.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());
        rpTradePaymentOrderDao.update(rpTradePaymentOrder);

        LOG.info("------cancelCompleteSuccessOrder[订单{}完成支付CANCELING阶段结束时间{}]------",rpTradePaymentRecord.getBankOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
    }
}
