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
package pers.zb.pay.service.trade.enums.weixinpay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付,交易类型枚举类
 *
 * @author zhoubang
 * @date 2017年10月17日 22:25:12
 *
 */
public enum WeiXinTradeTypeEnum {

    /** JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
     MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口 **/
    JSAPI("公众号支付"),NATIVE("原生扫码支付"),APP("app支付"),MICROPAY("刷卡支付");

    /** 描述 */
    private String desc;

    private WeiXinTradeTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        WeiXinTradeTypeEnum[] ary = WeiXinTradeTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = ary[num].name();
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List toList() {
        WeiXinTradeTypeEnum[] ary = WeiXinTradeTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static WeiXinTradeTypeEnum getEnum(String name) {
        WeiXinTradeTypeEnum[] arry = WeiXinTradeTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        WeiXinTradeTypeEnum[] enums = WeiXinTradeTypeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (WeiXinTradeTypeEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
