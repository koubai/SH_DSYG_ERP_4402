package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.IssueDao;
import com.cn.dsyg.dto.IssueDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class IssueDaoImpl extends BaseDao implements IssueDao {

	@Override
	public IssueDto queryAllIssueByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryAllIssueByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<IssueDto> queryIssueByPage(String idLow,
			String idHigh, String issueName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(issueName)) {
			paramMap.put("issueName", StringUtil.replaceDatabaseKeyword_mysql(issueName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryIssueByPage", paramMap);
		return list;
	}

	@Override
	public int queryIssueCountByPage(String idLow,
			String idHigh, String issueName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(issueName)) {
			paramMap.put("issueName", StringUtil.replaceDatabaseKeyword_mysql(issueName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryIssueCountByPage", paramMap);
	}

	@Override
	public IssueDto queryIssueByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryIssueByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<IssueDto> queryAllIssue() {
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryAllIssue");
		return list;
	}

	@Override
	public List<IssueDto> queryIssueWorking() {
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryIssueWorking");
		return list;
	}

	@Override
	public void insertIssue(IssueDto issue) {
		getSqlMapClientTemplate().insert("insertIssue", issue);
	}

	@Override
	public void updateIssue(IssueDto issue) {
		getSqlMapClientTemplate().update("updateIssue", issue);
	}

	@Override
	public List<IssueDto> queryAllIssueExport(String idLow,
			String idHigh, String issueName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若名称存在，则忽略编号按名称来查询。
		if(StringUtil.isNotBlank(issueName)) {
			paramMap.put("issueName", StringUtil.replaceDatabaseKeyword_mysql(issueName));
		} else {
			paramMap.put("idLow", idLow);
			paramMap.put("idHigh", idHigh);
		}
		@SuppressWarnings("unchecked")
		List<IssueDto> list = getSqlMapClientTemplate().queryForList("queryAllIssueExport", paramMap);
		return list;
	}
}
