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
	public List<FinanceDto> queryFinanceByPage(String status,
			String financetype, String invoiceid, String receiptid,
			String customerid, String receiptdateLow, String receiptdateHigh,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("financetype", financetype);
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("receiptid", receiptid);
		paramMap.put("customerid", customerid);
		paramMap.put("receiptdateLow", receiptdateLow);
		paramMap.put("receiptdateHigh", receiptdateHigh);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<FinanceDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByPage", paramMap);
		return list;
	}

	@Override
	public int queryFinanceCountByPage(String status, String financetype,
			String invoiceid, String receiptid, String customerid,
			String receiptdateLow, String receiptdateHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("financetype", financetype);
		paramMap.put("invoiceid", invoiceid);
		paramMap.put("receiptid", receiptid);
		paramMap.put("customerid", customerid);
		paramMap.put("receiptdateLow", receiptdateLow);
		paramMap.put("receiptdateHigh", receiptdateHigh);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryFinanceCountByPage", paramMap);
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
	public void updateFinance(FinanceDto finance) {
		getSqlMapClientTemplate().update("updateFinance", finance);
	}

}
