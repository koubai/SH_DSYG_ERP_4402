package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerTrackDao;
import com.cn.dsyg.dto.CustomerTrackDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerTrackDaoImpl extends BaseDao implements CustomerTrackDao {

	@Override
	public CustomerTrackDto queryAllCustomerTrackByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<CustomerTrackDto> list = getSqlMapClientTemplate().queryForList("queryAllCustomerTrackByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CustomerTrackDto> queryCustomerTrackByPage(String idLow,
			String idHigh, String customerName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("customerName", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<CustomerTrackDto> list = getSqlMapClientTemplate().queryForList("queryCustomerTrackByPage", paramMap);
		return list;
	}

	@Override
	public int queryCustomerTrackCountByPage(String idLow,
			String idHigh, String customerName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("customerName", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryCustomerTrackCountByPage", paramMap);
	}

	@Override
	public CustomerTrackDto queryCustomerTrackByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<CustomerTrackDto> list = getSqlMapClientTemplate().queryForList("queryCustomerTrackByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CustomerTrackDto> queryAllCustomerTrack() {
		@SuppressWarnings("unchecked")
		List<CustomerTrackDto> list = getSqlMapClientTemplate().queryForList("queryAllCustomerTrack");
		return list;
	}

	@Override
	public void insertCustomerTrack(CustomerTrackDto customertrack) {
		getSqlMapClientTemplate().insert("insertCustomerTrack", customertrack);
	}

	@Override
	public void updateCustomerTrack(CustomerTrackDto customertrack) {
		getSqlMapClientTemplate().update("updateCustomerTrack", customertrack);
	}

	@Override
	public List<CustomerTrackDto> queryAllCustomerTrackExport(String idLow,
			String idHigh, String customerName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("customerName", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		@SuppressWarnings("unchecked")
		List<CustomerTrackDto> list = getSqlMapClientTemplate().queryForList("queryAllCustomerTrackExport", paramMap);
		return list;
	}
}
