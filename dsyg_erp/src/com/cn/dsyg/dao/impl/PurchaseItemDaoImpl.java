package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesItemDto;

/**
 * @name PurchaseItemDaoImpl.java
 * @author Frank
 * @time 2015-5-17下午10:38:48
 * @version 1.0
 */
public class PurchaseItemDaoImpl extends BaseDao implements PurchaseItemDao {
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByPurchaseno(
			String purchaseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		@SuppressWarnings("unchecked")
		List<PurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByPurchaseno", paramMap);
		return list;
	}

	@Override
	public PurchaseItemDto queryPurchaseItemByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<PurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deletePurchaseItemByPurchaseno(String purchaseno,
			String updateuid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		paramMap.put("updateuid", updateuid);
		getSqlMapClientTemplate().update("deletePurchaseItemByPurchaseno", paramMap);
	}
	
	@Override
	public void deleteNoUsePurchaseItemByPurchaseno(String purchaseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		getSqlMapClientTemplate().update("deleteNoUsePurchaseItemByPurchaseno", paramMap);
	}
	
	@Override
	public void deleteAllPurchaseItemByPurchaseno(String purchaseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		getSqlMapClientTemplate().update("deleteAllPurchaseItemByPurchaseno", paramMap);
	}

	@Override
	public void insertPurchaseItem(PurchaseItemDto purchaseItem) {
		getSqlMapClientTemplate().insert("insertPurchaseItem", purchaseItem);
	}

	@Override
	public void updatePurchaseItem(PurchaseItemDto purchaseItem) {
		getSqlMapClientTemplate().update("updatePurchaseItem", purchaseItem);
	}
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByProductid(String productid,
			String supplierid, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("supplierid", supplierid);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByProductid", paramMap);
		return list;
	}
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByProductidForCompare(String productid,
			String supplierid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("supplierid", supplierid);
		@SuppressWarnings("unchecked")
		List<PurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByProductidForCompare", paramMap);
		return list;
	}

	@Override
	public int queryRemainPurchaseCountByPage(String suppliername) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("suppliername", suppliername);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryRemainPurchaseItemCount", paramMap);
	}

	@Override
	public List<PurchaseItemDto> queryRemainPurchaseByPage(String suppliername,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("suppliername", suppliername);
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryRemainPurchaseItem", paramMap);
		return list;
	}
}
