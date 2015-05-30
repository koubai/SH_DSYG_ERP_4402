package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.dsyg.dao.EtbPurchaseDao;
import com.cn.dsyg.dao.EtbPurchaseItemDao;
import com.cn.dsyg.dto.EtbPurchaseDto;
import com.cn.dsyg.dto.EtbPurchaseItemDto;
import com.cn.dsyg.service.EtbPurchaseService;

/**
 * @name EtbPurchaseServiceImpl.java
 * @author Frank
 * @time 2015-5-9下午10:26:35
 * @version 1.0
 */
public class EtbPurchaseServiceImpl implements EtbPurchaseService {
	
	private EtbPurchaseDao etbPurchaseDao;
	private EtbPurchaseItemDao etbPurchaseItemDao;

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
	public String addEtbPurchase(EtbPurchaseDto etbPurchase,
			List<EtbPurchaseItemDto> listPurchaseItem, String userid) {
		//生成采购单号
		String purchaseno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		etbPurchase.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		purchaseno = "PURCHASE" + belongto + sdf.format(date);
		etbPurchase.setPurchaseno(purchaseno);
		etbPurchase.setUpdateuid(userid);
		etbPurchase.setCreateuid(userid);
		etbPurchaseDao.insertEtbPurchase(etbPurchase);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(EtbPurchaseItemDto purchaseItem : listPurchaseItem) {
				//采购单号
				purchaseItem.setPurchaseno(purchaseno);
				purchaseItem.setUpdateuid(userid);
				purchaseItem.setCreateuid(userid);
				purchaseItem.setStatus("" + Constants.STATUS_NORMAL);
				purchaseItem.setBelongto(belongto);
				etbPurchaseItemDao.insertPurchaseItem(purchaseItem);
			}
		}
		return purchaseno;
	}

	@Override
	public void insertEtbPurchase(EtbPurchaseDto etbPurchase) {
		etbPurchaseDao.insertEtbPurchase(etbPurchase);
	}

	@Override
	public void updateEtbPurchase(EtbPurchaseDto etbPurchase, List<EtbPurchaseItemDto> listPurchaseItem, String userid) {
		etbPurchaseDao.updateEtbPurchase(etbPurchase);
		//根据采购单号删除所有的货物数据
		etbPurchaseItemDao.deletePurchaseItemByPurchaseno(etbPurchase.getPurchaseno(), userid);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(EtbPurchaseItemDto purchaseItem : listPurchaseItem) {
				if(purchaseItem.getId() == null) {
					//新增
					//采购单号
					purchaseItem.setPurchaseno(etbPurchase.getPurchaseno());
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setCreateuid(userid);
					purchaseItem.setStatus("" + Constants.STATUS_NORMAL);
					purchaseItem.setBelongto(etbPurchase.getBelongto());
					etbPurchaseItemDao.insertPurchaseItem(purchaseItem);
				} else {
					//修改
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setStatus("" + Constants.STATUS_NORMAL);
					etbPurchaseItemDao.updatePurchaseItem(purchaseItem);
				}
			}
		}
	}

	public EtbPurchaseDao getEtbPurchaseDao() {
		return etbPurchaseDao;
	}

	public void setEtbPurchaseDao(EtbPurchaseDao etbPurchaseDao) {
		this.etbPurchaseDao = etbPurchaseDao;
	}

	public EtbPurchaseItemDao getEtbPurchaseItemDao() {
		return etbPurchaseItemDao;
	}

	public void setEtbPurchaseItemDao(EtbPurchaseItemDao etbPurchaseItemDao) {
		this.etbPurchaseItemDao = etbPurchaseItemDao;
	}
}
