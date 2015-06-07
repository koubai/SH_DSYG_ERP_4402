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

	@Override
	public List<ChartDto> querySalesByDate(String theme1, String from_date, String to_date) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> list = chartDao.querySalesByDate(theme1, from_date, to_date);
	        System.out.println("querySalesByDate theme1:" + theme1);
	        System.out.println("querySalesByDate from_date:" + from_date);
	        System.out.println("querySalesByDate to_date:" + to_date);
	        System.out.println("querySalesByDate list:" + list.size());
			return list;		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ChartDto> queryWareHouseRptByDate(String theme1, String from_date, String to_date) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> list = chartDao.queryWareHouseRptByDate(theme1, from_date, to_date);
	        System.out.println("queryWareHouseRptByDate theme1:" + theme1);
	        System.out.println("queryWareHouseRptByDate from_date:" + from_date);
	        System.out.println("queryWareHouseRptByDate to_date:" + to_date);
	        System.out.println("queryWareHouseRptByDate list:" + list.size());
			return list;		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ChartDto> queryFinanceByDate(String theme1, String from_date, String to_date) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> list = chartDao.queryFinanceByDate(theme1, from_date, to_date);
	        System.out.println("queryFinanceByDate theme1:" + theme1);
	        System.out.println("queryFinanceByDate from_date:" + from_date);
	        System.out.println("queryFinanceByDate to_date:" + to_date);
	        System.out.println("queryFinanceByDate list:" + list.size());
			return list;		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
