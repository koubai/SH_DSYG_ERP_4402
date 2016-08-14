package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dto.FinanceDto;

/**
 * @name FinanceDaoImpl.java
 * @author Frank
 * @time 2015-6-27下午11:41:28
 * @version 1.0
 */
public class FinanceDaoImpl extends BaseDao implements FinanceDao {
	
	@Override
	public List<FinanceDto> queryFinanceByStatus(String status, String res10sql, String customername, String invoiceid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("res10sql", res10sql);
		paramMap.put("customername", customername);
		paramMap.put("invoiceid", invoiceid);
		@SuppressWarnings("unchecked")
		List<FinanceDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByStatus", paramMap);
		return list;
	}

	@Override
	public List<FinanceDto> queryFinanceByPage(String expressno, String status,
			String financetype, String invoiceid, String receiptid,
			String customerid, String receiptdateLow, String receiptdateHigh, String billno, String res02, String expressName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("res08", expressno);
		paramMap.put("financetype", financetype);
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("receiptid", receiptid);
		paramMap.put("customerid", customerid);
		paramMap.put("receiptdateLow", receiptdateLow);
		paramMap.put("receiptdateHigh", receiptdateHigh);
		paramMap.put("billno", billno);
		paramMap.put("res02", res02);
		paramMap.put("customername", expressName);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<FinanceDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByPage", paramMap);
		return list;
	}

	@Override
	public int queryFinanceCountByPage(String expressno, String status, String financetype,
			String invoiceid, String receiptid, String customerid,
			String receiptdateLow, String receiptdateHigh, String billno, String res02, String expressName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("res08", expressno);
		paramMap.put("financetype", financetype);
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("receiptid", receiptid);
		paramMap.put("customerid", customerid);
		paramMap.put("receiptdateLow", receiptdateLow);
		paramMap.put("receiptdateHigh", receiptdateHigh);
		paramMap.put("billno", billno);
		paramMap.put("res02", res02);
		paramMap.put("customername", expressName);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryFinanceCountByPage", paramMap);
	}

	@Override
	public Double queryFinance(String expressno, String status,
			String financetype, String invoiceid, String receiptid,
			String customerid, String receiptdateLow, String receiptdateHigh, String billno, String res02, String expressName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("res08", expressno);
		paramMap.put("financetype", financetype);
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("receiptid", receiptid);
		paramMap.put("customerid", customerid);
		paramMap.put("receiptdateLow", receiptdateLow);
		paramMap.put("receiptdateHigh", receiptdateHigh);
		paramMap.put("billno", billno);
		paramMap.put("res02", res02);
		paramMap.put("customername", expressName);
		return (Double) getSqlMapClientTemplate().queryForObject("queryFinance", paramMap);
	}
	
	@Override
	public FinanceDto queryFinanceByInvoiceid(String invoiceid, String financetype) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("financetype", financetype);
		@SuppressWarnings("unchecked")
		List<FinanceDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByInvoiceid", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public FinanceDto queryFinanceByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<FinanceDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertFinance(FinanceDto finance) {
		getSqlMapClientTemplate().insert("insertFinance", finance);
	}
	
	@Override
	public void deleteFinance(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteFinance", paramMap);
	}

	@Override
	public void updateFinance(FinanceDto finance) {
		getSqlMapClientTemplate().update("updateFinance", finance);
	}
}
