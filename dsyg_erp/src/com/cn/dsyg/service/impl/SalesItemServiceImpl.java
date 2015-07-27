package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.service.SalesItemService;

/**
 * @name SalesItemServiceImpl.java
 * @author Frank
 * @time 2015-6-17下午9:49:18
 * @version 1.0
 */
public class SalesItemServiceImpl implements SalesItemService {
	
	private SalesItemDao salesItemDao;

	@Override
	public List<SalesItemDto> querySalesItemBySalesno(String salesno) {
		return salesItemDao.querySalesItemBySalesno(salesno);
	}

	@Override
	public SalesItemDto querySalesItemByID(String id) {
		return salesItemDao.querySalesItemByID(id);
	}

	@Override
	public void deleteSalesItemBySalesno(String salesno, String updateuid) {
		salesItemDao.deleteSalesItemBySalesno(salesno, updateuid);
	}

	@Override
	public void insertSalesItem(SalesItemDto salesItem) {
		salesItemDao.insertSalesItem(salesItem);
	}

	@Override
	public void updateSalesItem(SalesItemDto salesItem) {
		salesItemDao.updateSalesItem(salesItem);
	}
	
	@Override
	public List<SalesItemDto> querySalesItemByProductid(String productid,
			String customerid, int start, int end) {
		return salesItemDao.querySalesItemByProductid(productid, customerid, start, end);
	}

	public SalesItemDao getSalesItemDao() {
		return salesItemDao;
	}

	public void setSalesItemDao(SalesItemDao salesItemDao) {
		this.salesItemDao = salesItemDao;
	}
}
