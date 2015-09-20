package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.UserDto;

/**
 * @name UserDaoImpl.java
 * @author Frank
 * @time 2014-12-9上午12:42:14
 * @version 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public List<UserDto> queryUserByPage(String username, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<UserDto> list = getSqlMapClientTemplate().queryForList("queryUserByPage", paramMap);
		return list;
	}

	@Override
	public int queryUserCountByPage(String username) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryUserCountByPage", paramMap);
	}

	@Override
	public UserDto queryUserByID(String userid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		@SuppressWarnings("unchecked")
		List<UserDto> list = getSqlMapClientTemplate().queryForList("queryUserByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<UserDto> queryMulUserByID(String userids) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userids", userids);
		@SuppressWarnings("unchecked")
		List<UserDto> list = getSqlMapClientTemplate().queryForList("queryMulUserByID", paramMap);
		return list;
	}

	@Override
	public List<UserDto> queryAllUser() {
		@SuppressWarnings("unchecked")
		List<UserDto> list = getSqlMapClientTemplate().queryForList("queryAllUser");
		return list;
	}

	@Override
	public void deleteUser(String userid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		getSqlMapClientTemplate().delete("deleteUser", paramMap);
	}

	@Override
	public void insertUser(UserDto user) {
		getSqlMapClientTemplate().insert("insertUser", user);
	}

	@Override
	public void updateUser(UserDto user) {
		getSqlMapClientTemplate().update("updateUser", user);
	}
	
	@Override
	public List<ProductDto> queryUserListByPage(String fieldno, String keyword, String useridfrom,
			String useridto, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("keyword", keyword);
		paramMap.put("useridfrom", useridfrom);
		paramMap.put("useridto", useridto);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<ProductDto> list = getSqlMapClientTemplate().queryForList("queryUserListByPage", paramMap);
		return list;
	}

	@Override
	public int queryUserListCountByPage(String fieldno, String keyword, String useridfrom,
			String useridto, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("keyword", keyword);
		paramMap.put("useridfrom", useridfrom);
		paramMap.put("useridto", useridto);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryUserListCountByPage", paramMap);
	}

}
