package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.PregatheringDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface PregatheringService {
	
	/**
	 * 翻页查询预收款
	 * @param page
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @param pregatheringAddFlag
	 * @param pregatheringName
	 * @return
	 */
	public Page queryEtbPregatheringByPage(Page page, String pregatheringNoLow, String pregatheringNoHigh, String pregatheringName);

	/**
	 * 根据ID查询预收款（查询未删除的记录）
	 * @param pregatheringNo
	 * @return
	 */
	public PregatheringDto queryEtbPregatheringByID(String pregatheringNo);
	
	/**
	 * 根据ID预收款（查询所有记录）
	 * @param pregatheringNo
	 * @return
	 */
	public PregatheringDto queryAllEtbPregatheringByID(String pregatheringNo);
	
	/**
	 * 查询所有的预收款
	 * @return
	 */
	public List<PregatheringDto> queryAllEtbPregathering();
	
	/**
	 * 新增预收款
	 * @param pregathering
	 * @return
	 */
	public String insertEtbPregathering(PregatheringDto pregathering);
	
	/**
	 * 修改预收款
	 * @param pregathering
	 */
	public void updateEtbPregathering(PregatheringDto pregathering);
	
	/**
	 * 删除预收款
	 * @param pregatheringNo
	 * @param username
	 */
	public void deleteEtbPregathering(String pregatheringNo, String username);
	
	/**
	 * 查询资产（Excel导出用）
	 * @param pregatheringNoLow
	 * @param pregatheringNoHigh
	 * @return
	 */
	public List<PregatheringDto> queryAllEtbPregatheringExport(String pregatheringNoLow, String pregatheringNoHigh);
}
