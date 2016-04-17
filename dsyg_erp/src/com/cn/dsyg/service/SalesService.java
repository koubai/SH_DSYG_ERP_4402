package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;

/**
 * @name SalesService.java
 * @author Frank
 * @time 2015-6-16下午9:57:18
 * @version 1.0
 */
public interface SalesService {
	
	//finance start
	/**
	 * 翻页查询满足条件的销售数据（财务）
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param status
	 * @param page
	 * @return
	 */
	public Page queryFinanceSalesByPage(String bookdateLow, String bookdateHigh, String status, Page page);
	//finance end
	
	/**
	 * 新增销售单和产品
	 * @param sales
	 * @param listSalesItem
	 * @param userid
	 * @return
	 */
	public String addSales(SalesDto sales, List<SalesItemDto> listSalesItem, String userid);

	/**
	 * 翻页查询满足条件的销售数据(without productid)
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param theme2
	 * @param type
	 * @param customername
	 * @param status
	 * @param page
	 * @return
	 */
	public Page querySalesByPage(String bookdateLow, String bookdateHigh, String theme2, String type, String customername, String status, Page page);
	
	/**
	 * 翻页查询满足条件的销售数据
	 * @param bookdateLow
	 * @param bookdateHigh
	 * @param theme2
	 * @param type
	 * @param customername
	 * @param status
	 * @param page
	 * @return
	 */
	public Page querySalesExtByPage(String bookdateLow, String bookdateHigh, String theme2, String type, String customername, String productid, String status, Page page);

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
	 * 根据编号查询销售单数据(By theme2)
	 * @param salesno
	 * @return
	 */
	public SalesDto querySalesByTheme2(String Theme2);

	
	/**
	 * 逻辑删除销售单
	 * @param id
	 * @param userid
	 */
	public void deleteSales(String id, String userid);
	
	/**
	 * 新增销售单
	 * @param sales
	 */
	public void insertSales(SalesDto sales);
	
	/**
	 * 修改销售单
	 * @param sales
	 * @param listSalesItem
	 * @param userid
	 */
	public void updateSales(SalesDto sales, List<SalesItemDto> listSalesItem, String userid);
	
	/**
	 * 修改销售单
	 * @param sales
	 */
	public void updateSales(SalesDto sales);
	
	/**
	 * 修改销售单状态
	 * @param id
	 * @param userid
	 * @param status
	 */
	public void updateFinanceSales(String id, String userid, String status);
	
	/**
	 * 终了销售单
	 * @param id
	 * @param userid
	 */
	public void finishSales(String id, String userid);
}
