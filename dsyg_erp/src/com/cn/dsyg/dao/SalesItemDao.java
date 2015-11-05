package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;

/**
 * @name SalesItemDao.java
 * @author Frank
 * @time 2015-5-17下午10:34:54
 * @version 1.0
 */
public interface SalesItemDao {

	//detail start
	/**
	 * 根据条件查询满足条件的销售单数量
	 * @param customerid
	 * @return
	 */
	public int queryDetailProductCountByPage(String customerid);
	
	/**
	 * 翻页查询满足条件的销售数据
	 * @param customerid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesItemDto> queryDetailProductByPage(String customerid, int start, int end);
	//detail end
	
	/**
	 * 根据销售单号查询销售单货物列表
	 * @param salesno
	 * @return
	 */
	public List<SalesItemDto> querySalesItemBySalesno(String salesno);
	
	/**
	 * 根据ID查询销售单货物
	 * @param id
	 * @return
	 */
	public SalesItemDto querySalesItemByID(String id);
	
	/**
	 * 根据销售单ID逻辑删除销售单货物
	 * @param salesno
	 * @param updateuid
	 */
	public void deleteSalesItemBySalesno(String salesno, String updateuid);
	
	/**
	 * 新增销售单货物
	 * @param salesItem
	 */
	public void insertSalesItem(SalesItemDto salesItem);
	
	/**
	 * 修改销售单货物
	 * @param salesItem
	 */
	public void updateSalesItem(SalesItemDto salesItem);
	
	/**
	 * 根据产品ID和客户ID查询销售单货物
	 * @param productid
	 * @param customerid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesItemDto> querySalesItemByProductid(String productid, String customerid, int start, int end);
}
