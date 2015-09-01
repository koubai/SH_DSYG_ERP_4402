package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dto.ProductDto;

/**
 * ProductDaoImpl
 * @Company 盛大游戏
 * @author chenguangquan.frank
 * @version 1.0
 * @create 2015-5-19下午1:47:37
 */
public class ProductDaoImpl extends BaseDao implements ProductDao {
	
	@Override
	public ProductDto queryProductByLogicId(String tradename, String typeno,
			String color) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		@SuppressWarnings("unchecked")
		List<ProductDto> list = getSqlMapClientTemplate().queryForList("queryProductByLogicId", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ProductDto> queryProductByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("item01", item01);
		paramMap.put("keyword", keyword);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("supplierid", supplierId);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<ProductDto> list = getSqlMapClientTemplate().queryForList("queryProductByPage", paramMap);
		return list;
	}

	@Override
	public List<ProductDto> queryProductCostCheckByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String belongto, String status, int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("item01", item01);
		paramMap.put("keyword", keyword);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("supplierid", supplierId);
		paramMap.put("belongto", belongto);
		paramMap.put("status", status);
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<ProductDto> list = getSqlMapClientTemplate().queryForList("queryProductCostCheckByPage", paramMap);
		return list;
	}
	
	
	@Override
	public int queryProductCountByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("item01", item01);
		paramMap.put("keyword", keyword);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("supplierid", supplierId);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryProductCountByPage", paramMap);
	}

	@Override
	public int queryProductCostCountByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String belongto, String status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fieldno", fieldno);
		paramMap.put("item01", item01);
		paramMap.put("keyword", keyword);
		paramMap.put("tradename", tradename);
		paramMap.put("typeno", typeno);
		paramMap.put("color", color);
		paramMap.put("supplierid", supplierId);
		paramMap.put("belongto", belongto);
		paramMap.put("status", status);
		return (Integer) getSqlMapClientTemplate().queryForObject("queryProductCostCountByPage", paramMap);
	}	
	
	
	@Override
	public ProductDto queryProductByID(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<ProductDto> list = getSqlMapClientTemplate().queryForList("queryProductByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void insertProduct(ProductDto product) {
		getSqlMapClientTemplate().insert("insertProduct", product);
	}

	@Override
	public void updateProduct(ProductDto product) {
		getSqlMapClientTemplate().update("updateProduct", product);
	}
}
