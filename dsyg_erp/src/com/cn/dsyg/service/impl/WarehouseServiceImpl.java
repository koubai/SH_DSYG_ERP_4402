package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.FinanceDao;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.dto.WarehouseProductDto;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.WarehouseService;

/**
 * @name WarehouseServiceImpl.java
 * @author Frank
 * @time 2015-6-7下午9:05:51
 * @version 1.0
 */
public class WarehouseServiceImpl implements WarehouseService {
	
	private PurchaseDao purchaseDao;
	private PurchaseItemDao purchaseItemDao;
	private SalesDao salesDao;
	private SalesItemDao salesItemDao;
	private WarehouseDao warehouseDao;
	private WarehouserptDao warehouserptDao;
	private SupplierDao supplierDao;
	private FinanceDao financeDao;
	
	@Override
	public Page queryWarehouseProductByPage(
			String parentid, String warehousetype, String warehouseno,
			String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseProductCountByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseProductDto> list = warehouseDao.queryWarehouseProductByPage(parentid, warehousetype,
				warehouseno, theme1, productid, tradename, typeno, color, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}
	
	@Override
	public void warehouseInOk(String ids, String userid) throws RuntimeException {
		if(StringUtil.isNotBlank(ids)) {
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			
			String[] idList = ids.split(",");
			WarehouseDto warehouse = null;
			
			//验证是否是同一个仓库，相同的预入库时间
			String warehousename = "";
			String plandate = "";
			String suppid = "";
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						if(StringUtil.isBlank(warehousename)) {
							warehousename = warehouse.getWarehousename();
						}
						if(StringUtil.isBlank(plandate)) {
							plandate = warehouse.getPlandate();
						}
						if(StringUtil.isBlank(suppid)) {
							suppid = "" + warehouse.getSupplierid();
						}
						
						if(!warehousename.equals(warehouse.getWarehousename())) {
							throw new RuntimeException("不同仓库记录不能合并成一个入库单！");
						}
						if(!suppid.equals("" + warehouse.getSupplierid())) {
							throw new RuntimeException("不同供应商的记录不能合并成一个入库单！");
						}
//						if(!plandate.equals(warehouse.getPlandate())) {
//							throw new RuntimeException("不同预入库时间记录不能合并成一个入库单！");
//						}
					}
				}
			}
			
			//供应商ID
			String supplierid = "";
			//产品合集
			Map<String, Integer> quantityMap = new HashMap<String, Integer>();
			Map<String, BigDecimal> amountMap = new HashMap<String, BigDecimal>();
			//入库单号集合
			String warehousenos = "";
			int count = 0;
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						supplierid = "" + warehouse.getSupplierid();
						
						if(quantityMap.get(warehouse.getProductid()) != null) {
							quantityMap.put(warehouse.getProductid(), quantityMap.get(warehouse.getProductid()) + warehouse.getQuantity());
							amountMap.put(warehouse.getProductid(), amountMap.get(warehouse.getProductid()).add(warehouse.getTaxamount()));
						} else {
							quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							amountMap.put(warehouse.getProductid(), warehouse.getTaxamount());
						}
						
						warehouse.setUpdateuid(userid);
						warehouse.setApproverid(userid);
						warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
						
						//计算当前集集的库存数量
						count += warehouse.getQuantity();
						warehousenos += warehouse.getWarehouseno() + ",";
						
						//计算含税金额
						totaltaxamount = totaltaxamount.add(warehouse.getTaxamount());
						
						warehouseDao.updateWarehouse(warehouse);
						
