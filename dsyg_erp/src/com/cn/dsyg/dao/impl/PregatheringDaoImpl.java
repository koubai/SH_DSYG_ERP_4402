package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.PregatheringDao;
import com.cn.dsyg.dto.PregatheringDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class PregatheringDaoImpl extends BaseDao implements PregatheringDao {

	@Override
	public PregatheringDto queryAllPregatheringByID(String pregatheringNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", pregatheringNo);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllPregatheringByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PregatheringDto> queryPregatheringByPage(String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName, String customerName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若预收款名称存在，则忽略预收款代码按预收款名称来查询。
		if(StringUtil.isNotBlank(pregatheringName)) {
			paramMap.put("PREGATHERING_NAME", StringUtil.replaceDatabaseKeyword_mysql(pregatheringName));
		} else {
			paramMap.put("ID_LOW", pregatheringNoLow);
			paramMap.put("ID_HIGH", pregatheringNoHigh);
		}
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("CUSTOMER_NAME", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryPregatheringByPage", paramMap);
		return list;
	}

	@Override
	public int queryPregatheringCountByPage(String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName, String customerName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若预收款名称存在，则忽略预收款代码按预收款名称来查询。
		if(StringUtil.isNotBlank(pregatheringName)) {
			paramMap.put("PREGATHERING_NAME", StringUtil.replaceDatabaseKeyword_mysql(pregatheringName));
		} else {
			paramMap.put("ID_LOW", pregatheringNoLow);
			paramMap.put("ID_HIGH", pregatheringNoHigh);
		}
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("CUSTOMER_NAME", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryPregatheringCountByPage", paramMap);
	}

	@Override
	public PregatheringDto queryPregatheringByID(String pregatheringNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", pregatheringNo);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryPregatheringByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PregatheringDto> queryAllPregathering() {
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllPregathering");
		return list;
	}

	@Override
	public void insertPregathering(PregatheringDto pregathering) {
		getSqlMapClientTemplate().insert("insertPregathering", pregathering);
	}

	@Override
	public void updatePregathering(PregatheringDto pregathering) {
		getSqlMapClientTemplate().update("updatePregathering", pregathering);
	}

	@Override
	public List<PregatheringDto> queryAllPregatheringExport(String pregatheringNoLow,
			String pregatheringNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", pregatheringNoLow);
		paramMap.put("ID_HIGH", pregatheringNoHigh);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllPregatheringExport", paramMap);
		return list;
	}
}
