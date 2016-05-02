package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.UndeliProductDao;
import com.cn.dsyg.dto.UndeliProductDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class UndeliProductDaoImpl extends BaseDao implements UndeliProductDao {


	@Override
	public List<UndeliProductDto> queryUnDeliSaleProductByFieldno(String fromDate, String toDate, String fieldno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("from_date", fromDate);
		paramMap.put("to_date", toDate);
		paramMap.put("fieldno", fieldno);
		@SuppressWarnings("unchecked")
		List<UndeliProductDto> list = getSqlMapClientTemplate().queryForList("queryUnDeliSaleProductByFieldno", paramMap);
		System.out.println("queryUnDeliSaleProductByFieldno size:" + list.size());
		System.out.println("paramMap" + paramMap);
		return list;
	}

	@Override
	public List<UndeliProductDto> queryUnDeliPurchaseProductByFieldno(String fromDate, String toDate, String fieldno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("from_date", fromDate);
		paramMap.put("to_date", toDate);
		paramMap.put("fieldno", fieldno);
		@SuppressWarnings("unchecked")
		List<UndeliProductDto> list = getSqlMapClientTemplate().queryForList("queryUnDeliPurchaseProductByFieldno", paramMap);
		System.out.println("queryUnDeliPurchaseProductByFieldno size:" + list.size());
		System.out.println("paramMap" + paramMap);
		return list;
	}
}
