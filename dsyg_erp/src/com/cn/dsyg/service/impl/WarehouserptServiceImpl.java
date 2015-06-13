package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.WarehouserptService;

/**
 * @name WarehouserptServiceImpl.java
 * @author Frank
 * @time 2015-6-3下午9:50:32
 * @version 1.0
 */
public class WarehouserptServiceImpl implements WarehouserptService {
	
	private WarehouserptDao warehouserptDao;
	private PurchaseItemDao purchaseItemDao;
	private PurchaseDao purchaseDao;

	@Override
	public Page queryWarehouserptByPage(String status, String warehousetype,
			String warehouseno, String theme1, String parentid,
			String supplierid, String productid, Page page) {
		
		//查询总记录数
		int totalCount = warehouserptDao.queryWarehouserptCountByPage(status, warehousetype, warehouseno, theme1, parentid, supplierid, productid);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouserptDto> list = warehouserptDao.queryWarehouserptByPage(status, warehousetype, warehouseno, theme1, parentid, supplierid, productid,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public WarehouserptDto queryWarehouserptByID(String id) {
		return warehouserptDao.queryWarehouserptByID(id);
	}

	@Override
	public void insertWarehouserpt(WarehouserptDto warehouserpt) {
		warehouserptDao.insertWarehouserpt(warehouserpt);
	}

	@Override
	public void updateWarehouserpt(WarehouserptDto warehouserpt) {
		warehouserptDao.updateWarehouserpt(warehouserpt);
	}

	public WarehouserptDao getWarehouserptDao() {
		return warehouserptDao;
	}

	public void setWarehouserptDao(WarehouserptDao warehouserptDao) {
		this.warehouserptDao = warehouserptDao;
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
}
