package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dto.ChartDto;

public class ChartDaoImpl extends BaseDao implements ChartDao {
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @return
	 */
	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);
		
		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateY", paramMap);			
		}
		return list;
	}

	public List<ChartDto> querySalesByDate(String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateY", paramMap);
		}
		return list;
	}

	public List<ChartDto> queryWareHouseRptByDate(String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		@SuppressWarnings("unchecked")
		List<ChartDto> list = getSqlMapClientTemplate().queryForList("queryDeliveryByDate", paramMap);
		return list;
	}

	public List<ChartDto> queryFinanceByDate(String theme1, String from_date, String to_date, String dur_type, String handerList){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		@SuppressWarnings("unchecked")
		List<ChartDto> list = getSqlMapClientTemplate().queryForList("queryFinanceByDate", paramMap);
		return list;
	}
}
