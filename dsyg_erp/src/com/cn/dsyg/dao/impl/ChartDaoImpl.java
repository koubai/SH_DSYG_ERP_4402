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
	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		
		@SuppressWarnings("unchecked")
		List<ChartDto> list = getSqlMapClientTemplate().queryForList("queryPurchaseByDate", paramMap);
		return list;
	}


}
