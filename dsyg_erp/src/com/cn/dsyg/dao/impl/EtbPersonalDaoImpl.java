package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbPersonalDao;
import com.cn.dsyg.dto.EtbPersonalDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbPersonalDaoImpl extends BaseDao implements EtbPersonalDao {

	@Override
	public EtbPersonalDto queryAllEtbPersonalByID(String userNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", userNo);
		@SuppressWarnings("unchecked")
		List<EtbPersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonalByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbPersonalDto> queryEtbPersonalByPage(String userNoLow,
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
		List<EtbPersonalDto> list = getSqlMapClientTemplate().queryForList("queryEtbPersonalByPage", paramMap);
		for (EtbPersonalDto personaltmp : list) {  
			String registerdate = personaltmp.getRegistdate();
			String employeddate = personaltmp.getEmployeddate();
			String retiredate = personaltmp.getRetiredate();
			if(registerdate.length()>=10){
				personaltmp.setRegistdate(registerdate.substring(0, 10));
			}
			if(employeddate.length()>=10){
				personaltmp.setEmployeddate(employeddate.substring(0, 10));
			}
			if(retiredate.length()>=10){ 
				personaltmp.setRetiredate(retiredate.substring(0, 10));  
			} 
		}  
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
	public EtbPersonalDto queryEtbPersonalByID(String userNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", userNo);
		@SuppressWarnings("unchecked")
		List<EtbPersonalDto> list = getSqlMapClientTemplate().queryForList("queryEtbPersonalByID", paramMap);
		if(list != null && list.size() > 0) {
			String registerdate = list.get(0).getRegistdate();
			String employeddate = list.get(0).getEmployeddate();
			String retiredate = list.get(0).getRetiredate();
			if(registerdate.length()>=10){
				list.get(0).setRegistdate(registerdate.substring(0, 10));
			}
			if(employeddate.length()>=10){
				list.get(0).setEmployeddate(employeddate.substring(0, 10));
			}
			if(retiredate.length()>=10){ 
				list.get(0).setRetiredate(retiredate.substring(0, 10));  
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbPersonalDto> queryAllEtbPersonal() {
		@SuppressWarnings("unchecked")
		List<EtbPersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonal");
		return list;
	}

	@Override
	public void insertEtbPersonal(EtbPersonalDto personal) {
		getSqlMapClientTemplate().insert("insertEtbPersonal", personal);
	}

	@Override
	public void updateEtbPersonal(EtbPersonalDto personal) {
		getSqlMapClientTemplate().update("updateEtbPersonal", personal);
	}

	@Override
	public List<EtbPersonalDto> queryAllEtbPersonalExport(String userNoLow,
			String userNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", userNoLow);
		paramMap.put("ID_HIGH", userNoHigh);
		@SuppressWarnings("unchecked")
		List<EtbPersonalDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbPersonalExport", paramMap);
		return list;
	}
}
