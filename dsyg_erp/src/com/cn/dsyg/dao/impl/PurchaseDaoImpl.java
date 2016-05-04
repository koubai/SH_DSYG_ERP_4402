package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseExtDto;

/**
 * @name PurchaseDaoImpl.java
 * @author Frank
 * @time 2015-5-9下午10:22:05
 * @version 1.0
 */
public class PurchaseDaoImpl extends BaseDao implements PurchaseDao {
	
	@Override
	public List<PurchaseDto> queryAllPurchaseToExcel(String purchasedateLow,
			String purchasedateHigh, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("status", status);
		@SuppressWarnings("unchecked")
		List<PurchaseDto> list = getSqlMapClientTemplate().queryForList("queryAllPurchaseToExcel", paramMap);
		return list;
	}
	
	@Override
	public int queryFinancePurchaseCountByPage(String purchasedateLow,
			String purchasedateHigh, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryFinancePurchaseCountByPage", paramMap);
	}

	@Override
	public List<PurchaseDto> queryFinancePurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PurchaseDto> list = getSqlMapClientTemplate().queryForList("queryFinancePurchaseByPage", paramMap);
		return list;
	}
	
	@Override
	public PurchaseDto queryPurchaseByNo(String purchaseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		@SuppressWarnings("unchecked")
		List<PurchaseDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseByNo", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PurchaseDto queryPurchaseByTheme2(String theme2) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme2", theme2);
		@SuppressWarnings("unchecked")
		List<PurchaseDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseByTheme2", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	@Override
	public int queryPurchaseCountByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryPurchaseCountByPage", paramMap);
	}

	@Override
	public int queryPurchaseExtCountByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryPurchaseExtCountByPage", paramMap);
	}

	@Override
	public String queryPurchaseQuantity(String purchasedateLow,
			String purchasedateHigh, String theme2, String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		return (String) getSqlMapClientTemplate().queryForObject("queryPurchaseQuantity", paramMap);
	}
	
	@Override
	public List<PurchaseExtDto> queryPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PurchaseExtDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseByPage", paramMap);
		return list;
	}

	@Override
	public List<PurchaseExtDto> queryPurchaseExtByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String productid, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("theme2", theme2);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PurchaseExtDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseExtByPage", paramMap);
		return list;
	}
	
	@Override
	public PurchaseDto queryPurchaseByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<PurchaseDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deletePurchase(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deletePurchase", paramMap);
	}

	@Override
	public void insertPurchase(PurchaseDto purchase) {
		getSqlMapClientTemplate().insert("insertPurchase", purchase);
	}

	@Override
	public void updatePurchase(PurchaseDto purchase) {
		getSqlMapClientTemplate().update("updatePurchase", purchase);
	}
}
