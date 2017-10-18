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
package pers.zb.pay.web.boss.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pers.zb.pay.common.core.dwz.DWZ;
import pers.zb.pay.common.core.dwz.DwzAjax;
import pers.zb.pay.common.core.exception.BizException;


/**
 * Spring异常拦截器.
 *
 * @author zhoubang
 * @date 2017年10月18日 21:43:17
 *
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Log LOG = LogFactory.getLog(WebExceptionHandler.class);

	/**
	 * 没有权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processBizException(HttpServletRequest request, BizException e) {
		LOG.error("BizException", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage(e.getMsg());
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}


	/**
	 * 总异常
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(Exception e, HttpServletRequest request) {
		LOG.error("Exception", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage("系统异常");
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

}
