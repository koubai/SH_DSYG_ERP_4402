package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbDeliveryDao;
import com.cn.dsyg.dto.EtbDeliveryDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbDeliveryDaoImpl extends BaseDao implements EtbDeliveryDao {

	@Override
	public EtbDeliveryDto queryAllEtbDeliveryByID(String deliveryNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", deliveryNo);
		@SuppressWarnings("unchecked")
		List<EtbDeliveryDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDeliveryByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbDeliveryDto> queryEtbDeliveryByPage(String deliveryNoLow,
			String deliveryNoHigh, String deliveryName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若快递名称存在，则忽略快递代码按快递名称来查询。
		if(StringUtil.isNotBlank(deliveryName)) {
			paramMap.put("DELIVERY_NAME", StringUtil.replaceDatabaseKeyword_mysql(deliveryName));
		} else {
			paramMap.put("ID_LOW", deliveryNoLow);
			paramMap.put("ID_HIGH", deliveryNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<EtbDeliveryDto> list = getSqlMapClientTemplate().queryForList("queryEtbDeliveryByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbDeliveryCountByPage(String deliveryNoLow,
			String deliveryNoHigh, String deliveryName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若快递名称存在，则忽略快递代码按快递名称来查询。
		if(StringUtil.isNotBlank(deliveryName)) {
			paramMap.put("DELIVERY_NAME", StringUtil.replaceDatabaseKeyword_mysql(deliveryName));
		} else {
			paramMap.put("ID_LOW", deliveryNoLow);
			paramMap.put("ID_HIGH", deliveryNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbDeliveryCountByPage", paramMap);
	}

	@Override
	public EtbDeliveryDto queryEtbDeliveryByID(String deliveryNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", deliveryNo);
		@SuppressWarnings("unchecked")
		List<EtbDeliveryDto> list = getSqlMapClientTemplate().queryForList("queryEtbDeliveryByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbDeliveryDto> queryAllEtbDelivery() {
		@SuppressWarnings("unchecked")
		List<EtbDeliveryDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDelivery");
		return list;
	}

	@Override
	public void insertEtbDelivery(EtbDeliveryDto delivery) {
		getSqlMapClientTemplate().insert("insertEtbDelivery", delivery);
	}

	@Override
	public void updateEtbDelivery(EtbDeliveryDto delivery) {
		getSqlMapClientTemplate().update("updateEtbDelivery", delivery);
	}

	@Override
	public List<EtbDeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow,
			String deliveryNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", deliveryNoLow);
		paramMap.put("ID_HIGH", deliveryNoHigh);
		@SuppressWarnings("unchecked")
		List<EtbDeliveryDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDeliveryExport", paramMap);
		return list;
	}
}
