package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.service.FinanceService;

/**
 * @name FinanceServiceImpl.java
 * @author Frank
 * @time 2015-6-27下午11:46:02
 * @version 1.0
 */
public class FinanceServiceImpl implements FinanceService {
	
	private FinanceDao financeDao;

	@Override
	public Page queryFinanceByPage(String status, String financetype,
			String invoiceid, String receiptid, String customerid,
			String receiptdateLow, String receiptdateHigh, Page page) {
		invoiceid = StringUtil.replaceDatabaseKeyword_mysql(invoiceid);
		receiptid = StringUtil.replaceDatabaseKeyword_mysql(receiptid);
		
		//查询总记录数
		int totalCount = financeDao.queryFinanceCountByPage(status, financetype,
				invoiceid, receiptid, customerid, receiptdateLow, receiptdateHigh);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<FinanceDto> list = financeDao.queryFinanceByPage(status, financetype, invoiceid, receiptid, customerid, receiptdateLow, receiptdateHigh,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public FinanceDto queryFinanceByID(String id) {
		return financeDao.queryFinanceByID(id);
	}

	@Override
	public void insertFinance(FinanceDto finance) {
		finance.setRank(Constants.ROLE_RANK_OPERATOR);
		finance.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
		financeDao.insertFinance(finance);
	}

	@Override
	public void updateFinance(FinanceDto finance) {
		financeDao.updateFinance(finance);
	}

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}
}
