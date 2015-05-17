package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbCustomerDao;
import com.cn.dsyg.dto.EtbCustomerDto;
import com.cn.dsyg.service.EtbCustomerService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbCustomerServiceImpl implements EtbCustomerService {
	
	private EtbCustomerDao etbCustomerDao;

	@Override
	public EtbCustomerDto queryAllEtbCustomerByID(String ID) {
		return etbCustomerDao.queryAllEtbCustomerByID(ID);
	}

	public EtbCustomerDao getEtbCustomerDao() {
		return etbCustomerDao;
	}

	public void setEtbCustomerDao(EtbCustomerDao etbCustomerDao) {
		this.etbCustomerDao = etbCustomerDao;
	}

	@Override
	public Page queryEtbCustomerByPage(Page page, String customerNoLow,
			String customerNoHigh, String customerName) {
		customerNoLow = StringUtil.replaceDatabaseKeyword_mysql(customerNoLow);
		//查询总记录数
		int totalCount = etbCustomerDao.queryEtbCustomerCountByPage(customerNoLow, customerNoHigh, customerName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbCustomerDto> list = etbCustomerDao.queryEtbCustomerByPage(customerNoLow, customerNoHigh,
				customerName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbCustomerDto queryEtbCustomerByID(String customerNo) {
		return etbCustomerDao.queryEtbCustomerByID(customerNo);
	}

	@Override
	public List<EtbCustomerDto> queryAllEtbCustomer() {
		return etbCustomerDao.queryAllEtbCustomer();
	}

	@Override
	public void insertEtbCustomer(EtbCustomerDto customer) {
		etbCustomerDao.insertEtbCustomer(customer);
	}

	@Override
	public void updateEtbCustomer(EtbCustomerDto customer) {
		etbCustomerDao.updateEtbCustomer(customer);
	}

	@Override
	public void deleteEtbCustomer(String customerNo, String username) {
		EtbCustomerDto customer = etbCustomerDao.queryEtbCustomerByID(customerNo);
		if(customer != null) {
			//状态=已删除
			customer.setStatus(Constants.STATUS_DEL);
			customer.setUpdateuid(username);
			etbCustomerDao.updateEtbCustomer(customer);
		}
	}

	@Override
	public List<EtbCustomerDto> queryAllEtbCustomerExport(String customerNoLow,
			String customerNoHigh) {
		return etbCustomerDao.queryAllEtbCustomerExport(customerNoLow, customerNoHigh);
	}
}
