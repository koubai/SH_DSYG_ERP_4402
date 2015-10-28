package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dto.SupplierDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SupplierDaoImpl extends BaseDao implements SupplierDao {

	@Override
	public SupplierDto queryAllSupplierByID(String supplierNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", supplierNo);
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("queryAllSupplierByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SupplierDto> querySupplierByPage(String supplierNoLow,
			String supplierNoHigh, String supplierName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若委托公司名称存在，则忽略委托公司代码按委托公司来查询。
		if(StringUtil.isNotBlank(supplierName)) {
			paramMap.put("SUPPLIER_NAME", StringUtil.replaceDatabaseKeyword_mysql(supplierName));
		} else {
			paramMap.put("ID_LOW", supplierNoLow);
			paramMap.put("ID_HIGH", supplierNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("querySupplierByPage", paramMap);
		return list;
	}

	@Override
	public int querySupplierCountByPage(String supplierNoLow,
			String supplierNoHigh, String supplierName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若供应商名称存在，则忽略供应商代码按供应商名称来查询。
		if(StringUtil.isNotBlank(supplierName)) {
			paramMap.put("SUPPLIER_NAME", StringUtil.replaceDatabaseKeyword_mysql(supplierName));
		} else {
			paramMap.put("ID_LOW", supplierNoLow);
			paramMap.put("ID_HIGH", supplierNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("querySupplierCountByPage", paramMap);
	}

	@Override
	public SupplierDto querySupplierByID(String supplierNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", supplierNo);
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("querySupplierByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public SupplierDto querySupplierByName(String supplierName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("SUPPLIERNAME", supplierName);
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("querySupplierByName", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<SupplierDto> queryAllSupplier() {
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("queryAllSupplier");
		return list;
	}

	@Override
	public void insertSupplier(SupplierDto supplier) {
		getSqlMapClientTemplate().insert("insertSupplier", supplier);
	}

	@Override
	public void updateSupplier(SupplierDto supplier) {
		getSqlMapClientTemplate().update("updateSupplier", supplier);
	}

	@Override
	public List<SupplierDto> queryAllSupplierExport(String supplierNoLow,
			String supplierNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", supplierNoLow);
		paramMap.put("ID_HIGH", supplierNoHigh);
		@SuppressWarnings("unchecked")
		List<SupplierDto> list = getSqlMapClientTemplate().queryForList("queryAllSupplierExport", paramMap);
		return list;
	}
}
