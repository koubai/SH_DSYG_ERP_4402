package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerDao;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.service.CustomerService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	private Dict01Dao dict01Dao;


	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

	@Override
	public CustomerDto queryAllEtbCustomerByID(String ID) {
		return customerDao.queryAllEtbCustomerByID(ID);
	}

	@Override
	public CustomerDto queryAllEtbCustomerByName(String customerName){
			return customerDao.queryAllEtbCustomerByName(customerName);
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
		//客户番号
		String code = "";
		
		List<Dict01Dto> listDict = dict01Dao.queryDict01ByFieldcode(Constants.DICT_CUSTOMER_ORDER, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listDict != null && listDict.size() > 0) {
			Dict01Dto dict = listDict.get(0);
			code = dict.getCode();
			//番号+1
			dict.setCode("" + (Integer.valueOf(dict.getCode()) + 1));
			dict01Dao.updateDict01(dict);
			code = "" + (Integer.valueOf(dict.getCode()) + 1);
		} else {
			//插入数据
			Dict01Dto dict = new Dict01Dto();
			dict.setFieldcode(Constants.DICT_CUSTOMER_ORDER);
			dict.setFieldname("客户番号");
			//番号默认从1开始
			dict.setCode("1");
			code = "1";
			dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			dict.setMean("客户番号");
			dict.setNote("客户番号");
			dict.setStatus(Constants.STATUS_NORMAL);
			dict.setCreateuid("admin");
			dict.setUpdateuid("admin");
			dict01Dao.insertDict01(dict);
		}
		customer.setId(Integer.valueOf(code));
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
