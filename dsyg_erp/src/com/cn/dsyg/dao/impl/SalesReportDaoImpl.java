package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SalesReportDao;
import com.cn.dsyg.dto.SalesReportDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SalesReportDaoImpl extends BaseDao implements SalesReportDao {

	@Override
	public SalesReportDto queryAllSalesReportByID(String salesreportNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", salesreportNo);
		@SuppressWarnings("unchecked")
		List<SalesReportDto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReportByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SalesReportDto> querySalesReportByPage(String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(salesreportName)) {
			paramMap.put("SALESREPORT_NAME", StringUtil.replaceDatabaseKeyword_mysql(salesreportName));
		} else {
			paramMap.put("ID_LOW", salesreportNoLow);
			paramMap.put("ID_HIGH", salesreportNoHigh);
		}
		if(StringUtil.isNotBlank(customerName)) {
			paramMap.put("CUSTOMER_NAME", StringUtil.replaceDatabaseKeyword_mysql(customerName));
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<SalesReportDto> list = getSqlMapClientTemplate().queryForList("querySalesReportByPage", paramMap);
		return list;
	}

	@Override
	public int querySalesReportCountByPage(String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		if(StringUtil.isNotBlank(salesreportName)) {
			paramMap.put("SALESREPORT_NAME", StringUtil.replaceDatabaseKeyword_mysql(salesreportName));
		} else {
			paramMap.put("ID_LOW", salesreportNoLow);
			paramMap.put("ID_HIGH", salesreportNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("querySalesReportCountByPage", paramMap);
	}

	@Override
	public SalesReportDto querySalesReportByID(String salesreportNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", salesreportNo);
		@SuppressWarnings("unchecked")
		List<SalesReportDto> list = getSqlMapClientTemplate().queryForList("querySalesReportByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SalesReportDto> queryAllSalesReport() {
		@SuppressWarnings("unchecked")
		List<SalesReportDto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReport");
		return list;
	}

	@Override
	public void insertSalesReport(SalesReportDto salesreport) {
		getSqlMapClientTemplate().insert("insertSalesReport", salesreport);
	}

	@Override
	public void updateSalesReport(SalesReportDto salesreport) {
		getSqlMapClientTemplate().update("updateSalesReport", salesreport);
	}

	@Override
	public List<SalesReportDto> queryAllSalesReportExport(String salesreportNoLow,
			String salesreportNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", salesreportNoLow);
		paramMap.put("ID_HIGH", salesreportNoHigh);
		@SuppressWarnings("unchecked")
		List<SalesReportDto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReportExport", paramMap);
		return list;
	}
}
