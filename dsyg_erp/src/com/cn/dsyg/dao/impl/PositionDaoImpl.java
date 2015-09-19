package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.PositionDao;
import com.cn.dsyg.dto.PositionDto;

/**
 * @name PositionDaoImpl.java
 * @author Frank
 * @time 2015-9-19下午2:54:15
 * @version 1.0
 */
public class PositionDaoImpl extends BaseDao implements PositionDao {

	@Override
	public PositionDto queryPositionByLogicId(String productid, String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("warehousename", warehousename);
		@SuppressWarnings("unchecked")
		List<PositionDto> list = getSqlMapClientTemplate().queryForList("queryPositionByLogicId", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
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
