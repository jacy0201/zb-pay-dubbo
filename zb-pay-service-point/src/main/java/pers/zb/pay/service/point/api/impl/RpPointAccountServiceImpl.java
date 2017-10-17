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
package pers.zb.pay.service.point.api.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zb.pay.common.core.enums.PublicEnum;
import pers.zb.pay.common.core.exception.BizException;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.common.core.utils.StringUtil;
import pers.zb.pay.service.point.api.RpPointAccountService;
import pers.zb.pay.service.point.dao.RpPointAccountDao;
import pers.zb.pay.service.point.dao.RpPointAccountHistoryDao;
import pers.zb.pay.service.point.entity.RpPointAccount;
import pers.zb.pay.service.point.entity.RpPointAccountHistory;
import pers.zb.pay.service.point.enums.PointAccountFundDirectionEnum;
import pers.zb.pay.service.point.enums.PointAccountHistoryStatusEnum;


/**
 * 账户service实现类
 *
 * @author zhoubang
 * @date 2017年10月17日 22:01:37
 *
 */
@Service("rpPointAccountService")
public class RpPointAccountServiceImpl implements RpPointAccountService {

	private static final Logger LOG = LoggerFactory.getLogger(RpPointAccountServiceImpl.class);
			
	@Autowired
	private RpPointAccountDao rpPointAccountDao;

	@Autowired
	private RpPointAccountHistoryDao rpPointAccountHistoryDao;

	@Override
	public void saveData(RpPointAccount rpPointAccount) {
		rpPointAccountDao.insert(rpPointAccount);
	}

	@Override
	public void updateData(RpPointAccount rpPointAccount) {
		rpPointAccountDao.update(rpPointAccount);
	}


	/**
	 * 积分账户加款 Trying
	 * @param transactionContext
	 * @param userNo
	 * @param pointAmount
	 * @param requestNo
	 * @param bankTrxNo
	 * @param trxType
	 * @param remark
	 * @throws BizException
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@Compensable(confirmMethod = "confirmCreditToPointAccountTcc",cancelMethod = "cancelCreditToPointAccountTcc")
	public void creditToPointAccountTcc(TransactionContext transactionContext, String userNo, Integer pointAmount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {

		LOG.info("===>creditToPointAccountTcc TRYING begin");
		
		//根据商户编号获取商户积分账户
		RpPointAccount rpPointAccount = rpPointAccountDao.getByUserNo(userNo);
		if (rpPointAccount == null){//如果不存在商户积分账户,创建一条新的积分账户
			rpPointAccount = new RpPointAccount();
			rpPointAccount.setBalance(0);
			rpPointAccount.setUserNo(userNo);
			rpPointAccount.setStatus(PublicEnum.YES.name());
			rpPointAccount.setCreateTime(new Date());
			rpPointAccount.setId(StringUtil.get32UUID());
			rpPointAccountDao.insert(rpPointAccount);
		}

		RpPointAccountHistory rpPointAccountHistory = rpPointAccountHistoryDao.getByRequestNo(requestNo);
		// 幂等判断
		if ( rpPointAccountHistory == null ){//防止多次提交
			rpPointAccountHistory = new RpPointAccountHistory();
			rpPointAccountHistory.setId(StringUtil.get32UUID());
			rpPointAccountHistory.setCreateTime(new Date());
			rpPointAccountHistory.setStatus(PointAccountHistoryStatusEnum.TRYING.name());//消息不可用
			rpPointAccountHistory.setAmount(pointAmount);///积分账户变动额
			rpPointAccountHistory.setBalance(rpPointAccount.getBalance() + pointAmount);
			rpPointAccountHistory.setBankTrxNo(bankTrxNo);//银行流水号
			rpPointAccountHistory.setRequestNo(requestNo);//请求号
			rpPointAccountHistory.setFundDirection(PointAccountFundDirectionEnum.ADD.name());
			rpPointAccountHistory.setTrxType(trxType);
			rpPointAccountHistory.setRemark(remark);
			rpPointAccountHistory.setUserNo(userNo);
			rpPointAccountHistoryDao.insert(rpPointAccountHistory);
		}else if (PointAccountHistoryStatusEnum.CANCEL.name().equals(rpPointAccountHistory.getStatus())){
			//如果是取消的,有可能是之前的业务出现异常问题而取消,那么重试阶段,再将状态更新为TYING状态,而不是重新创建一条
			LOG.info("之前因为业务问题取消后,又重试的{}" , rpPointAccountHistory.getBankTrxNo());
			rpPointAccountHistory.setStatus(PointAccountHistoryStatusEnum.TRYING.name());
			this.rpPointAccountHistoryDao.update(rpPointAccountHistory);
		}
		//添加一条不可用的积分账户流水
		LOG.info("===>creditToPointAccountTcc TRYING end");
	}

	/**
	 *	积分账户增加确认
	 * @param transactionContext
	 * @param userNo
	 * @param pointAmount
	 * @param requestNo
	 * @param bankTrxNo
	 * @param trxType
	 * @param remark
	 * @return
	 * @throws BizException
	 */

