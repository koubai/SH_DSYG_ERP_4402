package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.AssetsDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface AssetsService {
	
	/**
	 * 翻页查询资产
	 * @param page
	 * @param assetsNoLow
	 * @param assetsNoHigh
	 * @param assetsAddFlag
	 * @param assetsName
	 * @return
	 */
	public Page queryEtbAssetsByPage(Page page, String assetsNoLow, String assetsNoHigh, String assetsName);

	/**
	 * 根据ID查询资产（查询未删除的记录）
	 * @param assetsNo
	 * @return
	 */
	public AssetsDto queryEtbAssetsByID(String assetsNo);
	
	/**
	 * 根据ID资产（查询所有记录）
	 * @param assetsNo
	 * @return
	 */
	public AssetsDto queryAllEtbAssetsByID(String assetsNo);
	
	/**
	 * 查询所有的资产
	 * @return
	 */
	public List<AssetsDto> queryAllEtbAssets();
	
	/**
	 * 新增资产
	 * @param assets
	 * @return
	 */
	public String insertEtbAssets(AssetsDto assets);
	
	/**
	 * 修改资产
	 * @param assets
	 */
	public void updateEtbAssets(AssetsDto assets);
	
	/**
	 * 删除资产
	 * @param assetsNo
	 * @param username
	 */
	public void deleteEtbAssets(String assetsNo, String username);
	
	/**
	 * 查询资产（Excel导出用）
	 * @param assetsNoLow
	 * @param assetsNoHigh
	 * @return
	 */
	public List<AssetsDto> queryAllEtbAssetsExport(String assetsNoLow, String assetsNoHigh);
}
