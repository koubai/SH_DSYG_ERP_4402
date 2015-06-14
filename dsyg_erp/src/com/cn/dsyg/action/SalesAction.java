package com.cn.dsyg.action;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Page;
import com.cn.dsyg.dto.PurchaseDto;

/**
 * 销售单Action
 * @name SalesAction.java
 * @author Frank
 * @time 2015-6-13下午11:46:16
 * @version 1.0
 */
public class SalesAction extends BaseAction {

	private static final long serialVersionUID = 3137296006898420519L;
	private static final Logger log = LogManager.getLogger(SalesAction.class);
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;

	public String showSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
		} catch(Exception e) {
			log.error("showSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
}
