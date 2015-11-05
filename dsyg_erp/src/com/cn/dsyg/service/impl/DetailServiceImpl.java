package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.service.DetailService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class DetailServiceImpl implements DetailService {

	private SalesDao salesDao;
	private SalesItemDao salesItemDao;
	
	@Override
	public Page queryDetailCustomerByPage(Page page, String productid) {
		//查询总记录数
		int totalCount = salesDao.queryDetailCustomerCountByPage(productid);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesDto> list = salesDao.queryDetailCustomerByPage(productid, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
		/*
		List<SalesDto> listSales = new ArrayList<SalesDto>();
		listSalesItem = salesItemDao.querySalesItemByProductid(productid, "", 0, 10000);
		if(listSalesItem != null && listSalesItem.size() > 0){
			for(SalesItemDto salesItem : listSalesItem){
				SalesDto salesDto = salesDao.querySalesByTheme2(salesItem.getTheme2());
				listSales.add(salesDto);
			}
		}
		page.setItems(listSales);
		return page;*/
	}

	@Override
	public Page queryDetailProductByPage(Page page, String customerid) {
		//查询总记录数
		int totalCount = salesItemDao.queryDetailProductCountByPage(customerid);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesItemDto> list = salesItemDao.queryDetailProductByPage(customerid, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	public SalesDao getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	public SalesItemDao getSalesItemDao() {
		return salesItemDao;
	}

	public void setSalesItemDao(SalesItemDao salesItemDao) {
		this.salesItemDao = salesItemDao;
	}
}
