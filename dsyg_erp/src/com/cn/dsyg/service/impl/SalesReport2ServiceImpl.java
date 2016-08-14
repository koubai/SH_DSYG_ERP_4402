package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SalesReport2Dao;
import com.cn.dsyg.dto.SalesReport2Dto;
import com.cn.dsyg.service.SalesReport2Service;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SalesReport2ServiceImpl implements SalesReport2Service {
	
	private SalesReport2Dao salesreport2Dao;

	@Override
	public SalesReport2Dto queryAllSalesReport2ByID(String ID) {
		return salesreport2Dao.queryAllSalesReport2ByID(ID);
	}

	@Override
	public Page querySalesReport2ByPage(Page page, String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName) {
		System.out.println("querySalesReport2ByPage 01");
		salesreportNoLow = StringUtil.replaceDatabaseKeyword_mysql(salesreportNoLow);
		
		//查询总记录数
		int totalCount = salesreport2Dao.querySalesReport2CountByPage(salesreportNoLow, salesreportNoHigh, salesreportName, customerName);
		System.out.println("querySalesReport2ByPage TotalCount:" + totalCount);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesReport2Dto> list = salesreport2Dao.querySalesReport2ByPage(salesreportNoLow, salesreportNoHigh,
				salesreportName, customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		System.out.println("querySalesReport2ByPage listsize:" + list.size());
		for (SalesReport2Dto salesReport : list){
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			salesReport.setReporturl(pdfurl);
		}
		page.setItems(list);
		return page;
	}

	@Override
	public SalesReport2Dto querySalesReport2ByID(String salesreportNo) {
		SalesReport2Dto salereport = salesreport2Dao.querySalesReport2ByID(salesreportNo);
		if(salereport != null) {
			//文件显示地址
			String pdfurl = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_URL);
			salereport.setReporturl(pdfurl);
			return salereport;
		}
		return null;
	}

	@Override
	public List<SalesReport2Dto> queryAllSalesReport2() {
		return salesreport2Dao.queryAllSalesReport2();
	}

	@Override
	public String insertSalesReport2(SalesReport2Dto salesreport) {
		String salesreportno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		salesreport.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		salesreportno = Constants.SALESREPORT2_NO_PRE + belongto + sdf.format(date) + uuid;
		salesreport.setSalesreportno(salesreportno);
		
		salesreport2Dao.insertSalesReport2(salesreport);
		return salesreportno;
	}

	@Override
	public void updateSalesReport2(SalesReport2Dto salesreport) {
		salesreport2Dao.updateSalesReport2(salesreport);
	}

	@Override
	public void deleteSalesReport2(String salesreportNo, String username) {
		SalesReport2Dto salesreport = salesreport2Dao.querySalesReport2ByID(salesreportNo);
		if(salesreport != null) {
			//状态=已删除
			salesreport.setStatus(Constants.STATUS_DEL);
			salesreport.setUpdateuid(username);
			salesreport2Dao.updateSalesReport2(salesreport);
		}
	}

	@Override
	public List<SalesReport2Dto> queryAllSalesReport2Export(String salesreportNoLow,
			String salesreportNoHigh) {
		return salesreport2Dao.queryAllSalesReport2Export(salesreportNoLow, salesreportNoHigh);
	}

	public SalesReport2Dao getSalesReportDao() {
		return salesreport2Dao;
	}

	public void setSalesReport2Dao(SalesReport2Dao salesreport2Dao) {
		this.salesreport2Dao = salesreport2Dao;
	}
}
