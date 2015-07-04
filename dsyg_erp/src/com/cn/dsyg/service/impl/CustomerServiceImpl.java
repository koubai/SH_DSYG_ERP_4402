package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerDao;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.service.CustomerService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;

	@Override
	public CustomerDto queryAllEtbCustomerByID(String ID) {
		return customerDao.queryAllEtbCustomerByID(ID);
	}
	
	@Override
	public Page queryEtbCustomerByPage(Page page, String customerNoLow,
			String customerNoHigh, String customerName) {
		customerNoLow = StringUtil.replaceDatabaseKeyword_mysql(customerNoLow);
		//查询总记录数
		int totalCount = customerDao.queryEtbCustomerCountByPage(customerNoLow, customerNoHigh, customerName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<CustomerDto> list = customerDao.queryEtbCustomerByPage(customerNoLow, customerNoHigh,
				customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public CustomerDto queryEtbCustomerByID(String customerNo) {
		return customerDao.queryEtbCustomerByID(customerNo);
	}

	@Override
	public List<CustomerDto> queryAllEtbCustomer() {
		return customerDao.queryAllEtbCustomer();
	}

	@Override
	public void insertEtbCustomer(CustomerDto customer) {
		customerDao.insertEtbCustomer(customer);
	}

	@Override
	public void updateEtbCustomer(CustomerDto customer) {
		customerDao.updateEtbCustomer(customer);
	}

	@Override
	public void deleteEtbCustomer(String customerNo, String username) {
		CustomerDto customer = customerDao.queryEtbCustomerByID(customerNo);
		if(customer != null) {
			//状态=已删除
			customer.setStatus(Constants.STATUS_DEL);
			customer.setUpdateuid(username);
			customerDao.updateEtbCustomer(customer);
		}
	}

	@Override
	public List<CustomerDto> queryAllEtbCustomerExport(String customerNoLow,
			String customerNoHigh) {
		return customerDao.queryAllEtbCustomerExport(customerNoLow, customerNoHigh);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
