package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.SampleDao;
import com.cn.dsyg.dto.SampleDto;
import com.cn.dsyg.dto.SampleTotleDto;

/**
 * @name SampleDaoImpl.java
 * @author Frank
 * @time 2015-9-12下午9:02:03
 * @version 1.0
 */
public class SampleDaoImpl extends BaseDao implements SampleDao {
	
	@Override
	public List<SampleTotleDto> querySampleNumByProductId(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<SampleTotleDto> list = getSqlMapClientTemplate().queryForList("querySampleNumByProductId", paramMap);
		return list;
	}

	public List<SampleTotleDto> querySampleNumByKeys(String tradename, String typeno, String color){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		@SuppressWarnings("unchecked")
		List<SampleTotleDto> list = getSqlMapClientTemplate().queryForList("querySampleNumByKeys", paramMap);
		return list;
	}
	
	@Override
	public SampleDto querySampleId(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<SampleDto> list = getSqlMapClientTemplate().queryForList("querySampleId", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SampleDto> querySampleByPage(String warehousename,
			String productid, String status, String tradename, String customername, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousename", warehousename);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("tradename", tradename);
		paramMap.put("customername", customername);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SampleDto> list = getSqlMapClientTemplate().queryForList("querySampleByPage", paramMap);
		return list;
	}

	@Override
	public int querySampleCountByPage(String warehousename, String productid,
			String status, String tradename, String customername) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousename", warehousename);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("tradename", tradename);
		paramMap.put("customername", customername);
		return (Integer) getSqlMapClientTemplate().queryForObject("querySampleCountByPage", paramMap);
	}

	@Override
	public void insertSample(SampleDto sample) {
		getSqlMapClientTemplate().insert("insertSample", sample);
	}

	@Override
	public void updateSample(SampleDto sample) {
		getSqlMapClientTemplate().update("updateSample", sample);
	}
}
