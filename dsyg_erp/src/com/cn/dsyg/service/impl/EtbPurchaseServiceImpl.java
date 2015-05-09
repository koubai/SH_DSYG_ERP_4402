package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dao.EtbPurchaseDao;
import com.cn.dsyg.dto.EtbPurchaseDto;
import com.cn.dsyg.service.EtbPurchaseService;

/**
 * @name EtbPurchaseServiceImpl.java
 * @author Frank
 * @time 2015-5-9下午10:26:35
 * @version 1.0
 */
public class EtbPurchaseServiceImpl implements EtbPurchaseService {
	
	private EtbPurchaseDao etbPurchaseDao;

	@Override
	public Page queryEtbPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, Page page) {
		//查询总记录数
		int totalCount = etbPurchaseDao.queryEtbPurchaseCountByPage(purchasedateLow, purchasedateHigh);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbPurchaseDto> list = etbPurchaseDao.queryEtbPurchaseByPage(purchasedateLow, purchasedateHigh,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbPurchaseDto queryEtbPurchaseByID(String id) {
		return etbPurchaseDao.queryEtbPurchaseByID(id);
	}

	@Override
	public void deleteEtbPurchase(String id) {
		etbPurchaseDao.deleteEtbPurchase(id);
	}

	@Override
	public void insertEtbPurchase(EtbPurchaseDto etbPurchase) {
		etbPurchaseDao.insertEtbPurchase(etbPurchase);
	}

	@Override
	public void updateEtbPurchase(EtbPurchaseDto etbPurchase) {
		etbPurchaseDao.updateEtbPurchase(etbPurchase);
	}

	public EtbPurchaseDao getEtbPurchaseDao() {
		return etbPurchaseDao;
	}

	public void setEtbPurchaseDao(EtbPurchaseDao etbPurchaseDao) {
		this.etbPurchaseDao = etbPurchaseDao;
	}
}
