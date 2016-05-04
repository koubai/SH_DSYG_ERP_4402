package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dto.Dict01Dto;
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
	private Dict01Dao dict01Dao;

	@Override
	public List<SalesItemDto> querySalesItemBySalesno(String salesno) {
		List<SalesItemDto> list = salesItemDao.querySalesItemBySalesno(salesno);
		if(list != null && list.size() > 0) {
			for(SalesItemDto item : list) {
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
	
	@Override
	public List<SalesItemDto> querySalesItemByProductidForCompare(
			String productid, String customerid) {
		List<SalesItemDto> salesList = salesItemDao.querySalesItemByProductidForCompare(productid, customerid);
		List<SalesItemDto> salesListRet = new ArrayList<SalesItemDto>();
		if(salesList != null && salesList.size() > 0){
			for(SalesItemDto salesItem : salesList){
				if(salesItem.getRemainquantity().compareTo(BigDecimal.ZERO) == 1){
					salesListRet.add(salesItem);
				}
			}
		}
		return salesListRet;
	}

	@Override
	public Page queryRemainSalesByPage(String customername, Page page) {
		customername = StringUtil.replaceDatabaseKeyword_mysql(customername);
		
		//查询总记录数
		int totalCount = salesItemDao.queryRemainSalesCountByPage(customername);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SalesItemDto> list = salesItemDao.queryRemainSalesByPage(customername,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	public SalesItemDao getSalesItemDao() {
		return salesItemDao;
	}

	public void setSalesItemDao(SalesItemDao salesItemDao) {
		this.salesItemDao = salesItemDao;
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
