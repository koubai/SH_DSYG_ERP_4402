package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

public class CalendarDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778899572192068068L;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id2) {
		this.id = id2;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getAllDay() {
		return allDay;
	}
	public void setAllDay(Integer allDay) {
		this.allDay = allDay;
	}
	private Integer id;
	private String title;
	private String start;
	private String end;
	private Integer allDay;
	private String color;
	private Integer userid;
	
}
