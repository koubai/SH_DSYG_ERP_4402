package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

public class ChartSaleTotalDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6305755070526327403L;
	
	private String totaltaxamount;
	private String expresstaxamount;
	private String businesstripamount;

	public String getTotaltaxamount() {
		return totaltaxamount;
	}
	public void setTotaltaxamount(String totaltaxamount) {
		this.totaltaxamount = totaltaxamount;
	}
	public String getExpresstaxamount() {
		return expresstaxamount;
	}
	public void setExpresstaxamount(String expresstaxamount) {
		this.expresstaxamount = expresstaxamount;
	}
	public String getBusinesstripamount() {
		return businesstripamount;
	}
	public void setBusinesstripamount(String businesstripamount) {
		this.businesstripamount = businesstripamount;
	}

}
