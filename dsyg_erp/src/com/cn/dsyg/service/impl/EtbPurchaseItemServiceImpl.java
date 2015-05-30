package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.dsyg.dao.EtbPurchaseItemDao;
import com.cn.dsyg.dto.EtbPurchaseItemDto;
import com.cn.dsyg.service.EtbPurchaseItemService;

/**
 * @name EtbPurchaseItemServiceImpl.java
 * @author Frank
 * @time 2015-5-17下午10:44:24
 * @version 1.0
 */
public class EtbPurchaseItemServiceImpl implements EtbPurchaseItemService {
	
	private EtbPurchaseItemDao etbPurchaseItemDao;

	@Override
	public List<EtbPurchaseItemDto> queryPurchaseItemByPurchaseno(
			String purchaseno) {
		return etbPurchaseItemDao.queryPurchaseItemByPurchaseno(purchaseno);
	}

	@Override
	public EtbPurchaseItemDto queryPurchaseItemByID(String id) {
		return etbPurchaseItemDao.queryPurchaseItemByID(id);
	}

	@Override
	public void deletePurchaseItemByPurchaseno(String purchaseno,
			String updateuid) {
		etbPurchaseItemDao.deletePurchaseItemByPurchaseno(purchaseno, updateuid);
	}

	@Override
	public void insertPurchaseItem(EtbPurchaseItemDto purchaseItem) {
		etbPurchaseItemDao.insertPurchaseItem(purchaseItem);
	}

	@Override
	public void updatePurchaseItem(EtbPurchaseItemDto purchaseItem) {
		etbPurchaseItemDao.updatePurchaseItem(purchaseItem);
	}

	public EtbPurchaseItemDao getEtbPurchaseItemDao() {
		return etbPurchaseItemDao;
	}

	public void setEtbPurchaseItemDao(EtbPurchaseItemDao etbPurchaseItemDao) {
		this.etbPurchaseItemDao = etbPurchaseItemDao;
	}
}
