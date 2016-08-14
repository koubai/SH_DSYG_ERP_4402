package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SalesReport2Dao;
import com.cn.dsyg.dto.SalesReport2Dto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SalesReport2DaoImpl extends BaseDao implements SalesReport2Dao {

	@Override
	public SalesReport2Dto queryAllSalesReport2ByID(String salesreportNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", salesreportNo);
		@SuppressWarnings("unchecked")
		List<SalesReport2Dto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReport2ByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SalesReport2Dto> querySalesReport2ByPage(String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若资产名称存在，则忽略资产代码按资产名称来查询。
		System.out.println("querySalesReport2ByPage start");

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
		List<SalesReport2Dto> list = getSqlMapClientTemplate().queryForList("querySalesReport2ByPage", paramMap);
		return list;
	}

	@Override
	public int querySalesReport2CountByPage(String salesreportNoLow,
			String salesreportNoHigh, String salesreportName, String customerName) {
		System.out.println("querySalesReport2ByPage internal");

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
		System.out.println("querySalesReport2ByPage internal paramMap" + paramMap);
		return (Integer) getSqlMapClientTemplate().queryForObject("querySalesReport2CountByPage", paramMap);
	}

	@Override
	public SalesReport2Dto querySalesReport2ByID(String salesreportNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", salesreportNo);
		@SuppressWarnings("unchecked")
		List<SalesReport2Dto> list = getSqlMapClientTemplate().queryForList("querySalesReport2ByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SalesReport2Dto> queryAllSalesReport2() {
		@SuppressWarnings("unchecked")
		List<SalesReport2Dto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReport2");
		return list;
	}

	@Override
	public void insertSalesReport2(SalesReport2Dto salesreport) {
		getSqlMapClientTemplate().insert("insertSalesReport2", salesreport);
	}

	@Override
	public void updateSalesReport2(SalesReport2Dto salesreport) {
		getSqlMapClientTemplate().update("updateSalesReport2", salesreport);
	}

	@Override
	public List<SalesReport2Dto> queryAllSalesReport2Export(String salesreportNoLow,
			String salesreportNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", salesreportNoLow);
		paramMap.put("ID_HIGH", salesreportNoHigh);
		@SuppressWarnings("unchecked")
		List<SalesReport2Dto> list = getSqlMapClientTemplate().queryForList("queryAllSalesReport2Export", paramMap);
		return list;
	}
}
