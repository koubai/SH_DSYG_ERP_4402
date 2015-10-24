package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.SalesReportDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface SalesReportService {
	
	/**
	 * 翻页查询销售报告
	 * @param page
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @param salesreportName
	 * @return
	 */
	public Page querySalesReportByPage(Page page, String salesreportNoLow, String salesreportNoHigh, String salesreportName, String customerName);

	/**
	 * 根据ID查询销售报告（查询未删除的记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReportDto querySalesReportByID(String salesreportNo);
	
	/**
	 * 根据ID销售报告（查询所有记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReportDto queryAllSalesReportByID(String salesreportNo);
	
	/**
	 * 查询所有的销售报告
	 * @return
	 */
	public List<SalesReportDto> queryAllSalesReport();
	
	/**
	 * 新增销售报告
	 * @param salesreport
	 * @return
	 */
	public String insertSalesReport(SalesReportDto salesreport);
	
	/**
	 * 修改销售报告
	 * @param salesreport
	 */
	public void updateSalesReport(SalesReportDto salesreport);
	
	/**
	 * 删除销售报告
	 * @param salesreportNo
	 * @param username
	 */
	public void deleteSalesReport(String salesreportNo, String username);
	
	/**
	 * 查询销售报告（Excel导出用）
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @return
	 */
	public List<SalesReportDto> queryAllSalesReportExport(String salesreportNoLow, String salesreportNoHigh);
}
