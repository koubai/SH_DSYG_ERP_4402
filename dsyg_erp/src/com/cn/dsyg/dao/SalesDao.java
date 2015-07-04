package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.SalesDto;

/**
 * @name SalesDao.java
 * @author Frank
 * @time 2015-6-28下午9:29:38
 * @version 1.0
 */
public interface SalesDao {

	//finance start
	/**
	 * 根据条件查询满足条件的销售单数量（财务）
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param status
	 * @return
	 */
	public int queryFinanceSalesCountByPage(String bookdateLow, String bookdateHigh, String status);
	
	/**
	 * 翻页查询满足条件的销售数据（财务）
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesDto> queryFinanceSalesByPage(String bookdateLow, String bookdateHigh, String status, int start, int end);
	//finance end
	
	/**
	 * 根据条件查询满足条件的销售单数量
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @return
	 */
	public int querySalesCountByPage(String bookdateLow, String bookdateHigh);
	
	/**
	 * 翻页查询满足条件的销售数据
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesDto> querySalesByPage(String bookdateLow, String bookdateHigh, int start, int end);
	
	/**
	 * 根据ID查询销售单数据
	 * @param id
	 * @return
	 */
	public SalesDto querySalesByID(String id);
	
	/**
	 * 根据编号查询销售单数据
	 * @param salesno
	 * @return
	 */
	public SalesDto querySalesByNo(String salesno);
	
	/**
	 * 物理删除销售单
	 * @param id
	 */
	public void deleteSales(String id);
	
	/**
	 * 新增销售单
	 * @param sales
	 */
	public void insertSales(SalesDto sales);
	
	/**
	 * 修改销售单
	 * @param sales
	 */
	public void updateSales(SalesDto sales);
}
