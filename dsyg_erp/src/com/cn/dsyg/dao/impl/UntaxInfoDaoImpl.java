package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.UntaxInfoDao;
import com.cn.dsyg.dto.UntaxInfoDto;

/**
 * @name UntaxInfoDaoImpl.java
 * @author Frank
 * @time 2015-9-12下午9:02:03
 * @version 1.0
 */
public class UntaxInfoDaoImpl extends BaseDao implements UntaxInfoDao {
	
	@Override
	public List<UntaxInfoDto> queryUntaxInfoNumByProductId(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<UntaxInfoDto> list = getSqlMapClientTemplate().queryForList("queryUntaxInfoNumByProductId", paramMap);
		return list;
	}

	@Override
	public UntaxInfoDto queryUntaxInfoId(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<UntaxInfoDto> list = getSqlMapClientTemplate().queryForList("queryUntaxInfoId", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UntaxInfoDto> queryUntaxInfoByPage(
			String productid, String status, String tradename, String customername, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("tradename", tradename);
		paramMap.put("customername", customername);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<UntaxInfoDto> list = getSqlMapClientTemplate().queryForList("queryUntaxInfoByPage", paramMap);
		return list;
	}

	@Override
	public int queryUntaxInfoCountByPage(String productid,
			String status, String tradename, String customername) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("tradename", tradename);
		paramMap.put("customername", customername);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryUntaxInfoCountByPage", paramMap);
	}

	@Override
	public void insertUntaxInfo(UntaxInfoDto untaxinfo) {
		getSqlMapClientTemplate().insert("insertUntaxInfo", untaxinfo);
	}

	@Override
	public void updateUntaxInfo(UntaxInfoDto untaxinfo) {
		getSqlMapClientTemplate().update("updateUntaxInfo", untaxinfo);
	}

}
