package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ProductQuantityDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.dto.WarehouseDetailDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseInOutOkDto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.dto.WarehouseProductDto;

/**
 * @name WarehouseDao.java
 * @author Frank
 * @time 2015-6-3下午9:44:07
 * @version 1.0
 */
public interface WarehouseDao {
	
	/**
	 * 成本价计算
	 * @param productid
	 * @return
	 */
	public WarehouseDto queryCbjWarehouseByProductid(String productid);
	
	/**
	 * 根据产品ID，查询库存数量（状态=1，2，6的）
	 * @param productid
	 * @return
	 */
	public Double queryAmountByProductId(String productid);
	
	/**
	 * 根据产品ID查询库存数量
	 * @param productid
	 * @return
	 */
	public ProductQuantityDto queryProductQuantityById(String productid);
	
	/**
	 * 根据采购单OR销售单模糊查询库存记录
	 * @param warehousetype
	 * @param theme2
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByTheme2(String warehousetype, String theme2);
	
	/**
	 * 翻页查询退换货记录
	 * @param warehousetype
	 * @param theme1
	 * @param warehousename
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseRefundByPage(String warehousetype,
			String theme1, String warehousename, int start, int end);
	
	/**
	 * 查询退换货记录数
	 * @param warehousetype
	 * @param theme1
	 * @param warehousename
	 * @return
	 */
	public int queryWarehouseRefundCountByPage(String warehousetype,
			String theme1, String warehousename);
	
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseCheckDto> queryWarehouseCheckByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, int start, int end);
	
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
	
	/**
	 * 查询库存盘点记录数
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
	public int queryWarehouseCheckCountByPage(String parentid, String warehousetype,
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
	 * 查询入出库汇总总记录数（入出库）
	 * @param warehouseType
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @return
	 */
	public int queryWarehouseInOutOkCountByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status);

	/**
	 * 查询出库汇总总记录数（出库）
	 * @param warehouseType
	 * @param theme
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @param warehousename
	 * @param status
	 * @return
	 */
	public int queryWarehouseOutOkCountByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status);
	
	/**
	 * 翻页查询库存汇总数据（入库）
	 * @param warehouseType
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseInOutOkDto> queryWarehouseInOkByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end);
	
	/**
	 * 翻页查询库存汇总数据（出库）
	 * @param warehouseType
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseInOutOkDto> queryWarehouseOutOkByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end);
	
	/**
	 * 翻页查询库存汇总数据（ALL）
	 * @param warehouseType
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseOkDto> queryWarehouseOkByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end);
	
	/**
	 * 查询库存汇总总记录数（ALL）
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
	
	//产品对比
	/**
	 * 翻页查询产品对比
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<WarehouseDetailDto> queryWarehouseDetailByPage(String parentid, String keyword,
			String warehousetype, String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, int start, int end);
	
	/**
	 * 查询库存产品记录数
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
	 * @return
	 */
	public int queryWarehouseDetailCountByPage(String parentid, String keyword, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename);
	
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
	 * @param res05
	 * @return
	 */
	public List<WarehouseDto> queryWarehouseByParentid(String parentid, String res05);
	
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
	
	/**
	 * 根据采购单OR订单号删除库存记录
	 * @param parentid
	 * @param productid
	 * @param status
	 */
	public void deleteWarehouseByParentid(String parentid, String productid, String status);
	
	/**
	 * 根据产品ID查询流水账
	 * @param productid
	 * @return
	 */
	public List<WarehouseOkDto> queryProductBookByProductid(String productid);
}
