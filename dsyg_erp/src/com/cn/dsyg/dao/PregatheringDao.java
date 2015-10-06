package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.PregatheringDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface PregatheringDao {
	
	/**
	 * 根据ID查询预收款（查询所有记录）
	 * @param pregatheringNo
	 * @return
	 */
	public PregatheringDto queryAllPregatheringByID(String pregatheringNo);

	/**
	 * 翻页查询预收款
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PregatheringDto> queryPregatheringByPage(String pregatheringNoLow,
				String pregatheringNoHigh, String pregatheringName, String customerName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @return
	 */
	public int queryPregatheringCountByPage(String pregatheringNoLow, String pregatheringNoHigh, String pregatheringName, String customerName);
	
	/**
	 * 根据ID查询预收款（查询未删除的记录）
	 * @param pregatheringNo
	 * @return
	 */
	public PregatheringDto queryPregatheringByID(String pregatheringNo);
	
	/**
	 * 查询所有的预收款
	 * @return
	 */
	public List<PregatheringDto> queryAllPregathering();
	
	/**
	 * 新增预收款
	 * @param pregathering
	 */
	public void insertPregathering(PregatheringDto pregathering);
	
	/**
	 * 修改预收款
	 * @param pregathering
	 */
	public void updatePregathering(PregatheringDto pregathering);
	
	/**
	 * 查询资产（Excel导出用）
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @return
	 */
	public List<PregatheringDto> queryAllPregatheringExport(String pregatheringNoLow, String pregatheringNoHigh);
}
