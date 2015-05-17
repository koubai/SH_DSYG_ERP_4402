package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbCustomerDao;
import com.cn.dsyg.dto.EtbCustomerDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbCustomerDaoImpl extends BaseDao implements EtbCustomerDao {

	@Override
	public EtbCustomerDto queryAllEtbCustomerByID(String customerNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", customerNo);
		@SuppressWarnings("unchecked")
		List<EtbCustomerDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbCustomerByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbCustomerDto> queryEtbCustomerByPage(String customerNoLow,
			String customerNoHigh, String customerName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若供应商名称存在，则忽略供应商代码按供应商名称来查询。
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("CUSTOMER_NAME", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		} else {
			paramMap.put("ID_LOW", customerNoLow);
			paramMap.put("ID_HIGH", customerNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<EtbCustomerDto> list = getSqlMapClientTemplate().queryForList("queryEtbCustomerByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbCustomerCountByPage(String customerNoLow,
			String customerNoHigh, String customerName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若供应商名称存在，则忽略供应商代码按供应商名称来查询。
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("CUSTOMER_NAME", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		} else {
			paramMap.put("ID_LOW", customerNoLow);
			paramMap.put("ID_HIGH", customerNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbCustomerCountByPage", paramMap);
	}

	@Override
	public EtbCustomerDto queryEtbCustomerByID(String customerNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", customerNo);
		@SuppressWarnings("unchecked")
		List<EtbCustomerDto> list = getSqlMapClientTemplate().queryForList("queryEtbCustomerByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbCustomerDto> queryAllEtbCustomer() {
		@SuppressWarnings("unchecked")
		List<EtbCustomerDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbCustomer");
		return list;
	}

	@Override
	public void insertEtbCustomer(EtbCustomerDto customer) {
		getSqlMapClientTemplate().insert("insertEtbCustomer", customer);
	}

	@Override
	public void updateEtbCustomer(EtbCustomerDto customer) {
		getSqlMapClientTemplate().update("updateEtbCustomer", customer);
	}

	@Override
	public List<EtbCustomerDto> queryAllEtbCustomerExport(String customerNoLow,
			String customerNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", customerNoLow);
		paramMap.put("ID_HIGH", customerNoHigh);
		@SuppressWarnings("unchecked")
		List<EtbCustomerDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbCustomerExport", paramMap);
		return list;
	}
}
