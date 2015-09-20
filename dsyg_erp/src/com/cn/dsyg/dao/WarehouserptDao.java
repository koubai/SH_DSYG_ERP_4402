package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.WarehouserptDto;

/**
 * @name WarehouserptDao.java
 * @author Frank
 * @time 2015-6-3下午9:44:07
 * @version 1.0
 */
public interface WarehouserptDao {
	
	/**
	 * 导出入出库单数据
	 * @param status
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param parentid
	 * @param supplierid
	 * @param productid
	 * @return
	 */
	public List<WarehouserptDto> queryAllWarehouserptToExport(String status, String warehousetype,
			String warehouseno, String theme1, String parentid, String supplierid, String productid);

	/**
	 * 翻页查询
	 * @param status
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param parentid
	 * @param supplierid
	 * @param productid
	 * @param beginDate
	 * @param endDate
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouserptDto> queryWarehouserptByPage(String status, String warehousetype,
			String warehouseno, String theme1, String parentid, String supplierid,
			String productid, String beginDate, String endDate, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param status
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param parentid
	 * @param supplierid
	 * @param productid
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public int queryWarehouserptCountByPage(String status, String warehousetype, String warehouseno,
			String theme1, String parentid, String supplierid, String productid, String beginDate, String endDate);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public WarehouserptDto queryWarehouserptByID(String id);
	
	/**
	 * 根据入出库单查询数据
	 * @param warehouseno
	 * @return
	 */
	public WarehouserptDto queryWarehouserptByNo(String warehouseno);
	
	/**
	 * 新增数据
	 * @param warehouserpt
	 */
	public void insertWarehouserpt(WarehouserptDto warehouserpt);
	
	/**
	 * 修改数据
	 * @param warehouserpt
	 */
	public void updateWarehouserpt(WarehouserptDto warehouserpt);
}
