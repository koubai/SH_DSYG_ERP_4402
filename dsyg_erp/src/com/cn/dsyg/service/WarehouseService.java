package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.WarehouseDto;

/**
 * @name WarehouseService.java
 * @author Frank
 * @time 2015-6-7下午9:04:59
 * @version 1.0
 */
public interface WarehouseService {
	
	/**
	 * 库存确认
	 * @param productid
	 * @param supplierid
	 * @param userid
	 */
	public void warehouseInOk(String productid, String supplierid, String userid);
	
	/**
	 * 入库确认
	 * @param productid
	 * @param supplierid
	 * @param userid
	 */
	public void warehouseOk(String productid, String supplierid, String userid);
	
	/**
	 * 预入库确认数据
	 * @param status
	 * @param page
	 * @return
	 */
	public Page queryWarehouseOkByPage(String status, Page page);

	/**
	 * 翻页查询
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param supplierid
	 * @param productid
	 * @param status
	 * @param page
	 * @return
	 */
	public Page queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid, String productid,
			String status, Page page);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public WarehouseDto queryWarehouseByID(String id);
	
	/**
	 * 根据父单号和产品ID查询数据
	 * @param parentid
	 * @param productid
	 * @return
	 */
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid, String productid);
	
	/**
	 * 根据入库单号查询数据
	 * @param warehouseno
	 * @return
	 */
	public WarehouseDto queryWarehouseByWarehouseno(String warehouseno);
	
	/**
	 * 新增数据
	 * @param warehouse
	 */
	public void insertWarehouse(WarehouseDto warehouse);
	
	/**
	 * 修改数据
	 * @param warehouse
	 */
	public void updateWarehouse(WarehouseDto warehouse);
}
