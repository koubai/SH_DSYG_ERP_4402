package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.AssetsDao;
import com.cn.dsyg.dto.AssetsDto;
import com.cn.dsyg.service.AssetsService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class AssetsServiceImpl implements AssetsService {
	
	private AssetsDao assetsDao;

	@Override
	public AssetsDto queryAllEtbAssetsByID(String ID) {
		return assetsDao.queryAllEtbAssetsByID(ID);
	}

	@Override
	public Page queryEtbAssetsByPage(Page page, String assetsNoLow,
			String assetsNoHigh, String assetsName) {
		assetsNoLow = StringUtil.replaceDatabaseKeyword_mysql(assetsNoLow);
		//查询总记录数
		int totalCount = assetsDao.queryEtbAssetsCountByPage(assetsNoLow, assetsNoHigh, assetsName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<AssetsDto> list = assetsDao.queryEtbAssetsByPage(assetsNoLow, assetsNoHigh,
				assetsName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public AssetsDto queryEtbAssetsByID(String assetsNo) {
		return assetsDao.queryEtbAssetsByID(assetsNo);
	}

	@Override
	public List<AssetsDto> queryAllEtbAssets() {
		return assetsDao.queryAllEtbAssets();
	}

	@Override
	public void insertEtbAssets(AssetsDto assets) {
		assetsDao.insertEtbAssets(assets);
	}

	@Override
	public void updateEtbAssets(AssetsDto assets) {
		assetsDao.updateEtbAssets(assets);
	}

	@Override
	public void deleteEtbAssets(String assetsNo, String username) {
		AssetsDto assets = assetsDao.queryEtbAssetsByID(assetsNo);
		if(assets != null) {
			//状态=已删除
			assets.setStatus(Constants.STATUS_DEL);
			assets.setUpdateuid(username);
			assetsDao.updateEtbAssets(assets);
		}
	}

	@Override
	public List<AssetsDto> queryAllEtbAssetsExport(String assetsNoLow,
			String assetsNoHigh) {
		return assetsDao.queryAllEtbAssetsExport(assetsNoLow, assetsNoHigh);
	}

	public AssetsDao getAssetsDao() {
		return assetsDao;
	}

	public void setAssetsDao(AssetsDao assetsDao) {
		this.assetsDao = assetsDao;
	}
}
