package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseExtDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesExtDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouserptDto;
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
	private WarehouserptDao warehouserptDao;
	private Dict01Dao dict01Dao;
	private FinanceDao financeDao;
	private UserDao userDao;
	
	/**
	 * 翻页查询满足条件的采购数据，然后等
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @return
	 */
	public List<PurchaseDto> queryAllPurchaseToExcel(String purchasedateLow,
			String purchasedateHigh, String status) {
		return purchaseDao.queryAllPurchaseToExcel(purchasedateLow, purchasedateHigh, status);
	}
	
	@Override
	public Page queryFinancePurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String status, Page page) {
		//查询总记录数
		int totalCount = purchaseDao.queryFinancePurchaseCountByPage(purchasedateLow, purchasedateHigh, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PurchaseDto> list = purchaseDao.queryFinancePurchaseByPage(purchasedateLow, purchasedateHigh, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(PurchaseDto purchase : list) {
				UserDto user = userDao.queryUserByID(purchase.getHandler());
				if(user != null) {
					purchase.setHandlername(user.getUsername());
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String status, Page page) {
		//查询总记录数
		int totalCount = purchaseDao.queryPurchaseCountByPage(purchasedateLow, purchasedateHigh, theme2, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PurchaseExtDto> list = purchaseDao.queryPurchaseByPage(purchasedateLow, purchasedateHigh, theme2, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(PurchaseDto purchase : list) {
				UserDto user = userDao.queryUserByID(purchase.getHandler());
				if(user != null) {
					purchase.setHandlername(user.getUsername());
				}
				
				//查询退换货数据
				String rptno = "";
				List<WarehouseDto> warehouseList = warehouseDao.queryWarehouseByParentid(purchase.getPurchaseno(), "1");
				if(warehouseList != null && warehouseList.size() > 0) {
					for(WarehouseDto warehouse : warehouseList) {
						//查询RPT记录
						WarehouserptDto rpt = warehouserptDao.queryWarehouserptByParentid(warehouse.getWarehouseno());
						if(rpt != null) {
							//RPT单号
							rptno += rpt.getWarehouseno() + "\r\n";
						}
					}
				}
				if(StringUtil.isNotBlank(rptno)) {
					purchase.setRptno("退换货入库单：\r\n" + rptno);
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryPurchaseExtByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String productid, String status, Page page) {
		//查询总记录数
		int totalCount = 0;
		purchaseDao.queryPurchaseCountByPage(purchasedateLow, purchasedateHigh, theme2, status);
		if (!productid.isEmpty() && !productid.equals("")){
			totalCount = purchaseDao.queryPurchaseExtCountByPage(purchasedateLow, purchasedateHigh, theme2, productid, status);
		} else {
			totalCount = purchaseDao.queryPurchaseCountByPage(purchasedateLow, purchasedateHigh, theme2, status);
		}			
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PurchaseExtDto> list = null;
		if (!productid.isEmpty() && !productid.equals("")){
			list = purchaseDao.queryPurchaseExtByPage(purchasedateLow, purchasedateHigh, theme2, productid, status,
					page.getStartIndex() * page.getPageSize(), page.getPageSize());
		} else {
			list = purchaseDao.queryPurchaseByPage(purchasedateLow, purchasedateHigh, theme2, status,
					page.getStartIndex() * page.getPageSize(), page.getPageSize());
		}

		if(list != null && list.size() > 0) {
			String allquantity = purchaseDao.queryPurchaseQuantity(purchasedateLow, purchasedateHigh, theme2, productid, status);
			list.get(0).setAllquantity(allquantity);
			for(PurchaseExtDto purchase : list) {
				UserDto user = userDao.queryUserByID(purchase.getHandler());
				if(user != null) {
					purchase.setHandlername(user.getUsername());
				}
				
				//查询退换货数据
				String rptno = "";
				List<WarehouseDto> warehouseList = warehouseDao.queryWarehouseByParentid(purchase.getPurchaseno(), "1");
				if(warehouseList != null && warehouseList.size() > 0) {
					for(WarehouseDto warehouse : warehouseList) {
						//查询RPT记录
						WarehouserptDto rpt = warehouserptDao.queryWarehouserptByParentid(warehouse.getWarehouseno());
						if(rpt != null) {
							//RPT单号
							rptno += rpt.getWarehouseno() + "\r\n";
						}
					}
				}
				if(StringUtil.isNotBlank(rptno)) {
					purchase.setRptno("退换货入库单：\r\n" + rptno);
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public PurchaseDto queryPurchaseByID(String id) {
		PurchaseDto purchase = purchaseDao.queryPurchaseByID(id);
		if(purchase != null) {
			UserDto user = userDao.queryUserByID(purchase.getHandler());
			if(user != null) {
				purchase.setHandlername(user.getUsername());
			}
		}
		return purchase;
	}

	public PurchaseDto queryPurchaseByTheme2(String theme2){
		PurchaseDto purchase = purchaseDao.queryPurchaseByTheme2(theme2);
		if(purchase != null) {
			UserDto user = userDao.queryUserByID(purchase.getHandler());
			if(user != null) {
				purchase.setHandlername(user.getUsername());
			}
		}
		return purchase;
	}

	
	@Override
	public void deletePurchase(String id, String userid) {
		PurchaseDto purchase = purchaseDao.queryPurchaseByID(id);
		if(purchase != null) {
			//物理删除item
			purchaseItemDao.deleteAllPurchaseItemByPurchaseno(purchase.getPurchaseno());
			//物理删除采购单
			purchaseDao.deletePurchase(id);
			//删除warehouse记录
			warehouseDao.deleteWarehouseByParentid(purchase.getPurchaseno(), "", "");
		}
		//逻辑删除
//		if(purchase != null) {
//			purchase.setStatus(Constants.STATUS_DEL);
//			purchase.setUpdateuid(userid);
//			purchaseDao.updatePurchase(purchase);
//		}
	}
	
	@Override
	public String addPurchase(PurchaseDto purchase,
			List<PurchaseItemDto> listPurchaseItem, String userid) {
		//采购订单号
		String purchaseorder = "";
		String code = "";
		Date date = new Date();
		SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");
		String year = sdfyear.format(date);
		
		List<Dict01Dto> listDict = dict01Dao.queryDict01ByFieldcode(Constants.DICT_PURCHASE_ORDER + year, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listDict != null && listDict.size() > 0) {
			Dict01Dto dict = listDict.get(0);
			code = dict.getCode();
			//番号+1
			dict.setCode("" + (Integer.valueOf(dict.getCode()) + 1));
			dict01Dao.updateDict01(dict);
		} else {
			//插入数据
			Dict01Dto dict = new Dict01Dto();
			dict.setFieldcode(Constants.DICT_PURCHASE_ORDER + year);
			dict.setFieldname("采购单番号" + year);
			//番号默认从1开始
			dict.setCode("1");
			code = "1";
			dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			dict.setMean("采购单番号" + year);
			dict.setNote("采购单番号" + year);
			dict.setStatus(Constants.STATUS_NORMAL);
			dict.setCreateuid("admin");
			dict.setUpdateuid("admin");
			dict01Dao.insertDict01(dict);
		}
		code = StringUtil.replenishStr(code, 6);
		purchaseorder = Constants.PURCHASE_ORDER_PRE + year + code;
		
		//生成采购单号
		String purchaseno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		purchase.setBelongto(belongto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		purchaseno = Constants.PURCHASE_NO_PRE + belongto + sdf.format(date) + uuid;
		purchase.setPurchaseno(purchaseno);
		
		//采购订单号
		purchase.setTheme2(purchaseorder);
		//status
		purchase.setStatus(Constants.PURCHASE_STATUS_NEW);
		//rank
		purchase.setRank(Constants.ROLE_RANK_OPERATOR);
		
		//经手人默认为自己
		purchase.setHandler(userid);
		//仓库名
		purchase.setWarehouse(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_WAREHOUSE_NAME));
		
		purchase.setUpdateuid(userid);
		purchase.setCreateuid(userid);
		purchaseDao.insertPurchase(purchase);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(PurchaseItemDto purchaseItem : listPurchaseItem) {
				//采购单号
				purchaseItem.setPurchaseno(purchaseno);
				purchaseItem.setHandler(userid);
				purchaseItem.setUpdateuid(userid);
				purchaseItem.setCreateuid(userid);
				//用户自己输入的订单号
				purchaseItem.setTheme2(purchase.getTheme2());
				purchaseItem.setStatus(Constants.STATUS_NORMAL);
				purchaseItem.setBelongto(belongto);
				purchaseItem.setRank(Constants.ROLE_RANK_OPERATOR);
				purchaseItem.setSupplierid(purchase.getSupplierid());
				purchaseItem.setPlandate(purchase.getPlandate());
				
				//判断预入库数量是否大于0，若大于0则产生库存记录
				if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity().floatValue() > 0) {
					//新增库存记录
					addWarehouse(purchase, purchaseItem);
					//已入库数=预入库数+之前已入库数
					if(purchaseItem.getInquantity() == null) {
						purchaseItem.setInquantity(purchaseItem.getBeforequantity());
					} else {
						purchaseItem.setInquantity(purchaseItem.getBeforequantity().add(purchaseItem.getInquantity()));
					}
				}
				//特别号
				purchaseItem.setRes09(purchaseItem.getRes09());
				//预入库数重置为0
				purchaseItem.setBeforequantity(new BigDecimal(0));
				//备注
				purchaseItem.setNote(purchase.getNote());
				purchaseItemDao.insertPurchaseItem(purchaseItem);
			}
		}
		return purchaseorder;
	}

	@Override
	public void insertPurchase(PurchaseDto purchase) {
		purchaseDao.insertPurchase(purchase);
	}
	
	@Override
	public void updatePurchase(PurchaseDto purchase, List<PurchaseItemDto> listPurchaseItem, String userid) {
		purchaseDao.updatePurchase(purchase);
		
		//在更新之前查询出所有货物ID
		List<PurchaseItemDto> oldItemList = purchaseItemDao.queryPurchaseItemByPurchaseno(purchase.getPurchaseno());
		//当前最新的item信息map，对应货物删除的情况
		Map<String, String> newItemMap = new HashMap<String, String>();
				
		//根据采购单号删除所有的货物数据，将item的状态更新为0
		purchaseItemDao.deletePurchaseItemByPurchaseno(purchase.getPurchaseno(), userid);
		//保存采购单对应的货物数据
		if(listPurchaseItem != null) {
			for(PurchaseItemDto purchaseItem : listPurchaseItem) {
				
				newItemMap.put("" + purchaseItem.getProductid(), "1");
				
				if(purchaseItem.getId() == null) {
					//新增
					//采购单号
					purchaseItem.setPurchaseno(purchase.getPurchaseno());
					//用户自己输入的订单号
					purchaseItem.setTheme2(purchase.getTheme2());
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setCreateuid(userid);
					purchaseItem.setStatus(Constants.STATUS_NORMAL);
					purchaseItem.setBelongto(purchase.getBelongto());
					purchaseItem.setSupplierid(purchase.getSupplierid());
					
					//判断预入库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity().floatValue() != 0) {
					//if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity() > 0) {
						//新增库存记录
						addWarehouse(purchase, purchaseItem);
						//已入库数=预入库数+之前已入库数
						if(purchaseItem.getInquantity() == null) {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity());
						} else {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity().add(purchaseItem.getInquantity()));
						}
					}
					//特别号
					purchaseItem.setRes09(purchaseItem.getRes09());

					//预入库数重置为0
					purchaseItem.setBeforequantity(new BigDecimal(0));
					//备注
					purchaseItem.setNote(purchase.getNote());
					purchaseItemDao.insertPurchaseItem(purchaseItem);
				} else {
					//修改
					purchaseItem.setUpdateuid(userid);
					purchaseItem.setStatus(Constants.STATUS_NORMAL);
					
					//判断预入库数量是!=0，若!=0则生产库存记录，这里不判断是否大于0的情况，考虑可能会增加负的库存记录
					if(purchaseItem.getBeforequantity() != null && purchaseItem.getBeforequantity().floatValue() != 0) {
						//新增库存记录
						addWarehouse(purchase, purchaseItem);
						//已入库数=预入库数+之前已入库数
						if(purchaseItem.getInquantity() == null) {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity());
						} else {
							purchaseItem.setInquantity(purchaseItem.getBeforequantity().add(purchaseItem.getInquantity()));
						}
					}
					//特别号
					purchaseItem.setRes09(purchaseItem.getRes09());

					//预入库数重置为0
					purchaseItem.setBeforequantity(new BigDecimal(0));
					//备注
					purchaseItem.setNote(purchase.getNote());
					purchaseItemDao.updatePurchaseItem(purchaseItem);
				}
			}
		}
		
		//对应货物删除时，warehouse里的垃圾数据
		if(oldItemList != null && oldItemList.size() > 0) {
			for(PurchaseItemDto item : oldItemList) {
				if(newItemMap.get("" + item.getProductid()) == null) {
					//说明该货物已经被删除，这里需要删除掉warehouse状态=10的垃圾数据
					warehouseDao.deleteWarehouseByParentid(purchase.getPurchaseno(), "" + item.getProductid(), "" + Constants.PURCHASE_STATUS_NEW);
				}
			}
		}
		
		//删除status=0的数据
		purchaseItemDao.deleteNoUsePurchaseItemByPurchaseno(purchase.getPurchaseno());
		
		//当前采购单状态若!=已入库时
		if(purchase.getStatus() != Constants.PURCHASE_STATUS_WAREHOUSE_OK) {
			//判断采购单是否是所有产品都均已入库
			List<PurchaseItemDto> purchaseItemList = purchaseItemDao.queryPurchaseItemByPurchaseno(purchase.getPurchaseno());
			if(purchaseItemList != null && purchaseItemList.size() > 0) {
				boolean b = true;
				//判断当前的采购单对应的货物是否都已入库：采购数量=入库数量
				for(PurchaseItemDto item : purchaseItemList) {
					if(item.getQuantity() != null && item.getQuantity().floatValue() > item.getInquantity().floatValue()) {
						b = false;
						break;
					}
				}
				if(b) {
					//判断所有的库存记录均为已确认
					List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByParentid(purchase.getPurchaseno(), "");
					for(WarehouseDto warehouseDto : listWarehouse) {
						if(warehouseDto.getStatus() <= Constants.WAREHOUSE_STATUS_NEW) {
							b = false;
							break;
						}
					}
				}
				//以上2个条件均满足，则更新采购单状态
				if(b) {
					//需要更新采购单状态=入库确认
					purchase.setStatus(Constants.PURCHASE_STATUS_WAREHOUSE_OK);
					purchase.setUpdateuid(userid);
					purchaseDao.updatePurchase(purchase);
				}
			}
		}
	}
	
	@Override
	public void updateFinancePurchase(String id, String userid, String status) {
		PurchaseDto purchase = purchaseDao.queryPurchaseByID(id);
		if(purchase != null) {
			purchase.setUpdateuid(userid);
			//更新采购单状态
			purchase.setStatus(Integer.valueOf(status));
			purchaseDao.updatePurchase(purchase);
			
			//更新财务数据状态
			FinanceDto finance = financeDao.queryFinanceByInvoiceid(purchase.getPurchaseno(), "" + Constants.FINANCE_TYPE_PURCHASE);
			if(finance != null) {
				if(status.equals("" + Constants.FINANCE_STATUS_PAY_INVOICE)) {
					//开票日期=当天
					finance.setReceiptdate(DateUtil.dateToShortStr(new Date()));
				}
				//确认者=当前用户
				finance.setApproverid(userid);
				finance.setStatus(Integer.valueOf(status));
				financeDao.updateFinance(finance);
			}
		}
	}
	
	@Override
	public void updatePurchase(PurchaseDto purchase) {
		purchaseDao.updatePurchase(purchase);
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
		//用户自己输入的订单号
		warehouse.setTheme2(purchase.getTheme2());
		//库存类型=入库单
		warehouse.setWarehousetype(Constants.WAREHOUSE_TYPE_IN);
		//仓库
		warehouse.setWarehousename(purchase.getWarehouse());
		//预入库时间
		warehouse.setPlandate(purchase.getPlandate());
		
		//库存单号
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		String warehouseno = Constants.WAREHOUSE_NO_PRE + belongto + sdf.format(date) + uuid;
		warehouse.setWarehouseno(warehouseno);
		
		//支付方式
		warehouse.setRes01(purchase.getRes01());
		
		warehouse.setBelongto(belongto);
		//主题
		warehouse.setTheme1(purchase.getTheme1());
		//产品ID
		warehouse.setProductid("" + purchaseItem.getProductid());
		//入库数量=预入库数
		warehouse.setQuantity(purchaseItem.getBeforequantity());
		
		//判断数量是否是负数
		if(purchaseItem.getBeforequantity().floatValue() < 0) {
			//这里做个标记，1为退换货
			warehouse.setRes05("1");
		}
				
		//单价
		warehouse.setUnitprice(purchaseItem.getUnitprice());
		//含税单价
		warehouse.setRes02("" + purchaseItem.getTaxunitprice().setScale(6, BigDecimal.ROUND_HALF_UP));
		//产地
		warehouse.setRes03("" + purchaseItem.getMakearea());
		
		//入库金额=入库数量*单价
		BigDecimal amount = purchaseItem.getUnitprice().multiply(purchaseItem.getBeforequantity());
		//入库金额含税=入库金额*税率
		BigDecimal taxamount = new BigDecimal(0);
		
		//入库金额（含税）
		if(purchaseItem.getRemainquantity().floatValue() == 0) {
			//若当前ITEM为最后一部分预入库的ITEM，则需要通过含税总金额-之前入库的金额，这里防止含税总金额用户自己手动修改，导致金额合计时不正确
			//之前的预入库数量
			BigDecimal oldQuantity = purchaseItem.getQuantity().subtract(purchaseItem.getBeforequantity());
			//之前的预入库金额（含税）
			BigDecimal oldAmount = purchaseItem.getTaxunitprice().multiply(oldQuantity);
			//当前库存记录的含税金额=含税总金额-之前的预入库金额（含税）
			taxamount = purchaseItem.getTaxamount().subtract(oldAmount);
		} else {
			//不是最后一部分预入库的ITEM
			//入入库金额（含税）=入库金额*（1+税率）
//			List<Dict01Dto> listRate = dict01Dao.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
//			//默认为0
//			BigDecimal rate = new BigDecimal(0);
//			if(listRate != null && listRate.size() > 0) {
//				rate = new BigDecimal(listRate.get(0).getCode());
//				rate = rate.add(new BigDecimal(1));
//				taxamount = amount.multiply(rate);
//				taxamount = taxamount.setScale(2, BigDecimal.ROUND_HALF_UP);
//			}
			taxamount = purchaseItem.getBeforequantity().multiply(purchaseItem.getTaxunitprice());
			taxamount = taxamount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		warehouse.setAmount(amount);
		//入出库金额（含税）
		warehouse.setTaxamount(taxamount);
		//入库日期=当天
		warehouse.setWarehousedate(DateUtil.dateToShortStr(new Date()));
		//供应商ID
		warehouse.setSupplierid(purchase.getSupplierid());
		//收货人
		warehouse.setHandler(purchase.getHandler());
		
		//特别号
		warehouse.setRes09(purchaseItem.getRes09());

		warehouse.setRank(Constants.ROLE_RANK_OPERATOR);
		//入库单数据状态=新增
		warehouse.setStatus(Constants.WAREHOUSE_STATUS_NEW);
		
		warehouse.setUpdateuid(purchase.getCreateuid());
		warehouse.setCreateuid(purchase.getCreateuid());
		
		warehouseDao.insertWarehouse(warehouse);
	}
	
	@Override
	public void finishPurchase(String id, String userid) {
		PurchaseDto purchase = purchaseDao.queryPurchaseByID(id);
		if(purchase != null) {
			//状态=终了（已收货）
			purchase.setStatus(Constants.PURCHASE_STATUS_WAREHOUSE_OK);
			purchase.setUpdateuid(userid);
			purchaseDao.updatePurchase(purchase);
		}
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

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public WarehouserptDao getWarehouserptDao() {
		return warehouserptDao;
	}

	public void setWarehouserptDao(WarehouserptDao warehouserptDao) {
		this.warehouserptDao = warehouserptDao;
	}
}