						List<PurchaseItemDto> purchaseItemList = purchaseItemDao.queryPurchaseItemByPurchaseno(warehouse.getParentid());
						if(purchaseItemList != null && purchaseItemList.size() > 0) {
							boolean b = true;
							//判断当前的采购单对应的货物是否都已入库：采购数量=入库数量
							for(PurchaseItemDto item : purchaseItemList) {
								if(item.getQuantity() != null && item.getQuantity() > item.getInquantity()) {
									b = false;
									break;
								}
							}
							if(b) {
								//判断所有的库存记录均为已确认
								List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByParentid(warehouse.getParentid());
								for(WarehouseDto warehouseDto : listWarehouse) {
									if(warehouseDto.getStatus() <= Constants.WAREHOUSE_STATUS_NEW) {
										b = false;
										break;
									}
								}
							}
							//以上2个条件均满足，则更新采购单状态
							if(b) {
								PurchaseDto purchaseDto = purchaseDao.queryPurchaseByNo(warehouse.getParentid());
								//需要更新采购单状态=付款申请
								purchaseDto.setStatus(Constants.PURCHASE_STATUS_PAY_APPLY);
								purchaseDto.setUpdateuid(userid);
								purchaseDao.updatePurchase(purchaseDto);
								
								//新增一条财务记录
								FinanceDto finance = new FinanceDto();
								//类型=采购单
								finance.setFinancetype(Constants.FINANCE_TYPE_PURCHASE);
								//采购单（付款）
								finance.setMode("2");
								finance.setBelongto(belongto);
								//单据号=采购单号
								finance.setInvoiceid(purchaseDto.getPurchaseno());
								//发票号
								String receiptid = Constants.FINANCE_NO_PRE + belongto + sdf.format(date);
								finance.setReceiptid(receiptid);
								//开票日期
								//finance.setReceiptdate(receiptdate);
								//结算日期=当天
								finance.setAccountdate(DateUtil.dateToShortStr(date));
								//金额=采购金额含税
								finance.setAmount(purchaseDto.getTaxamount());
								//负责人
								finance.setHandler(purchaseDto.getHandler());
								//供应商信息
								finance.setCustomerid(purchaseDto.getSupplierid());
								finance.setCustomername(purchaseDto.getSuppliername());
								finance.setCustomertel(purchaseDto.getSuppliertel());
								finance.setCustomermanager(purchaseDto.getSuppliermanager());
								finance.setCustomeraddress(purchaseDto.getSupplieraddr());
								finance.setCustomermail(purchaseDto.getSuppliermail());
								finance.setRank(Constants.ROLE_RANK_OPERATOR);
								//状态=付款申请
								finance.setStatus(Constants.FINANCE_STATUS_PAY_APPLY);
								finance.setCreateuid(userid);
								finance.setUpdateuid(userid);
								financeDao.insertFinance(finance);
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=入库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_IN);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//入库单号
			String warehouseno = Constants.WAREHOUSERPT_IN_NO_PRE + belongto + sdf.format(date);
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			warehouserpt.setWarehousename(warehousename);
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
			String productinfo = "";


			Set<?> key = quantityMap.keySet();
			for (Iterator<?> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				productinfo += k + "," + quantityMap.get(k) + "," + amountMap.get(k) + "#";
			}
			
			warehouserpt.setProductinfo(productinfo);
			//入库单RPT日期
			warehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
			//入库数量
			warehouserpt.setTotalnum(count);
			//含税金额
			warehouserpt.setTotaltaxamount(totaltaxamount);
			//收货人
			warehouserpt.setHandler("");
			
			//查询供应商信息
			SupplierDto supplier = supplierDao.queryAllSupplierByID(supplierid);
			//获得采购单的供应商
			warehouserpt.setSupplierid(supplierid);
			if(supplier != null) {
				warehouserpt.setSuppliername(supplier.getSuppliername());
				warehouserpt.setSupplieraddress(supplier.getSupplieraddress1());
				warehouserpt.setSuppliermail(supplier.getSuppliermail1());
				warehouserpt.setSuppliermanager(supplier.getSuppliermanager1());
				warehouserpt.setSuppliertel(supplier.getSuppliertel1());
				warehouserpt.setSupplierfax(supplier.getSupplierfax1());
			}
			//快递公司ID==============================这里不做填充，等发货单时填充
			
			warehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
			warehouserpt.setStatus(Constants.STATUS_NORMAL);
			//warehouserpt.setProductid(Long.valueOf(productid));
			//入库单单号集合
			warehouserpt.setParentid(warehousenos);
			warehouserpt.setCreateuid(userid);
			warehouserpt.setUpdateuid(userid);
			
			warehouserptDao.insertWarehouserpt(warehouserpt);
		}
	}
	
	@Override
	public void warehouseOutOk(String ids, String userid) throws RuntimeException {
		if(StringUtil.isNotBlank(ids)) {
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			
			String[] idList = ids.split(",");
			WarehouseDto warehouse = null;
			
			//验证是否是同一个仓库，相同的预出库时间
			String warehousename = "";
			String supplierid = "";
			String plandate = "";
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						if(StringUtil.isBlank(warehousename)) {
							warehousename = warehouse.getWarehousename();
						}
						if(StringUtil.isBlank(plandate)) {
							plandate = warehouse.getPlandate();
						}
						if(StringUtil.isBlank(supplierid)) {
							supplierid = "" + warehouse.getSupplierid();
						}
						
						if(!warehousename.equals(warehouse.getWarehousename())) {
							throw new RuntimeException("不同仓库记录不能合并成一个出库单！");
						}
						//对于出库单，这里记录的是客户ID
						if(!supplierid.equals("" + warehouse.getSupplierid())) {
							throw new RuntimeException("不同客户的记录不能合并成一个出库单！");
						}
//						if(!plandate.equals(warehouse.getPlandate())) {
//							throw new RuntimeException("不同预出库时间记录不能合并成一个出库单！");
//						}
					}
				}
			}
			
			//客户ID
			String customerid = "";
			//入库单号集合
			String warehousenos = "";
			//产品合集
			Map<String, Integer> quantityMap = new HashMap<String, Integer>();
			Map<String, BigDecimal> amountMap = new HashMap<String, BigDecimal>();
			//int count = 0;
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						customerid = "" + warehouse.getSupplierid();
						
						if(quantityMap.get(warehouse.getProductid()) != null) {
							//发货单数量是负数，所以需要变成正的
							if(warehouse.getQuantity() < 0) {
								quantityMap.put(warehouse.getProductid(), quantityMap.get(warehouse.getProductid()) + warehouse.getQuantity() * -1);
							} else {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							}
							amountMap.put(warehouse.getProductid(), amountMap.get(warehouse.getProductid()).add(warehouse.getTaxamount()));
						} else {
							//发货单数量是负数，所以需要变成正的
							if(warehouse.getQuantity() < 0) {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity() * -1);
							} else {
								quantityMap.put(warehouse.getProductid(), warehouse.getQuantity());
							}
							amountMap.put(warehouse.getProductid(), warehouse.getTaxamount());
						}
						
						warehouse.setUpdateuid(userid);
						warehouse.setApproverid(userid);
						warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
						
						//计算当前集集的库存数量
						//count += warehouse.getQuantity();
						warehousenos += warehouse.getWarehouseno() + ",";
						
						//计算含税金额
						totaltaxamount = totaltaxamount.add(warehouse.getTaxamount());
						
						warehouseDao.updateWarehouse(warehouse);
						
						List<SalesItemDto> itemList = salesItemDao.querySalesItemBySalesno(warehouse.getParentid());
						if(itemList != null && itemList.size() > 0) {
							boolean b = true;
							//判断当前的采购单对应的货物是否都已入库：采购数量=入库数量
							for(SalesItemDto item : itemList) {
								if(item.getQuantity() != null && item.getQuantity() > item.getOutquantity()) {
									b = false;
									break;
								}
							}
							if(b) {
								//判断所有的库存记录均为已确认
								List<WarehouseDto> listWarehouse = warehouseDao.queryWarehouseByParentid(warehouse.getParentid());
								for(WarehouseDto warehouseDto : listWarehouse) {
									if(warehouseDto.getStatus() <= Constants.WAREHOUSE_STATUS_NEW) {
										b = false;
										break;
									}
								}
							}
							//以上2个条件均满足，则更新采购单状态
							if(b) {
								//需要更新采购单状态=付款申请
								SalesDto salesDto = salesDao.querySalesByNo(warehouse.getParentid());
								salesDto.setStatus(Constants.SALES_STATUS_BILL_APPLY);
								salesDto.setUpdateuid(userid);
								salesDao.updateSales(salesDto);
								
								//新增一条财务记录
								FinanceDto finance = new FinanceDto();
								//类型=采购单
								finance.setFinancetype(Constants.FINANCE_TYPE_SALES);
								//采购单（付款）
								finance.setMode("2");
								finance.setBelongto(belongto);
								//单据号=销售单号
								finance.setInvoiceid(salesDto.getSalesno());
								//发票号
								String receiptid = Constants.FINANCE_NO_PRE + belongto + sdf.format(date);
								finance.setReceiptid(receiptid);
								//开票日期
								//finance.setReceiptdate(receiptdate);
								//结算日期=当天
								finance.setAccountdate(DateUtil.dateToShortStr(date));
								//金额=销售金额含税
								finance.setAmount(salesDto.getTaxamount());
								//负责人
								finance.setHandler(salesDto.getHandler());
								//供应商信息
								finance.setCustomerid(salesDto.getCustomerid());
								finance.setCustomername(salesDto.getCustomername());
								finance.setCustomertel(salesDto.getCustomertel());
								finance.setCustomermanager(salesDto.getCustomermanager());
								finance.setCustomeraddress(salesDto.getCustomeraddress());
								finance.setCustomermail(salesDto.getCustomermail());
								finance.setRank(Constants.ROLE_RANK_OPERATOR);
								//状态=开票申请
								finance.setStatus(Constants.FINANCE_STATUS_PAY_APPLY);
								finance.setCreateuid(userid);
								finance.setUpdateuid(userid);
								financeDao.insertFinance(finance);
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=出库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_OUT);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//入库单号
			String warehouseno = Constants.WAREHOUSERPT_IN_NO_PRE + belongto + sdf.format(date);
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			warehouserpt.setWarehousename(warehousename);
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
			String productinfo = "";
			Set<?> key = quantityMap.keySet();
			for (Iterator<?> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				productinfo += k + "," + quantityMap.get(k) + "," + amountMap.get(k) + "#";
			}
			warehouserpt.setProductinfo(productinfo);
			//入库单RPT日期
			warehouserpt.setWarehousedate(DateUtil.dateToShortStr(date));
			
			//入库数量，由于会对应多个货物，故这里的数量不需要了。
//			if(count < 0) {
//				count = -1 * count;
//			}
//			warehouserpt.setTotalnum(count);
			
			//含税金额
			warehouserpt.setTotaltaxamount(totaltaxamount);
			//收货人
			warehouserpt.setHandler("");
			
			//查询客户信息
			//SupplierDto supplier = supplierDao.queryAllSupplierByID(supplierid);
			//获得销售单的客户信息
			warehouserpt.setSupplierid(customerid);
//			if(supplier != null) {
//				warehouserpt.setSuppliername(supplier.getSuppliername());
//				warehouserpt.setSupplieraddress(supplier.getSupplieraddress1());
//				warehouserpt.setSuppliermail(supplier.getSuppliermail1());
//				warehouserpt.setSuppliermanager(supplier.getSuppliermanager1());
//				warehouserpt.setSuppliertel(supplier.getSuppliertel1());
//				warehouserpt.setSupplierfax(supplier.getSupplierfax1());
//			}
			//快递公司ID==============================这里不做填充，等发货单时填充
			
			warehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
			warehouserpt.setStatus(Constants.STATUS_NORMAL);
			//入库单单号集合
			warehouserpt.setParentid(warehousenos);
			warehouserpt.setCreateuid(userid);
			warehouserpt.setUpdateuid(userid);
			
			warehouserptDao.insertWarehouserpt(warehouserpt);
		}
	}
	
	@Override
	public Page queryWarehouseOkByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page) {
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		typeno = StringUtil.replaceDatabaseKeyword_mysql(typeno);
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseOkCountByPage(warehouseType,
				theme, tradename, typeno, color, warehousename, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseOkDto> list = warehouseDao.queryWarehouseOkByPage(warehouseType, theme, tradename,
				typeno, color, warehousename, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid,
			String productid, String status, String warehousename, Page page) {
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseCountByPage(parentid, warehousetype, warehouseno,
				theme1, supplierid, productid, status, warehousename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDto> list = warehouseDao.queryWarehouseByPage(parentid, warehousetype,
				warehouseno, theme1, supplierid, productid, status, warehousename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public WarehouseDto queryWarehouseByID(String id) {
		return warehouseDao.queryWarehouseByID(id);
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByIds(String ids) {
		return warehouseDao.queryWarehouseByIds(ids);
	}

	@Override
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid,
			String productid) {
		return warehouseDao.queryWarehouseByParentidAndProductid(parentid, productid);
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByParentid(String parentid) {
		return warehouseDao.queryWarehouseByParentid(parentid);
	}

	@Override
	public WarehouseDto queryWarehouseByWarehouseno(String warehouseno) {
		return warehouseDao.queryWarehouseByWarehouseno(warehouseno);
	}

	@Override
	public void insertWarehouse(WarehouseDto warehouse) {
		warehouseDao.insertWarehouse(warehouse);
	}

	@Override
	public void updateWarehouse(WarehouseDto warehouse) {
		warehouseDao.updateWarehouse(warehouse);
	}

	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	public WarehouserptDao getWarehouserptDao() {
		return warehouserptDao;
	}

	public void setWarehouserptDao(WarehouserptDao warehouserptDao) {
		this.warehouserptDao = warehouserptDao;
	}

	public SupplierDao getSupplierDao() {
		return supplierDao;
	}

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
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

	public SalesDao getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	public SalesItemDao getSalesItemDao() {
		return salesItemDao;
	}

	public void setSalesItemDao(SalesItemDao salesItemDao) {
		this.salesItemDao = salesItemDao;
	}

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}
}
