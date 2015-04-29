package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.RoleDao;
import com.cn.dsyg.dto.RoleDto;

/**
 * @name RoleDaoImpl.java
 * @author Frank
 * @time 2014-12-12下午11:16:11
 * @version 1.0
 */
public class RoleDaoImpl extends BaseDao implements RoleDao {

	@Override
	public List<RoleDto> queryRoleByPage(String rolename, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rolename", rolename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<RoleDto> list = getSqlMapClientTemplate().queryForList("queryRoleByPage", paramMap);
		return list;
	}

	@Override
	public int queryRoleCountByPage(String rolename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rolename", rolename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryRoleCountByPage", paramMap);
	}

	@Override
	public RoleDto queryRoleByID(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<RoleDto> list = getSqlMapClientTemplate().queryForList("queryRoleByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public RoleDto queryRoleByCode(String rolecode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rolecode", rolecode);
		@SuppressWarnings("unchecked")
		List<RoleDto> list = getSqlMapClientTemplate().queryForList("queryRoleByCode", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RoleDto> queryAllRole() {
		@SuppressWarnings("unchecked")
		List<RoleDto> list = getSqlMapClientTemplate().queryForList("queryAllRole");
		return list;
	}

	@Override
	public void deleteRole(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		getSqlMapClientTemplate().delete("deleteRole", paramMap);
	}

	@Override
	public void insertRole(RoleDto role) {
		getSqlMapClientTemplate().insert("insertRole", role);
	}

	@Override
	public void updateRole(RoleDto role) {
		getSqlMapClientTemplate().update("updateRole", role);
	}
}
