/*
 * ====================================================================
 * 【个人网站】：http://www.2b2b92b.com -->
 * 【网站源码】：http://git.oschina.net/zhoubang85/zb -->
 * 【技术论坛】：http://www.2b2b92b.cn -->
 * 【开源中国】：https://gitee.com/zhoubang85 -->
 *
 * 【支付-微信_支付宝_银联】技术QQ群：470414533
 * 【联系QQ】：842324724
 * 【联系Email】：842324724@qq.com
 * ====================================================================
 */
package pers.zb.pay.web.shop.test;

import org.apache.commons.lang.math.RandomUtils;
import pers.zb.pay.web.shop.utils.MerchantApiUtil;
import pers.zb.pay.web.shop.utils.PayConfigUtil;
import pers.zb.pay.web.shop.utils.httpclient.SimpleHttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainTest {

	/**
	 * 建议通过此main方法进行分布式事务的测试；这种是不会出现什么问题的；方便学习、研究，如何处理分布式事务问题；
	 * 		而通过其他（比如页面点击进行支付、其他支付方式）方式支付测试的，代码健壮性不保证；有可能会出现莫名的问题哦~
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * 模拟添加商户信息（这些商户在数据库中已经存在，不是凭空捏造），表示哪些商户进行了支付网关的请求
		 */
		List<MerchantKey> listMerchantKey = new ArrayList<MerchantKey>();//添加商户key
		listMerchantKey.add(new MerchantKey("4c52295065654407b42797cda80dd07d", "6606353e0dab4f7b9a723f2d3e3276b7"));//商户1 roncoo
		//listMerchantKey.add(new MerchantKey("abcf900288114d5fa7fde764966eb2ff", "d94d7c2d56eb4b06828cf746c8be17e6"));//商户2 Along
		//listMerchantKey.add(new MerchantKey("8ba459f55ff04f39b0128a3cb4a48f2b", "3e2ca2eb1f024570b241d65eb4832c37"));//商户3 wusc
		//listMerchantKey.add(new MerchantKey("93d1ea2f9f3b448994f39d6efc7746ef", "fad7ea79810c4af7a973c091aa7c6143"));//商户4 leslie
		//listMerchantKey.add(new MerchantKey("ca6577dff6d647ac882dfb405ceda21e", "1b8da6c9b7544856955fcff6bf920f84"));//商户5 hpt
		
		//listMerchantKey.add(new MerchantKey("4c52295065654407b42797cda80dd07d", "6606353e0dab4f7b9a723f2d3e3276b7"));//商户1 roncoo
		//listMerchantKey.add(new MerchantKey("abcf900288114d5fa7fde764966eb2ff", "d94d7c2d56eb4b06828cf746c8be17e6"));//商户2 Along
		//listMerchantKey.add(new MerchantKey("8ba459f55ff04f39b0128a3cb4a48f2b", "3e2ca2eb1f024570b241d65eb4832c37"));//商户3 wusc
		//listMerchantKey.add(new MerchantKey("93d1ea2f9f3b448994f39d6efc7746ef", "fad7ea79810c4af7a973c091aa7c6143"));//商户4 leslie
		//listMerchantKey.add(new MerchantKey("ca6577dff6d647ac882dfb405ceda21e", "1b8da6c9b7544856955fcff6bf920f84"));//商户5 hpt
		
		for(MerchantKey merchantKey : listMerchantKey){
			final String payKey = merchantKey.getPayKey();
			final String paySecret = merchantKey.getPaySecret();

			new Thread(new Runnable() {//模拟商户并发请求支付
				@Override
				public void run() {
					testGateWayPay(payKey ,paySecret);
				}
			}).start();
		}
		
	}
	
	/**
	 * 模拟支付
	 */
	private static void testGateWayPay( String payKey , String paySecret){
				
		long threadID = Thread.currentThread().getId();
		for (int i = 0; i < 1; i++) { //每个线程的生成数
			try {
				int random = RandomUtils.nextInt(10);
				long sleepNum  = 10l * random;
				Thread.sleep(sleepNum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// 创建支付订单（如果失败，需要商户重新发起）
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String merchantOrderNo = "pt"+ threadID +sdf.format(new Date()) + 000 + i; // 生成商户订单号
			Map<String , Object> paramMap = getInitRequestMap(merchantOrderNo ,  payKey ,  paySecret); // 构建订单的请求参数
			System.out.println(PayConfigUtil.readConfig("scanPayUrl"));
			String resultStr = SimpleHttpUtils.httpPost(PayConfigUtil.readConfig("scanPayUrl"), paramMap); // 请求网关创建订单与支付记录
			System.out.println(resultStr);//这个返回的内容实际上是一个页面；这个页面就是支付页面；你可以理解成：用户进入了支付页面，准备进行支付操作；
			
			if(resultStr == null || "".equals(resultStr)){//说明用户进入支付页面出现了问题或者其他错误；就不做处理了，直接跳过返回；
				continue;
			}
			
			// 用户支付的时候，肯定需要时间的；这里我们就模拟用户支付的行为 —— 等待片刻，表示用户正在进行支付；
			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//模拟构建银行扣款成功结果通知 —— 到了这里，说明用户已经支付成功了，肯定需要银行的支付回调通知了；
			Map<String , Object> notifyMap = getNotifyRequestMap(merchantOrderNo);

			//银行的回调通知，请求的是支付网关接口；由支付网关进行支付的回调处理；
			String notifyResultStr = SimpleHttpUtils.httpGet(PayConfigUtil.readConfig("gatewayWebUrl") + "/scanPayNotify/notify/TEST_PAY_HTTP_CLIENT", notifyMap);
			System.out.println(PayConfigUtil.readConfig("gatewayWebUrl"));
			System.out.println(notifyResultStr);//支付网关回调处理的结果；如果打印的是 TEST_PAY_HTTP_CLIENT success ，说明处理成功了；
		}
	}
	
	/**
	 * 模拟构建银行扣款成功结果通知
	 * @param bankOrderNo
	 * @return
	 */
	private static Map<String , Object> getNotifyRequestMap(String bankOrderNo){
		Map<String, Object> notifyMap = new HashMap<String, Object>();
		notifyMap.put("result_code", "SUCCESS");
		String timeEnd = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		notifyMap.put("time_end", timeEnd);
		notifyMap.put("out_trade_no", bankOrderNo);
		notifyMap.put("transaction_id", timeEnd);
		
		return notifyMap;
	}
	
	/**
	 * 构建订单的请求参数
	 * @param merchantOrderNo
	 * @param payKey
	 * @param paySecret
	 * @return
	 */
	private static Map<String , Object> getInitRequestMap(String merchantOrderNo , String payKey , String paySecret){
		Map<String , Object> paramMap = new HashMap<String , Object>();

        paramMap.put("orderPrice","10"); // 订单金额 , 单位:元
        paramMap.put("payWayCode","TEST_PAY_HTTP_CLIENT"); //模拟网关支付
        paramMap.put("orderNo",merchantOrderNo);   // 订单编号

        Date orderDate = new Date();//订单日期
        String orderDateStr = new SimpleDateFormat("yyyyMMdd").format(orderDate);// 订单日期
        paramMap.put("orderDate",orderDateStr);

        Date orderTime = new Date();//订单时间
        String orderTimeStr =  new SimpleDateFormat("yyyyMMddHHmmss").format(orderTime);// 订单时间
        paramMap.put("orderTime",orderTimeStr);

        paramMap.put("payKey",payKey);
        paramMap.put("productName","模拟支付网关支付产品");// 商品名称
        paramMap.put("orderIp","127.0.0.1");
        paramMap.put("orderPeriod","30"); // 订单有效期
        paramMap.put("returnUrl",PayConfigUtil.readConfig("returnUrl"));//  zb-pay-web-shop/payNotify/notify  //页面通知返回url
        paramMap.put("notifyUrl",PayConfigUtil.readConfig("notifyUrl"));//  zb-pay-web-shop/payNotify/notify // 后台消息通知Url
        paramMap.put("remark"," 支付备注"); // 支付备注

        ////////////扩展字段,选填,原值返回///////////
        String field1 = "扩展字段1"; // 扩展字段1
        paramMap.put("field1",field1);
        String field2 = "扩展字段2"; // 扩展字段2
        paramMap.put("field2",field2);
        String field3 = "扩展字段3"; // 扩展字段3
        paramMap.put("field3",field3);
        String field4 = "扩展字段4"; // 扩展字段4
        paramMap.put("field4",field4);
        String field5 = "扩展字段5"; // 扩展字段5
        paramMap.put("field5",field5);

        /////签名及生成请求API的方法///
        String sign = MerchantApiUtil.getSign(paramMap, paySecret);
        paramMap.put("sign",sign);
        
        return paramMap;
	}
}

class MerchantKey{
	private String payKey;
	
	private String paySecret;

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public String getPaySecret() {
		return paySecret;
	}

	public void setPaySecret(String paySecret) {
		this.paySecret = paySecret;
	}
	
	public MerchantKey(String payKey , String paySecret){
		this.payKey = payKey;
		this.paySecret = paySecret;
	}
}
