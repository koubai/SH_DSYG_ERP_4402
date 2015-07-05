package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbAssetsDao;
import com.cn.dsyg.dto.EtbAssetsDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbAssetsDaoImpl extends BaseDao implements EtbAssetsDao {

	@Override
	public EtbAssetsDto queryAllEtbAssetsByID(String assetsNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", assetsNo);
		@SuppressWarnings("unchecked")
		List<EtbAssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssetsByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbAssetsDto> queryEtbAssetsByPage(String assetsNoLow,
			String assetsNoHigh, String assetsName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(assetsName)) {
			paramMap.put("ASSETS_NAME", StringUtil.replaceDatabaseKeyword_mysql(assetsName));
		} else {
			paramMap.put("ID_LOW", assetsNoLow);
			paramMap.put("ID_HIGH", assetsNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<EtbAssetsDto> list = getSqlMapClientTemplate().queryForList("queryEtbAssetsByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbAssetsCountByPage(String assetsNoLow,
			String assetsNoHigh, String assetsName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(assetsName)) {
			paramMap.put("ASSETS_NAME", StringUtil.replaceDatabaseKeyword_mysql(assetsName));
		} else {
			paramMap.put("ID_LOW", assetsNoLow);
			paramMap.put("ID_HIGH", assetsNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbAssetsCountByPage", paramMap);
	}

	@Override
	public EtbAssetsDto queryEtbAssetsByID(String assetsNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", assetsNo);
		@SuppressWarnings("unchecked")
		List<EtbAssetsDto> list = getSqlMapClientTemplate().queryForList("queryEtbAssetsByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbAssetsDto> queryAllEtbAssets() {
		@SuppressWarnings("unchecked")
		List<EtbAssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssets");
		return list;
	}

	@Override
	public void insertEtbAssets(EtbAssetsDto assets) {
		getSqlMapClientTemplate().insert("insertEtbAssets", assets);
	}

	@Override
	public void updateEtbAssets(EtbAssetsDto assets) {
		getSqlMapClientTemplate().update("updateEtbAssets", assets);
	}

	@Override
	public List<EtbAssetsDto> queryAllEtbAssetsExport(String assetsNoLow,
			String assetsNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", assetsNoLow);
		paramMap.put("ID_HIGH", assetsNoHigh);
		@SuppressWarnings("unchecked")
		List<EtbAssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssetsExport", paramMap);
		return list;
	}
}
