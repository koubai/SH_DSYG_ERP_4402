package com.cn.dsyg.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dao.CalendarDao;
import com.cn.dsyg.dto.CalendarDto;
import com.cn.dsyg.service.CalendarService;

public class CalendarServiceImpl implements CalendarService{

	private CalendarDao calendarDao;

	
	public CalendarDao getCalendarDao() {
		return calendarDao;
	}

	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}
	private ApplicationContext ctx;


	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	
	public boolean modify(CalendarDto calendar) {
		return calendarDao.modify(calendar);
	}

	public boolean add(CalendarDto calendar) {
		return calendarDao.add(calendar);
	}

	
	//根据编号删除日历事件
	public boolean del(Integer id) {
		return calendarDao.del(id);
	}
	
	
	//查询所有日历事件
	public List<CalendarDto> find() {
		List<CalendarDto> list = calendarDao.find();
		return list;
	}
	
	//根据编号查询单个日历事件
	public CalendarDto findById(Integer id) {
		return calendarDao.findById(id);
	}
		
}
