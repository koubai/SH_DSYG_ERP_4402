package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.CustomerTrackDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface CustomerTrackDao {
	
	/**
	 * 根据ID查询客户跟踪记录（查询所有记录）
	 * @param id
	 * @return
	 */
	public CustomerTrackDto queryAllCustomerTrackByID(String id);

	/**
	 * 翻页查询客户跟踪记录
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @param start
	 * @param end
	 * @return
	 */
	public List<CustomerTrackDto> queryCustomerTrackByPage(String idLow,
				String idHigh, String customerName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @return
	 */
	public int queryCustomerTrackCountByPage(String idLow, String idHigh, String customerName);
	
	/**
	 * 根据编号查询客户跟踪记录（查询未删除的记录）
	 * @param id
	 * @return
	 */
	public CustomerTrackDto queryCustomerTrackByID(String id);
	
	/**
	 * 查询所有的客户跟踪记录
	 * @return
	 */
	public List<CustomerTrackDto> queryAllCustomerTrack();
	
	/**
	 * 新增客户跟踪记录
	 * @param customerTrack
	 */
	public void insertCustomerTrack(CustomerTrackDto customerTrack);
	
	/**
	 * 修改客户跟踪记录
	 * @param customerTrack
	 */
	public void updateCustomerTrack(CustomerTrackDto customerTrack);
	
	/**
	 * 查询客户跟踪记录（Excel导出用）
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @return
	 */
	public List<CustomerTrackDto> queryAllCustomerTrackExport(String idLow, String idHigh, String customerName);
}
