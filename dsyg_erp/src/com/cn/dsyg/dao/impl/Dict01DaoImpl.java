package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dto.Dict01Dto;

/**
 * @name Dict01DaoImpl.java
 * @author Frank
 * @time 2014-12-16上午1:26:14
 * @version 1.0
 */
public class Dict01DaoImpl extends BaseDao implements Dict01Dao {

	@Override
	public List<Dict01Dto> queryDict01ByPage(String fieldcode,
			String fieldname, String lang, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldcode", fieldcode);
		paramMap.put("fieldname", fieldname);
		paramMap.put("lang", lang);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<Dict01Dto> list = getSqlMapClientTemplate().queryForList("queryDict01ByPage", paramMap);
		return list;
	}

	@Override
	public int queryDict01CountByPage(String fieldcode, String fieldname, String lang) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldcode", fieldcode);
		paramMap.put("fieldname", fieldname);
		paramMap.put("lang", lang);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryDict01CountByPage", paramMap);
	}

	@Override
	public Dict01Dto queryDict01ByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<Dict01Dto> list = getSqlMapClientTemplate().queryForList("queryDict01ByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Dict01Dto queryDict01ByLogicId(String fieldcode, String code,
			String lang) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldcode", fieldcode);
		paramMap.put("code", code);
		paramMap.put("lang", lang);
		@SuppressWarnings("unchecked")
		List<Dict01Dto> list = getSqlMapClientTemplate().queryForList("queryDict01ByLogicId", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Dict01Dto> queryDict01ByFieldcode(String fieldcode, String lang) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldcode", fieldcode);
		paramMap.put("lang", lang);
		@SuppressWarnings("unchecked")
		List<Dict01Dto> list = getSqlMapClientTemplate().queryForList("queryDict01ByFieldcode", paramMap);
		return list;
	}

	@Override
	public void deleteDict01(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteDict01", paramMap);
	}

	@Override
	public void insertDict01(Dict01Dto dict01) {
		getSqlMapClientTemplate().insert("insertDict01", dict01);
	}

	@Override
	public void updateDict01(Dict01Dto dict01) {
		getSqlMapClientTemplate().update("updateDict01", dict01);
	}
}
