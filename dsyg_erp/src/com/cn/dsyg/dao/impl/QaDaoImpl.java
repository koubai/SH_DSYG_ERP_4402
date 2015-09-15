package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.QaDao;
import com.cn.dsyg.dto.QaDto;

public class QaDaoImpl extends BaseDao implements QaDao {

	@Override
	public List<QaDto> queryQaByPage(String title, String company, String tell, String status,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("company", company);
		paramMap.put("tell", tell);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<QaDto> list = getSqlMapClientTemplate().queryForList("queryQaByPage", paramMap);
		return list;
	}

	@Override
	public int queryQaCountByPage(String title, String company, String tell, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("company", company);
		paramMap.put("tell", tell);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryQaCountByPage", paramMap);
	}

	@Override
	public QaDto queryQaByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<QaDto> list = getSqlMapClientTemplate().queryForList("queryQaByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteQa(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteQa", paramMap);
	}

	@Override
	public void insertQa(QaDto qa) {
		getSqlMapClientTemplate().insert("insertQa", qa);
	}

	@Override
	public void updateQa(QaDto qa) {
		getSqlMapClientTemplate().update("updateQa", qa);
	}
}
