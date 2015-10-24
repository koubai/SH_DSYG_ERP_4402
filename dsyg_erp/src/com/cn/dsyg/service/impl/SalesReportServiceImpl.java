package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SalesReportDao;
import com.cn.dsyg.dto.SalesReportDto;
import com.cn.dsyg.service.SalesReportService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SalesReportServiceImpl implements SalesReportService {
	
	private SalesReportDao salesreportDao;

	@Override
	public SalesReportDto queryAllSalesReportByID(String ID) {
		return salesreportDao.queryAllSalesReportByID(ID);
	}

	@Override
	public Page querySalesReportByPage(Page page, String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName) {
		salesreportNoLow = StringUtil.replaceDatabaseKeyword_mysql(salesreportNoLow);
		//查询总记录数
		int totalCount = salesreportDao.querySalesReportCountByPage(salesreportNoLow, salesreportNoHigh, salesreportName, customerName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesReportDto> list = salesreportDao.querySalesReportByPage(salesreportNoLow, salesreportNoHigh,
				salesreportName, customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		for (SalesReportDto salesReport : list){
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			salesReport.setReporturl(pdfurl);
		}
		page.setItems(list);
		return page;
	}

	@Override
	public SalesReportDto querySalesReportByID(String salesreportNo) {
		SalesReportDto salereport = salesreportDao.querySalesReportByID(salesreportNo);
		if(salereport != null) {
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			salereport.setReporturl(pdfurl);
			return salereport;
		}
		return null;
	}

	@Override
	public List<SalesReportDto> queryAllSalesReport() {
		return salesreportDao.queryAllSalesReport();
	}

	@Override
	public String insertSalesReport(SalesReportDto salesreport) {
		String salesreportno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		salesreport.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		salesreportno = Constants.SALESREPORT_NO_PRE + belongto + sdf.format(date) + uuid;
		salesreport.setSalesreportno(salesreportno);
		
		salesreportDao.insertSalesReport(salesreport);
		return salesreportno;
	}

	@Override
	public void updateSalesReport(SalesReportDto salesreport) {
		salesreportDao.updateSalesReport(salesreport);
	}

	@Override
	public void deleteSalesReport(String salesreportNo, String username) {
		SalesReportDto salesreport = salesreportDao.querySalesReportByID(salesreportNo);
		if(salesreport != null) {
			//状态=已删除
			salesreport.setStatus(Constants.STATUS_DEL);
			salesreport.setUpdateuid(username);
			salesreportDao.updateSalesReport(salesreport);
		}
	}

	@Override
	public List<SalesReportDto> queryAllSalesReportExport(String salesreportNoLow,
			String salesreportNoHigh) {
		return salesreportDao.queryAllSalesReportExport(salesreportNoLow, salesreportNoHigh);
	}

	public SalesReportDao getSalesReportDao() {
		return salesreportDao;
	}

	public void setSalesReportDao(SalesReportDao salesreportDao) {
		this.salesreportDao = salesreportDao;
	}
}
