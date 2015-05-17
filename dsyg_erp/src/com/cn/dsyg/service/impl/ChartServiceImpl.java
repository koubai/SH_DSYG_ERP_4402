package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.service.ChartService;

public class ChartServiceImpl implements ChartService{

	private ChartDao chartDao;

	@Override
	public List<ChartDto> queryPurchaseByDate(String from_date, String to_date) {
		List<ChartDto> list = chartDao.queryPurchaseByDate(from_date, to_date);
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				ChartDto chartDto = list.get(i);
			}
		}
		return list;
	}

}
