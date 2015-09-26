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
	public PregatheringDto queryAllEtbPregatheringByID(String pregatheringNo);

	/**
	 * 翻页查询预收款
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PregatheringDto> queryEtbPregatheringByPage(String pregatheringNoLow,
				String pregatheringNoHigh, String pregatheringName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @return
	 */
	public int queryEtbPregatheringCountByPage(String pregatheringNoLow, String pregatheringNoHigh, String pregatheringName);
	
	/**
	 * 根据ID查询预收款（查询未删除的记录）
	 * @param pregatheringNo
	 * @return
	 */
	public PregatheringDto queryEtbPregatheringByID(String pregatheringNo);
	
	/**
	 * 查询所有的预收款
	 * @return
	 */
	public List<PregatheringDto> queryAllEtbPregathering();
	
	/**
	 * 新增预收款
	 * @param pregathering
	 */
	public void insertEtbPregathering(PregatheringDto pregathering);
	
	/**
	 * 修改预收款
	 * @param pregathering
	 */
	public void updateEtbPregathering(PregatheringDto pregathering);
	
	/**
	 * 查询资产（Excel导出用）
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @return
	 */
	public List<PregatheringDto> queryAllEtbPregatheringExport(String pregatheringNoLow, String pregatheringNoHigh);
}
