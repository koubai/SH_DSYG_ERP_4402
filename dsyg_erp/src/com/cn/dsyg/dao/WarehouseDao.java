package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.dto.WarehouseProductDto;

/**
 * @name WarehouseDao.java
 * @author Frank
 * @time 2015-6-3下午9:44:07
 * @version 1.0
 */
public interface WarehouseDao {
	
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseProductDto> queryWarehouseProductByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, int start, int end);
	
	/**
	 * 查询库存产品记录数
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
	public int queryWarehouseProductCountByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename);
	
	//汇总 start
	/**
	 * 翻页查询库存汇总数据
	 * @param warehouseType
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseOkDto> queryWarehouseOkByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end);
	
	/**
	 * 查询库存汇总总记录数
	 * @param warehouseType
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @return
	 */
	public int queryWarehouseOkCountByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status);
	
	/**
	 * 根据产品ID和供应商ID查询数据
	 * @param warehouseType
	 * @param status
	 * @param productid
	 * @param supplierid
	 * @param warehousename
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseBySupplieridProductid(String warehouseType,
				String status, String productid, String supplierid, String warehousename);
	//汇总 end
	
	/**
	 * 根据ID列表查询库存记录
	 * @param ids
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByIds(String ids);
	
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid, String productid,
			String status, String warehousename, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param supplierid
	 * @param productid
	 * @param status
	 * @param warehousename
	 * @return
	 */
	public int queryWarehouseCountByPage(String parentid, String warehousetype, String warehouseno,
			String theme1, String supplierid, String productid, String status, String warehousename);
	
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
