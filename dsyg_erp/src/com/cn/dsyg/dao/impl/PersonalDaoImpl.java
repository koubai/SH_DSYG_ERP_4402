package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.PersonalDao;
import com.cn.dsyg.dto.PersonalDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class PersonalDaoImpl extends BaseDao implements PersonalDao {

	@Override
	public PersonalDto queryAllEtbPersonalByID(String userNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", userNo);
		@SuppressWarnings("unchecked")
		List<PersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonalByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PersonalDto> queryEtbPersonalByPage(String userNoLow,
			String userNoHigh, String userName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若员工名称存在，则忽略员工编号按员工名称来查询。
		if(StringUtil.isNotBlank(userName)) {
			paramMap.put("USER_NAME", StringUtil.replaceDatabaseKeyword_mysql(userName));
		} else {
			paramMap.put("ID_LOW", userNoLow);
			paramMap.put("ID_HIGH", userNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PersonalDto> list = getSqlMapClientTemplate().queryForList("queryEtbPersonalByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbPersonalCountByPage(String userNoLow,
			String userNoHigh, String userName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若员工名称存在，则忽略员工编号按员工名称来查询。
		if(StringUtil.isNotBlank(userName)) {
			paramMap.put("USER_NAME", StringUtil.replaceDatabaseKeyword_mysql(userName));
		} else {
			paramMap.put("ID_LOW", userNoLow);
			paramMap.put("ID_HIGH", userNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbPersonalCountByPage", paramMap);
	}

	@Override
	public PersonalDto queryEtbPersonalByID(String userNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", userNo);
		@SuppressWarnings("unchecked")
		List<PersonalDto> list = getSqlMapClientTemplate().queryForList("queryEtbPersonalByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PersonalDto> queryAllEtbPersonal() {
		@SuppressWarnings("unchecked")
		List<PersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonal");
		return list;
	}

	@Override
	public void insertEtbPersonal(PersonalDto personal) {
		getSqlMapClientTemplate().insert("insertEtbPersonal", personal);
	}

	@Override
	public void updateEtbPersonal(PersonalDto personal) {
		getSqlMapClientTemplate().update("updateEtbPersonal", personal);
	}

	@Override
	public List<PersonalDto> queryAllEtbPersonalExport(String userNoLow,
			String userNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", userNoLow);
		paramMap.put("ID_HIGH", userNoHigh);
		@SuppressWarnings("unchecked")
		List<PersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonalExport", paramMap);
		return list;
	}
}
