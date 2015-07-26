package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.service.PurchaseItemService;

/**
 * @name PurchaseItemServiceImpl.java
 * @author Frank
 * @time 2015-5-17下午10:44:24
 * @version 1.0
 */
public class PurchaseItemServiceImpl implements PurchaseItemService {
	
	private PurchaseItemDao purchaseItemDao;
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByPurchaseno(
			String purchaseno) {
		return purchaseItemDao.queryPurchaseItemByPurchaseno(purchaseno);
	}

	@Override
	public PurchaseItemDto queryPurchaseItemByID(String id) {
		return purchaseItemDao.queryPurchaseItemByID(id);
	}

	@Override
	public void deletePurchaseItemByPurchaseno(String purchaseno,
			String updateuid) {
		purchaseItemDao.deletePurchaseItemByPurchaseno(purchaseno, updateuid);
	}

	@Override
	public void insertPurchaseItem(PurchaseItemDto purchaseItem) {
		purchaseItemDao.insertPurchaseItem(purchaseItem);
	}

	@Override
	public void updatePurchaseItem(PurchaseItemDto purchaseItem) {
		purchaseItemDao.updatePurchaseItem(purchaseItem);
	}
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByProductid(String productid,
			String supplierid, int start, int end) {
		return purchaseItemDao.queryPurchaseItemByProductid(productid, supplierid, start, end);
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
	}
}
