package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbAssetsDao;
import com.cn.dsyg.dto.EtbAssetsDto;
import com.cn.dsyg.service.AssetsService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbAssetsServiceImpl implements AssetsService {
	
	private EtbAssetsDao etbAssetsDao;

	@Override
	public EtbAssetsDto queryAllEtbAssetsByID(String ID) {
		return etbAssetsDao.queryAllEtbAssetsByID(ID);
	}

	public EtbAssetsDao getEtbAssetsDao() {
		return etbAssetsDao;
	}

	public void setEtbAssetsDao(EtbAssetsDao etbAssetsDao) {
		this.etbAssetsDao = etbAssetsDao;
	}

	@Override
	public Page queryEtbAssetsByPage(Page page, String assetsNoLow,
			String assetsNoHigh, String assetsName) {
		assetsNoLow = StringUtil.replaceDatabaseKeyword_mysql(assetsNoLow);
		//查询总记录数
		int totalCount = etbAssetsDao.queryEtbAssetsCountByPage(assetsNoLow, assetsNoHigh, assetsName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbAssetsDto> list = etbAssetsDao.queryEtbAssetsByPage(assetsNoLow, assetsNoHigh,
				assetsName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbAssetsDto queryEtbAssetsByID(String assetsNo) {
		return etbAssetsDao.queryEtbAssetsByID(assetsNo);
	}

	@Override
	public List<EtbAssetsDto> queryAllEtbAssets() {
		return etbAssetsDao.queryAllEtbAssets();
	}

	@Override
	public void insertEtbAssets(EtbAssetsDto assets) {
		etbAssetsDao.insertEtbAssets(assets);
	}

	@Override
	public void updateEtbAssets(EtbAssetsDto assets) {
		etbAssetsDao.updateEtbAssets(assets);
	}

	@Override
	public void deleteEtbAssets(String assetsNo, String username) {
		EtbAssetsDto assets = etbAssetsDao.queryEtbAssetsByID(assetsNo);
		if(assets != null) {
			//状态=已删除
			assets.setStatus(Constants.STATUS_DEL);
			assets.setUpdateuid(username);
			etbAssetsDao.updateEtbAssets(assets);
		}
	}

	@Override
	public List<EtbAssetsDto> queryAllEtbAssetsExport(String assetsNoLow,
			String assetsNoHigh) {
		return etbAssetsDao.queryAllEtbAssetsExport(assetsNoLow, assetsNoHigh);
	}
}
