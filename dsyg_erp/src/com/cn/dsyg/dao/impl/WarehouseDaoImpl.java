package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.WarehouseDao;
import com.cn.dsyg.dto.ProductQuantityDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.dto.WarehouseDetailDto;
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.dto.WarehouseInOutOkDto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.dto.WarehouseProductDto;

/**
 * @name WarehouseDaoImpl.java
 * @author Frank
 * @time 2015-6-7下午8:42:12
 * @version 1.0
 */
public class WarehouseDaoImpl extends BaseDao implements WarehouseDao {
	
	@Override
	public WarehouseDto queryCbjWarehouseByProductid(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryCbjWarehouseByProductid", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByTheme2(String warehousetype,
			String theme2) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("theme2", theme2);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByTheme2", paramMap);
		return list;
	}
	
	@Override
	public Double queryAmountByProductId(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		return (Double) getSqlMapClientTemplate().queryForObject("queryAmountByProductId", paramMap);
	}
	
	@Override
	public ProductQuantityDto queryProductQuantityById(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<ProductQuantityDto> list = getSqlMapClientTemplate().queryForList("queryProductQuantityById", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseRefundByPage(String warehousetype,
			String theme1, String warehousename, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("theme1", theme1);
		paramMap.put("warehousename", warehousename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseRefundByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseRefundCountByPage(String warehousetype,
			String theme1, String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("theme1", theme1);
		paramMap.put("warehousename", warehousename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseRefundCountByPage", paramMap);
	}
	
	@Override
	public List<WarehouseProductDto> queryWarehouseProductByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseProductDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseProductByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseProductCountByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String productid, String tradename,
			String typeno, String color, String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseProductCountByPage", paramMap);
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseBySupplieridProductid(String warehouseType, String status,
			String productid, String supplierid, String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("status", status);
		paramMap.put("productid", productid);
		paramMap.put("supplierid", supplierid);
		paramMap.put("warehousename", warehousename);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseBySupplieridProductid", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouseInOutOkDto> queryWarehouseInOkByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("suppliername", suppliername);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseInOutOkDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseInOkByPage", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouseInOutOkDto> queryWarehouseOutOkByPage(String warehouseType, String suppliername, String theme, String tradename,
			String typeno, String color, String warehousename, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("suppliername", suppliername);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseInOutOkDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseOutOkByPage", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouseOkDto> queryWarehouseOkByPage(String warehouseType,
			String theme, String tradename, String typeno, String color,
			String warehousename, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseOkDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseOkByPage", paramMap);
		return list;
	}
	
	@Override
	public int queryWarehouseInOutOkCountByPage(String warehouseType, String suppliername, String theme,
			String tradename, String typeno, String color,
			String warehousename, String status) {
		System.out.println("suppliername: " + suppliername);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("suppliername", suppliername);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseInOutOkCountByPage", paramMap);
	}
	
	@Override
	public int queryWarehouseOutOkCountByPage(String warehouseType, String suppliername, String theme,
			String tradename, String typeno, String color,
			String warehousename, String status) {
		System.out.println("suppliername: " + suppliername);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("suppliername", suppliername);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseOutOkCountByPage", paramMap);
	}

	@Override
	public int queryWarehouseOkCountByPage(String warehouseType, String theme, String tradename,
			String typeno, String color, String warehousename, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehousetype", warehouseType);
		paramMap.put("theme1", theme);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseOkCountByPage", paramMap);
	}

	@Override
	public List<WarehouseDto> queryWarehouseByPage(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String supplierid, String productid, String status, String warehousename, int start,
			int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("warehousename", warehousename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseCountByPage(String parentid, String warehousetype,
			String warehouseno, String theme1, String supplierid,
			String productid, String status, String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("supplierid", supplierid);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		paramMap.put("warehousename", warehousename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseCountByPage", paramMap);
	}

	@Override
	public WarehouseDto queryWarehouseByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public WarehouseDto queryWarehouseByParentidAndProductid(String parentid,
			String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByParentidAndProductid", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByIds(String ids) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", ids);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByIds", paramMap);
		return list;
	}
	
	@Override
	public List<WarehouseDto> queryWarehouseByParentid(String parentid, String res05) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("res05", res05);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByParentid", paramMap);
		return list;
	}

	@Override
	public WarehouseDto queryWarehouseByWarehouseno(String warehouseno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("warehouseno", warehouseno);
		@SuppressWarnings("unchecked")
		List<WarehouseDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseByWarehouseno", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertWarehouse(WarehouseDto warehouse) {
		getSqlMapClientTemplate().insert("insertWarehouse", warehouse);
	}

	@Override
	public void updateWarehouse(WarehouseDto warehouse) {
		getSqlMapClientTemplate().update("updateWarehouse", warehouse);
	}

	@Override
	public List<WarehouseCheckDto> queryWarehouseCheckByPage(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseCheckDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseCheckByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseCheckCountByPage(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseCheckCountByPage", paramMap);
	}

	@Override
	public List<WarehouseDetailDto> queryWarehouseDetailByPage(String parentid,
			String keyword, String warehousetype, String warehouseno,
			String theme1, String productid, String tradename, String typeno,
			String color, String warehousename, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("keyword", keyword);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<WarehouseDetailDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseDetailByPage", paramMap);
		return list;
	}

	@Override
	public int queryWarehouseDetailCountByPage(String parentid, String keyword,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("keyword", keyword);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryWarehouseDetailCountByPage", paramMap);
	}

	@Override
	public List<WarehouseCheckDto> queryWarehouseCheckToExcel(String parentid,
			String warehousetype, String warehouseno, String theme1,
			String productid, String tradename, String typeno, String color,
			String warehousename) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("warehousetype", warehousetype);
		paramMap.put("warehouseno", warehouseno);
		paramMap.put("theme1", theme1);
		paramMap.put("productid", productid);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("warehousename", warehousename);
		@SuppressWarnings("unchecked")
		List<WarehouseCheckDto> list = getSqlMapClientTemplate().queryForList("queryWarehouseCheckToExcel", paramMap);
		return list;
	}

	@Override
	public void deleteWarehouseByParentid(String parentid, String productid, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("productid", productid);
		paramMap.put("status", status);
		getSqlMapClientTemplate().update("deleteWarehouseByParentid", paramMap);
	}
	
	@Override
	public List<WarehouseOkDto> queryProductBookByProductid(String productid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productid", productid);
		@SuppressWarnings("unchecked")
		List<WarehouseOkDto> list = getSqlMapClientTemplate().queryForList("queryProductBookByProductid", paramMap);
		return list;
	}
}
