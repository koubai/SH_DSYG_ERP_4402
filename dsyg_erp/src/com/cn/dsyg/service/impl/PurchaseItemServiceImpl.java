package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.service.PurchaseItemService;

/**
 * @name PurchaseItemServiceImpl.java
 * @author Frank
 * @time 2015-5-17下午10:44:24
 * @version 1.0
 */
public class PurchaseItemServiceImpl implements PurchaseItemService {
	
	private PurchaseItemDao purchaseItemDao;
	private Dict01Dao dict01Dao;
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByPurchaseno(
			String purchaseno) {
		List<PurchaseItemDto> list = purchaseItemDao.queryPurchaseItemByPurchaseno(purchaseno);
		if(list != null && list.size() > 0) {
			for(PurchaseItemDto item : list) {
				BigDecimal rate = new BigDecimal(1);
				//税率
				List<Dict01Dto> listRate = dict01Dao.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
				if(listRate != null && listRate.size() > 0) {
					rate = rate.add(new BigDecimal(listRate.get(0).getCode()));
				}
				if(item.getUnitprice() != null && item.getTaxunitprice() == null) {
					//计算税后价格，保留6位有效数字
					item.setTaxunitprice(item.getUnitprice().multiply(rate).setScale(6, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return list;
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
	
	@Override
	public List<PurchaseItemDto> queryPurchaseItemByProductidForCompare(
			String productid, String supplierid) {
		List<PurchaseItemDto> purchaseList = purchaseItemDao.queryPurchaseItemByProductidForCompare(productid, supplierid);
		List<PurchaseItemDto> purchaseListRet = new ArrayList<PurchaseItemDto>();
		if(purchaseList != null && purchaseList.size() > 0){
			for(PurchaseItemDto purchaseItem : purchaseList){
				if(purchaseItem.getRemainquantity().compareTo(BigDecimal.ZERO) == 1){
					purchaseListRet.add(purchaseItem);
				}
			}
		}
		return purchaseListRet;
	}

	@Override
	public Page queryRemainPurchaseByPage(String suppliername, Page page) {
		suppliername = StringUtil.replaceDatabaseKeyword_mysql(suppliername);
		
		//查询总记录数
		int totalCount = purchaseItemDao.queryRemainPurchaseCountByPage(suppliername);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PurchaseItemDto> list = purchaseItemDao.queryRemainPurchaseByPage(suppliername,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
