package com.cn.dsyg.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.PurchaseDao;
import com.cn.dsyg.dao.PurchaseItemDao;
import com.cn.dsyg.dao.SalesDao;
import com.cn.dsyg.dao.SalesItemDao;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dao.WarehouserptDao;
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
	public void warehouseInOk(String ids, String userid) {
		if(StringUtil.isNotBlank(ids)) {
			String[] idList = ids.split(",");
			
			//供应商ID
			String supplierid = "";
			//产品信息
			String productinfo = "";
			//入库单号集合
			String warehousenos = "";
			int count = 0;
			WarehouseDto warehouse = null;
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						supplierid = "" + warehouse.getSupplierid();
						productinfo += warehouse.getProductid() + "," + warehouse.getQuantity() + "," + warehouse.getTaxamount() + "#";
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
								//需要更新采购单状态=付款申请
								PurchaseDto purchaseDto = purchaseDao.queryPurchaseByNo(warehouse.getParentid());
								purchaseDto.setStatus(Constants.PURCHASE_STATUS_PAY_APPLY);
								purchaseDto.setUpdateuid(userid);
								purchaseDao.updatePurchase(purchaseDto);
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=入库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_IN);
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//入库单号
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String warehouseno = "warehouserpt" + belongto + sdf.format(date);
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			//warehouserpt.setWarehousename(list.get(0).getWarehousename());
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
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
	public void warehouseOutOk(String ids, String userid) {
		if(StringUtil.isNotBlank(ids)) {
			String[] idList = ids.split(",");
			
			//客户ID
			String customerid = "";
			//产品信息
			String productinfo = "";
			//入库单号集合
			String warehousenos = "";
			//int count = 0;
			WarehouseDto warehouse = null;
			//含税金额合计
			BigDecimal totaltaxamount = new BigDecimal(0);
			for(int i = 0; i < idList.length; i++) {
				String id = idList[i];
				if(StringUtil.isNotBlank(id)) {
					//根据ID查询库存记录
					warehouse = warehouseDao.queryWarehouseByID(id);
					if(warehouse != null) {
						customerid = "" + warehouse.getSupplierid();
						//发货单数量是负数，所以需要变成正的
						if(warehouse.getQuantity() < 0) {
							productinfo += warehouse.getProductid() + "," + (warehouse.getQuantity() * -1) + "," + warehouse.getTaxamount() + "#";
						} else {
							productinfo += warehouse.getProductid() + "," + warehouse.getQuantity() + "," + warehouse.getTaxamount() + "#";
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
							}
						}
					}
				}
			}
			
			WarehouserptDto warehouserpt = new WarehouserptDto();
			//数据来源类型=出库单
			warehouserpt.setWarehousetype(Constants.WAREHOUSERPT_TYPE_OUT);
			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//入库单号
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String warehouseno = "warehouserpt" + belongto + sdf.format(date);
			warehouserpt.setWarehouseno(warehouseno);
			//仓库名
			//warehouserpt.setWarehousename(list.get(0).getWarehousename());
			//主题
			//warehouserpt.setTheme1(list.get(0).getTheme1());
			//产品信息
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
}
