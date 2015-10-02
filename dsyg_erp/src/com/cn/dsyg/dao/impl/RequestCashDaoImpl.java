package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.RequestCashDao;
import com.cn.dsyg.dto.RequestCashDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class RequestCashDaoImpl extends BaseDao implements RequestCashDao {

	@Override
	public RequestCashDto queryAllRequestCashByID(String requestcashNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", requestcashNo);
		@SuppressWarnings("unchecked")
		List<RequestCashDto> list = getSqlMapClientTemplate().queryForList("queryAllRequestCashByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RequestCashDto> queryRequestCashByPage(String requestcashNoLow,
			String requestcashNoHigh, String requestcashName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(requestcashName)) {
			paramMap.put("REQUESTCASH_NAME", StringUtil.replaceDatabaseKeyword_mysql(requestcashName));
		} else {
			paramMap.put("ID_LOW", requestcashNoLow);
			paramMap.put("ID_HIGH", requestcashNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<RequestCashDto> list = getSqlMapClientTemplate().queryForList("queryRequestCashByPage", paramMap);
		return list;
	}

	@Override
	public int queryRequestCashCountByPage(String requestcashNoLow,
			String requestcashNoHigh, String requestcashName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(requestcashName)) {
			paramMap.put("REQUESTCASH_NAME", StringUtil.replaceDatabaseKeyword_mysql(requestcashName));
		} else {
			paramMap.put("ID_LOW", requestcashNoLow);
			paramMap.put("ID_HIGH", requestcashNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryRequestCashCountByPage", paramMap);
	}

	@Override
	public RequestCashDto queryRequestCashByID(String requestcashNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", requestcashNo);
		@SuppressWarnings("unchecked")
		List<RequestCashDto> list = getSqlMapClientTemplate().queryForList("queryRequestCashByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RequestCashDto> queryAllRequestCash() {
		@SuppressWarnings("unchecked")
		List<RequestCashDto> list = getSqlMapClientTemplate().queryForList("queryAllRequestCash");
		return list;
	}

	@Override
	public void insertRequestCash(RequestCashDto requestcash) {
		getSqlMapClientTemplate().insert("insertRequestCash", requestcash);
	}

	@Override
	public void updateRequestCash(RequestCashDto requestcash) {
		getSqlMapClientTemplate().update("updateRequestCash", requestcash);
	}

	@Override
	public List<RequestCashDto> queryAllRequestCashExport(String requestcashNoLow,
			String requestcashNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", requestcashNoLow);
		paramMap.put("ID_HIGH", requestcashNoHigh);
		@SuppressWarnings("unchecked")
		List<RequestCashDto> list = getSqlMapClientTemplate().queryForList("queryAllRequestCashExport", paramMap);
		return list;
	}
}
