package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.EtbPurchaseDao;
import com.cn.dsyg.dto.EtbPurchaseDto;

/**
 * @name EtbPurchaseDaoImpl.java
 * @author Frank
 * @time 2015-5-9下午10:22:05
 * @version 1.0
 */
public class EtbPurchaseDaoImpl extends BaseDao implements EtbPurchaseDao {

	@Override
	public int queryEtbPurchaseCountByPage(String purchasedateLow,
			String purchasedateHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbPurchaseCountByPage", paramMap);
	}

	@Override
	public List<EtbPurchaseDto> queryEtbPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchasedateLow", purchasedateLow);
		paramMap.put("purchasedateHigh", purchasedateHigh);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<EtbPurchaseDto> list = getSqlMapClientTemplate().queryForList("queryEtbPurchaseByPage", paramMap);
		return list;
	}

	@Override
	public EtbPurchaseDto queryEtbPurchaseByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<EtbPurchaseDto> list = getSqlMapClientTemplate().queryForList("queryEtbPurchaseByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteEtbPurchase(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteEtbPurchase", paramMap);
	}

	@Override
	public void insertEtbPurchase(EtbPurchaseDto etbPurchase) {
		getSqlMapClientTemplate().insert("insertEtbPurchase", etbPurchase);
	}

	@Override
	public void updateEtbPurchase(EtbPurchaseDto etbPurchase) {
		getSqlMapClientTemplate().update("updateEtbPurchase", etbPurchase);
	}
}
