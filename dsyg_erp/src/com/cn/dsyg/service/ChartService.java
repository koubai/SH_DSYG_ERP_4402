package com.cn.dsyg.service;

import java.util.List;

import com.cn.dsyg.dto.ChartDto;

public interface ChartService {

	public List<ChartDto> queryPurchaseByDate(String from_date, String to_date);

}
