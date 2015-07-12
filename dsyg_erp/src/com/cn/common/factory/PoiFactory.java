package com.cn.common.factory;

import com.cn.common.util.Constants;

/**
 * @name PoiFactory.java
 * @author Frank
 * @time 2013-10-9下午8:34:00
 * @version 1.0
 */
public class PoiFactory {

	/**
	 * @param type
	 * @return
	 */
	public static Poi2007Base getPoi(String type) {
		if(Constants.EXCEL_TYPE_PURCHASELIST.equals(type)) {
			//采购单数据
			return new PoiPurchase();
		}
		if(Constants.EXCEL_TYPE_WAREHOUSERPT_IN_LIST.equals(type)) {
			//入库单数据
			return new PoiWarehouserptIn();
		}
		if(Constants.EXCEL_TYPE_WAREHOUSERPT_OUT_LIST.equals(type)) {
			//出库单数据
			return new PoiWarehouserptOut();
		}
		return null;
	}
}
