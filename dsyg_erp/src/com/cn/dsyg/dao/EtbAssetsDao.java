package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.EtbAssetsDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface EtbAssetsDao {
	
	/**
	 * 根据ID查询资产（查询所有记录）
	 * @param assetsNo
	 * @return
	 */
	public EtbAssetsDto queryAllEtbAssetsByID(String assetsNo);

	/**
	 * 翻页查询资产
	 * @param assetsNoLow
	 * @param assetsNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbAssetsDto> queryEtbAssetsByPage(String assetsNoLow,
				String assetsNoHigh, String assetsName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param assetsNoLow
	 * @param assetsNoHigh
	 * @return
	 */
	public int queryEtbAssetsCountByPage(String assetsNoLow, String assetsNoHigh, String assetsName);
	
	/**
	 * 根据ID查询资产（查询未删除的记录）
	 * @param assetsNo
	 * @return
	 */
	public EtbAssetsDto queryEtbAssetsByID(String assetsNo);
	
	/**
	 * 查询所有的资产
	 * @return
	 */
	public List<EtbAssetsDto> queryAllEtbAssets();
	
	/**
	 * 新增资产
	 * @param assets
	 */
	public void insertEtbAssets(EtbAssetsDto assets);
	
	/**
	 * 修改资产
	 * @param assets
	 */
	public void updateEtbAssets(EtbAssetsDto assets);
	
	/**
	 * 查询资产（Excel导出用）
	 * @param assetsNoLow
	 * @param assetsNoHigh
	 * @return
	 */
	public List<EtbAssetsDto> queryAllEtbAssetsExport(String assetsNoLow, String assetsNoHigh);
}
