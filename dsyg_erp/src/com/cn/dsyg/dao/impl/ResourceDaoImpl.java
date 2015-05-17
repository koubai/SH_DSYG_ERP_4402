package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.ResourceDao;
import com.cn.dsyg.dto.ResourceDto;

/**
 * @name ResourceDaoImpl.java
 * @author Frank
 * @time 2015-4-28下午10:11:25
 * @version 1.0
 */
public class ResourceDaoImpl extends BaseDao implements ResourceDao {
	
	@Override
	public List<ResourceDto> queryAllResource() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		List<ResourceDto> list = getSqlMapClientTemplate().queryForList("queryAllResource", paramMap);
		return list;
	}

	@Override
	public List<ResourceDto> queryResourceByRole(String roleid, String restype) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleid", roleid);
		paramMap.put("restype", restype);
		@SuppressWarnings("unchecked")
		List<ResourceDto> list = getSqlMapClientTemplate().queryForList("queryResourceByRole", paramMap);
		return list;
	}

	@Override
	public ResourceDto queryResourceID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<ResourceDto> list = getSqlMapClientTemplate().queryForList("queryResourceID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertResource(ResourceDto resource) {
		getSqlMapClientTemplate().insert("insertResource", resource);
	}

	@Override
	public void updateResource(ResourceDto resource) {
		getSqlMapClientTemplate().update("updateResource", resource);
	}
}
