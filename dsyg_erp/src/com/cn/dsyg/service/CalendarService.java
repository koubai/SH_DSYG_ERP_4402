package com.cn.dsyg.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dao.CalendarDao;
import com.cn.dsyg.dto.CalendarDto;

public interface CalendarService {

	public ApplicationContext getCtx();

	public void setCtx(ApplicationContext ctx);

	public CalendarDao getCalendarDao();

	public void setCalendarDao(CalendarDao calendarDao);

	public boolean  modify(CalendarDto calendar);

	public boolean add(CalendarDto calendar) ;

	public boolean del(Integer id);	
	
	public List<CalendarDto> find();
	
	public CalendarDto findById(Integer id);
	
}
