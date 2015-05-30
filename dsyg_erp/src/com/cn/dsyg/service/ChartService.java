package com.cn.dsyg.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dto.ChartDto;

public interface ChartService {

	public ApplicationContext getCtx();

	public void setCtx(ApplicationContext ctx);

	public ChartDao getChartDao();

	public void setChartDao(ChartDao chartDao);

	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date);

}
