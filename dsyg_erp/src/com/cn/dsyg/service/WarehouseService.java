package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.ProductQuantityDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseOkDto;

/**
 * @name WarehouseService.java
 * @author Frank
 * @time 2015-6-7下午9:04:59
 * @version 1.0
 */
public interface WarehouseService {
	
	/**
	 * 产品数量验证
	 * @param productInfo
	 * @return
	 */
	public String checkProductAmount(String productInfo);
	
	/**
	 * 根据产品ID盘点库存数量
	 * @param productid
	 * @param num
	 * @param position
	 * @param userid
	 * @return
	 */
	public boolean checkProductQuantity(String productid, String num, String position, String userid);
	
	/**
	 * 根据产品ID查询库存数量
	 * @param productid
	 * @return
	 */
	public ProductQuantityDto queryProductQuantityById(String productid);
	
	/**
	 * 翻页查询退换货记录
	 * @param warehousetype
	 * @param theme1
	 * @param warehousename
	 * @param page
	 * @return
	 */
	public Page queryWarehouseRefundByPage(String warehousetype,
			String theme1, String warehousename, Page page);
	
	/**
	 * 库存盘点
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
	public Page queryWarehouseCheckByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, Page page);
	
	/**
	 * 库存盘点
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param productid
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @return
	 */
	public List<WarehouseCheckDto> queryWarehouseCheckToExcel(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename);
	
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
	 * @throws Exception
	 */
	public void warehouseInOk(String ids, String userid) throws RuntimeException;
	
	/**
	 * 预出库确认
	 * @param ids
	 * @param userid
	 * @throws Exception
	 */
	public void warehouseOutOk(String ids, String userid) throws RuntimeException;
	
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
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @return
	 */
	public Page queryWarehouseInOkByPage(String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, Page page);
	
	/**
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @return
	 */
	public Page queryWarehouseOutOkByPage(String suppliername, String theme, String tradename,
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
	 * 产品对比
	 * @param parentid
	 * @param keyword
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
	public Page queryWarehouseDetailByPage(String parentid, String keyword, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, Page page);
	
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
	 * 新增退换货数据
	 * @param warehouse
	 * @return
	 */
	public String insertRefundWarehouse(WarehouseDto warehouse);
	
	/**
	 * 修改数据
	 * @param warehouse
	 */
	public void updateWarehouse(WarehouseDto warehouse);
	
	/**
	 * 根据产品id查询流水
	 * @param productid
	 * @return
	 */
	public List<WarehouseOkDto> queryProductBookByProductid(String productid);
}
