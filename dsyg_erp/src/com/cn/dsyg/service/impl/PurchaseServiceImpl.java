package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.service.PurchaseService;

/**
 * @name PurchaseServiceImpl.java
 * @author Frank
 * @time 2015-5-9下午10:26:35
 * @version 1.0
 */
public class PurchaseServiceImpl implements PurchaseService {
	
	private PurchaseDao purchaseDao;
	private PurchaseItemDao purchaseItemDao;
	private WarehouseDao warehouseDao;

	@Override
	public Page queryPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, Page page) {
		//查询总记录数
		int totalCount = purchaseDao.queryPurchaseCountByPage(purchasedateLow, purchasedateHigh);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PurchaseDto> list = purchaseDao.queryPurchaseByPage(purchasedateLow, purchasedateHigh,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public PurchaseDto queryPurchaseByID(String id) {
		return purchaseDao.queryPurchaseByID(id);
	}

	@Override
	public void deletePurchase(String id) {
		purchaseDao.deletePurchase(id);
	}
	
	@Override
	public String addPurchase(PurchaseDto purchase,
			List<PurchaseItemDto> listPurchaseItem, String userid) {
		//生成采购单号
		String purchaseno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		purchase.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		purchaseno = "purchase" + belongto + sdf.format(date) + uuid;
		purchase.setPurchaseno(purchaseno);
		
		//rank
		purchase.setRank(Constants.ROLE_RANK_OPERATOR);
		
		purchase.setUpdateuid(userid);
		purchase.setCreateuid(userid);
		purchaseDao.insertPurchase(purchase);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(PurchaseItemDto purchaseItem : listPurchaseItem) {
				//采购单号
				purchaseItem.setPurchaseno(purchaseno);
				purchaseItem.setUpdateuid(userid);
				purchaseItem.setCreateuid(userid);
				purchaseItem.setStatus(Constants.STATUS_NORMAL);
				purchaseItem.setBelongto(belongto);
				purchaseItem.setRank(Constants.ROLE_RANK_OPERATOR);
				purchaseItem.setSupplierid(purchase.getSupplierid());
				
				//判断预入库数量是否大于0，若大于0则生产库存记录
				if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity() > 0) {
					//新增库存记录
					addWarehouse(purchase, purchaseItem);
					//已入库数=预入库数+之前已入库数
					if(purchaseItem.getInquantity() == null) {
						purchaseItem.setInquantity(purchaseItem.getBeforequantity());
					} else {
						purchaseItem.setInquantity(purchaseItem.getBeforequantity() + purchaseItem.getInquantity());
					}
				}
				//预入库数重置为0
				purchaseItem.setBeforequantity(0);
				purchaseItemDao.insertPurchaseItem(purchaseItem);
			}
		}
		return purchaseno;
	}

	@Override
	public void insertPurchase(PurchaseDto purchase) {
		purchaseDao.insertPurchase(purchase);
	}

	@Override
	public void updatePurchase(PurchaseDto purchase, List<PurchaseItemDto> listPurchaseItem, String userid) {
		purchaseDao.updatePurchase(purchase);
		//根据采购单号删除所有的货物数据
		purchaseItemDao.deletePurchaseItemByPurchaseno(purchase.getPurchaseno(), userid);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(PurchaseItemDto purchaseItem : listPurchaseItem) {
				if(purchaseItem.getId() == null) {
					//新增
					//采购单号
					purchaseItem.setPurchaseno(purchase.getPurchaseno());
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setCreateuid(userid);
					purchaseItem.setStatus(Constants.STATUS_NORMAL);
					purchaseItem.setBelongto(purchase.getBelongto());
					purchaseItem.setSupplierid(purchase.getSupplierid());
					
					//判断预入库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity() != 0) {
					//if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity() > 0) {
						//新增库存记录
						addWarehouse(purchase, purchaseItem);
						//已入库数=预入库数+之前已入库数
						if(purchaseItem.getInquantity() == null) {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity());
						} else {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity() + purchaseItem.getInquantity());
						}
					}
					
					//预入库数重置为0
					purchaseItem.setBeforequantity(0);
					purchaseItemDao.insertPurchaseItem(purchaseItem);
				} else {
					//修改
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setStatus(Constants.STATUS_NORMAL);
					
					//判断预入库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity() != 0) {
						//新增库存记录
						addWarehouse(purchase, purchaseItem);
						//已入库数=预入库数+之前已入库数
						if(purchaseItem.getInquantity() == null) {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity());
						} else {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity() + purchaseItem.getInquantity());
						}
					}
					
					//预入库数重置为0
					purchaseItem.setBeforequantity(0);
					purchaseItemDao.updatePurchaseItem(purchaseItem);
				}
			}
		}
	}
	
	/**
	 * 新增库存记录
	 * @param purchase
	 * @param purchaseItem
	 */
	private void addWarehouse(PurchaseDto purchase, PurchaseItemDto purchaseItem) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		
		WarehouseDto warehouse = new WarehouseDto();
		//数据来源单号=采购单
		warehouse.setParentid(purchase.getPurchaseno());
		//库存类型=入库单
		warehouse.setWarehousetype(Constants.WAREHOUSE_TYPE_IN);
		
		//入库单号
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		String warehouseno = "warehouse" + belongto + sdf.format(date) + uuid;
		warehouse.setWarehouseno(warehouseno);
		
		warehouse.setBelongto(belongto);
		//主题
		warehouse.setTheme1(purchase.getTheme1());
		//产品ID
		warehouse.setProductid("" + purchaseItem.getProductid());
		//入库数量=预入库数
		warehouse.setQuantity(purchaseItem.getBeforequantity());
		//单价
		warehouse.setUnitprice(purchaseItem.getUnitprice());
		//入库金额
		warehouse.setAmount(purchaseItem.getAmount());
		//入出库金额（含税）
		warehouse.setTaxamount(purchaseItem.getTaxamount());
		//入库日期=当天
		warehouse.setWarehousedate(date);
		//供应商ID
		warehouse.setSupplierid(purchase.getSupplierid());
		//收货人
		warehouse.setHandler(purchase.getHandler());
		warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
		//入库单数据状态=新增
		warehouse.setStatus(Constants.WAREHOUSE_STATUS_NEW);
		
		warehouse.setUpdateuid(purchase.getCreateuid());
		warehouse.setCreateuid(purchase.getCreateuid());
		
		warehouseDao.insertWarehouse(warehouse);
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public PurchaseItemDao getPurchaseItemDao() {
		return purchaseItemDao;
	}

	public void setPurchaseItemDao(PurchaseItemDao purchaseItemDao) {
		this.purchaseItemDao = purchaseItemDao;
	}

	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}
}
