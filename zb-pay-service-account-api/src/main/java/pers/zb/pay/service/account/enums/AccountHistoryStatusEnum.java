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
package pers.zb.pay.service.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户资金流水状态
 *
 * @author zhoubang
 * @date 2017年10月17日 20:34:26
 *
 */
public enum AccountHistoryStatusEnum {

	/**
	 * 预处理阶段
	 */
	TRYING("处理中"),

	/**
	 * 已确认的
	 */
	CONFORM("已确认"),

	/**
	 * 取消的
	 */
	CANCEL("取消");


	/** 描述 */
	private String label;

	private AccountHistoryStatusEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}



	public static List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		AccountHistoryStatusEnum[] val = AccountHistoryStatusEnum.values();
		for (AccountHistoryStatusEnum e : val) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", e.getLabel());
			map.put("name", e.name());
			list.add(map);
		}
		return list;
	}

	public static AccountHistoryStatusEnum getEnum(String name) {
		AccountHistoryStatusEnum resultEnum = null;
		AccountHistoryStatusEnum[] enumAry = AccountHistoryStatusEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].name().equals(name)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

}
