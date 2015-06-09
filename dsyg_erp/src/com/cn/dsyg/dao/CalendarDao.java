package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.CalendarDto;

public interface CalendarDao {
	
	//添加日历事件
	public boolean add(CalendarDto calendar);
	
	//根据编号删除日历事件
	public boolean del(Integer id);
	
	//修改
	public boolean modify(CalendarDto calendar);
		
	//查询所有日历事件
	public List<CalendarDto> find();
	
	//根据编号查询单个日历事件
	public CalendarDto findById(Integer id);
	
}
