package com.cn.dsyg.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.CalendarDao;
import com.cn.dsyg.dto.CalendarDto;

public class CalendarDaoImpl extends BaseDao implements CalendarDao {
	
	//添加日历事件
	public boolean add(CalendarDto calendar) {
		boolean flag = false;
		try {
	        System.out.println("CalendarDaoImpl add" );

			getSqlMapClientTemplate().insert("insertCalendar01", calendar);
			flag = true;
		} catch (Exception e) {
	        System.out.println(e);
		} finally {
		}
		return flag;
	}
	
	//根据编号删除日历事件
	public boolean del(Integer id) {
		boolean flag = false;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			getSqlMapClientTemplate().delete("deleteCalendar01", paramMap);
			flag = true;
		} catch (Exception e) {
	        System.out.println(e);
		} finally {
		}
		return flag;
	}
	
	//修改
	public boolean modify(CalendarDto calendar) {
		boolean flag = false;
		try {
	        System.out.println("updateCalendar01");
			getSqlMapClientTemplate().insert("updateCalendar01", calendar);
			flag = true;
		} catch (Exception e) {
	        System.out.println(e);
		} finally {
		}		
		return flag;
	}
		
	//查询所有日历事件
	@SuppressWarnings("unchecked")
	public List<CalendarDto> find() {
		List<CalendarDto> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("queryCalendar01", null);
		} catch (Exception e) {
	        System.out.println(e);
		} finally {
		}		
		return list;
	}
	
	//根据编号查询单个日历事件
	@SuppressWarnings("unchecked")
	public CalendarDto findById(Integer id) {
		List<CalendarDto> list = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			list = getSqlMapClientTemplate().queryForList("queryCalendar02", paramMap);
		} catch (Exception e) {
	        System.out.println(e);
		} finally {
		}		
		return list.get(0);
	}

	
}
