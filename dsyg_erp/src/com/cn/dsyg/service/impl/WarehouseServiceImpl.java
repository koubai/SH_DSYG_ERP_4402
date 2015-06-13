package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.service.WarehouseService;

/**
 * @name WarehouseServiceImpl.java
 * @author Frank
 * @time 2015-6-7下午9:05:51
 * @version 1.0
 */
public class WarehouseServiceImpl implements WarehouseService {
	
	private WarehouseDao warehouseDao;
	
	@Override
	public void warehouseOk(String productid, String supplierid, String userid) {
		List<WarehouseDto> list = warehouseDao.queryWarehouseBySupplieridProductid("" + Constants.WAREHOUSE_STATUS_NEW, productid, supplierid);
		if(list != null && list.size() > 0) {
			//计算此次入库数量=预入库数量-已入库数量
			for(WarehouseDto warehouse : list) {
				warehouse.setUpdateuid(userid);
				warehouse.setApproverid(userid);
				warehouse.setStatus(Constants.WAREHOUSE_STATUS_OK);
				warehouseDao.updateWarehouse(warehouse);
			}
//			WarehouserptDto warehouserpt = new WarehouserptDto();
//			//库存类型=入库单
//			warehouserpt.setWarehousetype(Constants.WAREHOUSE_TYPE_IN);
//			String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
//			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
//			//入库单号
//			Date date = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
//			String warehouseno = "warehouse" + belongto + sdf.format(date);
//			warehouserpt.setWarehouseno(warehouseno);
//			//主题
//			warehouserpt.setTheme1(list.get(0).getTheme1());
//			//入库单日期
//			warehouserpt.setWarehousedate(new Date());
//			//入库数量
//			warehouserpt.setTotalnum(count);
//			//收货人
//			warehouserpt.setHandler("");
//			
//			//查询采购单的供应商信息
//			PurchaseDto purchaseDto = purchaseDao.queryPurchaseByNo(purchaseno);
//			//获得采购单的供应商
//			warehouserpt.setSupplierid(supplierid);
//			if(purchaseDto != null) {
//				warehouserpt.setSuppliername(purchaseDto.getSuppliername());
//				warehouserpt.setSupplieraddress(purchaseDto.getSuppliermanageraddr());
//				warehouserpt.setSuppliermail(purchaseDto.getSuppliermail());
//				warehouserpt.setSuppliermanager(purchaseDto.getSuppliermanager());
//				warehouserpt.setSuppliertel(purchaseDto.getSuppliertel());
//			}
//			//快递公司ID==============================预留
//			
//			warehouserpt.setRank(Constants.ROLE_RANK_OPERATOR);
//			warehouserpt.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
//			//库存表
//			warehouserpt.setStatus(Constants.STATUS_NORMAL);
//			warehouserpt.setProductid(Long.valueOf(productid));
//			//采购单号
//			warehouserpt.setParentid(purchaseno);
//			warehouserpt.setCreateuid(userid);
//			warehouserpt.setUpdateuid(userid);
//			
//			warehouserptDao.insertWarehouserpt(warehouserpt);
		}
	}
	
	@Override
	public Page queryWarehouseOkByPage(String status, Page page) {
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseOkCountByPage(status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDto> list = warehouseDao.queryWarehouseOkByPage(status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public Page queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid,
			String productid, String status, Page page) {
		//查询总记录数
		int totalCount = warehouseDao.queryWarehouseCountByPage(parentid, warehousetype, warehouseno, theme1, supplierid, productid, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<WarehouseDto> list = warehouseDao.queryWarehouseByPage(parentid, warehousetype, warehouseno, theme1, supplierid, productid, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public WarehouseDto queryWarehouseByID(String id) {
		return warehouseDao.queryWarehouseByID(id);
	}

	@Override
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid,
			String productid) {
		return warehouseDao.queryWarehouseByParentidAndProductid(parentid, productid);
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
}
