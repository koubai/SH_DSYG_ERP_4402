package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.CustomerTrackDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface CustomerTrackService {
	
	/**
	 * 翻页查询客户跟踪
	 * @param page
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @return
	 */
	public Page queryCustomerTrackByPage(Page page, String idLow, String idHigh, String customerName);

	/**
	 * 根据员工编号查询客户跟踪（查询未删除的记录）
	 * @param id
	 * @return
	 */
	public CustomerTrackDto queryCustomerTrackByID(String id);
	
	/**
	 * 根据员工编号查询客户跟踪（查询所有记录）
	 * @param id
	 * @return
	 */
	public CustomerTrackDto queryAllCustomerTrackByID(String id);
	
	/**
	 * 查询所有的客户跟踪
	 * @return
	 */
	public List<CustomerTrackDto> queryAllCustomerTrack();
	
	/**
	 * 新增客户跟踪
	 * @param customerTrack
	 * @return
	 */
	public String insertCustomerTrack(CustomerTrackDto customerTrack);
	
	/**
	 * 修改客户跟踪
	 * @param customerTrack
	 */
	public void updateCustomerTrack(CustomerTrackDto customerTrack);
	
	/**
	 * 删除客户跟踪
	 * @param id
	 * @param userName
	 */
	public void deleteCustomerTrack(String id, String userName);
	
	/**
	 * 查询客户跟踪（Excel导出用）
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @return
	 */
	public List<CustomerTrackDto> queryAllCustomerTrackExport(String idLow, String idHigh, String customerName);
}
