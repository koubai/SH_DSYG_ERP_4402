package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.WarehouseDto;

/**
 * @name WarehouseDao.java
 * @author Frank
 * @time 2015-6-3下午9:44:07
 * @version 1.0
 */
public interface WarehouseDao {
	
	//汇总 start
	/**
	 * 翻页查询入库汇总数据
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseOkByPage(String status, int start, int end);
	
	/**
	 * 查询入库汇总总记录数
	 * @param status
	 * @return
	 */
	public int queryWarehouseOkCountByPage(String status);
	
	/**
	 * 根据产品ID和供应商ID查询数据
	 * @param status
	 * @param productid
	 * @param supplierid
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseBySupplieridProductid(String status, String productid, String supplierid);
	//汇总 end
	
	/**
	 * 翻页查询
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param supplierid
	 * @param productid
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid, String productid,
			String status, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param parentid
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param supplierid
	 * @param productid
	 * @param status
	 * @return
	 */
	public int queryWarehouseCountByPage(String parentid, String warehousetype, String warehouseno,
			String theme1, String supplierid, String productid, String status);
	
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
