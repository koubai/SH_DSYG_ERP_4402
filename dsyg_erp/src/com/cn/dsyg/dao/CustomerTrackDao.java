package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.CustomerTrackDto;
import com.cn.dsyg.dto.CustomerTrackHistDto;

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
	 * @param strStatus
	 * @param start
	 * @param end
	 * @return
	 */
	public List<CustomerTrackDto> queryCustomerTrackByPage(String idLow,
				String idHigh, String customerName, String strStatus, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param idLow
	 * @param idHigh
	 * @param customerName
	 * @param strStatus
	 * @return
	 */
	public int queryCustomerTrackCountByPage(String idLow, String idHigh, String customerName, String strStatus);
	
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
	
	/**
	 * 根据跟踪记录编号查询所有履历
	 * @param trackno
	 * @return
	 */
	public List<CustomerTrackHistDto> queryAllTrackHist(String trackno);
	
	/**
	 * 根据ID查询履历
	 * @param trackHisSeq
	 * @return
	 */
	public CustomerTrackHistDto queryTrackHistByID(String trackHisSeq);
	
	/**
	 * 新增履历
	 * @param trackHist
	 */
	public void insertTrackHist(CustomerTrackHistDto trackHist);
}
