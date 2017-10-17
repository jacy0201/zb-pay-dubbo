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
package pers.zb.pay.service.point.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户资金变动方向
 *
 * @author zhoubang
 * @date 2017年10月17日 21:52:58
 *
 */
public enum PointAccountHistoryStatusEnum {

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

	private PointAccountHistoryStatusEnum(String label) {
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
		PointAccountHistoryStatusEnum[] val = PointAccountHistoryStatusEnum.values();
		for (PointAccountHistoryStatusEnum e : val) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", e.getLabel());
			map.put("name", e.name());
			list.add(map);
		}
		return list;
	}

	public static PointAccountHistoryStatusEnum getEnum(String name) {
		PointAccountHistoryStatusEnum resultEnum = null;
		PointAccountHistoryStatusEnum[] enumAry = PointAccountHistoryStatusEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].name().equals(name)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

}
