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
package pers.zb.pay.web.boss.controller.trade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.zb.pay.common.core.enums.PayTypeEnum;
import pers.zb.pay.common.core.enums.PayWayEnum;
import pers.zb.pay.common.core.enums.TrxTypeEnum;
import pers.zb.pay.common.core.page.PageBean;
import pers.zb.pay.common.core.page.PageParam;
import pers.zb.pay.service.trade.api.RpTradePaymentQueryService;
import pers.zb.pay.service.trade.enums.TradeStatusEnum;
import pers.zb.pay.service.trade.vo.PaymentOrderQueryVo;
import pers.zb.pay.service.user.enums.FundInfoTypeEnum;


@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private RpTradePaymentQueryService rpTradePaymentQueryService;

    @RequestMapping(value = "/listPaymentOrder", method ={RequestMethod.POST,RequestMethod.GET})
    public String listPaymentOrder(HttpServletRequest request, PaymentOrderQueryVo paymentOrderQueryVo, PageParam pageParam, Model model) {
        PageBean pageBean = rpTradePaymentQueryService.listPaymentOrderPage(pageParam, paymentOrderQueryVo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpTradePaymentOrder",paymentOrderQueryVo);//查询条件

        model.addAttribute("statusEnums", TradeStatusEnum.toMap());//状态
        model.addAttribute("payWayNameEnums", PayWayEnum.toMap());//支付方式
        model.addAttribute("payTypeNameEnums", PayTypeEnum.toMap());//支付类型
        model.addAttribute("fundIntoTypeEnums", FundInfoTypeEnum.toMap());//支付类型

        return "trade/listPaymentOrder";
    }


    @RequestMapping(value = "/listPaymentRecord", method ={RequestMethod.POST,RequestMethod.GET})
    public String listPaymentRecord(HttpServletRequest request,PaymentOrderQueryVo paymentOrderQueryVo,PageParam pageParam, Model model) {
        PageBean pageBean = rpTradePaymentQueryService.listPaymentRecordPage(pageParam, paymentOrderQueryVo);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpUserInfo",paymentOrderQueryVo);

        model.addAttribute("statusEnums", TradeStatusEnum.toMap());//状态
        model.addAttribute("payWayNameEnums", PayWayEnum.toMap());//支付方式
        model.addAttribute("payTypeNameEnums", PayTypeEnum.toMap());//支付类型
        model.addAttribute("fundIntoTypeEnums", FundInfoTypeEnum.toMap());//支付类型
        model.addAttribute("trxTypeEnums", TrxTypeEnum.toMap());//支付类型
        return "trade/listPaymentRecord";
    }
}
