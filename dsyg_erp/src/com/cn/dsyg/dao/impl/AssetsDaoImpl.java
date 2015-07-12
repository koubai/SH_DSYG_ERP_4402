package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.AssetsDao;
import com.cn.dsyg.dto.AssetsDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class AssetsDaoImpl extends BaseDao implements AssetsDao {

	@Override
	public AssetsDto queryAllEtbAssetsByID(String assetsNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", assetsNo);
		@SuppressWarnings("unchecked")
		List<AssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssetsByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AssetsDto> queryEtbAssetsByPage(String assetsNoLow,
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
		List<AssetsDto> list = getSqlMapClientTemplate().queryForList("queryEtbAssetsByPage", paramMap);
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
	public AssetsDto queryEtbAssetsByID(String assetsNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", assetsNo);
		@SuppressWarnings("unchecked")
		List<AssetsDto> list = getSqlMapClientTemplate().queryForList("queryEtbAssetsByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AssetsDto> queryAllEtbAssets() {
		@SuppressWarnings("unchecked")
		List<AssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssets");
		return list;
	}

	@Override
	public void insertEtbAssets(AssetsDto assets) {
		getSqlMapClientTemplate().insert("insertEtbAssets", assets);
	}

	@Override
	public void updateEtbAssets(AssetsDto assets) {
		getSqlMapClientTemplate().update("updateEtbAssets", assets);
	}

	@Override
	public List<AssetsDto> queryAllEtbAssetsExport(String assetsNoLow,
			String assetsNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", assetsNoLow);
		paramMap.put("ID_HIGH", assetsNoHigh);
		@SuppressWarnings("unchecked")
		List<AssetsDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbAssetsExport", paramMap);
		return list;
	}
}
