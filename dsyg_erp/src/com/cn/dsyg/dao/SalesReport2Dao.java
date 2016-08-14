package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.SalesReport2Dto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface SalesReport2Dao {
	
	/**
	 * 根据ID查询SALES报告（查询所有记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReport2Dto queryAllSalesReport2ByID(String salesreportNo);

	/**
	 * 翻页查询SALES报告
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesReport2Dto> querySalesReport2ByPage(String salesreportNoLow,
				String salesreportNoHigh, String salesreportName, String customerName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @return
	 */
	public int querySalesReport2CountByPage(String salesreportNoLow, String salesreportNoHigh, String salesreportName, String customerName);
	
	/**
	 * 根据ID查询SALES报告（查询未删除的记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReport2Dto querySalesReport2ByID(String salesreportNo);
	
	/**
	 * 查询所有的SALES报告
	 * @return
	 */
	public List<SalesReport2Dto> queryAllSalesReport2();
	
	/**
	 * 新增SALES报告
	 * @param salesreport
	 */
	public void insertSalesReport2(SalesReport2Dto salesreport);
	
	/**
	 * 修改SALES报告
	 * @param salesreport
	 */
	public void updateSalesReport2(SalesReport2Dto salesreport);
	
	/**
	 * 查询SALES报告（Excel导出用）
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @return
	 */
	public List<SalesReport2Dto> queryAllSalesReport2Export(String salesreportNoLow, String salesreportNoHigh);
}
