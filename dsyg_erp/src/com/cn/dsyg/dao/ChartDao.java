package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ChartDto;

public interface ChartDao {
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @return
	 */
	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date);

}
