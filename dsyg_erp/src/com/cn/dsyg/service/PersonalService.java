package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.EtbPersonalDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface PersonalService {
	
	/**
	 * 翻页查询员工档案
	 * @param page
	 * @param userNoLow
	 * @param userNoHigh
	 * @param personalAddFlag
	 * @param userName
	 * @return
	 */
	public Page queryEtbPersonalByPage(Page page, String userNoLow, String userNoHigh, String userName);

	/**
	 * 根据员工编号查询员工档案（查询未删除的记录）
	 * @param userNo
	 * @return
	 */
	public EtbPersonalDto queryEtbPersonalByID(String userNo);
	
	/**
	 * 根据员工编号查询员工档案（查询所有记录）
	 * @param userNo
	 * @return
	 */
	public EtbPersonalDto queryAllEtbPersonalByID(String userNo);
	
	/**
	 * 查询所有的员工档案
	 * @return
	 */
	public List<EtbPersonalDto> queryAllEtbPersonal();
	
	/**
	 * 新增员工档案
	 * @param personal
	 */
	public void insertEtbPersonal(EtbPersonalDto personal);
	
	/**
	 * 修改员工档案
	 * @param personal
	 */
	public void updateEtbPersonal(EtbPersonalDto personal);
	
	/**
	 * 删除员工档案
	 * @param userNo
	 * @param username
	 */
	public void deleteEtbPersonal(String userNo, String username);
	
	/**
	 * 查询员工档案（Excel导出用）
	 * @param userNoLow
	 * @param userNoHigh
	 * @return
	 */
	public List<EtbPersonalDto> queryAllEtbPersonalExport(String userNoLow, String userNoHigh);
}
