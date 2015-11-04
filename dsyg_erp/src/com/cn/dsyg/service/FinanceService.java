package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.FinanceDto;

/**
 * @name FinanceService.java
 * @author Frank
 * @time 2015-6-27下午11:45:41
 * @version 1.0
 */
public interface FinanceService {

	/**
	 * 翻页查询财务信息
	 * @param status
	 * @param financetype
	 * @param invoiceid
	 * @param receiptid
	 * @param customerid
	 * @param receiptdateLow
	 * @param receiptdateHigh
	 * @param billno
	 * @param page
	 * @return
	 */
	public Page queryFinanceByPage(String status, String financetype, String invoiceid,
			String receiptid, String customerid, String receiptdateLow, String receiptdateHigh, String billno, String res02, Page page);
	
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public FinanceDto queryFinanceByID(String id);
	
	/**
	 * 新增记录
	 * @param finance
	 */
	public String insertFinance(FinanceDto finance);
	
	/**
	 * 修改记录
	 * @param finance
	 */
	public void updateFinance(FinanceDto finance);
}
