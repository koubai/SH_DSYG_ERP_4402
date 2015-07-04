package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.WarehouseDto;

/**
 * @name WarehouseService.java
 * @author Frank
 * @time 2015-6-7下午9:04:59
 * @version 1.0
 */
public interface WarehouseService {
	
	//库存产品
	/**
	 * 翻页查询库存产品
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param productid
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param page
	 * @return
	 */
	public Page queryWarehouseProductByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, Page page);
	
	/**
	 * 预入库确认
	 * @param ids
	 * @param userid
	 */
	public void warehouseInOk(String ids, String userid);
	
	/**
	 * 预出库确认
	 * @param ids
	 * @param userid
	 */
	public void warehouseOutOk(String ids, String userid);
	
	/**
	 * 库存汇总数据
	 * @param warehouseType
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @param page
	 * @return
	 */
	public Page queryWarehouseOkByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page);

	/**
	 * 翻页查询
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param supplierid
	 * @param productid
	 * @param status
	 * @param warehousename
	 * @param page
	 * @return
	 */
	public Page queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid, String productid,
			String status, String warehousename, Page page);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public WarehouseDto queryWarehouseByID(String id);
	
	/**
	 * 根据ID列表查询库存记录
	 * @param ids
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByIds(String ids);
	
	/**
	 * 根据父单号和产品ID查询数据
	 * @param parentid
	 * @param productid
	 * @return
	 */
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid, String productid);
	
	/**
	 * 根据父单号查询数据
	 * @param parentid
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByParentid(String parentid);
	
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
