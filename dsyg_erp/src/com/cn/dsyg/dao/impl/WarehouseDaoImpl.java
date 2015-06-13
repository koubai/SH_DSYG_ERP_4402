package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dto.WarehouseDto;

/**
 * @name WarehouseDaoImpl.java
 * @author Frank
 * @time 2015-6-7下午8:42:12
 * @version 1.0
 */
public class WarehouseDaoImpl extends BaseDao implements WarehouseDao {
	
	@Override
	public List<WarehouseDto> queryWarehouseBySupplieridProductid(String status,
			String productid, String supplierid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("productid", productid);
		paramMap.put("supplierid", supplierid);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseBySupplieridProductid", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseOkByPage(String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseOkByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseOkCountByPage(String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseOkCountByPage", paramMap);
	}

	@Override
	public List<WarehouseDto> queryWarehouseByPage(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String supplierid, String productid, String status, int start,
			int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseCountByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid,
			String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseCountByPage", paramMap);
	}

	@Override
	public WarehouseDto queryWarehouseByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid,
			String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByParentidAndProductid", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WarehouseDto queryWarehouseByWarehouseno(String warehouseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehouseno", warehouseno);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByWarehouseno", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertWarehouse(WarehouseDto warehouse) {
		getSqlMapClientTemplate().insert("insertWarehouse", warehouse);
	}

	@Override
	public void updateWarehouse(WarehouseDto warehouse) {
		getSqlMapClientTemplate().update("updateWarehouse", warehouse);
	}
}
