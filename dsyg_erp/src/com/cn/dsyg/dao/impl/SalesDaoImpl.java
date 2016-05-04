package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesExtDto;

/**
 * @name SalesDaoImpl.java
 * @author Frank
 * @time 2015-6-16下午9:53:31
 * @version 1.0
 */
public class SalesDaoImpl extends BaseDao implements SalesDao {
	
	@Override
	public int queryFinanceSalesCountByPage(String bookdateLow,
			String bookdateHigh, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryFinanceSalesCountByPage", paramMap);
	}

	@Override
	public List<SalesDto> queryFinanceSalesByPage(String bookdateLow,
			String bookdateHigh, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesDto> list = getSqlMapClientTemplate().queryForList("queryFinanceSalesByPage", paramMap);
		return list;
	}

	@Override
	public int querySalesCountByPage(String bookdateLow, String bookdateHigh, String theme2, String res02, String customername, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		
		paramMap.put("customername", customername);
		paramMap.put("res02", res02);
		return (Integer) getSqlMapClientTemplate().queryForObject("querySalesCountByPage", paramMap);
	}

	@Override
	public List<SalesExtDto> querySalesByPage(String bookdateLow,
			String bookdateHigh, String theme2, String res02, String customername, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		
		paramMap.put("customername", customername);
		paramMap.put("res02", res02);
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesExtDto> list = getSqlMapClientTemplate().queryForList("querySalesByPage", paramMap);
		return list;
	}

	@Override
	public String querySalesQuantity(String bookdateLow, String bookdateHigh, String theme2, String res02, String customername, String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		
		paramMap.put("customername", customername);
		paramMap.put("res02", res02);
		paramMap.put("productid", productid);
		return (String) getSqlMapClientTemplate().queryForObject("querySalesQuantity", paramMap);
	}

	@Override
	public int querySalesExtCountByPage(String bookdateLow, String bookdateHigh, String theme2, String res02, String customername, String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		
		paramMap.put("customername", customername);
		paramMap.put("res02", res02);
		paramMap.put("productid", productid);
		return (Integer) getSqlMapClientTemplate().queryForObject("querySalesExtCountByPage", paramMap);
	}

	@Override
	public List<SalesExtDto> querySalesExtByPage(String bookdateLow,
			String bookdateHigh, String theme2, String res02, String customername, String productid, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bookdateLow", bookdateLow);
		paramMap.put("bookdateHigh", bookdateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		
		paramMap.put("customername", customername);
		paramMap.put("res02", res02);
		paramMap.put("productid", productid);
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesExtDto> list = getSqlMapClientTemplate().queryForList("querySalesExtByPage", paramMap);
		return list;
	}

	@Override
	public SalesDto querySalesByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<SalesDto> list = getSqlMapClientTemplate().queryForList("querySalesByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public SalesDto querySalesByNo(String salesno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("salesno", salesno);
		@SuppressWarnings("unchecked")
		List<SalesDto> list = getSqlMapClientTemplate().queryForList("querySalesByNo", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public SalesDto querySalesByTheme2(String theme2) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme2", theme2);
		@SuppressWarnings("unchecked")
		List<SalesDto> list = getSqlMapClientTemplate().queryForList("querySalesByTheme2", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	@Override
	public void deleteSales(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteSales", paramMap);
	}

	@Override
	public void insertSales(SalesDto sales) {
		getSqlMapClientTemplate().insert("insertSales", sales);
	}

	@Override
	public void updateSales(SalesDto sales) {
		getSqlMapClientTemplate().update("updateSales", sales);
	}

	@Override
	public int queryDetailCustomerCountByPage(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryDetailCustomerCountByPage", paramMap);
	}

	@Override
	public List<SalesDto> queryDetailCustomerByPage(String productid, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesDto> list = getSqlMapClientTemplate().queryForList("queryDetailCustomerByPage", paramMap);
		return list;
	}
}
