package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.FinanceService;

/**
 * @name FinanceServiceImpl.java
 * @author Frank
 * @time 2015-6-27下午11:46:02
 * @version 1.0
 */
public class FinanceServiceImpl implements FinanceService {
	
	private FinanceDao financeDao;
	private UserDao userDao;

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
		if(list != null && list.size() > 0) {
			for(FinanceDto finance : list) {
				UserDto user = userDao.queryUserByID(finance.getHandler());
				if(user != null) {
					finance.setHandlername(user.getUsername());
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public FinanceDto queryFinanceByID(String id) {
		FinanceDto finance = financeDao.queryFinanceByID(id);
		if(finance != null) {
			UserDto user = userDao.queryUserByID(finance.getHandler());
			if(user != null) {
				finance.setHandlername(user.getUsername());
			}
		}
		return finance;
	}

	@Override
	public String insertFinance(FinanceDto finance) {
		String no = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		no = Constants.FINANCE_NO_PRE + belongto + sdf.format(date) + uuid;
		
		if(StringUtil.isBlank(finance.getReceiptdate())) {
			finance.setReceiptdate(null);
		}
		finance.setRank(Constants.ROLE_RANK_OPERATOR);
		finance.setReceiptid(no);
		finance.setBelongto(belongto);
		financeDao.insertFinance(finance);
		return no;
	}

	@Override
	public void updateFinance(FinanceDto finance) {
		if(StringUtil.isBlank(finance.getReceiptdate())) {
			finance.setReceiptdate(null);
		}
		financeDao.updateFinance(finance);
	}

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
