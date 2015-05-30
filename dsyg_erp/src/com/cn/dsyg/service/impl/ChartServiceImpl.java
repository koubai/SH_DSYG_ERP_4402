package com.cn.dsyg.service.impl;

import java.util.List;

import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dao.impl.ChartDaoImpl;
import com.cn.dsyg.service.ChartService;

public class ChartServiceImpl implements ChartService{

	private ChartDao chartDao;
	private ApplicationContext ctx;


	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public ChartDao getChartDao() {
		return chartDao;
	}

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	@Override
	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> list = chartDao.queryPurchaseByDate(theme1, from_date, to_date);
	        System.out.println("queryPurchaseByDate theme1:" + theme1);
	        System.out.println("queryPurchaseByDate from_date:" + from_date);
	        System.out.println("queryPurchaseByDate to_date:" + to_date);
	        System.out.println("queryPurchaseByDate list:" + list.size());
			return list;		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