	@Transactional(rollbackFor = Exception.class)
	public void confirmCreditToPointAccountTcc(TransactionContext transactionContext, String userNo, Integer pointAmount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {

		LOG.info("===>confirmCreditToPointAccountTcc begin");
		//根据请求号获取账户基本流水
		RpPointAccountHistory rpPointAccountHistory = rpPointAccountHistoryDao.getByRequestNo(requestNo);
		// 幂等判断
		if ( rpPointAccountHistory == null  || PointAccountHistoryStatusEnum.CONFORM.name().equals(rpPointAccountHistory.getStatus())){//该笔交易流水已处理过,不需再处理
			return;
		}

		rpPointAccountHistory.setStatus(PointAccountHistoryStatusEnum.CONFORM.name());
		rpPointAccountHistoryDao.update(rpPointAccountHistory);

		RpPointAccount rpPointAccount = rpPointAccountDao.getByUserNo(userNo);//获取用户积分账户
		rpPointAccount.setBalance(rpPointAccount.getBalance() + pointAmount);//增加账户余额
		rpPointAccountDao.update(rpPointAccount);
		
		LOG.info("===>confirmCreditToPointAccountTcc end");

	}

	/**
	 *	积分账户增加回滚
	 * @param transactionContext
	 * @param userNo
	 * @param pointAmount
	 * @param requestNo
	 * @param bankTrxNo
	 * @param trxType
	 * @param remark
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void cancelCreditToPointAccountTcc(TransactionContext transactionContext, String userNo, Integer pointAmount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {
		LOG.info("===>cancelCreditToPointAccountTcc begin");
		
		RpPointAccountHistory rpPointAccountHistory = rpPointAccountHistoryDao.getByRequestNo(requestNo);
		// 幂等判断
		if ( rpPointAccountHistory == null  || !PointAccountHistoryStatusEnum.TRYING.name().equals(rpPointAccountHistory.getStatus())){//该笔交易流水已处理过,不需再处理
			return;
		}

		rpPointAccountHistory.setStatus(PointAccountHistoryStatusEnum.CANCEL.name());
		rpPointAccountHistoryDao.update(rpPointAccountHistory);
		LOG.info("===>cancelCreditToPointAccountTcc end");
	}

	/**
	 * 积分账户加款 Trying
	 * @param userNo
	 * @param pointAmount
	 * @param requestNo
	 * @param bankTrxNo
	 * @param trxType
	 * @param remark
	 * @throws BizException
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void creditToPointAccount(String userNo, Integer pointAmount, String requestNo, String bankTrxNo, String trxType, String remark) throws BizException {

		//根据商户编号获取商户积分账户
		RpPointAccount rpPointAccount = rpPointAccountDao.getByUserNo(userNo);
		if (rpPointAccount == null){//如果不存在商户积分账户,创建一条新的积分账户
			rpPointAccount = new RpPointAccount();
			rpPointAccount.setBalance(0);
			rpPointAccount.setUserNo(userNo);
			rpPointAccount.setStatus(PublicEnum.YES.name());
			rpPointAccount.setCreateTime(new Date());
			rpPointAccount.setId(StringUtil.get32UUID());
			rpPointAccountDao.insert(rpPointAccount);
		}


		//添加一条积分历史
		RpPointAccountHistory rpPointAccountHistory = rpPointAccountHistoryDao.getByRequestNo(requestNo);
		if ( rpPointAccountHistory == null ){//防止多次提交
			rpPointAccountHistory = new RpPointAccountHistory();
			rpPointAccountHistory.setId(StringUtil.get32UUID());
			rpPointAccountHistory.setCreateTime(new Date());
			rpPointAccountHistory.setStatus(PointAccountHistoryStatusEnum.CONFORM.name());//可用
			rpPointAccountHistory.setAmount(pointAmount);///积分账户变动额
			rpPointAccountHistory.setBalance(rpPointAccount.getBalance() + pointAmount);
			rpPointAccountHistory.setBankTrxNo(bankTrxNo);//银行流水号
			rpPointAccountHistory.setRequestNo(requestNo);//请求号
			rpPointAccountHistory.setFundDirection(PointAccountFundDirectionEnum.ADD.name());
			rpPointAccountHistory.setTrxType(trxType);
			rpPointAccountHistory.setRemark(remark);
			rpPointAccountHistory.setUserNo(userNo);
			rpPointAccountHistoryDao.insert(rpPointAccountHistory);
		}

		//增加积分账户
		rpPointAccount.setBalance(rpPointAccount.getBalance() + pointAmount);//增加账户余额
		rpPointAccountDao.update(rpPointAccount);
	}

	@Override
	public RpPointAccount getDataById(String id) {
		return rpPointAccountDao.getById(id);
	}

	@Override
	public PageBean listPage(PageParam pageParam, RpPointAccount rpPointAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return rpPointAccountDao.listPage(pageParam, paramMap);
	}
}