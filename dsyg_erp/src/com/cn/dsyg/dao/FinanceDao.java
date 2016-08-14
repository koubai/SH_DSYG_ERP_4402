package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.FinanceDto;

/**
 * @name FinanceDao.java
 * @author Frank
 * @time 2015-6-27下午11:38:28
 * @version 1.0
 */
public interface FinanceDao {
	
	/**
	 * 根据状态查询财务记录
	 * @param status
	 * @param res10sql
	 * @param customername
	 * @param invoiceid
	 * @return
	 */
	public List<FinanceDto> queryFinanceByStatus(String status, String res10sql, String customername, String invoiceid);

	/**
	 * 翻页查询财务信息
	 * @param expressno
	 * @param status
	 * @param financetype
	 * @param invoiceid
	 * @param receiptid
	 * @param customerid
	 * @param receiptdateLow
	 * @param receiptdateHigh
	 * @param billno
	 * @param res02
	 * @param expressName
	 * @param start
	 * @param end
	 * @return
	 */
	public List<FinanceDto> queryFinanceByPage(String expressno, String status, String financetype, String invoiceid,
			String receiptid, String customerid, String receiptdateLow, String receiptdateHigh,
			String billno, String res02, String expressName, int start, int end);

	
	/**
	 * 查询财务金额信息
	 * @param expressno
	 * @param status
	 * @param financetype
	 * @param invoiceid
	 * @param receiptid
	 * @param customerid
	 * @param receiptdateLow
	 * @param receiptdateHigh
	 * @param billno
	 * @param res02
	 * @param expressName
	 * @return
	 */
	public Double queryFinance(String expressno, String status, String financetype, String invoiceid,
			String receiptid, String customerid, String receiptdateLow, String receiptdateHigh,
			String billno, String res02, String expressName);
	
	
	/**
	 * 查询记录数
	 * @param expressno
	 * @param status
	 * @param financetype
	 * @param invoiceid
	 * @param receiptid
	 * @param customerid
	 * @param receiptdateLow
	 * @param receiptdateHigh
	 * @param billno
	 * @param res02
	 * @param expressName
	 * @return
	 */
	public int queryFinanceCountByPage(String expressno, String status, String financetype, String invoiceid,
			String receiptid, String customerid, String receiptdateLow, String receiptdateHigh, String billno, String res02, String expressName);
	
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public FinanceDto queryFinanceByID(String id);
	
	/**
	 * 根据invoiceid查询记录
	 * @param invoiceid
	 * @param financetype
	 * @return
	 */
	public FinanceDto queryFinanceByInvoiceid(String invoiceid, String financetype);
	
	/**
	 * 新增记录
	 * @param finance
	 */
	public void insertFinance(FinanceDto finance);
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void deleteFinance(String id);
	
	/**
	 * 修改记录
	 * @param finance
	 */
	public void updateFinance(FinanceDto finance);
}
