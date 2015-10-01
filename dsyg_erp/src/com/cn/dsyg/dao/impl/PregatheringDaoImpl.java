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
	public PregatheringDto queryAllEtbPregatheringByID(String pregatheringNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", pregatheringNo);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPregatheringByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PregatheringDto> queryEtbPregatheringByPage(String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若预收款名称存在，则忽略预收款代码按预收款名称来查询。
		if(StringUtil.isNotBlank(pregatheringName)) {
			paramMap.put("PREGATHERING_NAME", StringUtil.replaceDatabaseKeyword_mysql(pregatheringName));
		} else {
			paramMap.put("ID_LOW", pregatheringNoLow);
			paramMap.put("ID_HIGH", pregatheringNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryEtbPregatheringByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbPregatheringCountByPage(String pregatheringNoLow,
			String pregatheringNoHigh, String pregatheringName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若预收款名称存在，则忽略预收款代码按预收款名称来查询。
		if(StringUtil.isNotBlank(pregatheringName)) {
			paramMap.put("PREGATHERING_NAME", StringUtil.replaceDatabaseKeyword_mysql(pregatheringName));
		} else {
			paramMap.put("ID_LOW", pregatheringNoLow);
			paramMap.put("ID_HIGH", pregatheringNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbPregatheringCountByPage", paramMap);
	}

	@Override
	public PregatheringDto queryEtbPregatheringByID(String pregatheringNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", pregatheringNo);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryEtbPregatheringByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PregatheringDto> queryAllEtbPregathering() {
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPregathering");
		return list;
	}

	@Override
	public void insertEtbPregathering(PregatheringDto pregathering) {
		getSqlMapClientTemplate().insert("insertEtbPregathering", pregathering);
	}

	@Override
	public void updateEtbPregathering(PregatheringDto pregathering) {
		getSqlMapClientTemplate().update("updateEtbPregathering", pregathering);
	}

	@Override
	public List<PregatheringDto> queryAllEtbPregatheringExport(String pregatheringNoLow,
			String pregatheringNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", pregatheringNoLow);
		paramMap.put("ID_HIGH", pregatheringNoHigh);
		@SuppressWarnings("unchecked")
		List<PregatheringDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPregatheringExport", paramMap);
		return list;
	}
}
