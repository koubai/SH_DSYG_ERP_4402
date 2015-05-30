package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.EtbPurchaseItemDao;
import com.cn.dsyg.dto.EtbPurchaseItemDto;

/**
 * @name EtbPurchaseItemDaoImpl.java
 * @author Frank
 * @time 2015-5-17下午10:38:48
 * @version 1.0
 */
public class EtbPurchaseItemDaoImpl extends BaseDao implements EtbPurchaseItemDao {

	@Override
	public List<EtbPurchaseItemDto> queryPurchaseItemByPurchaseno(
			String purchaseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("purchaseno", purchaseno);
		@SuppressWarnings("unchecked")
		List<EtbPurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByPurchaseno", paramMap);
		return list;
	}

	@Override
	public EtbPurchaseItemDto queryPurchaseItemByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<EtbPurchaseItemDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseItemByID", paramMap);
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
	public void insertPurchaseItem(EtbPurchaseItemDto purchaseItem) {
		getSqlMapClientTemplate().insert("insertPurchaseItem", purchaseItem);
	}

	@Override
	public void updatePurchaseItem(EtbPurchaseItemDto purchaseItem) {
		getSqlMapClientTemplate().update("updatePurchaseItem", purchaseItem);
	}
}
