package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.PersonalDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface PersonalDao {
	
	/**
	 * 根据ID查询员工档案（查询所有记录）
	 * @param userNo
	 * @return
	 */
	public PersonalDto queryAllEtbPersonalByID(String userNo);

	/**
	 * 翻页查询员工档案
	 * @param userNoLow
	 * @param userNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PersonalDto> queryEtbPersonalByPage(String userNoLow,
				String userNoHigh, String userName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param userNoLow
	 * @param userNoHigh
	 * @return
	 */
	public int queryEtbPersonalCountByPage(String userNoLow, String userNoHigh, String userName);
	
	/**
	 * 根据员工编号查询员工档案（查询未删除的记录）
	 * @param userNo
	 * @return
	 */
	public PersonalDto queryEtbPersonalByID(String userNo);
	
	/**
	 * 查询所有的员工档案
	 * @return
	 */
	public List<PersonalDto> queryAllEtbPersonal();
	
	/**
	 * 新增员工档案
	 * @param personal
	 */
	public void insertEtbPersonal(PersonalDto personal);
	
	/**
	 * 修改员工档案
	 * @param personal
	 */
	public void updateEtbPersonal(PersonalDto personal);
	
	/**
	 * 查询员工档案（Excel导出用）
	 * @param userNoLow
	 * @param userNoHigh
	 * @return
	 */
	public List<PersonalDto> queryAllEtbPersonalExport(String userNoLow, String userNoHigh);
}
