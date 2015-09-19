package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.PositionDao;
import com.cn.dsyg.dto.PositionCollectDto;
import com.cn.dsyg.dto.PositionDto;

/**
 * @name PositionDaoImpl.java
 * @author Frank
 * @time 2015-9-19下午2:54:15
 * @version 1.0
 */
public class PositionDaoImpl extends BaseDao implements PositionDao {
	
	@Override
	public List<PositionCollectDto> queryPositionCollectByDay(String handler,
			String checkday) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("handler", handler);
		paramMap.put("checkday", checkday);
		@SuppressWarnings("unchecked")
		List<PositionCollectDto> list = getSqlMapClientTemplate().queryForList("queryPositionCollectByDay", paramMap);
		return list;
	}

	@Override
	public List<PositionCollectDto> queryPositionCollectByPage(String handler,
			String checkday, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("handler", handler);
		paramMap.put("checkday", checkday);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<PositionCollectDto> list = getSqlMapClientTemplate().queryForList("queryPositionCollectByPage", paramMap);
		return list;
	}

	@Override
	public int queryPositionCollectCountByPage(String handler, String checkday) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("handler", handler);
		paramMap.put("checkday", checkday);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryPositionCollectCountByPage", paramMap);
	}

	@Override
	public List<PositionDto> queryPositionByLogicId(String userid, String productid, String checkday) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("checkday", checkday);
		paramMap.put("handler", userid);
		@SuppressWarnings("unchecked")
		List<PositionDto> list = getSqlMapClientTemplate().queryForList("queryPositionByLogicId", paramMap);
		return list;
	}

	@Override
	public PositionDto queryPositionById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<PositionDto> list = getSqlMapClientTemplate().queryForList("queryPositionById", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertPosition(PositionDto position) {
		getSqlMapClientTemplate().insert("insertPosition", position);
	}

	@Override
	public void updatePosition(PositionDto position) {
		getSqlMapClientTemplate().update("updatePosition", position);
	}
}
