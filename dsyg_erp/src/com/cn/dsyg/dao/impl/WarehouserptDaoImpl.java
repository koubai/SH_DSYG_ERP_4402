package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.WarehouserptDto;

/**
 * @name WarehouserptDaoImpl.java
 * @author Frank
 * @time 2015-6-3下午9:44:36
 * @version 1.0
 */
public class WarehouserptDaoImpl extends BaseDao implements WarehouserptDao {
	
	@Override
	public List<WarehouserptDto> queryWarehouserptByWarehouse(
			String warehousetype, String warehouseno, String parentid,
			String suppliername, String createdateLow, String createdateHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("parentid", parentid);
		paramMap.put("createdateLow", createdateLow);
		paramMap.put("createdateHigh", createdateHigh);
		paramMap.put("suppliername", suppliername);
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryWarehouserptByWarehouse", paramMap);
		return list;
	}

	@Override
	public List<WarehouserptDto> queryAllWarehouserptToExport(String status,
			String warehousetype, String warehouseno, String theme1,
			String parentid, String supplierid, String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("parentid", parentid);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryAllWarehouserptToExport", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouserptDto> queryWarehouserptByPage(String status, String warehousetype,
			String warehouseno, String theme1, String parentid, String supplierid,
			String productid, String beginDate, String endDate, String strSuppliername,
			String strWarehouseno, String createdateLow, String createdateHigh, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("parentid", parentid);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		paramMap.put("suppliername", strSuppliername);
		paramMap.put("warehouseno", strWarehouseno);
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		
		paramMap.put("createdateLow", createdateLow);
		paramMap.put("createdateHigh", createdateHigh);
		
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryWarehouserptByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouserptCountByPage(String status, String warehousetype, String warehouseno, String theme1,
			String parentid, String supplierid, String productid, String beginDate, String endDate,
			String strSuppliername, String strWarehouseno, String createdateLow, String createdateHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("parentid", parentid);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		paramMap.put("suppliername", strSuppliername);
		paramMap.put("warehouseno", strWarehouseno);
		
		paramMap.put("beginDate", beginDate);
		paramMap.put("endDate", endDate);
		
		paramMap.put("createdateLow", createdateLow);
		paramMap.put("createdateHigh", createdateHigh);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouserptCountByPage", paramMap);
	}

	@Override
	public WarehouserptDto queryWarehouserptByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryWarehouserptByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public WarehouserptDto queryWarehouserptByParentid(String parentid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryWarehouserptByParentid", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public WarehouserptDto queryWarehouserptByNo(String warehouseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehouseno", warehouseno);
		@SuppressWarnings("unchecked")
		List<WarehouserptDto> list = getSqlMapClientTemplate().queryForList("queryWarehouserptByNo", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertWarehouserpt(WarehouserptDto warehouserpt) {
		getSqlMapClientTemplate().insert("insertWarehouserpt", warehouserpt);
	}

	@Override
	public void updateWarehouserpt(WarehouserptDto warehouserpt) {
		getSqlMapClientTemplate().update("updateWarehouserpt", warehouserpt);
	}
}
