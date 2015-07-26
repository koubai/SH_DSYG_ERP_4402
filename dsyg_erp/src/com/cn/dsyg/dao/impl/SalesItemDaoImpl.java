package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dto.SalesItemDto;

/**
 * @name SalesItemDaoImpl.java
 * @author Frank
 * @time 2015-6-17下午9:42:32
 * @version 1.0
 */
public class SalesItemDaoImpl extends BaseDao implements SalesItemDao {

	@Override
	public List<SalesItemDto> querySalesItemBySalesno(String salesno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("salesno", salesno);
		@SuppressWarnings("unchecked")
		List<SalesItemDto> list = getSqlMapClientTemplate().queryForList("querySalesItemBySalesno", paramMap);
		return list;
	}

	@Override
	public SalesItemDto querySalesItemByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<SalesItemDto> list = getSqlMapClientTemplate().queryForList("querySalesItemByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteSalesItemBySalesno(String salesno, String updateuid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("salesno", salesno);
		paramMap.put("updateuid", updateuid);
		getSqlMapClientTemplate().update("deleteSalesItemBySalesno", paramMap);
	}

	@Override
	public void insertSalesItem(SalesItemDto salesItem) {
		getSqlMapClientTemplate().insert("insertSalesItem", salesItem);
	}

	@Override
	public void updateSalesItem(SalesItemDto salesItem) {
		getSqlMapClientTemplate().update("updateSalesItem", salesItem);
	}
	
	@Override
	public List<SalesItemDto> querySalesItemByProductid(String productid,
			String customerid, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("customerid", customerid);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesItemDto> list = getSqlMapClientTemplate().queryForList("querySalesItemByProductid", paramMap);
		return list;
	}
}
