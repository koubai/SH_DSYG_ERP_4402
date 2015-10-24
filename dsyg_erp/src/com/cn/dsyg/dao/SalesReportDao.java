package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.SalesReportDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface SalesReportDao {
	
	/**
	 * 根据ID查询销售报告（查询所有记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReportDto queryAllSalesReportByID(String salesreportNo);

	/**
	 * 翻页查询销售报告
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SalesReportDto> querySalesReportByPage(String salesreportNoLow,
				String salesreportNoHigh, String salesreportName, String customerName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @return
	 */
	public int querySalesReportCountByPage(String salesreportNoLow, String salesreportNoHigh, String salesreportName, String customerName);
	
	/**
	 * 根据ID查询销售报告（查询未删除的记录）
	 * @param salesreportNo
	 * @return
	 */
	public SalesReportDto querySalesReportByID(String salesreportNo);
	
	/**
	 * 查询所有的销售报告
	 * @return
	 */
	public List<SalesReportDto> queryAllSalesReport();
	
	/**
	 * 新增销售报告
	 * @param salesreport
	 */
	public void insertSalesReport(SalesReportDto salesreport);
	
	/**
	 * 修改销售报告
	 * @param salesreport
	 */
	public void updateSalesReport(SalesReportDto salesreport);
	
	/**
	 * 查询销售报告（Excel导出用）
	 * @param salesreportNoLow
	 * @param salesreportNoHigh
	 * @return
	 */
	public List<SalesReportDto> queryAllSalesReportExport(String salesreportNoLow, String salesreportNoHigh);
}
